

public class RRPriority extends CPUPriority{
    private int timeQuantum;
    private int time;
    private int lowestPriority;
    private int lowestPriorityCount;
    private int currentPriorityPosition;
    private Process currentProcess;

    private boolean quantum = false;
    private boolean terminate = false;
    private int quantCounter = 0;

    public RRPriority (int timeQuantum){
        super();
        this.timeQuantum = timeQuantum;
        this.time = 0;
        this.lowestPriority = -1;
        this.lowestPriorityCount = -1;
        this.currentProcess = null;
    }

    @Override
    public String run() {
        String action = time + " - ";

        if (getQueue().getSize() == 0){
            return null;
        }

        //Change Process if needed
        if (!runnable()){
            boolean changed = false;
            if (terminate){
                int tempPriority = currentProcess.getPriority();
                if (hasPriority(tempPriority)){ //We still have other priorities of the same value
                    Process tempProcess = findNextProcess(currentProcess);
                    terminateProcess(currentProcess);
                    currentProcess = tempProcess;
                }else { //All priorities of this level is done, so we start from the header again.
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
        validateChange();
        action = action + currentProcess.getName() + "\n";

        return action;
    }

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

    private boolean runnable(){
        return (!quantum) & (!terminate);
    }
}
