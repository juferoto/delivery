package com.delivery.validation;

/**
 * Class that validates the movements for a drone
 * 
 * @author Juan Rodriguez
 *
 */
public class DroneValidation
{
	//Number of blocks allowed inside the application
	private static final int BLOCKS = 10;

	/**
	 * Method to validate if a movement within its limits can be approved
	 * 
	 * @param movement x in the X-axis position
	 * @param movement y in the Y-axis position
	 * 
	 * @return validates if a movement is permitted or not
	 */
	public static boolean validateMovements(int x, int y)
	{
		boolean permitted = true;
		if ((Math.abs(x) > BLOCKS) || (Math.abs(y) > BLOCKS))
		{
			permitted = false;
		}
		return permitted;
	}
}
