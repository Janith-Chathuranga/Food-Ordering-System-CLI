
public class Order {
    private final String foodName;
    private String status;

    public Order(String foodName) {
        this.foodName = foodName;
        this.status = "Created";
    }

    public String getFoodName() { return foodName; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}
