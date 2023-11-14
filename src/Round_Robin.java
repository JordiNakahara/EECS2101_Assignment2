package rrPackage;
import java.util.*;

public class Round_Robin 
{
	RobinProcess[] processes;
	int quantum;
	
	public Round_Robin(RobinProcess[] processes, int quantum)
	{
		this.processes = processes;
		this.quantum = quantum;
	}
	
	public double robinWaitTime()
	{
		ArrayList<RobinProcess> pList = new ArrayList<RobinProcess>();
		int todo = processes.length;
		int time = 0;
		int index = 0;
		int qCount = 0;
		RobinProcess currentProcess = null;
		
		while(todo > 0)
		{
			System.out.print(time + "ms : ");
			for(int i = 0; i < processes.length; i++)
			{
				if(processes[i].getArrival() == time)
				{
					System.out.print("Process " + processes[i].getId() + " matches at time = " + time + ", ");
					if(pList.isEmpty())
					{
						currentProcess = processes[i];
					}
					pList.add(processes[i]);
				}
			}
			
			if(pList.isEmpty())
			{
				System.out.println("No processes at this time.");
			}
			
			//So long as we have processes to work on
			if(pList.isEmpty() != true)
			{
				currentProcess.subBursts();
				qCount++;
				System.out.println("Bursts needed for process " + currentProcess.getId() + " = " + currentProcess.getBursts() + " quantum : " + qCount + "/" + quantum + " index : " + index);
			
				//Adding to wait times
				for(RobinProcess p: processes)
				{
					if(!(p.equals(currentProcess)) && p.getBursts() > 0)
					{
						if(!(p.getArrival() > time))
						{
							p.addWaitTime();
						}
					}
				}
				
				//Our process is finished
				if(currentProcess.getBursts() <= 0)
				{
					System.out.println(" Process " + currentProcess.getId() + " finished.");
					todo--;
					//Removing now-finished process
					pList.remove(currentProcess);
					
					//If the removed element was in the last index of the list, we need reset to 0
					qCount = 0;
					
					if(pList.size() == 1)
					{
						index = 0;
					}
					if(index == pList.size())
					{
						index = 0;
					}
					
					//Need a new check here
					if(pList.isEmpty() != true)
					{
						currentProcess = pList.get(index);
					}
				}
				
				if((qCount != 0) && (qCount % quantum == 0))
				{
					qCount = 0;
					if(index < (pList.size() - 1))
					{
						index++;
						currentProcess = pList.get(index);
					}
					else if(index == (pList.size() - 1))
					{
						index = 0;
						currentProcess = pList.get(index);
					}
				}
			}
			
			
			time++;
		}
		
		
		//Calculating average wait time.
		int sum = 0;
		for(RobinProcess p: processes)
		{
			System.out.print("[" + p.getWaitTime() + "], ");
			sum+= p.getWaitTime();
		}
		double toReturn = (double)sum / processes.length;
		return toReturn;
	}
}
