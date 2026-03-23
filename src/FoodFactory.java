public class FoodFactory {
    public Food getFood(String type) {
        if (type.equalsIgnoreCase("pizza")) {
            return new Pizza();
        } else if (type.equalsIgnoreCase("burger")) {
            return new Burger();
        }
        return null;
    }
}

