package solstice.bootcamp.stocksapi.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.stocksapi.model.AggregateData;
import solstice.bootcamp.stocksapi.model.Company;
import solstice.bootcamp.stocksapi.model.StockData;
import solstice.bootcamp.stocksapi.service.StockDataService;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;

@Repository
public class StockDataRepository {

  private final JdbcTemplate template;
  private StockDataService stockDataService;
  private CompanyRepository companyRepository;

  private final RowMapper<StockData> stockDataRowMapper = (ResultSet rs, int row) -> new StockData(
      rs.getInt("id"),
      companyRepository.getCompanyById(rs.getInt("companyId")),
      rs.getFloat("price"),
      rs.getInt("volume"),
      rs.getTimestamp("date")
  );

  private final RowMapper<AggregateData> aggregateDataRowMapper = (ResultSet rs, int row) -> new AggregateData(
      rs.getFloat(1),
      rs.getFloat(2),
      rs.getInt(3),
      rs.getFloat(4),
      companyRepository.getCompanyById(rs.getInt("companyId")).getSymbol()
  );

  // Query Strings to be used
  private final String INSERT = "INSERT INTO stocks (companyId, price, volume, date) values(?, ?, ?, ?)";

  private final String GET_ALL = "SELECT * FROM stocks";

  private final String COMPILE_DATE = "SELECT MAX(price), MIN(price), SUM(volume), " +
      "(select price from stocks where companyId = '[ID]'" +
      " and date = (select max(date) from stocks where date like '[DATE]%')) as CLOSE_PRICE," +
      " companyId from stocks " +
      "where companyId = '[ID]' and date like '[DATE]%'";

  public StockDataRepository(JdbcTemplate template,
      StockDataService stockDataService,
      CompanyRepository companyRepository) {

    this.template = template;
    this.stockDataService = stockDataService;
    this.companyRepository = companyRepository;
  }

  public List<StockData> save() throws IOException {

    List<StockData> stockData = stockDataService.mapJSON();
    HashSet<Company> companySet = stockDataService.getCompanies();

    companyRepository.save(companySet);


    stockData.forEach(datum -> template.update(INSERT,
        companyRepository.getCompanyBySymbol(datum.getSymbol()).getId(),
        datum.getPrice(),
        datum.getVolume(),
        datum.getDate()
    ));

    return getAll();

  }

  public List<StockData> getAll() throws DataAccessException {
    return template.query(GET_ALL, stockDataRowMapper);
  }

  public AggregateData compileDataDate(String symbol, String date)
      throws EmptyResultDataAccessException, NullPointerException {

    int companyId = companyRepository.getCompanyBySymbol(symbol).getId();

    String queryString = COMPILE_DATE.replace("[ID]",
        Integer.toString(companyId)).replace("[DATE]", date);

    return template.queryForObject(queryString, aggregateDataRowMapper);

  }
}