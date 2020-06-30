package com.delivery.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * It contains the test for the class of {@link DroneValidation}
 * 
 * @author Juan Rodriguez
 *
 */
public class DroneValidationTest
{
	/**
     * Test that validates if a movement within its limits can be approved
     */
	@Test
	public void testPositiveValidateMovements() {
		int x = 10;
		int y = 10;
		boolean validateMovements = DroneValidation.validateMovements(x, y);
		assertTrue(validateMovements);
	}
	
	/**
     * Test that validates if a movement over its limits can not be approved
     */
	@Test
	public void testNegativeValidateMovements() {
		int x = 11;
		int y = 11;
		boolean validateMovements = DroneValidation.validateMovements(x, y);
		assertFalse(validateMovements);
	}

}
