import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class DealershipFileManager {
    private static final String FILE_PATH = "inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            // 1. Read the first line for Dealership details: name|address|phone
            String dealershipLine = reader.readLine();
            if (dealershipLine != null) {
                String[] dealershipParts = dealershipLine.split("\\|");
                String name = dealershipParts[0];
                String address = dealershipParts[1];
                String phone = dealershipParts[2];

                dealership = new Dealership(name, address, phone);
            }

            // 2. Read remaining lines for Vehicles: vin|year|make|model|type|color|odometer|price
            String vehicleLine;
            while ((vehicleLine = reader.readLine()) != null) {
                String[] vehicleParts = vehicleLine.split("\\|");

                int vin = Integer.parseInt(vehicleParts[0]);
                int year = Integer.parseInt(vehicleParts[1]);
                String make = vehicleParts[2];
                String model = vehicleParts[3];
                String vehicleType = vehicleParts[4];
                String color = vehicleParts[5];
                int odometer = Integer.parseInt(vehicleParts[6]);
                //double price = Double.parseDouble(vehicleParts[7]);
                String price = vehicleParts[7];

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, new BigDecimal(price));
                if (dealership != null) {
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }

    }


}
