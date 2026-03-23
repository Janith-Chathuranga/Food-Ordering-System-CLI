import java.util.*;

public class OrderNotifier {
    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer o) {
        observers.add(o);
    }

    public void notifyAllObservers(String message) {
        for(Observer o : observers) {
            o.update(message);
        }
    }
}
