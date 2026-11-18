import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    private static final String FILE_PATH = "contracts.csv"; // Update path if your file is in a specific folder

    public void saveContract(Contract contract) {
        // Passing 'true' to FileWriter enables append mode so existing contracts are not overwritten
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            Vehicle v = contract.getVehicleSold();

            // Common fields shared by both SalesContract and LeaseContract
            String commonDetails = String.format("%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f",
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getCustomerEmail(),
                    v.getVin(),
                    v.getYear(),
                    v.getMake(),
                    v.getModel(),
                    v.getVehicleType(),
                    v.getColor(),
                    v.getOdometer(),
                    v.getPrice()
            );

            // Check subclass type to format contract-specific fields
            if (contract instanceof SalesContract) {
                SalesContract sale = (SalesContract) contract;
                String financeOption = sale.isFinanced() ? "YES" : "NO";

                String saleLine = String.format("SALE|%s|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        commonDetails,
                        sale.getSalesTaxAmount(),
                        sale.getRecordingFee(),
                        sale.getProcessingFee(),
                        sale.getTotalPrice(),
                        financeOption,
                        sale.getMonthlyPayment()
                );
                writer.write(saleLine);
                writer.newLine();

            } else if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;

                String leaseLine = String.format("LEASE|%s|%.2f|%.2f|%.2f|%.2f",
                        commonDetails,
                        lease.getExpectedEndingValue(),
                        lease.getLeaseFee(),
                        lease.getTotalPrice(),
                        lease.getMonthlyPayment()
                );
                writer.write(leaseLine);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving contract to file: " + e.getMessage());
        }
    }
}
