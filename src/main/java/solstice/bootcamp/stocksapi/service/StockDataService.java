package solstice.bootcamp.stocksapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import solstice.bootcamp.stocksapi.model.Company;
import solstice.bootcamp.stocksapi.model.StockData;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Service
public class StockDataService {

  @Value("${stocks.remote.url}")
  private String dataSource;

  public StockDataService() {
  }

  public List<StockData> mapJSON() throws IOException {
    URL url = new URL(dataSource);
    URLConnection conn = url.openConnection();

    ObjectMapper mapper = new ObjectMapper(); //New up a Jackson Object Mapper
    List<StockData> data = mapper.readValue(conn.getInputStream(), new TypeReference<List<StockData>>() {
    });

    return data;
  }

  public HashSet<Company> getCompanies() throws IOException {
    HashSet<String> symbols = new HashSet<>();
    HashSet<Company> companies = new HashSet<>();

    URL url = new URL(dataSource);
    URLConnection conn = url.openConnection();

    ObjectMapper mapper = new ObjectMapper();
    JsonNode data = mapper.readTree(conn.getInputStream());

    data.forEach(obj -> symbols.add(obj.get("symbol").asText()));

    symbols.forEach(symbol -> companies.add(new Company(symbol)));

    return companies;
  }
}
