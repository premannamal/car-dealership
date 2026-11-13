import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<Vehicle>();
    }

    // Getters for name, address, and phone

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhone()
    {
        return phone;
    }

    // Settors for name, address, and phone

    public void setName(String name)
    {
        this.name = name;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    //addVehicle method
    public void addVehicle(Vehicle vehicle)
    {
        this.inventory.add(vehicle);
    }

    //removeVehicle method
    public void removeVehicle(Vehicle vehicle) {
        // Empty implementation for now
        this.inventory.remove(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles()
    {
        return this.inventory;
    }

    // Search methods returning null per instructions
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
         ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getPrice().doubleValue() >= min && vehicle.getPrice().doubleValue() <= max) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model)
    {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max)
    {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color)
    {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max)
    {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType)
    {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                matches.add(vehicle);
            }
        }
        return matches;
    }
}
