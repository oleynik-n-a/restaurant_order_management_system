package OrderLib;

import DishLib.Dish;

import java.io.Serializable;
import java.util.ArrayList;

public final class Order implements Serializable {
    private final ArrayList<Dish> _dishesList;
    private OrderStatus _status;
    private int _timeLeft = 0;

    public Order(ArrayList<Dish> orderList) {
        _status = OrderStatus.Accepted;
        _dishesList = orderList;
        _timeLeft = getCookingTime();
    }

    public ArrayList<Dish> getDishesList() {
        return _dishesList;
    }

    public int getCost() {
        int cost = 0;
        for (Dish dish : _dishesList) {
            cost += dish.getCost();
        }
        return cost;
    }

    public int getCookingTime() {
        int cookingTime = 0;
        for (Dish dish : _dishesList) {
            cookingTime += dish.getCookingTime();
        }
        return cookingTime;
    }

    public OrderStatus getStatus() {
        return _status;
    }

    public void updateStatus() {
        if (_status == OrderStatus.Paid) {
            throw new ArrayIndexOutOfBoundsException("Order is done already!");
        }
        _status = OrderStatus.values()[_status.ordinal() + 1];
    }

    public int getTimeLeft() {
        return _timeLeft;
    }

    public void setTimeLeft(int value) {
        _timeLeft = value;
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
