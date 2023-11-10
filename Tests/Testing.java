import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Testing {
    @Test
    public void roundRobinOne() {
        RobinProcess[] p = new RobinProcess[3];
        p[0] = new RobinProcess();
        p[0].arrivalTime = 0;
        p[0].burstTime = 1;
        p[1] = new RobinProcess();
        p[1].arrivalTime = 1;
        p[1].burstTime = 3;
        p[2] = new RobinProcess();
        p[2].arrivalTime = 2;
        p[2].burstTime = 7;

        Round_Robin test = new Round_Robin();

        Assert.assertEquals("1.3333334", test.scheduler(p, 1));
    }

    @Test
    public void roundRobinPriorityOne() { //Note: This is not a real test case, just a way to make sure I can test, will need the program to output average waiting time to test properly
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

        String log = "";
        while (log != null){
            log = test.run();

        }

        System.out.println(test.getAverageWaitingTime());
        double temp = test.getAverageWaitingTime();
        String output = "" + test.getAverageWaitingTime();

        Assert.assertEquals("5.4", output);
    }

    @Test
    public void SJFOne() {
        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();


        toPass.add(new SJFProcess("SJFProcessOne", 8, 0));
        toPass.add(new SJFProcess("SJFProcessTwo", 4, 1));
        toPass.add(new SJFProcess("SJFProcessThree", 9, 2));
        toPass.add(new SJFProcess("SJFProcessFour", 5, 3));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        String output = "" + toRun.averageWaiting();
        Assert.assertEquals("6.5", output);
    }

}
