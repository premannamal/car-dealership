import java.math.BigDecimal;
import java.math.RoundingMode;

public class LeaseContract extends Contract {
    private BigDecimal expectedEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        BigDecimal originalPrice = vehicleSold.getPrice();

        this.expectedEndingValue = originalPrice.multiply(new BigDecimal("0.50")).setScale(2, RoundingMode.HALF_UP);
        this.leaseFee = originalPrice.multiply(new BigDecimal("0.07")).setScale(2, RoundingMode.HALF_UP);
    }

    // Getters and Setters
    public BigDecimal getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(BigDecimal expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public BigDecimal getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(BigDecimal leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return expectedEndingValue.add(leaseFee).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        BigDecimal totalPrice = getTotalPrice();
        double annualInterestRate = 0.04;
        int months = 36;

        double monthlyInterestRate = annualInterestRate / 12.0;
        double payment = (totalPrice.doubleValue() * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -months));

        return BigDecimal.valueOf(payment).setScale(2, RoundingMode.HALF_UP);
    }
}
