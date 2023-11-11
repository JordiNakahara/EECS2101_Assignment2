import java.util.*;

//Class for our ready-queue for SJF scheduling and associated methods
public class SJFQueue 
{
	//Our list of processes with given arrival and burst time.
	ArrayList<SJFProcess> processList = new ArrayList<SJFProcess>();
	
	/*
	 * Creating a copy of our list of processes. The entries of the 
	 * original list will be removed as the processes "terminate" in
	 * the algorithm, and so another reference is required to obtain
	 * their wait times later on.
	 */
	ArrayList<SJFProcess> processCopy = new ArrayList<SJFProcess>();
	
	//Primary constructor
	public SJFQueue(ArrayList<SJFProcess> processes)
	{
		processList = processes;
	}
	
	//Method to return the process list (copy) for calculation of wait times
	public ArrayList<SJFProcess> getCopy()
	{
		//No real reason to deep copy here
		return processCopy;
	}
	
	//Method to determine preempting for processes entering the list
	public boolean isShorter(SJFProcess current, SJFProcess next)
	{
		if(next.getBursts() < current.getBursts())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Below is the effective preemptive SJF algorithm.
	public void averageWaitingTime()
	{
		for(SJFProcess p : processList)
		{
			processCopy.add(p);
		}
		
		int processLeft = processList.size();
		int cpuTime = 0;
		SJFProcess current = null;
		
		//This queue will hold any unfinished processes ordered by the comparator below this class.
		BurstSort comparator = new BurstSort();
		PriorityQueue<SJFProcess> undone = new PriorityQueue<SJFProcess>(comparator);
		
		System.out.println("Beginning preemptive SJF on " + processLeft + " processes\n");
		
		//While we've still processes to compute, do the following:
		while(processLeft != 0)
		{
			System.out.print(cpuTime + "ms ");

			//True when a new process arrives
			if(processList.size() != 0)
			{
				if((processList.get(0).getArrival()) == cpuTime)
				{
					//If there's no active process, we can get right to work.
					if(current == null)
					{
						current = processList.get(0);
						System.out.print("Obtained process " + current.getName() + ", ");
					}
					//Otherwise:
					else
					{
						//The new process is shorter than the current process:
						if(isShorter(current, processList.get(0)))
						{
							//The current process is now waiting
							current.waitSwap();
							
							undone.add(current);
							current = processList.get(0);
							System.out.print("Obtained process " + current.getName() + ", ");
						}
						//The new process is longer than the current process:
						else
						{
							//The new process is now waiting
							processList.get(0).waitSwap();
							undone.add(processList.get(0));
						}
					}
					
					processList.remove(0);
				}
			}
			System.out.print("Cycles needed for " + current.getName() + " = " + current.getBursts() + " ");
			
			//If our process terminates
			if(current != null && current.getBursts() <= 0)
			{
				processLeft--;
				System.out.print(" SJFProcess " + current.getName() + " Finished");
				
				//The next queued process is no longer waiting
				current = undone.poll();
				if(current != null)
				{
					current.waitSwap();
				}
			}
			
			System.out.print("\n");
			
			cpuTime++;
			//Incrementing wait times
			for(SJFProcess p : processCopy)
			{
				if(p.waiting())
				{
					p.incrementWait();
				}
			}
			
			if(current != null)
			{
				current.decrement();
			}
		}
	}

	public double averageWaiting() {
		double waitingAvg = 0;
		ArrayList<SJFProcess> waitTimes = this.getCopy();
		for(int i = 0; i < waitTimes.size(); i++)
		{
			waitingAvg += waitTimes.get(i).getWaitTime();
		}
		waitingAvg = (waitingAvg / waitTimes.size());
		return waitingAvg;
	}
}

/**
 *Comparator to sort processes by burst time remaining
 */
class BurstSort implements Comparator<SJFProcess>
{
    public int compare(SJFProcess first, SJFProcess second)
    {
        if(first.getBursts() < second.getBursts())
        {
        	return -1;
        }
        else if(first.getBursts() == second.getBursts())
        {
        	return 0;
        }
        else
        {
        	return 1;
        }
    }
}
