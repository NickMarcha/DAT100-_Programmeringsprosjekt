package no.hvl.dat100.prosjekt;

import static java.lang.Math.*;

public class GPSUtils {

	public GPSUtils() {
	
	}
	
	// konverter sekunder til string på formen hh:mm:ss
	public static String printTime(int secs) {
		
		String timestr = "";
		String TIMESEP = ":";
		
		// TODO
		// OPPGAVE - START
		
		int secondsInAnHour = 60 * 60;
		int secondsInAMinute = 60;

		int Hours   = (secs)/(secondsInAnHour);
		int Minutes = (secs%(secondsInAnHour))/secondsInAMinute;
		int Seconds = (secs%(secondsInAnHour))%secondsInAMinute;
		
		timestr = String.format("%02d", Hours) + TIMESEP +
				String.format("%02d", Minutes) + TIMESEP +
				String.format("%02d", Seconds); 
		// OPPGAVE - SLUTT
		
		return timestr;
	}
	
	// beregn maximum av en tabell av doubles med minnst et element
	public static double findMax(double[] da) {

		double max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max; 
	}

	// beregn minimum av en tabell av doubles med minnst et element
	public static double findMin(double[] da) {

		// fjern = "0.0" når metoden implementeres for ikke få forkert minimum
		double min =  da[0]; 

		// TODO
		// OPPGAVE - START
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		// OPPGAVE - SLUT
		return min;
	}

	
	private static int R = 6371000; // jordens radius
	
	// Beregn avstand mellom to gps punkter ved bruk av Haversine formlen
	public static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {

		double a,c,d = 1.0; // fjern = 1.0
		
		double R = 6371000; //  meter er jordens gjennomsnittsradius.
		// TODO:
		// OPPGAVE - START
		double phi1 = Math.toRadians(latitude1);
		double phi2 = Math.toRadians(latitude2);
		double deltaPhi = phi2 - phi1;
		double deltaLambda = Math.toRadians(longitude1) - Math.toRadians(longitude2);
		a = Math.pow(Math.sin(deltaPhi/2),2) + (Math.cos(phi1) * Math.cos(phi2) * (Math.pow(Math.sin(deltaLambda / 2), 2)));
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		d = R * c;
		// OPPGAVE - SLUTT

		return d;
	}
	
	// beregn gjennomsnits hastighet i km/t mellom to gps punkter
	public static double speed(int secs, double latitude1, double longitude1, double latitude2, double longitude2) {

		double speed = 0.0;

		// TODO:
		// OPPGAVE - START
		double mDistance = distance(latitude1, longitude1, latitude2, longitude2);
		
		speed = mDistance/secs * 3.6;
		
		// OPPGAVE - SLUTT

		return speed;
	}
	
	private static int TEXTWIDTH = 10;
	
	// konverter double til string med 2 decimaler og streng lengde 10
	// eks. 1.346 konverteres til "      1.35" og enhet til slutt
	// Hint: se på String.format metoden
	
	public static String printDouble(double d) {
		
		String str = "";
		
		// TODO
		// OPPGAVE - START
		str = "      "+String.format("%.2f", d);
		// OPPGAVE - SLUTT
		
		return str;
	}
}
