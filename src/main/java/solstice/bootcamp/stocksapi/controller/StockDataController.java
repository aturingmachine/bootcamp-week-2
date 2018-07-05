package solstice.bootcamp.stocksapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.stocksapi.model.AggregateData;
import solstice.bootcamp.stocksapi.model.StockData;
import solstice.bootcamp.stocksapi.repository.StockDataRepository;
import solstice.bootcamp.stocksapi.service.StockDataService;

import java.io.IOException;
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

    @GetMapping("/{symbol}/{date}/{type}")
    public ResponseEntity<AggregateData> getAggregateData(
            @PathVariable("symbol") String symbol,
            @PathVariable("date") String date,
            @PathVariable("type") String type) {

        AggregateData data;

        if (type.equals("date")) {
            data = stockDataRepository.compileDataDate(symbol, date);
        } else if (type.equals("month")) {
            if (Integer.parseInt(date) > 12 || Integer.parseInt(date) < 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            data = stockDataRepository.compileDataMonth(symbol, date);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        data.setType(type);
        data.setSymbol(symbol);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
