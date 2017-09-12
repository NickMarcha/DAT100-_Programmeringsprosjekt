package no.hvl.dat100.prosjekt;

public class GPSComputer {
	
	public GPSComputer(GPSData gpsdata) {

		GPSDataConverter converter = new GPSDataConverter(gpsdata);
		converter.convert();

		this.times = converter.times;
		this.latitudes = converter.latitudes;
		this.longitudes = converter.longitudes;
		this.elevations = converter.elevations;
	}

	// tabeller for GPS datapunkter
	public int[] times;
	public double[] latitudes;
	public double[] longitudes;
	public double[] elevations;
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO
		// OPPGAVE - START

		// Hint: bruk distance-metoden fra GPSUtils.
		
		// OPPGAVE - SLUTT

		return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO
		// OPPGAVE - START

		// OPPGAVE - SLUTT
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		int totaltime = 0;
		
		// TODO 
		// OPPGAVE START
		
		// OPPGAVE SLUTT
		
		return totaltime;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {

		double[] speeds = new double[times.length-1];
		
		// TODO
		// OPPGAVE - START
		
		// OPPGAVE - SLUTT
		return speeds;
	}

	// beregn maximum hastighet km/t
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO
		// OPPGAVE - START
				
		// OPPGAVE - SLUTT
		
		return maxspeed;
	}
	
	// beregn gjennomsnittshasitiget for hele turen km/t
	public double averageSpeed() {

		double average = 0;
		
		// TODO
		// OPPGAVE - START
				
		// OPPGAVE - SLUTT
		
		return average;
	}


	// conversion factor kph (km/t) to miles per hour (mph)
	public static double MS = 0.62;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal = 0;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO
		// OPPGAVE START
		
		// Energy Expended (kcal) = MET x Body Weight (kg) x Time (h)

		// OPPGAVE SLUTT
		
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO
		// OPPGAVE - START 
		
		// Hint: hent hastigheter i speeds tabellen og tider i timestabellen
		// disse er definer i toppen av klassen og lese automatisk inn
		
		// OPPGAVE - SLUTT
		
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	// skriv ut statistikk for turen
	public void print() {
		
		// TODO
		// OPPGAVE - START
				
		// OPPGAVE - SLUT
	}
	
	// ekstraoppgaver
	public void climbs() {
		
	}
	
	public void maxClimb() {
		
	}
}
