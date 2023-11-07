package com.company;

public class Main {

    public static void main(String[] args) {
	    RRPriority test = new RRPriority(2);
        Process one = new Process(4,1,"1");
        test.addProcess(one);
        Process four = new Process(4,1,"4");
        test.addProcess(four);
        Process two = new Process(1,10,"2");
        test.addProcess(two);
        Process five = new Process(1,3,"5");
        test.addProcess(five);
        Process three = new Process(1,0,"3");
        test.addProcess(three);
        test.printQueue();
        System.out.println("######");

        String log = "";
        while (log != null){
            log = test.run();
            System.out.print(log);
        }
    }
}
