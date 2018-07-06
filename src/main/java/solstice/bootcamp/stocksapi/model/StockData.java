package solstice.bootcamp.stocksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;


public class StockData {

    @JsonIgnore
    @Id
    @GeneratedValue
    private int id;
    @JsonIgnore
    @ManyToOne
    private Company companyId;
    private String symbol;
    private float price;
    private int volume;
    private Timestamp date;

    public StockData(int id, Company companyId, float price, int volume, Timestamp date) {
        this.id = id;
        this.companyId = companyId;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }

    public StockData(Company companyId, float price, int volume, Timestamp date) {
        this.companyId = companyId;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }

    public StockData(String symbol, float price, int volume, Timestamp date) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }

    public StockData() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}