package solstice.bootcamp.stocksapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import solstice.bootcamp.stocksapi.model.Company;
import solstice.bootcamp.stocksapi.model.StockData;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StockDataService {

    @Value("${stocks.remote.url}")
    private String dataSource;
    private ObjectMapper objectMapper;

    public StockDataService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public StockDataService() {}

    public List<StockData> mapJSON() throws IOException {
        URL url = new URL(dataSource);
        URLConnection conn = url.openConnection();

        ObjectMapper mapper = new ObjectMapper(); //New up a Jackson Object Mapper
        List<StockData> data = mapper.readValue(conn.getInputStream(), new TypeReference<List<StockData>>() {
        });

        return data;
    }

    public HashSet<Company> getCompanies() throws IOException {
        URL url = new URL(dataSource);
        URLConnection conn = url.openConnection();

        ObjectMapper mapper = new ObjectMapper();
        HashSet<Company> data = mapper.readValue(conn.getInputStream(), new TypeReference<HashSet<Company>>(){});

        return data;
    }
}
