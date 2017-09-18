package no.hvl.dat100.prosjekt.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import no.hvl.dat100.prosjekt.GPSData;
import no.hvl.dat100.prosjekt.GPSDataConverter;
import no.hvl.dat100.prosjekt.GPSDataReaderWriter;

public class GPSDataConverterTester {

	/*
	2017-08-13T08:52:26.000Z,60.385390,5.217217,61.9,7.0,219.93,0.94605947,0,gps,1.02,0.85,1.33,,,,,100.0,
	2017-08-13T08:53:00.000Z,60.385588,5.217857,56.2,11.1,0.0,0.0,0,gps,2.22,0.94,2.41,,,,TILTING,100.0,
	
	*/
	
	private String[] times = {"2017-08-13T08:52:26.000Z", "2017-08-13T08:53:00.000Z"};
	private int[] timesexp = {26 + 60 * 52 + 60 * 60 * 8, 00 + 53 * 60 + 8 * 60 * 60};
	
	private String[] latitudes = {"60.385390","60.385588"};
	private double[] latitudesexp = {60.385390,60.385588};
			
	private String[] longitudes = {"5.217217","5.217857"};
	private double[] longitudesexp = {5.217217,5.217857};
			
	private String[] elevations = {"61.9","56.2"};
	private double[] elevationsexp = {61.9,56.2};
		
	private double ACCURACY = 0.0000001;
	
	private GPSData gpsdata = new GPSData(times,latitudes,longitudes,elevations);
	
	GPSDataConverter converter;
	
	@Before
	public void SetUp() {
		converter = new GPSDataConverter(gpsdata);
		converter.convert();
	}
	
	@Test
	public void test_toSeconds() {
		
		String timestr = "2017-08-13T08:52:26.000Z";
		
	    assertEquals("testtoSeconds", 
	    		8*60*60 + 52*60 + 26,GPSDataConverter.toSeconds(timestr));
		
	}

	// TODO: LMK: shared test functions for the four tables below would be possible
	@Test
	public void test_convertTime() {

		assertNotNull("konvertert tabell var null", converter.times);
		assertEquals("tabell lengde", 2, converter.times.length);
		assertArrayEquals("tabell innhold", timesexp, converter.times);
	}
	
	@Test
	public void test_convertLatitude() {

		assertNotNull("tabell var null", converter.latitudes);
		assertEquals("tabell lengde", 2, converter.latitudes.length);
		assertArrayEquals(latitudesexp, converter.latitudes,ACCURACY);
	}
	
	@Test
	public void test_convertLongitude() {

		assertNotNull("Konvertert tabell var null", converter.longitudes);
		assertEquals("konvertering tabell lengde", 2, converter.longitudes.length);
		assertArrayEquals(longitudesexp, converter.longitudes,ACCURACY);
	}
	
	@Test
	public void test_convertElevation() {

		assertNotNull("Konvertert tabell var null", converter.elevations);
		assertEquals("Høydekonvertering tabell lengde", 2, converter.elevations.length);
		assertArrayEquals(elevationsexp, converter.elevations,ACCURACY);
	}
	
	@Test
	public void test_Printshortlog () {
			
			String testfile = "short";
			System.out.println(testfile);
			
			GPSData gpsdata = GPSDataReaderWriter.readGPSFile(testfile);
			
			converter = new GPSDataConverter(gpsdata);
			converter.convert();
			converter.print();
			
		}
		
	@Test
	public void test_Printmediumlog () {
			
			String testfile = "medium";
			System.out.println(testfile);
			
			GPSData gpsdata = GPSDataReaderWriter.readGPSFile(testfile);
			
			converter = new GPSDataConverter(gpsdata);
			converter.convert();
			converter.print();
			
		}
	
	@Test
	public void test_Printlonglog () {
			
			String testfile = "long";
			System.out.println(testfile);
			
			GPSData gpsdata = GPSDataReaderWriter.readGPSFile(testfile);
			
			converter = new GPSDataConverter(gpsdata);
			converter.convert();
			converter.print();
			
		}
	
	@Test
	public void test_Prinvmlog () {
			
			String testfile = "vm";
			System.out.println(testfile);
			
			GPSData gpsdata = GPSDataReaderWriter.readGPSFile(testfile);
			
			converter = new GPSDataConverter(gpsdata);
			converter.convert();
			converter.print();
			
		}
}
