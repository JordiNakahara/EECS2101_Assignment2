import java.util.*;

//Class representing a process, comprised of identifier, bursts time needed, and arrival time.
public class SJFProcess
{
	String name;
	int burstTime;
	int arrival;
	int waitingTime = 0;
	boolean isWaiting = false;
	
	//Primary constructor
	public SJFProcess(String name, int burstTime, int arrival)
	{
		this.name = name;
		this.burstTime = burstTime;
		this.arrival = arrival;
		
	}
	
	//Access methods
	public String getName()
	{
		String toReturn = new String(this.name);
		return toReturn;
	}
	
	public int getBursts()
	{
		int toReturn = this.burstTime;
		return toReturn;
	}
	
	public int getArrival()
	{
		int toReturn = this.arrival;
		return toReturn;
	}
	
	//Method to reduce time needed for a given process
	public void decrement()
	{
		this.burstTime--;
	}
	
	public void incrementWait()
	{
		this.waitingTime++;
	}
	
	public boolean waiting()
	{
		return this.isWaiting;
	}
	
	public int getWaitTime()
	{
		return this.waitingTime;
	}
	
	public void waitSwap()
	{
		if(this.isWaiting)
		{
			this.isWaiting = false;
		}
		else
		{
			this.isWaiting = true;
		}
	}
	
}
