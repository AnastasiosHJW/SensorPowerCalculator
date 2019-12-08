import java.util.ArrayList;

public class Device {

	private ArrayList<Sensor> sensorList;
	
	public Device()
	{
		sensorList = new ArrayList<Sensor>();
	}
	
	public Device(String deviceInfo)
	{	
		sensorList = new ArrayList<Sensor>();
		String[] splitInfo = deviceInfo.split("\n");
		for (int i=0;i< splitInfo.length; i++)
		{
			String[] sensorInfo = splitInfo[i].split(" # ");
			sensorList.add(new Sensor(sensorInfo[0], Long.parseLong(sensorInfo[1]), Integer.parseInt(sensorInfo[2]), 
										Integer.parseInt(sensorInfo[3]), Double.parseDouble(sensorInfo[4])));
		}
	}
	
	public void addSensor(Sensor sensor)
	{
		sensorList.add(sensor);
	}
	
	public void removeSensor(String sensorName)
	{
		for (int i=0; i<sensorList.size(); i++)
		{
			if (sensorList.get(i).getName().equals(sensorName))
			{
				sensorList.remove(i);
				break;
			}
		}
	}
	
	public int getSensorListSize()
	{
		return sensorList.size();
	}
	
	public Sensor getSensor(int i)
	{
		return sensorList.get(i);
	}
	
	public void clearList()
	{
		sensorList.clear();
	}
	
	public double getWirelessTransmissionPower()
	{
		double transmissionPower = 0;
		for (int i=0; i<sensorList.size(); i++)
		{
			transmissionPower+=sensorList.get(i).getDatarate()*MainView.getEnergyPerBit();
		}
		return transmissionPower;
	}
	
	public double getADCConversionPower()
	{
		double ADCPower = 0;
		for (int i=0; i<sensorList.size(); i++)
		{
			ADCPower+=sensorList.get(i).getADCPowerConsumption();
		}
		return ADCPower;
	}
	
	public double getTotalPower()
	{
		return this.getADCConversionPower()+this.getWirelessTransmissionPower();
	}
	
	public long getTotalDatarate()
	{
		long datarate = 0;
		for (int i=0; i<sensorList.size(); i++)
		{
			datarate+=sensorList.get(i).getDatarate();
		}
		return datarate;
	}
	
	public String getDeviceInfo()
	{
		String deviceInfo = "";
		String info;
		for (int i=0;i<sensorList.size();i++)
		{
			Sensor sensor = sensorList.get(i);
			info = sensor.getName() + " # " + sensor.getDatarate() + " # " + sensor.getMinResolution()
					+ " # " + sensor.getMaxResolution() + " # " + sensor.getADCPowerConsumption();
			deviceInfo += info + " \n";
		}
		
		return deviceInfo;
		
	}

}
