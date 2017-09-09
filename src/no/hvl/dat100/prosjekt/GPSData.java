package no.hvl.dat100.prosjekt;

public class GPSData {

	private String[] times;
	private String[] latitudes;
	private String[] longitudes;
	private String[] elevations;
	
	public GPSData() {
		
	}

	public GPSData(String[] times, String[] lattitudes, String[] longitudes, String[] elevations) {
		super();
		this.times = times;
		this.latitudes = lattitudes;
		this.longitudes = longitudes;
		this.elevations = elevations;
	}


	public String[] getTimes() {
		return times;
	}

	public void setTimes(String[] times) {
		this.times = times;
	}

	public String[] getLattitudes() {
		return latitudes;
	}

	public void setLattitudes(String[] lattitudes) {
		this.latitudes = lattitudes;
	}

	public String[] getLongitudes() {
		return longitudes;
	}

	public void setLongitudes(String[] longitudes) {
		this.longitudes = longitudes;
	}

	public String[] getElevations() {
		return elevations;
	}

	public void setElevations(String[] elevations) {
		this.elevations = elevations;
	}

}
