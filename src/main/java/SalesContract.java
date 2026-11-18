import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalesContract extends Contract {
    private BigDecimal salesTaxAmount;
    private BigDecimal recordingFee;
    private BigDecimal processingFee;
    private boolean isFinanced;

    //Constructor
    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;

        // Convert vehicle price to BigDecimal (assuming vehicleSold.getPrice() returns double)
        // Note: If vehicleSold.getPrice() already returns BigDecimal, use it directly.
        BigDecimal originalPrice = BigDecimal.valueOf(vehicleSold.getPrice());
        BigDecimal taxRate = new BigDecimal("0.05");

        this.salesTaxAmount = originalPrice.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        this.recordingFee = new BigDecimal("100.00");

        if (originalPrice.compareTo(new BigDecimal("10000")) < 0) {
            this.processingFee = new BigDecimal("295.00");
        } else {
            this.processingFee = new BigDecimal("495.00");
        }
    }

    // Getters and Setters
    public BigDecimal getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(BigDecimal salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public BigDecimal getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(BigDecimal recordingFee) {
        this.recordingFee = recordingFee;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal originalPrice = BigDecimal.valueOf(getVehicleSold().getPrice());
        return originalPrice
                .add(salesTaxAmount)
                .add(recordingFee)
                .add(processingFee)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        if (!isFinanced) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        double totalPrice = getTotalPrice().doubleValue();
        double annualInterestRate;
        int months;

        if (getVehicleSold().getPrice() >= 10000) {
            annualInterestRate = 0.0425;
            months = 48;
        } else {
            annualInterestRate = 0.0525;
            months = 24;
        }

        double monthlyInterestRate = annualInterestRate / 12.0;
        double monthlyPayment = (totalPrice * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -months));

        return BigDecimal.valueOf(monthlyPayment).setScale(2, RoundingMode.HALF_UP);
    }
}
