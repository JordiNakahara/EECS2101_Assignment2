package com.company;

public class Process {
    private final int priority, burstTime;
    private int progress, waitingTime;
    private final String name;

    public Process(int burstTime, int priority, String name){
        this.burstTime = burstTime;
        this.priority = priority;
        this.progress = 0;
        this.name = name;
        this.waitingTime = 0;
    }

    public void run(int time){
        progress++;
    }
    public void addWaitingTime(){
        waitingTime++;
    }

    public int getPriority(){
        return priority;
    }
    public int getBurstTime() {
        return burstTime;
    }
    public int getProgress() {
        return progress;
    }
    public String getName() {
        return name;
    }
    public int getWaitingTime() {
        return waitingTime;
    }
}
