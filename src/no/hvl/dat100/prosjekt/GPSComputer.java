package no.hvl.dat100.prosjekt;

public class GPSComputer {
	
	public GPSComputer(GPSData gpsdata) {

		GPSDataConverter converter = new GPSDataConverter(gpsdata);
		converter.convert();

		this.times = converter.times;
		this.latitudes = converter.latitudes;
		this.longitudes = converter.longitudes;
		this.elevations = converter.elevations;
		this.thisGpsData = gpsdata;
	}

	// tabeller for GPS datapunkter
	public int[] times;
	public double[] latitudes;
	public double[] longitudes;
	public double[] elevations;
	GPSData thisGpsData;
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO
		// OPPGAVE - START

		for (int i = 0; i < times.length -1; i++) {
			distance += GPSUtils.distance(latitudes[i], longitudes[i], latitudes[i+1], longitudes[i + 1]);
		}
		// Hint: bruk distance-metoden fra GPSUtils.
		
		// OPPGAVE - SLUTT

		return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO
		// OPPGAVE - START
		for(int i = 0; i < times.length -1; i++) {
			double deltaElevation = elevations[i+1] - elevations[i];
			if(deltaElevation > 0) {
				elevation += deltaElevation;
			}
		}

		// OPPGAVE - SLUTT
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		int totaltime = 0;
		
		// TODO 
		// OPPGAVE START
		for (int i = 0; i < times.length -1; i++) {
			totaltime += (times[i+1] - times[i]) ;
		}
		// OPPGAVE SLUTT
		
		return totaltime;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {

		double[] speeds = new double[times.length-1];
		
		// TODO
		// OPPGAVE - START
		
		for(int i = 0; i < speeds.length; i++) {
			speeds[i] = GPSUtils.speed(times[i +1] - times[i], latitudes[i], longitudes[i], latitudes[i + 1], longitudes[i +1 ]);
		}
	
		// OPPGAVE - SLUTT
		return speeds;
	}

	// beregn maximum hastighet km/t
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO
		maxspeed = GPSUtils.findMax(speeds());
		
		return maxspeed;
	}
	
	// beregn gjennomsnittshasitiget for hele turen km/t
	public double averageSpeed() {

		double average = 0;
		
		// TODO
		// OPPGAVE - START
		double totalDistance = this.totalDistance();
		double totalTime = times[times.length -1]- times[0];
		
		average = totalDistance / totalTime * 3.6;
				
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
		double[] maxSpeedSteps = new double[] {
				 0,10, 12,  14,  16, 20, Integer.MAX_VALUE
		};
		double[] METSteps = new double[] {
				 0, 4.0, 6.0, 8.0, 10.0,12.0, 16.0 
		};
		
		for (int i = 0; i < maxSpeedSteps.length; i++) {
			if(speedmph >= maxSpeedSteps[i]) {
				met = METSteps[i + 1];
				
			}
		}
		
		// Energy Expended (kcal) = MET x Body Weight (kg) x Time (h)
		//System.out.println(weight);
		
		kcal = met * weight * (secs/ (60.0 * 60.0));// * (secs/ 60 /60);
		//Energy         :     742.80 kcal
		//System.out.println("Calculated: " + kcal+" kcal with met: " + met+ " And speed : " + speedmph);
		// OPPGAVE SLUTT
		
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO
		// OPPGAVE - START 
		
		// Hint: hent hastigheter i speeds tabellen og tider i timestabellen
		// disse er definer i toppen av klassen og lese automatisk inn
		double[] allSpeeds = speeds();
		
		for	(int i = 0; i < allSpeeds.length; i++) {
			totalkcal += kcal(weight, times[i +1] - times[i], allSpeeds[i]);
			//System.out.println(i+" "+ kcal(weight, times[i +1] - times[i], allSpeeds[i]));
		}
		// OPPGAVE - SLUTT
		
		return totalkcal;
	}
	
	public double WEIGHT = 80.0;
	
	// skriv ut statistikk for turen
	public void print() {
		
		// TODO
		// OPPGAVE - START
		String outputString = new String();
		outputString+= "GPS datafile: " + thisGpsData.getName() + "\n";
		outputString+= "Total Time     : " + String.format("%1$12s", GPSUtils.printTime(this.totalTime())) + "\n";
		outputString+= "Total distance : " + String.format("%1$12s", GPSUtils.printDouble(this.totalDistance()/1000)) + " km \n";
		outputString+= "Total elevation: " + String.format("%1$10s", GPSUtils.printDouble(this.totalElevation())) + " m \n";
		outputString+= "Max speed      : " + String.format("%1$12s", GPSUtils.printDouble(this.maxSpeed())) + " km/t \n";
		outputString+= "Average speed  : " + String.format("%1$12s", GPSUtils.printDouble(this.averageSpeed())) + " km/t \n";
		outputString+= "Energy         : " + String.format("%1$12s", GPSUtils.printDouble(this.totalKcal(WEIGHT))) + " kcal";
		
		
		System.out.println(outputString.replace(",", "."));
		// OPPGAVE - SLUT
		/*
		GPS datafile: medium
		Total Time     :   00:36:35
		Total distance :      13.74 km
		Total elevation:     210.60 m
		Max speed      :      47.98 km/t
		Average speed  :      22.54 km/t
		Energy         :     742.80 kcal
		*/
	}
	
	// ekstraoppgaver
	
	double[] slopePercentages;
	
	public void climbs() {
		// (meter klatret / lengde)* 100 = prosent
		slopePercentages = new double[elevations.length -1];
		
		for	(int i = 0; i < slopePercentages.length; i++) {
			double thisElevation = elevations[i +1] - elevations[i];
			double thisDistance = GPSUtils.distance(latitudes[i], longitudes[i], latitudes[i +1], longitudes[i +1]);
			slopePercentages[i] = (thisElevation/thisDistance) *10;
			System.out.println(slopePercentages[i]);
		}
	}
	
	public double maxClimb() {
		return GPSUtils.findMax(slopePercentages);
	}
	
}
