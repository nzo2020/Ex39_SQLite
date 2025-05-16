package com.example.ex39_sqlite;

/**
 * Represents an Employee with card ID, first and last names,
 * company, ID number, and phone number.
 *
 * @author      Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/4/2025
 */
public class Employee {
    /**
     * The employee's card ID.
     */
    private int card_id;

    /**
     * The employee's first name.
     */
    private String first_name;

    /**
     * The employee's last name.
     */
    private String last_name;

    /**
     * The company the employee works for.
     */
    private String company;

    /**
     * The employee's ID number.
     */
    private String id_number;

    /**
     * The employee's phone number.
     */
    private String phone;

    /**
     * Constructs an Employee instance with all fields initialized.
     *
     * @param card_id The employee's card ID.
     * @param first_name The employee's first name.
     * @param last_name The employee's last name.
     * @param company The company name.
     * @param id_number The employee's ID number.
     * @param phone The employee's phone number.
     */
    public Employee(int card_id, String first_name, String last_name, String company, String id_number, String phone) {
        this.card_id = card_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.id_number = id_number;
        this.phone = phone;
    }

    /**
     * Returns the employee's card ID.
     *
     * @return card ID as an integer.
     */
    public int getCard_id() {
        return card_id;
    }

    /**
     * Sets the employee's card ID.
     *
     * @param card_id The card ID to set.
     */
    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    /**
     * Returns the employee's first name.
     *
     * @return first name as a String.
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets the employee's first name.
     *
     * @param first_name The first name to set.
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Returns the employee's last name.
     *
     * @return last name as a String.
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets the employee's last name.
     *
     * @param last_name The last name to set.
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Returns the company name the employee works for.
     *
     * @return company name as a String.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company name for the employee.
     *
     * @param company The company name to set.
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Returns the employee's ID number.
     *
     * @return ID number as a String.
     */
    public String getId_number() {
        return id_number;
    }

    /**
     * Sets the employee's ID number.
     *
     * @param id_number The ID number to set.
     */
    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    /**
     * Returns the employee's phone number.
     *
     * @return phone number as a String.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the employee's phone number.
     *
     * @param phone The phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
