package no.hvl.dat100.prosjekt.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import no.hvl.dat100.prosjekt.GPSData;
import no.hvl.dat100.prosjekt.GPSComputer;
import no.hvl.dat100.prosjekt.GPSDataConverter;
import no.hvl.dat100.prosjekt.GPSDataReaderWriter;

public class GPSComputerTester {

	private String[] times = 
		{"2017-08-13T08:00:00.000Z", "2017-08-13T08:00:10.000Z", "2017-08-13T08:00:40.000Z", 
		 "2017-08-13T08:01:10.000Z", "2017-08-13T08:01:20.000Z"};
	
	private String[] latitudes =  {"60.376988", "60.385390", "60.379527",  "60.385390", "60.376988"};
	private String[] longitudes = { "5.227082",  "5.217217",  "5.3227322", "5.217217", "5.227082"};

	private String[] elevations = { "10", "20", "10", "40", "50"};
	
	private int EXP_TOTALTIME = 1 * 60 + 20;
	private double EXP_TOTALDISTANCE = 1080 + 5835 + 5835 + 1080;
	private double EXP_TOTALELEVATION = 10 + 30 + 10;
	private double[] EXP_SPEEDS = 
		{(108.0 * 60 * 60) / 1000, (194.5 * 60 * 60) / 1000, (194.5 * 60 * 60) / 1000, (108.0 * 60 * 60) / 1000 };
	private double EXP_MAXSPEED = (194.5 * 60 * 60 / 1000) ;
	private double EXP_AVERAGESPEED = ((EXP_TOTALDISTANCE / EXP_TOTALTIME) * 60 * 60) / 1000;
			
	private GPSData gpsdata = new GPSData(times,latitudes,longitudes,elevations);
	GPSComputer gpscomp;
	
	@Before
	public void SetUp() {
		gpscomp = new GPSComputer(gpsdata);
	}
	
	@Test
	public void testTotalDistance() {
		assertEquals("Total distance",EXP_TOTALDISTANCE,gpscomp.totalDistance(),1.0);
	}
	
	@Test
	public void testTotalElevation() {
		assertEquals("Total elevation",EXP_TOTALELEVATION,gpscomp.totalElevation(), 0);
	}

	@Test
	public void testTotalTime() {
		assertEquals("Total time",EXP_TOTALTIME,gpscomp.totalTime());
	}
	@Test
	public void testSpeeds() {
		assertArrayEquals("speed",EXP_SPEEDS,gpscomp.speeds(),0.1);
	}
	
	@Test
	public void testMaxSpeed() {
		assertEquals("maxSpeed",EXP_MAXSPEED,gpscomp.maxSpeed(),0.1);
	}
	
	@Test
	public void testAverageSpeed() {
		assertEquals("averageSpeed",EXP_AVERAGESPEED,gpscomp.averageSpeed(),0.1);
	}
	
	// TODO test for kcal and totalkcal
	@Test
	public void testkcal() {
		assertEquals("kcal",8.0/3600.0,gpscomp.kcal(1.0, 1, 13.0/GPSComputer.MS),0.1);
		assertEquals("kcal",2*8.0/3600.0,gpscomp.kcal(2.0, 1, 13.0/GPSComputer.MS),0.1);
		assertEquals("kcal",3*2*8.0/3600.0,gpscomp.kcal(3.0, 2, 13.0/GPSComputer.MS),0.1);
	}
	
	// TODO: factor out common parts in the tests below
	@Test
	public void test_Printshort() {
		String testfile = "short";
		System.out.println("GPS datafile: " + testfile);
		
		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(testfile);
		
		gpscomp = new GPSComputer(gpsdata);
		
		gpscomp.print();
	}
	
	@Test
	public void test_Printmedium() {
		String testfile = "medium";
		System.out.println("GPS datafile: " + testfile);
		
		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(testfile);
		
		gpscomp = new GPSComputer(gpsdata);
		
		gpscomp.print();
		
	}
	
	@Test
	public void test_Printlong() {
		String testfile = "long";
		System.out.println("GPS datafile: " + testfile);
		
		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(testfile);
		
		gpscomp = new GPSComputer(gpsdata);
		
		gpscomp.print();
	}
	
	@Test
	public void test_Printlvm() {
		String testfile = "vm";
		System.out.println("GPS datafile: " + testfile);
		
		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(testfile);
		
		gpscomp = new GPSComputer(gpsdata);
		
		gpscomp.print();
	}
}
