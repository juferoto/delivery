package com.delivery.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.delivery.model.Drone;
import com.delivery.model.Position;

/**
 * It contains the test for the class of {@link ManipulateFiles}
 * 
 * @author Juan Rodriguez
 *
 */
public class ManipulateFilesTest
{
	private static final PrintStream originalOut = System.out;

	// Current directory of the project
	private static final String USER_DIR = System.getProperty("user.dir");

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}

	/**
     * Test that generates a single output file successfully
     */
	@Test
	public void testGenerateOutputFileSuccessfully() throws IOException {
		Position position = new Position();
		position.setX(-1);
		position.setY(2);
		position.setOrientation('N');
		List<Position> listPositions = new ArrayList<>();
		listPositions.add(position);
		Drone droneOne = new Drone("01", listPositions, null);
		List<Drone> droneList = new ArrayList<>();
		droneList.add(droneOne);
		String path = String.format("%s\\resources\\surpassed", USER_DIR);
		ManipulateFiles manipulateFilesTemp = new ManipulateFiles(path);
		manipulateFilesTemp.createOutputFiles(droneList);
		File file = tempFolder.newFile("out01.txt");
	    assertTrue(file.exists());
	}

	/**
     * Test that validates when the files which are going to be loaded surpassed the limit permitted
     */
	@Test
	public void testSurpassedLimitedOfRequest() {
		String fileName = "test.txt";
		String path = String.format("%s\\resources\\%s", USER_DIR, fileName);
		ManipulateFiles manipulateFilesTemp = new ManipulateFiles(path);
		manipulateFilesTemp.listDronesByAllFiles();
		assertTrue(outContent.toString().contains(
				String.format("You are adding more than %s requests allowed per unit in file %s", ManipulateFiles.REQUEST, fileName)));
	}

	/**
     * Test that gets an empty drone list when the directory path is invalid and receives a system notification
     */
	@Test
	public void testInvalidDirectory() {
		String message = "The directory or file that you are trying to load does not exist";
		String path = String.format("%s\\resourcesFake", USER_DIR);
		ManipulateFiles manipulateFilesTemp = new ManipulateFiles(path);
		List<Drone> droneList = manipulateFilesTemp.listDronesByAllFiles();
		assertTrue(outContent.toString().contains(message));
		assertEquals(droneList.isEmpty(), true);
	}

	/**
     * Test that validates when the movements which are going to be loaded surpassed the limit permitted
     */
	@Test
	public void testSurpassedLimitedOfFiles() {
		String path = String.format("%s\\resources\\surpassed", USER_DIR);
		ManipulateFiles manipulateFilesTemp = new ManipulateFiles(path);
		manipulateFilesTemp.listDronesByAllFiles();
		assertTrue(outContent.toString().contains(
				String.format("You surpassed %s number of files permitted", ManipulateFiles.PERMITTED)));
	}

}
