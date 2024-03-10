package OrderLib;

import java.io.Serializable;

public class Kitchen implements Serializable {
    private static final Kitchen _instance = new Kitchen();

    private Kitchen() {}

    public static Kitchen getInstance() {
        return _instance;
    }

    public void pushOrder(Order order) {
        CookThread cookThread = new CookThread(order);
        cookThread.start();
    }
}
