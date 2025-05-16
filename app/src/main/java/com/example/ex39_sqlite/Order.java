package com.example.ex39_sqlite;

/**
 * Represents an order with details such as order ID, date, time,
 * employee ID who handled the order, meal ID, and provider ID.
 *
 *
 * @author      Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/4/2025
 */
public class Order {
    /**
     * Unique identifier for the order.
     */
    private int order_id;

    /**
     * Date when the order was placed.
     */
    private String date;

    /**
     * Time when the order was placed.
     */
    private String time;

    /**
     * ID of the employee who handled the order.
     */
    private int employee_id;

    /**
     * ID of the meal ordered.
     */
    private int meal_id;

    /**
     * ID of the provider supplying the meal.
     */
    private int provider_id;

    /**
     * Constructs an Order instance with all fields initialized.
     *
     * @param order_id Unique identifier for the order.
     * @param date Date of the order.
     * @param time Time of the order.
     * @param employee_id ID of the employee.
     * @param meal_id ID of the meal.
     * @param provider_id ID of the provider.
     */
    public Order(int order_id, String date, String time, int employee_id, int meal_id, int provider_id) {
        this.order_id = order_id;
        this.date = date;
        this.time = time;
        this.employee_id = employee_id;
        this.meal_id = meal_id;
        this.provider_id = provider_id;
    }

    /**
     * Returns the order ID.
     *
     * @return order ID as an integer.
     */
    public int getOrder_id() {
        return order_id;
    }

    /**
     * Sets the order ID.
     *
     * @param order_id The order ID to set.
     */
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    /**
     * Returns the date of the order.
     *
     * @return date as a String.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the order.
     *
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the time of the order.
     *
     * @return time as a String.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of the order.
     *
     * @param time The time to set.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Returns the employee ID who handled the order.
     *
     * @return employee ID as an integer.
     */
    public int getEmployee_id() {
        return employee_id;
    }

    /**
     * Sets the employee ID.
     *
     * @param employee_id The employee ID to set.
     */
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    /**
     * Returns the meal ID.
     *
     * @return meal ID as an integer.
     */
    public int getMeal_id() {
        return meal_id;
    }

    /**
     * Sets the meal ID.
     *
     * @param meal_id The meal ID to set.
     */
    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    /**
     * Returns the provider ID.
     *
     * @return provider ID as an integer.
     */
    public int getProvider_id() {
        return provider_id;
    }

    /**
     * Sets the provider ID.
     *
     * @param provider_id The provider ID to set.
     */
    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }
}
