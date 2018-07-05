package solstice.bootcamp.stocksapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
            System.out.println("IO ERROR");
            e.printStackTrace();
            return null;
        }
    }

}
