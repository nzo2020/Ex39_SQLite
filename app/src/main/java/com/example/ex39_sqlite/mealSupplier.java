package com.example.ex39_sqlite;

/**
 * Represents a meal supplier with provider ID, company name,
 * main phone number, and secondary phone number.
 *
 *
 * @author      Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/4/2025
 */
public class mealSupplier {
    /**
     * Unique identifier for the provider.
     */
    private int provider_id;

    /**
     * Name of the supplier company.
     */
    private String company_name;

    /**
     * Main phone number of the supplier.
     */
    private String main_phone;

    /**
     * Secondary phone number of the supplier.
     */
    private String secondary_phone;

    /**
     * Constructs a mealSupplier instance with all fields initialized.
     *
     * @param provider_id Unique identifier for the provider.
     * @param company_name Name of the supplier company.
     * @param main_phone Main phone number.
     * @param secondary_phone Secondary phone number.
     */
    public mealSupplier(int provider_id, String company_name, String main_phone, String secondary_phone) {
        this.provider_id = provider_id;
        this.company_name = company_name;
        this.main_phone = main_phone;
        this.secondary_phone = secondary_phone;
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

    /**
     * Returns the company name.
     *
     * @return company name as a String.
     */
    public String getCompany_name() {
        return company_name;
    }

    /**
     * Sets the company name.
     *
     * @param company_name The company name to set.
     */
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    /**
     * Returns the main phone number.
     *
     * @return main phone as a String.
     */
    public String getMain_phone() {
        return main_phone;
    }

    /**
     * Sets the main phone number.
     *
     * @param main_phone The main phone number to set.
     */
    public void setMain_phone(String main_phone) {
        this.main_phone = main_phone;
    }

    /**
     * Returns the secondary phone number.
     *
     * @return secondary phone as a String.
     */
    public String getSecondary_phone() {
        return secondary_phone;
    }

    /**
     * Sets the secondary phone number.
     *
     * @param secondary_phone The secondary phone number to set.
     */
    public void setSecondary_phone(String secondary_phone) {
        this.secondary_phone = secondary_phone;
    }
}
