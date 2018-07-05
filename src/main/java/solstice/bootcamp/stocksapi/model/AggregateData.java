package solstice.bootcamp.stocksapi.model;


public class AggregateData {

    private float highestPrice;
    private float lowestPrice;
    private int totalVolume;
    private float closePrice;
    private String type;
    private String symbol;
    private String dateRequested;

    public AggregateData(float highestPrice, float lowestPrice, int totalVolume, float closePrice) {
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.totalVolume = totalVolume;
        this.closePrice = closePrice;
    }

    public AggregateData() {}

    public float getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(float highestPrice) {
        this.highestPrice = highestPrice;
    }

    public float getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(float lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(float closePrice) {
        this.closePrice = closePrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(String dateRequested) {
        this.dateRequested = dateRequested;
    }
}
