import java.math.BigDecimal;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    //Constructor method
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold)
    {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // Getters and Setters
    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail)
    {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold()
    {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold)
    {
        this.vehicleSold = vehicleSold;
    }

    // Abstract methods to be implemented by subclasses
    public abstract BigDecimal getTotalPrice();

    public abstract BigDecimal getMonthlyPayment();
}
