package com.company;

public class RRPriority extends CPUPriority{
    private int timeQuantum;
    private int time;
    private int lowestPriority;
    private int lowestPriorityCount;
    private int currentPriorityPosition;
    private Process currentProcess;
    private int totalWaitTime;
    private int totalProcesses;
    private int maxProcesses;

    private boolean quantum = false;
    private boolean terminate = false;
    private int quantCounter = 0;

    /**
     * Instantiates an RRPriority object. It assumes we start counting processes at time 0, with no current process.
     * @param timeQuantum the desired time quantum
     */
    public RRPriority (int timeQuantum){
        super();
        this.timeQuantum = timeQuantum;
        this.time = 0;
        this.lowestPriority = -1;
        this.lowestPriorityCount = -1;
        this.currentProcess = null;
        this.totalWaitTime = 0;
    }

    /**
     * The algorithm will start executing higher priority processes and work its way down to the lower priority
     * processes. If there are multiple of the same priorities, it will apply a round-robin scheduling algorithm
     * across that priority.
     * @return A line stating the current time and the process we ran at the stated time, till the next time
     */
    @Override
    public String run() {
        String action = time + " - ";

        if (getQueue().getSize() == 0){
            return null;
        }

        int max = getSize();
        if (max > maxProcesses){
            maxProcesses = max;
        }

        //Change Process if needed
        if (!runnable()){
            boolean changed = false;
            if (terminate){
                int tempPriority = currentProcess.getPriority();
                if (hasPriority(tempPriority)){ //We still have other priorities of the same value
                    totalWaitTime += currentProcess.getWaitingTime();
                    Process tempProcess = findNextProcess(currentProcess);
                    terminateProcess(currentProcess);
                    currentProcess = tempProcess;
                }else { //All priorities of this level is done, so we start from the header again.
                    totalWaitTime += currentProcess.getWaitingTime();
                    terminateProcess(currentProcess);
                    currentProcess = getHeadProcess();
                }
                terminate = false;
                changed = true;
                quantCounter = 0;
            }
            if (quantum & changed){
                quantCounter = 0;
                quantum = false;
            }else if (quantum){
                int tempPriority = currentProcess.getPriority();
                if (hasPriority(tempPriority)){ //We still have other priorities of the same value
                    currentProcess = findNextProcess(currentProcess);
                }
                quantCounter = 0;
                quantum = false;
            }
        }

        if (currentProcess == null){
            currentProcess = getHeadProcess();
        }


        //Run 1 "clock"
        if (currentProcess != null){
            currentProcess.run(1);
        }else {
            return null;
        }
        time++;
        quantCounter++;
        waitAll(currentProcess);
        validateChange();
        action = action + currentProcess.getName();

        return action;
    }

    /**
     * Private method used to change quantum and terminate boolean variables. The variables represent the state in
     * which the current queue is in:
     *      quantum = true (the time quantum has been reached)
     *      quantum = true (the time quantum has not been reached)
     *      terminate = true (the current process is finished)
     *      terminate = true (the current process is not finished)
     */
    private void validateChange(){
        if (quantCounter == timeQuantum){
            quantum = true;
        }
        if (currentProcess != null){
            if (currentProcess.getBurstTime() == currentProcess.getProgress()){
                terminate = true;
            }
        }

    }

    /**
     * Calculates the average waiting time by dividing the total waiting time by the amount of processes
     * @return the average waiting time
     */
    public double getAverageWaitingTime(){
        return (double) totalWaitTime / (double) maxProcesses;
    }

    /**
     * The algorithm needs to know if we should swap processes. This private method calculate whether or not a process
     * needs to be changed. The program is not runnable when we need to change a process, thus it will return false
     * when a process needs to be changed and true when we do not need to change a process.
     * @return whether the process needs to changed
     */
    private boolean runnable(){
        return (!quantum) & (!terminate);
    }
}
