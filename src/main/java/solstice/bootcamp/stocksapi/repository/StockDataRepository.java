package solstice.bootcamp.stocksapi.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.stocksapi.model.AggregateData;
import solstice.bootcamp.stocksapi.model.StockData;

import java.sql.ResultSet;
import java.sql.Timestamp;
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
            rs.getInt(3)
    );

    // Query Strings to be used
    private final String INSERT = "INSERT INTO stocks (symbol, price, volume, date) values(?, ?, ?, ?)";

    private final String COMPILE = "SELECT MAX(price), MIN(price), SUM(volume) from stocks " +
            "where symbol = '[SYMBOL]' and date like '[DATE]%'";


    public StockDataRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<StockData> save(List<StockData> stockData) {

        stockData.forEach(datum -> {
            template.update(INSERT,
                    datum.getSymbol(),
                    datum.getPrice(),
                    datum.getVolume(),
                    datum.getDate()
            );

        });

        return stockData;

    }

    public AggregateData compileData(String symbol, String date) {
        String queryString = COMPILE.replace("[SYMBOL]", symbol).replace("[DATE]", date);

        return template.queryForObject(queryString, aggregateDataRowMapper);

    }


}
