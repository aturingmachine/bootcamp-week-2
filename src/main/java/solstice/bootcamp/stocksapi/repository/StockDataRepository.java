package solstice.bootcamp.stocksapi.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.stocksapi.model.AggregateData;
import solstice.bootcamp.stocksapi.model.StockData;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class StockDataRepository {

    private final JdbcTemplate template;

    private final RowMapper<StockData> stockDataRowMapper = (ResultSet rs, int row) -> new StockData(
            rs.getString("symbol"),
            rs.getFloat("price"),
            rs.getInt("volume"),
            rs.getTimestamp("date")
    );

    private final RowMapper<AggregateData> aggregateDataRowMapper = (ResultSet rs, int row) -> new AggregateData(
            rs.getFloat(1),
            rs.getFloat(2),
            rs.getInt(3),
            rs.getFloat(4)
            );

    // Query Strings to be used
    private final String INSERT = "INSERT INTO stocks (symbol, price, volume, date) values(?, ?, ?, ?)";

    private final String COMPILE_DATE= "SELECT MAX(price), MIN(price), SUM(volume), " +
            "(select price from stocks where symbol = '[SYMBOL]'" +
            " and date = (select max(date) from stocks where date like '[DATE]%')) as CLOSE_PRICE from stocks " +
            "where symbol = '[SYMBOL]' and date like '[DATE]%'";

    private final String COMPILE_MONTH = "SELECT MAX(price), MIN(price), SUM(volume)," +
            " (select price from stocks where symbol = '[SYMBOL]'" +
            " and date = (select max(date) from stocks where date like '%-[DATE]-%')) as CLOSE_PRICE from stocks " +
            "where symbol = '[SYMBOL]' and date like '%-[DATE]-%'";


    public StockDataRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<StockData> save(List<StockData> stockData) {

        stockData.forEach(datum -> template.update(INSERT,
                datum.getSymbol(),
                datum.getPrice(),
                datum.getVolume(),
                datum.getDate()
        ));

        return stockData;

    }

    public AggregateData compileDataDate(String symbol, String date) {
        String queryString = COMPILE_DATE.replace("[SYMBOL]", symbol).replace("[DATE]", date);

        return template.queryForObject(queryString, aggregateDataRowMapper);

    }

    public AggregateData compileDataMonth(String symbol, String date) {
        //If the user passed us a single character make sure the query will work
        if (date.length() != 2) {
            date = "0" + date;
        }
        String queryString = COMPILE_MONTH.replace("[SYMBOL]", symbol).replace("[DATE]", date);

        return template.queryForObject(queryString, aggregateDataRowMapper);
    }


}
