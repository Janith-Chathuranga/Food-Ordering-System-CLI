import java.util.*;

public class Main {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            FoodFactory factory = new FoodFactory();
            OrderManager manager = OrderManager.getInstance();
            OrderNotifier notifier = new OrderNotifier();

            Observer customer = new Customer();
            notifier.subscribe(customer);

            while (true) {
                System.out.println("\n===== FOOD ORDERING SYSTEM =====");
                System.out.println("1. Add Order");
                System.out.println("2. View Orders");
                System.out.println("3. Update Order");
                System.out.println("4. Delete Order");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid menu choice. Please enter a number.");
                    sc.nextLine();
                    continue;
                }

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1 -> {
                        String[] foodOptions = {"Pizza", "Burger"};

                        System.out.println("Select food type:");
                        for (int i = 0; i < foodOptions.length; i++) {
                            System.out.println(i + ". " + foodOptions[i]);
                        }
                        System.out.print("Enter food index: ");

                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid food index. Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        int foodIndex = sc.nextInt();
                        sc.nextLine();

                        if (foodIndex < 0 || foodIndex >= foodOptions.length) {
                            System.out.println("Invalid food index.");
                            continue;
                        }

                        String type = foodOptions[foodIndex];

                        Food food = factory.getFood(type);
                        if (food != null) {
                            food.prepare();
                            manager.addOrder(new Order(type));
                            notifier.notifyAllObservers("New Order Created");
                        } else {
                            System.out.println("Selected food type is not available.");
                        }
                    }

                    case 2 -> {
                        List<Order> orders = manager.getOrders();
                        for (int i = 0; i < orders.size(); i++) {
                            System.out.println(i + ": " +
                                    orders.get(i).getFoodName() +
                                    " - " + orders.get(i).getStatus());
                        }
                    }

                    case 3 -> {
                        System.out.print("Enter index: ");
                        int i = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new status: ");
                        String status = sc.nextLine();

                        manager.getOrders().get(i).setStatus(status);
                        notifier.notifyAllObservers("Order Updated");
                    }

                    case 4 -> {
                        System.out.print("Enter index: ");
                        int d = sc.nextInt();
                        manager.deleteOrder(d);
                    }

                    case 5 -> {
                        return;
                    }
                }
            }
        }
    }
}
