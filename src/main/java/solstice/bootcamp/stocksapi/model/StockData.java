package solstice.bootcamp.stocksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
  private Company company;
  @JsonIgnore
  private String symbol;
  private float price;
  private int volume;
  private Timestamp date;

  public StockData(int id, Company company, float price, int volume, Timestamp date) {
    this.id = id;
    this.company = company;
    this.price = price;
    this.volume = volume;
    this.date = date;
  }

  public StockData(Company company, float price, int volume, Timestamp date) {
    this.company = company;
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

  public StockData() {
  }

  @JsonProperty("id")
  public int getId() {
    return id;
  }

  @JsonIgnore
  public void setId(int id) {
    this.id = id;
  }

  @JsonProperty("company")
  public Company getCompanyId() {
    return company;
  }

  @JsonIgnore
  public void setCompanyId(Company company) {
    this.company = company;
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

  @JsonIgnore
  public String getSymbol() {
    return symbol;
  }

  @JsonProperty("symbol")
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}