package no.hvl.dat100.prosjekt.test;

import static org.junit.Assert.*;

import org.junit.Test;

import no.hvl.dat100.prosjekt.GPSUtils;

public class GPSUtilsTester {

	@Test
	public void test_printDouble() {
		assertEquals("printDouble","      1.35",GPSUtils.printDouble(1.346));
	}
	
	@Test
	public void testPrintTime() {
		assertEquals("printTime","00:00:09",GPSUtils.printTime(9));
		assertEquals("printTime","00:01:22",GPSUtils.printTime(82));
		assertEquals("printTime","03:02:01",GPSUtils.printTime(60 * 60 * 3 + 60 * 2 + 1));
	}
	
	@Test
	public void testFindMax() {
		double[] damiddle = { 1.0, 2.0, 7.0, 3.0};
		double[] dastart =  { 9.0, 1.0, 2.0, 6.0};
		double[] daend =    { 1.0, 2.0, 7.0, 10.0};
		
		assertEquals("findMax",7,GPSUtils.findMax(damiddle),0);
		assertEquals("findMax",9,GPSUtils.findMax(dastart),0);
		assertEquals("findMax",10,GPSUtils.findMax(daend),0);
		
	}
	
	@Test
	public void testFindMin() {
		double[] dastart = { 1.0, 2.0, 7.0, 3.0};
		double[] damiddle =  { 9.0, 1.0, 2.0, 6.0};
		double[] daend =    { 2.0, 2.0, 7.0, 1.0};
		
		assertEquals("findMin",1,GPSUtils.findMin(damiddle),0);
		assertEquals("findMin",1,GPSUtils.findMin(dastart),0);
		assertEquals("findMin",1,GPSUtils.findMin(daend),0);
		
	}
	
	@Test
	public void testDistanceLong1() {
		assertEquals("GPS Long Distance 1", 5835, GPSUtils.distance(60.385390, 5.217217, 60.379527, 5.3227322), 1.0);

	}
	
	@Test
	public void testDistanceLong2() {
		assertEquals("GPS Long Distance 2", 5835, GPSUtils.distance(60.379527, 5.3227322, 60.385390, 5.217217), 1.0);

	}

	@Test
	public void testDistanceShort1() {
		assertEquals("GPS Short Distance 2", 1080, GPSUtils.distance(60.385390, 5.217217, 60.376988, 5.227082), 1.0);
	}

	@Test
	public void testDistanceShort2() {
		assertEquals("GPS Short Distance 2", 1080, GPSUtils.distance(60.376988, 5.227082, 60.385390, 5.217217), 1.0);
	}
	
	@Test
	public void testDistanceZero() {
		assertEquals("GPS Short Distance 2", 0, GPSUtils.distance(60.376988, 5.227082, 60.376988, 5.227082), 0.01);
	}
	
	@Test
	public void testSpeed() {
		assertEquals("GPS Speed",(108.0 * 60 * 60)/1000 ,GPSUtils.speed(10,60.385390, 5.217217, 60.376988, 5.227082),1.0);
	}
}