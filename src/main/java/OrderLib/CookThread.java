package OrderLib;

public class CookThread extends Thread {
    private final Order _order;

    public CookThread(Order order) {
        _order = order;
    }

    @Override
    public void run() {
        long k = 1000L;
        _order.updateStatus();
        try {
            while (_order.getTimeLeft() > 0) {
                //noinspection BusyWait
                sleep(k);
                _order.setTimeLeft(_order.getTimeLeft() - 1);
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        _order.updateStatus();
    }
}
