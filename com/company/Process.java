package com.company;

public class Process {
    private final int priority, burstTime;
    private int progress, waitingTime;
    private final String name;

    /**
     * Instantiates a Process object with given burstTime, priority and name. Assumes the process is fresh and that
     * its progress is 0 and that it hasn't waited
     * @param burstTime burst time for process
     * @param priority priority for process
     * @param name name of process
     */
    public Process(int burstTime, int priority, String name){
        this.burstTime = burstTime;
        this.priority = priority;
        this.progress = 0;
        this.name = name;
        this.waitingTime = 0;
    }

    /**
     * Increments the progress
     */
    public void run(int time){
        progress++;
    }

    /**
     * Increments the waiting time
     */
    public void addWaitingTime(){
        waitingTime++;
    }

    /**
     * @return the priority
     */
    public int getPriority(){
        return priority;
    }

    /**
     * @return the burst time
     */
    public int getBurstTime() {
        return burstTime;
    }

    /**
     * @return the progress
     */
    public int getProgress() {
        return progress;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the waiting time
     */
    public int getWaitingTime() {
        return waitingTime;
    }
}
