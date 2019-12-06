
public class Sensor {
	private String name;
	private long datarate;
	private int minResolution;
	private int maxResolution;
	private double ADCPowerConsumption;
	
	public Sensor (String name, long datarate, int minResolution, int maxResolution, double ADCPowerConsumption)
	{
		this.name = name;
		this.datarate = datarate;
		this.minResolution = minResolution;
		this.maxResolution = maxResolution;
		this.ADCPowerConsumption = ADCPowerConsumption;
	}
	
	

	public String getName() {
		return name;
	}



	public long getDatarate() {
		return datarate;
	}



	public int getMinResolution() {
		return minResolution;
	}



	public int getMaxResolution() {
		return maxResolution;
	}



	public double getADCPowerConsumption() {
		return ADCPowerConsumption;
	}



	public void setName(String name) {
		this.name = name;
	}

	public void setDatarate(long datarate) {
		this.datarate = datarate;
	}

	public void setMinResolution(int minResolution) {
		this.minResolution = minResolution;
	}

	public void setMaxResolution(int maxResolution) {
		this.maxResolution = maxResolution;
	}

	public void setADCPowerConsumption(double aDCPowerConsumption) {
		ADCPowerConsumption = aDCPowerConsumption;
	}
	
	

}
