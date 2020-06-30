package com.delivery;

import java.util.List;

import com.delivery.mapper.DroneMapper;
import com.delivery.model.Drone;
import com.delivery.util.ManipulateFiles;

/**
 * Main class that executes the program
 * 
 * @author Juan Rodriguez
 *
 */
public class Delivery
{
	// Path where is located all routes for drones
	private static final String PATH = "C:\\Users\\FamiliaRR\\Documents\\Delivery";

	public static void main(String[] args)
	{
		ManipulateFiles manipulateFiles = new ManipulateFiles(PATH);
		List<Drone> droneList = manipulateFiles.listDronesByAllFiles();
		DroneMapper droneMapper = new DroneMapper();
		droneMapper.convertRawInformationToDronePositions(droneList);
		manipulateFiles.createOutputFiles(droneList);
	}

}
