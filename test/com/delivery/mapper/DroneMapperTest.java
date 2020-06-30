package com.delivery.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.delivery.model.Drone;
import com.delivery.model.Position;

/**
 * It contains the test for the class of {@link DroneMapper}
 * 
 * @author Juan Rodriguez
 *
 */
public class DroneMapperTest
{

	private DroneMapper droneMapper;
	
	@Before
    public void setUp() throws Exception {
		droneMapper = new DroneMapper();
    }

	/**
     * Test that convert the raw information to drone positions
     */
	@Test
	public void testConvertRawInformationToDronePositions() {
		List<Drone> droneList = new ArrayList<>();
		List<String> rawInformation = new ArrayList<>();
		rawInformation.add("AAAA");
		Drone droneOne = new Drone("01", null, rawInformation);
		droneList.add(droneOne);
		assertNotNull(droneMapper.convertRawInformationToDronePositions(droneList));
	}
	
	/**
     * Test that verify if the information which is going to convert gets the correct values 
     */
	@Test
	public void testConvertRawInformationToPositions() {
		List<String> rawInformation = new ArrayList<>();
		rawInformation.add("AAIA");
		Position position = new Position();
		position.setX(-1);
		position.setY(2);
		position.setOrientation('O');
		List<Position> listPositions = droneMapper.convertRawInformationToPositions(rawInformation);
		assertNotNull(listPositions);
		assertEquals(listPositions.get(0).getX(), position.getX());
		assertEquals(listPositions.get(0).getY(), position.getY());
		assertEquals(listPositions.get(0).getOrientation(), position.getOrientation());
	}

	/**
     * Test that verify if the method gets a null result when received a null value
     */
	@Test
	public void testNullConvertRawInformationToDronePositions() {
		assertNull(droneMapper.convertRawInformationToDronePositions(null));
	}

	/**
     * Test that verify if the method gets a empty result when received a empty raw information
     */
	@Test
	public void testEmptyConvertRawInformationToPositions() {
		List<String> rawInformation = new ArrayList<>();
		rawInformation.add("");
		List<Position> listPositions = droneMapper.convertRawInformationToPositions(rawInformation);
		assertNotNull(listPositions);
		assertEquals(listPositions.get(0).getX(), 0);
		assertEquals(listPositions.get(0).getY(), 0);
		assertEquals(listPositions.get(0).getOrientation(), 'N');
	}
	
	/**
     * Test that verify if the method gets a null result when received a null position
     */
	@Test
	public void testGenerateNullPosition() {
		String request = "AAIA";
		Position result = droneMapper.generatePosition(request, null);
		assertNull(result);
	}
	
	/**
     * Test that shows that the movements inside the problem were invalid
     */
	@Test
	public void testInvalidMovements() {
		List<String> rawInformation = new ArrayList<>();
		rawInformation.add("AAAAIAA");
		Position position = new Position();
		position.setX(-2);
		position.setY(4);		
		position.setOrientation('N');
		List<Position> listPositions = droneMapper.convertRawInformationToPositions(rawInformation);
		assertNotNull(listPositions);
		assertEquals(listPositions.get(0).getX(), position.getX());
		assertEquals(listPositions.get(0).getY(), position.getY());
		assertNotEquals(listPositions.get(0).getOrientation(), position.getOrientation());
	}

}
