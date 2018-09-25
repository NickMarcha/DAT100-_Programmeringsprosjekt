package no.hvl.dat100.prosjekt;

public class GPSDataConverter {

	// arrays for GPS data in the original representation as array of strings
	private String[] timesstr, latitudesstr, longitudesstr, elevationsstr;
			
	// arrays for GPS data that we would like to transform to
	// public to simplify later code and since get/set methods have not yet been covered in the course
	public int[] times;
	public double[] latitudes, longitudes, elevations;
	
	public GPSDataConverter(GPSData gpsdata) {
		
		// initialize the arrays for the original representation
		timesstr = gpsdata.getTimes();
		latitudesstr = gpsdata.getLattitudes();
		longitudesstr = gpsdata.getLongitudes();
		elevationsstr = gpsdata.getElevations();
	}

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	public static int TIME_STARTINDEX = 11; // startindex for tidspunkt

	public static int toSeconds(String timestr) {
		
		int secs = 0;
		int hr, min, sec;
		
		// TODO
		// OPPGAVE - START
		hr  = Integer.parseInt( timestr.substring(TIME_STARTINDEX, 13));
		min = Integer.parseInt( timestr.substring(TIME_STARTINDEX+3, 16));
		sec = Integer.parseInt( timestr.substring(TIME_STARTINDEX+6, 19));
		secs = (hr * 60 *60) + (min *60) + sec;
		// OPPGAVE - SLUTT
		return secs;
	}

	// konverter representation av data fra strenger (String) til tall
	public void convert() {

		// antall GPS datapunkter
		int n = timesstr.length;

		// tabeller for konvertert gps data
		times = new int[n];			// ny tabell for tidsinformasjon i sekunder (int)
		latitudes = new double[n];  // ny tabell for breddegrad (double)
		longitudes = new double[n]; // ny tabell for lengdegrad (double)
		elevations = new double[n]; // ny tabell for høyde (double)

		// tabeller som skal konverteres fra er
		// timesstr, latitudesstr, longitudesstr, elevationsstr erklært først i klassen
		
		// TODO
		// OPPGAVE - START
		for (int i = 0; i < n; i++) {
			times[i] = toSeconds(timesstr[i]);
			latitudes[i] = Double.parseDouble( latitudesstr[i]);
			longitudes[i] = Double.parseDouble( longitudesstr[i]);
			elevations[i] = Double.parseDouble( elevationsstr[i]);
		}
		
		// Hint:
		// iterer igjennom alle gps punkter (hint: bruk en for-løkke)
		// konverter hver inngang gps datapunkt 
		// - tidsinformasjon til sekunder (int) (hint: bruk toSeconds)
		// - breddegrad til double (hint: bruk Double.parseDouble)
		// - lengdegrad til double 
		// - høyde til double
        // sett konvertert data inn på rett plass i den rette tabellen
		// END
		// OPPGAVE - SLUTT ;
		
	}
	
	// skriv ut konvertert GPS data op formatet:
	// sekunder (breddegrad,lengdegrad) høyde
	public void print() {
	
		System.out.println("Konvertert GPS Data");
		// TODO
		// OPPGAVE - START
		for(int i = 0; i < times.length; i++) {
				System.out.println(times[i] + " (" + latitudes[i] + ", " + longitudes[i] + ") " + elevations[i]);
		}
		// OPPGAVE - SLUTT
	}
}
