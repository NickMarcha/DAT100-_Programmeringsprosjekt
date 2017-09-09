package no.hvl.dat100.prosjekt;

import javax.swing.JOptionPane;

import easygraphics.*;

public class CycleComputer extends EasyGraphics {

	private static int[] times;
	private static double[] latitudes;
	private static double[] longitudes;
	private static double[] elevations;

	private static int SPACE = 20;
	private static int MARGIN = 50;
	private static int ROUTEMAPXSIZE = 800; 
	private static int ROUTEMAPYSIZE = 400;
	private static int HEIGHTSIZE = 300;
	private static int TEXTHEIGHT = 80;

	private static GPSComputer gpscomputer;
	private int N = 0;

	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;

	public CycleComputer() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");

		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(filename);

		gpscomputer = new GPSComputer(gpsdata);

		times = gpscomputer.times;
		latitudes = gpscomputer.latitudes;
		longitudes = gpscomputer.longitudes;
		elevations = gpscomputer.elevations;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		N = times.length; // number of gps points

		minlon = GPSUtils.findMin(longitudes);
		minlat = GPSUtils.findMin(latitudes);

		maxlon = GPSUtils.findMax(longitudes);
		maxlat = GPSUtils.findMax(latitudes);

		xstep = xstep();
		ystep = ystep();

		makeWindow("Cycle Computer", 2 * MARGIN + ROUTEMAPXSIZE,
				TEXTHEIGHT + MARGIN + ROUTEMAPYSIZE + HEIGHTSIZE + 2 * SPACE);

		bikeRoute();

	}

	// løp igjennom punkter på uten og visualiser løpende høyde, position og
	// statisikk
	public void bikeRoute() {

		// TODO
	}

	public double xstep() {

		double xstep = 0.0;

		// TODO

		return xstep;
	}

	public double ystep() {

		double ystep = 0.0;

		// TODO

		return ystep;
	}

	// beregn og vis statistikk eks. hastighet nå vi er kommet til punkt i på
	// ruten
	public void showCurrent(int i) {
		setColor(0, 0, 0);

		// TODO

	}

	// tegn høydepunkt nr. i
	public void showHeight(int ybase, int i) {

		// TODO

	}

	// tegn punkt nr. i på kartet/vinduet
	public void showPosition(int i) {

		// TODO

	}
}
