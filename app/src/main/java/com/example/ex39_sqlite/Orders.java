package com.example.ex39_sqlite;

/**
 * Contains constants for the Orders table and its column names,
 * used for database operations related to orders.
 *
 *
 * @author      Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/4/2025
 */
public class Orders {
    /**
     * The name of the Orders table in the database.
     */
    public static final String TABLE_ORDERS = "Orders";

    /**
     * Column name for the order ID.
     */
    public static final String ORDER_ID = "order_id";

    /**
     * Column name for the order date.
     */
    public static final String DATE = "date";

    /**
     * Column name for the order time.
     */
    public static final String TIME = "time";

    /**
     * Column name for the employee ID who made the order.
     */
    public static final String EMPLOYEE_ID = "employee_id";

    /**
     * Column name for the meal ID related to the order.
     */
    public static final String MEAL_ID = "meal_id";

    /**
     * Column name for the provider ID supplying the meal.
     */
    public static final String PROVIDER_ID = "provider_id";
}
