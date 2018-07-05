package solstice.bootcamp.stocksapi.controller;

import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.stocksapi.model.AggregateData;
import solstice.bootcamp.stocksapi.model.StockData;
import solstice.bootcamp.stocksapi.repository.StockDataRepository;
import solstice.bootcamp.stocksapi.service.StockDataService;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockDataController {
    private final StockDataRepository stockDataRepository;
    private final StockDataService stockDataService;

    public StockDataController(StockDataRepository stockDataRepository, StockDataService stockDataService) {
        this.stockDataRepository = stockDataRepository;
        this.stockDataService = stockDataService;
    }

    @PostMapping("/load")
    public List<StockData> load() {
        try {
            return stockDataRepository.save(stockDataService.mapJSON());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{symbol}/{date}")
    public AggregateData getAggregateData(
            @PathVariable("symbol") String symbol, @PathVariable("date") String date) {

        AggregateData data = stockDataRepository.compileData(symbol, date);
        System.out.println(data.getHighestPrice());
        return data;
    }
}
