import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

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
        inventory.add(vehicle);
    }

    //removeVehicle method
    public void removeVehicle(Vehicle vehicle) {
        // Empty implementation for now
        // vehicle.remove(0);
    }

    public ArrayList<Vehicle> getAllVehicles()
    {
        return inventory;
    }

    // Search methods returning null per instructions
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max)
    {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model)
    {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max)
    {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color)
    {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max)
    {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType)
    {
        return null;
    }
}
