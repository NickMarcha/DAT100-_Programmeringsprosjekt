package no.hvl.dat100.prosjekt;

import easygraphics.EasyGraphics;
import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static int[] times;
	private static double[] latitudes;
	private static double[] longitudes;
	private static double[] elevations;

	private static int MARGIN = 50;
	private static int BARHEIGHT = 500; // assume no height above 500 meters
	private static int SPACING = 2;
	private static int UNIT_WIDTH = 1;
	private static int UNIT_HEIGHT = 1;
	
	private static GPSData gpsdata;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpsdata = GPSDataReaderWriter.readGPSFile(filename);

		GPSDataConverter converter = new GPSDataConverter(gpsdata);
		converter.convert();

		times = converter.times;
		latitudes = converter.latitudes;
		longitudes = converter.longitudes;
		elevations = converter.elevations;
	}

	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = elevations.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN * UNIT_WIDTH+ SPACING * N, 2 * MARGIN + BARHEIGHT * UNIT_HEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + BARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {


		System.out.println("Angi tidsskalering i tegnevinduet ...");
		
		int timescaling = Integer.parseInt(getText("Tidsskalering"));

		
		//horizontal line
		setColor(0,0,0);
		drawLine(0,ybase, 2 * MARGIN * UNIT_WIDTH+ SPACING * elevations.length, ybase);
		//vertical line
		setColor(0,0,0);
		drawLine(MARGIN * UNIT_WIDTH,0 ,MARGIN * UNIT_WIDTH , 2 * MARGIN + BARHEIGHT * UNIT_HEIGHT);
		
		for(int i = 0; i <= 50; i ++) {
			int height = ybase- (i*10);
			if(i%10 == 0) {
				drawString(i * 10 +"m",MARGIN * UNIT_WIDTH -30,height);
				drawLine(MARGIN * UNIT_WIDTH, height,MARGIN * UNIT_WIDTH -30,height);
			}else {
				drawLine(MARGIN * UNIT_WIDTH, height,MARGIN * UNIT_WIDTH -10,height);
			}
		}
		
		
		setColor(0, 0, 255);
		

		// elevations tabellen innholder alle høydedata
		for (int i = 0; i < elevations.length; i++) {

			int height = (int)elevations[i];
			
			if(height <= 0) continue;
			else if(height > BARHEIGHT) height = BARHEIGHT;
			
			int x1, y1, x2, y2; // koordinator søylen 
			double thistime;
			if(i > 0) {
			 thistime = times[i] - times[i -1];
			}else {
				thistime= 0;
			}
			pause((int)(thistime/ timescaling* 1000));

			// TODO
			// OPPGAVE - START
			x1 = MARGIN * UNIT_WIDTH  + i*SPACING;
			y1 = ybase * UNIT_HEIGHT - (height * UNIT_HEIGHT);
			x2 = UNIT_WIDTH;// * timescaling;
			y2 = height * UNIT_HEIGHT;
			// regn ut koordinator for søylen / linjen
			// (x1,y1) er startpunkt for søylen (linjen)
			// (x2,y2) er slutt punkt.

			fillRectangle(x1,y1, x2,y2);
			// OPPGAVE - SLUTT
			
		}	
	}

}
