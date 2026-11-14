import java.util.*;
import java.util.Scanner;
import java.util.List;
import java.math.BigDecimal;

public class UserInterface {

private Dealership dealership;
private Scanner scanner;

public UserInterface() {
    this.scanner = new Scanner(System.in);
}

public void display() {
    init();
    boolean running = true;

    while (running) {
        System.out.println("\n=== Car Dealership Menu ===");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
        System.out.print("Enter your command: ");

        int command = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (command) {
            case 1:
                processGetByPriceRequest();
                break;
            case 2:
                processGetByMakeModelRequest();
                break;
            case 3:
                processGetByYearRequest();
                break;
            case 4:
                processGetByColorRequest();
                break;
            case 5:
                processGetByMileageRequest();
                break;
            case 6:
                processGetByVehicleTypeRequest();
                break;
            case 7:
                processGetAllVehiclesRequest();
                break;
            case 8:
                processAddVehicleRequest();
                break;
            case 9:
                processRemoveVehicleRequest();
                break;
            case 99:
                running = false;
                System.out.println("Exiting application...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

private void init() {
        // TODO: Load the dealership using DealershipFileManager in later phases
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("\n\n");
            System.out.println("No vehicles found.");
            System.out.println("---------------------------------------------------------------------------------");
            return;
        }

        //display header
        System.out.println("\n");
        System.out.println("Vehicle Listing satisfying the criteria");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println( "VIN  " + "|" + "YEAR "  + "|" + "MAKE " + "|" + "MODEL " + "|" + "VEHICLE TYPE " + "|" + "COLOR " + "|" + "MILEAGE " + "|" + "PRICE " );
        System.out.println("----------------------------------------------------------------------------------");

        for (Vehicle vehicle : vehicles) {
            System.out.println( vehicle.getVin() + "|" + vehicle.getYear()  + "|" + vehicle.getMake() + "|" + vehicle.getModel() + "|" + vehicle.getVehicleType()  + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice() );
        }
    }


public void processGetByPriceRequest()
{
    System.out.print("Enter minimum price: ");
    double min = scanner.nextDouble();
    System.out.print("Enter maximum price: ");
    double max = scanner.nextDouble();
    scanner.nextLine(); // Consume newline

    ArrayList<Vehicle> vehicles = this.dealership.getVehiclesByPrice(min, max);
    System.out.println("________________________________________");
    System.out.println("Finding vehicles in this price range ...");
    displayVehicles(vehicles);
}

public void processGetByMakeModelRequest()
{
    System.out.print("Enter make: ");
    String make = scanner.nextLine();
    System.out.print("Enter model: ");
    String model = scanner.nextLine();

    ArrayList<Vehicle> vehicles = this.dealership.getVehiclesByMakeModel(make, model);
    displayVehicles(vehicles);
}

public void processGetByYearRequest()
{
    System.out.print("Enter minimum year: ");
    int min = scanner.nextInt();
    System.out.print("Enter maximum year: ");
    int max = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    ArrayList<Vehicle> vehicles = this.dealership.getVehiclesByYear(min, max);
    displayVehicles(vehicles);
}

public void processGetByColorRequest()
{
    System.out.print("Enter color: ");
    String color = scanner.nextLine();

    ArrayList<Vehicle> vehicles = this.dealership.getVehiclesByColor(color);
    displayVehicles(vehicles);
}

public void processGetByMileageRequest()
{
    System.out.print("Enter minimum mileage: ");
    int min = scanner.nextInt();
    System.out.print("Enter maximum mileage: ");
    int max = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    ArrayList<Vehicle> vehicles = this.dealership.getVehiclesByMileage(min, max);
    displayVehicles(vehicles);
}

public void processGetByVehicleTypeRequest()
{
    //Get Vehicle Type from keyboard input
    String vehicleType;
    while (true) {
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        try {
            vehicleType = scanner.nextLine().trim();

            if (vehicleType.equalsIgnoreCase("car") ||
                    vehicleType.equalsIgnoreCase("truck") ||
                    vehicleType.equalsIgnoreCase("SUV") ||
                    vehicleType.equalsIgnoreCase("van")) {
                break; // Valid input, exit the loop
            } else {
                throw new IllegalArgumentException("Invalid vehicle type.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter car, truck, SUV, or van.");
        }
    }

    ArrayList<Vehicle> vehicles = this.dealership.getVehiclesByType(vehicleType);
    displayVehicles(vehicles);
}

public void processGetAllVehiclesRequest()
    {
    ArrayList<Vehicle> allVehicles = this.dealership.getAllVehicles();
    displayVehicles(allVehicles);
}

public void processAddVehicleRequest()
{
    int vin, year, odometer;
    String vehicleType, price;
    BigDecimal priceAsBigDecimal;

    //Get keyboard input for VIN
    while ( true ) {
        System.out.print("Enter vin: ");
        try {
            vin = Integer.parseInt(scanner.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid vin");
        }
    }

    //Get keyboard input for YEAR
    while ( true ) {
        System.out.print("Enter year: ");
        try {
            year = Integer.parseInt(scanner.nextLine().trim());
            if ( year >= 1000 && year <= 9999) {
                break;
            } else {
                System.out.println("Invalid year, Please enter a 4 digit year");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid year");
        }
    }


    System.out.print("Enter make: ");
    String make = scanner.nextLine();
    System.out.print("Enter model: ");
    String model = scanner.nextLine();

    //Get Vehicle Type from keyboard input
    while (true) {
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        try {
            vehicleType = scanner.nextLine().trim();

            if (vehicleType.equalsIgnoreCase("car") ||
                    vehicleType.equalsIgnoreCase("truck") ||
                    vehicleType.equalsIgnoreCase("SUV") ||
                    vehicleType.equalsIgnoreCase("van")) {
                break; // Valid input, exit the loop
            } else {
                throw new IllegalArgumentException("Invalid vehicle type.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter car, truck, SUV, or van.");
        }
    }

    System.out.print("Enter color: ");
    String color = scanner.nextLine();

    while ( true ) {
        System.out.print("Enter odometer reading: ");
        try {
            odometer = Integer.parseInt(scanner.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid odometer reading");
        }
    }

    //get price as keyboard input
    while (true) {
        System.out.print("Enter price: ");
        price = scanner.nextLine().trim();
        try {
            priceAsBigDecimal = new BigDecimal(price);

            if (priceAsBigDecimal.compareTo(BigDecimal.ZERO) > 0) {
                break; // Valid positive BigDecimal entered, exit the loop
            } else {
                System.out.println("Invalid price. Price must be greater than 0.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric price (e.g., 19999.99).");
        }
    }

    Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, priceAsBigDecimal);
    this.dealership.addVehicle(newVehicle);

    DealershipFileManager fileManager = new DealershipFileManager();
    fileManager.saveDealership(this.dealership);
    System.out.println("Vehicle added and inventory saved.");
}

public void processRemoveVehicleRequest()
{
    int vin;
    //Get keyboard input for VIN
    while ( true ) {
        System.out.print("Enter the VIN of the vehicle to remove:");
        try {
            vin = Integer.parseInt(scanner.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid vin number to remove");
        }
    }

    Vehicle vehicleToRemove = null;
    for (Vehicle vehicle : this.dealership.getAllVehicles()) {
        if (vehicle.getVin() == vin) {
            vehicleToRemove = vehicle;
            break;
        }
    }

    if (vehicleToRemove != null) {
        this.dealership.removeVehicle(vehicleToRemove);
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(this.dealership);
        System.out.println("Vehicle removed and inventory saved.");
    } else {
        System.out.println("No vehicle found with VIN: " + vin);
    }
}
}


