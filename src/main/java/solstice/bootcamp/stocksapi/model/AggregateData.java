package solstice.bootcamp.stocksapi.model;


public class AggregateData {

    private float highestPrice;
    private float lowestPrice;
    private int totalVolume;
    private String type;

    public AggregateData(float highestPrice, float lowestPrice, int totalVolume) {
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.totalVolume = totalVolume;
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
}
