package br.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import br.com.domain.Vehicle;
import br.com.service.*;

public class VehicleServiceImpl implements VehicleService {

	private List<Vehicle> vehicles = new ArrayList<Vehicle>();
	private Integer actualId = 10;
	
	public VehicleServiceImpl() {
		for (int i = 0; i < 10; i++){
			Vehicle vehicle = new Vehicle();
			vehicle.setId(i);
			vehicle.setName("SUBARU" + i);
			vehicle.setYear(2018);
			vehicles.add(vehicle);
		}
	}
	
	public List<Vehicle> getAll(){
		return vehicles;
	}
	
	public Vehicle findById(Integer id){
        Optional<Vehicle> vehicleOptional = vehicles.stream()
        		.filter(vehicle -> vehicle.getId().equals(id)).findFirst();
        return vehicleOptional.orElse(null);
    }

    public Vehicle saveVehicle(Vehicle vehicle){
    	if(vehicle.getId() != null) {
    		this.deleteById(vehicle.getId());
    	} else {
    		actualId++;
    		vehicle.setId(actualId);
    	}

    	this.vehicles.add(vehicle);
        return vehicle;
    }

    public void deleteById(Integer id){
    	this.vehicles.removeIf(vehicle -> vehicle.getId().equals(id));
    }
}
