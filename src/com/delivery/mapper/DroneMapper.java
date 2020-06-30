package com.delivery.mapper;

import java.util.ArrayList;
import java.util.List;

import com.delivery.model.Drone;
import com.delivery.model.Position;
import com.delivery.validation.DroneValidation;

/**
 * Class that converts raw information in drone or positions list
 * 
 * @author Juan Rodriguez
 *
 */
public class DroneMapper
{
	/**
	 * Method to convert the raw positions for each drone in a valid position list
	 * 
	 * @param listDrones to be checked
	 * 
	 * @return drone list with valid position list
	 */
	public List<Drone> convertRawInformationToDronePositions(List<Drone> listDrones) {
		if (listDrones == null) return listDrones;
		List<Drone> droneList = new ArrayList<>();
		for(Drone drone : listDrones) {
			drone.setDronePositions(convertRawInformationToPositions(drone.getRawInformation()));
		}
		return droneList;
	}
	
	/**
	 * Method to convert the raw movements in a valid position list
	 * 
	 * @param dataMovements raw movements
	 * 
	 * @return position list
	 */
	List<Position> convertRawInformationToPositions(List<String> dataMovements) {
		List<Position> positionList = new ArrayList<>();
		Position position = new Position();
		for (String request : dataMovements) {
			positionList.add(generatePosition(request, position));
		}
		return positionList;
	}

	/**
	 * Method to make a move or change the robot's orientation depending on the user's assignment
	 * 
	 * @param request user's assignment
	 * @param position current robot's position
	 * 
	 * @return position object
	 */
	Position generatePosition(String request, Position position) {
		if (position == null) return position;
		StringBuilder sb = new StringBuilder(request);
	  	for (int i = 0; i < sb.length(); i++) {
	  		char move = sb.charAt(i);
	  		if (move == 'A') 
	  			makeAMove(position);
	  		else
  				changeOrientation(move, position);
	  	}
	  	return new Position(position);
	}
	
	/**
	 * Method to make a move depending on the movement assigned
	 * 
	 * @param position current robot's position
	 */
	private void makeAMove(Position position) {
		switch (position.getOrientation()) {
			case 'N':
				if(DroneValidation.validateMovements(position.getX(), position.getY()+1))
					position.setY(position.getY()+1);
				break;

			case 'S':
				if(DroneValidation.validateMovements(position.getX(), position.getY()-1))
					position.setY(position.getY()-1);
				break;

			case 'E':
				if(DroneValidation.validateMovements(position.getX()+1, position.getY()))
					position.setX(position.getX()+1);
				break;

			default:
				if(DroneValidation.validateMovements(position.getX()-1, position.getY()))
					position.setX(position.getX()-1);
				break;
		}
	}

	/**
	 * Method to change the orientation depending on the movement assigned
	 * 
	 * @param position current robot's position
	 */
	private void changeOrientation(char move, Position position) {
		if (move == 'I') {
			switch (position.getOrientation()) {
				case 'N':
					position.setOrientation('O');
					break;
		
				case 'S':
					position.setOrientation('E');
					break;
		
				case 'E':
					position.setOrientation('N');
					break;
		
				default:
					position.setOrientation('S');
					break;
			}
		}
		else if (move == 'D') {
			switch (position.getOrientation()) {
				case 'N':
					position.setOrientation('E');
					break;
		
				case 'S':
					position.setOrientation('O');
					break;
		
				case 'E':
					position.setOrientation('S');
					break;
		
				default:
					position.setOrientation('N');
					break;
			}
		}
	}
	
}
