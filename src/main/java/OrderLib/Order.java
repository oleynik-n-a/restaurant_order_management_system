package OrderLib;

import DishLib.Dish;

import java.util.ArrayList;

public final class Order {
    private final ArrayList<Dish> _dishesList;
    private int _cost = 0;
    private int _cookingTime = 0;
    private OrderStatus _status;

    public Order(ArrayList<Dish> orderList) {
        _status = OrderStatus.Accepted;
        _dishesList = orderList;
        for (Dish dish : _dishesList) {
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

    public ArrayList<Dish> getDishesList() {
        return _dishesList;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Order:\n");
        for (int i = 0; i < _dishesList.size(); ++i) {
            str.append("  ").append(i + 1).append(". ").append(_dishesList.get(i)).append('\n');
        }
        return str.toString();
    }
}
