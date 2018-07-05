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
    public ResponseEntity load() {
        try {
            return new ResponseEntity<>(stockDataRepository.save(stockDataService.mapJSON()), HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{symbol}/{date}/{type}")
    public ResponseEntity getAggregateData(
            @PathVariable("symbol") String symbol,
            @PathVariable("date") String date,
            @PathVariable("type") String type) {

        AggregateData data;

        if (type.equals("date")) {
            data = stockDataRepository.compileDataDate(symbol, date);
        } else if (type.equals("month")) {
            if (Integer.parseInt(date) > 12 || Integer.parseInt(date) < 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Month out of scope, month must be < 13 && > 0");
            }
            data = stockDataRepository.compileDataMonth(symbol, date);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aggregation Format Unsupported, try month or date");
        }

        data.setType(type);
        data.setSymbol(symbol);
        data.setDateRequested(date);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
