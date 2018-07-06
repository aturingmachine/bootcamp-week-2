package solstice.bootcamp.stocksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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

    public Company() {}

    public int getId() {
        return id;
    }

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
