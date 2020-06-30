package com.delivery.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.delivery.model.Drone;
import com.delivery.model.Position;

/**
 * Class that manipulate the files that need to be uploaded inside the application
 * 
 * @author Juan Rodriguez
 *
 */
public class ManipulateFiles
{
	// Number of files permitted in the application
	static final int PERMITTED = 20;

	// Number of request allowed
	static final int REQUEST = 3;

	// Path where is located all of the application's files
	private String path;

	public ManipulateFiles(String path) {
		this.path = path;
	}

	/**
	 * Method to read all of file's information and convert in drone list with the corresponding
	 * raw movements information
	 * 
	 * @return drone list with raw information
	 */
	public List<Drone> listDronesByAllFiles() {
		int[] filesLoaded = {1};
		List<Drone> droneList = new ArrayList<>();
		try (Stream<Path> paths = Files.walk(Paths.get(path))) {
			paths.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					try {
						if (filesLoaded[0] != PERMITTED) {
							droneList.add(readContent(filePath));
						} else {
							System.out.println(String.format("You surpassed %s number of files permitted", PERMITTED));
						}
						filesLoaded[0]++;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			});
		} catch (Exception e) {
			System.out.println("The directory or file that you are trying to load does not exist");
		}
		return droneList;
	}

	/**
	 * Method to read the file's content and convert in a drone object
	 * 
	 * @param filePath where is located all of the application's files
	 * 
	 * @return drone object
	 */
	Drone readContent(Path filePath) throws IOException {
		String fileName = filePath.getFileName().toString();
		String droneNumber = fileName.substring(2).substring(0, 2);
		Drone drone = new Drone();
		drone.setDroneNumber(droneNumber);
		List<String> movesList = Files.readAllLines(filePath);
		if (movesList.size() > REQUEST) {
			throw new IllegalArgumentException(
					String.format("You are adding more than %s requests allowed per unit in file %s", REQUEST, fileName));
		}
		drone.setRawInformation(movesList);
		return drone;
	}

	/**
	 * Method to create the output files for the user after processing all of valid movements in each position list
	 * for every drone
	 * 
	 * @param listDrones to be uploaded
	 * 
	 */
	public void createOutputFiles(List<Drone> droneList) {
		try {
			for(Drone drone : droneList) {
				String init = "== Reporte de entregas ==\n\n";
				String routeDestiny = String.format("%s/out%s.txt", path, drone.getDroneNumber());
				FileOutputStream out = new FileOutputStream(routeDestiny);
				byte[] initialBytes = init.getBytes();
				out.write(initialBytes);
				for (Position position : drone.getDronePositions()) {
					String line = writeLinePerPosition(position);
					byte[] bytesToWrite = line.getBytes();
					out.write(bytesToWrite);
				}
				out.close();
			}
		} catch (Exception ex) {
			System.out.println("Error trying to generate the output document");
		}
	}

	/**
	 * Method to write a line for each position in a specific form
	 * 
	 * @param position to be verified
	 * 
	 * @return String that contains the correct format to be written
	 */
	private String writeLinePerPosition(Position position) {
		String orientationDescription = getOrientationDescription(position.getOrientation());
		return String.format("(%d, %d) dirección %s%n%n", position.getX(), position.getY(), orientationDescription);
	}

	/**
	 * Method to convert the single orientation character in a complete description
	 * 
	 * @param orientation of the robot
	 * 
	 * @return String complete description for a orientation
	 */
	private String getOrientationDescription(char orientation) {
		String orientationDes = "";
		switch (orientation) {
			case 'N':
				orientationDes = "Norte";
				break;
	
			case 'S':
				orientationDes = "Sur";
				break;
	
			case 'E':
				orientationDes = "Este";
				break;
	
			default:
				orientationDes = "Oriente";
				break;
		}
		return orientationDes;
	}
}
