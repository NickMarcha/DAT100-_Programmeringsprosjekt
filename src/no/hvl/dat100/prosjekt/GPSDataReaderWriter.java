package no.hvl.dat100.prosjekt;

import java.io.*;

public class GPSDataReaderWriter {

	// entry separator in CSV files
	private static String SEP_STR = ",";

	private static  String GPSDATA_FORMAT = "time,lat,lon,elevation,accuracy,bearing,speed,satellites,"
			+ "provider,hdop,vdop,pdop,geoidheight,ageofdgpsdata,dgpsid,activity,battery,annotation";

	// location of GPS data files in this Eclipse project
	private static String GPSLOGS_DIR = System.getProperty("user.dir") + "/logs/";

	public static GPSData readGPSFile(String filename) {

		BufferedReader br = null;
		GPSData gpsdata = null;

		String time, latitude, longitude, elevation;
		String[] times, latitudes, longitudes, elevations;

		try {

			br = new BufferedReader(new FileReader(GPSLOGS_DIR + filename + ".csv"));

			String line = br.readLine();

			// first line specifies number of entries in the gps data file
			int n = Integer.parseInt(line);

			// allocate arrays for the right number of entries
			times = new String[n];
			latitudes = new String[n];
			longitudes = new String[n];
			elevations = new String[n];

			// skip the description line by simply reading it
			line = br.readLine();

			int i = 0;

			line = br.readLine();

			while (line != null && i < n) {

				// split log entry
				String[] gpsdatapoint = line.split(SEP_STR);

				time = gpsdatapoint[0];
				latitude = gpsdatapoint[1];
				longitude = gpsdatapoint[2];
				elevation = gpsdatapoint[3];

				times[i] = time;
				latitudes[i] = latitude;
				longitudes[i] = longitude;
				elevations[i] = elevation;

				// try reading next line
				line = br.readLine();
				i++;
			}

			gpsdata = new GPSData();

			gpsdata.setTimes(times);
			gpsdata.setLattitudes(latitudes);
			gpsdata.setLongitudes(longitudes);
			gpsdata.setElevations(elevations);
			gpsdata.setName(filename);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return gpsdata;
	}

	public static void writeGPSFile(String filename, GPSData gpsdata) {

		PrintWriter pw = null;

		try {

			pw = new PrintWriter(new FileWriter(GPSLOGS_DIR + filename + ".csv"));

			// tabell med tidspunkter fra gps data punktene
			String[] times = gpsdata.getTimes();

			// tabell med breddegrader fra gps data punktene
			String[] latitudes = gpsdata.getLattitudes();

			// tabell med lengdegrader fra gps data punktene
			String[] longitudes = gpsdata.getLongitudes();

			// tabell med høyder fra gps data punktene
			String[] elevations = gpsdata.getElevations();

			// første linje skal inneholde antallet av gps punkter
			int n = times.length;
			pw.println(n);

			// andre linje skal inneholde en streng som beskriver GPS data
			// formatet
			pw.println("time,latitude,longitude,elevation");

			for (int i = 0; i < n; i++) {
				pw.println(times[i] + SEP_STR + latitudes[i] + SEP_STR + longitudes[i] + SEP_STR + elevations[i]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	public static void printGPSData(GPSData gpsdata) {

		// tabell med tidspunkter fra gps data punktene
		String[] times = gpsdata.getTimes();

		// tabell med breddegrader fra gps data punktene
		String[] latitudes = gpsdata.getLattitudes();

		// tabell med lengdegrader fra gps data punktene
		String[] longitudes = gpsdata.getLongitudes();

		// tabell med høyder fra gps data punktene
		String[] elevations = gpsdata.getElevations();

		// første linje skal inneholde antallet av gps punkter
		int n = times.length;
		System.out.println(n);

		// andre linje skal inneholde en streng som beskriver GPS data formatet
		System.out.println("time,latitude,longitude,elevation");

		// TODO
		// OPPGAVE - START
		
		
		// skriv ut også breddegrad (latitude), longitude(lengdegrad) og høyde (elevation) for hvert punkt
		
		for (int i = 0; i < n; i++) {
			System.out.println(times[i] + SEP_STR + latitudes[i] + SEP_STR + longitudes[i] + SEP_STR + elevations[i]);
		
		}

		// OPPGAVE - SLUTT;
	}

	// kjør programmet via Run for å teste utskrift av GPS data 
	public static void main(String[] args) {

		String testfile = "short";
		// fjern kommentar for å teste med ulike GPS data filer
		// String testfile = "medium";
		// String testfile = "long";
		// String testfile = "vm";
		
		GPSData gpsdata = readGPSFile(testfile);

		
		printGPSData(gpsdata);
		
		GPSComputer testcomp = new GPSComputer(gpsdata);
		testcomp.print();
	}

}