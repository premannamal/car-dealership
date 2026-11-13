import java.util.*;
import java.util.Scanner;
import java.util.List;

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
                System.out.println("Invalid command. Please try again.");
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
            System.out.println("No vehicles found.");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
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
    System.out.print("Enter vehicle type (car, truck, SUV, van): ");
    String type = scanner.nextLine();

    ArrayList<Vehicle> vehicles = this.dealership.getVehiclesByType(type);
    displayVehicles(vehicles);
}

public void processGetAllVehiclesRequest()
    {
ArrayList<Vehicle> allVehicles = this.dealership.getAllVehicles();
    displayVehicles(allVehicles);
}

public void processAddVehicleRequest()
{
    System.out.print("Enter VIN: ");
    int vin = scanner.nextInt();
    System.out.print("Enter year: ");
    int year = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    System.out.print("Enter make: ");
    String make = scanner.nextLine();
    System.out.print("Enter model: ");
    String model = scanner.nextLine();
    System.out.print("Enter vehicle type (car, truck, SUV, van): ");
    String vehicleType = scanner.nextLine();
    System.out.print("Enter color: ");
    String color = scanner.nextLine();

    System.out.print("Enter odometer reading: ");
    int odometer = scanner.nextInt();
    System.out.print("Enter price: ");
    String price = scanner.nextLine();
    scanner.nextLine(); // Consume newline

    Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, new BigDecimal(price));
    this.dealership.addVehicle(newVehicle);

    DealershipFileManager fileManager = new DealershipFileManager();
    fileManager.saveDealership(this.dealership);
    System.out.println("Vehicle added and inventory saved.");
}

public void processRemoveVehicleRequest()
{
    System.out.print("Enter the VIN of the vehicle to remove: ");
    int vin = scanner.nextInt();
    scanner.nextLine(); // Consume newline

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

