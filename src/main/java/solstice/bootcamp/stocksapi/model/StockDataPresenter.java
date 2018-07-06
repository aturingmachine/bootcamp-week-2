package solstice.bootcamp.stocksapi.model;

import java.sql.Timestamp;

public class StockDataPresenter {

  private int id;
  private Company company;
  private float price;
  private int volume;
  private Timestamp date;

  public StockDataPresenter(int id, Company company, float price, int volume, Timestamp date) {
    this.id = id;
    this.company = company;
    this.price = price;
    this.volume = volume;
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
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
}
