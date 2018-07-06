package solstice.bootcamp.stocksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@JsonIgnoreProperties({"price", "volume", "date"})
public class Company {

  @JsonIgnore
  @Id
  @GeneratedValue
  @OneToMany
  @JoinColumn(name = "companyId")
  private int id;
  private String symbol;

  public Company(String symbol) {
    this.symbol = symbol;
  }

  public Company(int id, String symbol) {
    this.id = id;
    this.symbol = symbol;
  }

  public Company() {
  }

  @JsonProperty("id")
  public int getId() {
    return id;
  }

  @JsonIgnore
  public void setId(int id) {
    this.id = id;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
