package com.example.ex39_sqlite;

/**
 * Represents a Meal with an ID and various courses including starter,
 * main course, side dish, dessert, and drink.
 *
 * @author      Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/4/2025
 */
public class Meal {
    /**
     * The unique identifier for the meal.
     */
    private int meal_id;

    /**
     * The starter dish of the meal.
     */
    private String starter;

    /**
     * The main course dish of the meal.
     */
    private String main_course;

    /**
     * The side dish of the meal.
     */
    private String side_dish;

    /**
     * The dessert dish of the meal.
     */
    private String dessert;

    /**
     * The drink served with the meal.
     */
    private String drink;

    /**
     * Constructs a Meal instance with all fields initialized.
     *
     * @param meal_id Unique identifier for the meal.
     * @param starter The starter dish.
     * @param main_course The main course dish.
     * @param side_dish The side dish.
     * @param dessert The dessert dish.
     * @param drink The drink served.
     */
    public Meal(int meal_id, String starter, String main_course, String side_dish, String dessert, String drink) {
        this.meal_id = meal_id;
        this.starter = starter;
        this.main_course = main_course;
        this.side_dish = side_dish;
        this.dessert = dessert;
        this.drink = drink;
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
     * Returns the starter dish.
     *
     * @return starter as a String.
     */
    public String getStarter() {
        return starter;
    }

    /**
     * Sets the starter dish.
     *
     * @param starter The starter to set.
     */
    public void setStarter(String starter) {
        this.starter = starter;
    }

    /**
     * Returns the main course dish.
     *
     * @return main course as a String.
     */
    public String getMain_course() {
        return main_course;
    }

    /**
     * Sets the main course dish.
     *
     * @param main_course The main course to set.
     */
    public void setMain_course(String main_course) {
        this.main_course = main_course;
    }

    /**
     * Returns the side dish.
     *
     * @return side dish as a String.
     */
    public String getSide_dish() {
        return side_dish;
    }

    /**
     * Sets the side dish.
     *
     * @param side_dish The side dish to set.
     */
    public void setSide_dish(String side_dish) {
        this.side_dish = side_dish;
    }

    /**
     * Returns the dessert dish.
     *
     * @return dessert as a String.
     */
    public String getDessert() {
        return dessert;
    }

    /**
     * Sets the dessert dish.
     *
     * @param dessert The dessert to set.
     */
    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    /**
     * Returns the drink served.
     *
     * @return drink as a String.
     */
    public String getDrink() {
        return drink;
    }

    /**
     * Sets the drink served.
     *
     * @param drink The drink to set.
     */
    public void setDrink(String drink) {
        this.drink = drink;
    }
}
