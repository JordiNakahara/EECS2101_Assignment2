package rrPackage;
import java.util.*;

public class RobinProcess 
{
	int id;		 //The process id
	int bursts;  //The process 
	int arrival; //The process arrival time
	int waitTime;//The process wait time
	
	//Primary Constructor
	RobinProcess(int id, int bursts, int arrival)
	{
		this.id = id;
		this.bursts = bursts;
		this.arrival = arrival;
		this.waitTime = 0;
	}
	

	//Comparator based on arrival time
    class ArrivalComparator implements Comparator<RobinProcess>
	{
		@Override
		public int compare(RobinProcess first, RobinProcess second)
		{
			int firstArrival = first.arrival;
			int secondArrival = second.arrival;
			return Integer.compare(firstArrival, secondArrival);
		}
	}
	
	//Method to return process ID
	public int getId() {
		return this.id;
	}
	
	//Method to return process bursts 
	public int getBursts() {
		return this.bursts;
	}
	
	//Method to return process arrival
	public int getArrival() {
		return this.arrival;
	}
	
	//Method to return process waiting time
	public int getWaitTime() {
		return this.waitTime;
	}
	
	public void addWaitTime() {
		this.waitTime++;
	}
	
	public void subBursts()
	{
		this.bursts--;
	}
}
