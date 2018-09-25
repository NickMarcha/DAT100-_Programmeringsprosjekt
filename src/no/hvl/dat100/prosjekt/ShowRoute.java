package no.hvl.dat100.prosjekt;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;

public class ShowRoute extends EasyGraphics {

	/**
	 * For � fjerne en warning lager jeg en Universal identifier
	 */
	private static final long serialVersionUID = 904813051469820352L;
	
	private static int[] times;
	private static double[] latitudes;
	private static double[] longitudes;
	private static double[] elevations;
 
	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private static GPSComputer gpscomputer;
	private static GPSData thisGpsData;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");

		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(filename);

		
		gpscomputer = new GPSComputer(gpsdata);
		thisGpsData = gpsdata;
		times = gpscomputer.times;
		latitudes = gpscomputer.latitudes;
		longitudes = gpscomputer.longitudes;
		elevations = gpscomputer.elevations;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		playRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(longitudes);
		double minlon = GPSUtils.findMin(longitudes);

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// y-pixels per breddegrad
	
	public double ystep() {
	
		double ystep = 1.0;
		
		// TODO
		// OPPGAVE - START
		
		double maxlat = GPSUtils.findMax(latitudes);
		double minlat = GPSUtils.findMin(latitudes);

		ystep = MAPXSIZE / (Math.abs(maxlat - minlat)); 
		
		// OPPGAVE SLUTT
		
		return ystep;
	}

	public void showRouteMap(int ybase) {
		
		double xstep = xstep();
		double ystep = ystep();

		double minlon = GPSUtils.findMin(longitudes);
		double minlat = GPSUtils.findMin(latitudes);

		setColor(0, 255, 0); // green

		// draw the locations
		
		int lastX = 0;
		int lastY = 0;
		for (int i = 0; i < latitudes.length; i++) {

			int x,y;

			// TODO: OPPGAVE START
			
			// må finne punkt nr i fra latitues og longitudes tabellene
			// og sette x og y til der de skal tegnes som et punkt i vinduet
			
			x = MARGIN + (int) ((longitudes[i] - minlon) * xstep);
			y = ybase - (int) ((latitudes[i] - minlat) * ystep);
			//((longitudes[0] - minlon) * xstep);
			//int y = ybase - (int) ((latitudes[0] - minlat) * ystep);
			fillCircle(x,y,3);
			// OPPGAVE SLUTT
			
			if(i > 0) {
				drawLine(lastX, lastY, x, y);
			}
			lastX = x;
			lastY = y;
	}
		

	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO:
		// OPPGAVE - START
		gpscomputer.climbs();
		String[] outPutStrings = new String[] {
				"GPS datafile: " + thisGpsData.getName(),
				"Total Time     : " + String.format("%1$12s", GPSUtils.printTime(gpscomputer.totalTime())),
				"Total distance : " + String.format("%1$12s", GPSUtils.printDouble(gpscomputer.totalDistance()/1000)) + " km",
				"Total elevation: " + String.format("%1$10s", GPSUtils.printDouble(gpscomputer.totalElevation())) + " m",
				"Max speed      : " + String.format("%1$12s", GPSUtils.printDouble(gpscomputer.maxSpeed())) + " km/t",
				"Average speed  : " + String.format("%1$12s", GPSUtils.printDouble(gpscomputer.averageSpeed())) + " km/t",
				"Energy         : " + String.format("%1$12s", GPSUtils.printDouble(gpscomputer.totalKcal(gpscomputer.WEIGHT))),
				"Max Climb      : " +  String.format("%1$12s", GPSUtils.printDouble(gpscomputer.maxClimb())) + "%"
		};
		
		for(int i = 0; i < outPutStrings.length; i++) {
			drawString(outPutStrings[i],TEXTDISTANCE,TEXTDISTANCE*(i+1));
		}
		
	}

	public void playRoute(int ybase) {
		double xstep = xstep();
		double ystep = ystep();

		double minlon = GPSUtils.findMin(longitudes);
		double minlat = GPSUtils.findMin(latitudes);
		setColor(0, 0, 255); // blue;

		// make a circle in the first point
		int x = MARGIN + (int) ((longitudes[0] - minlon) * xstep);
		int y = ybase - (int) ((latitudes[0] - minlat) * ystep);

		int movingcircle = fillCircle(x, y, 7);

		// TODO: 
		// EKSTRAOPPGAVE -- START

		for(int i = 0; i < longitudes.length; i++) {
			x = MARGIN + (int) ((longitudes[i] - minlon) * xstep);
			y = ybase - (int) ((latitudes[i] - minlat) * ystep);
			setSpeed(1);
			pause(50);
			moveCircle(movingcircle, x, y);
		}
		// Få cirklen til å flytte seg mellom punktene i vinduet
		
		// EKSTRAOPPGAVE - SLUTT
	}

}
