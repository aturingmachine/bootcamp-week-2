package solstice.bootcamp.stocksapi.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.stocksapi.model.StockData;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class StockDataRepository {

    private final JdbcTemplate template;

    private final RowMapper<StockData> mapper = (ResultSet rs, int row) -> new StockData(
            rs.getString("symbol"),
            rs.getFloat("price"),
            rs.getInt("volume"),
            rs.getTimestamp("date")
    );

    // Query Strings to be used
    private final String INSERT = "INSERT INTO stocks (symbol, price, volume, date) values(?, ?, ?, ?)";


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


}
