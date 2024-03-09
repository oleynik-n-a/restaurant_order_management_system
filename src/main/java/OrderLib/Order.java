package OrderLib;

import DishLib.Dish;

public final class Order {
    private final Dish[] _orderList;
    private int _cost = 0;
    private int _cookingTime = 0;
    private OrderStatus _status;

    public Order(Dish[] orderList) {
        _status = OrderStatus.Accepted;
        _orderList = orderList;
        for (Dish dish : _orderList) {
            _cost += dish.getCost();
            _cookingTime += dish.getCookingTime();
        }
    }

    public int getCost() {
        return _cost;
    }

    public int getCookingTime() {
        return _cookingTime;
    }

    public OrderStatus getStatus() {
        return _status;
    }

    public void updateStatus() {
        if (_status == OrderStatus.Ready) {
            throw new ArrayIndexOutOfBoundsException("Order is done already!");
        }
        _status = OrderStatus.values()[_status.ordinal() + 1];
    }

    public Dish[] getOrderList() {
        return _orderList;
    }
}
