import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Testing {
    @Test
    public void roundRobinOne() {
        RobinProcess[] p = new RobinProcess[3];
        p[0] = new RobinProcess();
        p[0].arrivalTime = 0;
        p[0].burstTime = 24;
        p[1] = new RobinProcess();
        p[1].arrivalTime = 0;
        p[1].burstTime = 3;
        p[2] = new RobinProcess();
        p[2].arrivalTime = 0;
        p[2].burstTime = 3;

        Round_Robin test = new Round_Robin();

        Assert.assertEquals("5.6666665", test.scheduler(p, 1));
    }


    @Test
    public void roundRobinThree() {
        RobinProcess[] p = new RobinProcess[3];
        p[0] = new RobinProcess();
        p[0].arrivalTime = 0;
        p[0].burstTime = 3;
        p[1] = new RobinProcess();
        p[1].arrivalTime = 0;
        p[1].burstTime = 5;
        p[2] = new RobinProcess();
        p[2].arrivalTime = 0;
        p[2].burstTime = 2;


        Round_Robin test = new Round_Robin();

        Assert.assertEquals("4.3333335", test.scheduler(p, 1));
    }

    @Test
    public void roundRobinFour() {
        RobinProcess[] p = new RobinProcess[5];
        p[0] = new RobinProcess();
        p[0].arrivalTime = 0;
        p[0].burstTime = 1;
        p[1] = new RobinProcess();
        p[1].arrivalTime = 0;
        p[1].burstTime = 2;
        p[2] = new RobinProcess();
        p[2].arrivalTime = 1;
        p[2].burstTime = 3;
        p[3] = new RobinProcess();
        p[3].arrivalTime = 2;
        p[3].burstTime = 4;
        p[4] = new RobinProcess();
        p[4].arrivalTime = 3;
        p[4].burstTime = 5;


        Round_Robin test = new Round_Robin();

        Assert.assertEquals("4.6", test.scheduler(p, 1));
    }

    @Test
    public void roundRobinFive() {
        RobinProcess[] p = new RobinProcess[20];

        for (int i = 0; i < 20; i++) {
            p[i] = new RobinProcess();
            p[i].arrivalTime = i;
            p[i].burstTime = i % 3 + 1;
        }


        Round_Robin test = new Round_Robin();

        Assert.assertEquals("8.85", test.scheduler(p, 3));
    }

    @Test
    public void roundRobinSix() {
        RobinProcess[] p = new RobinProcess[3];
        p[0] = new RobinProcess();
        p[0].arrivalTime = 7;
        p[0].burstTime = 6;
        p[1] = new RobinProcess();
        p[1].arrivalTime = 45;
        p[1].burstTime = 18;
        p[2] = new RobinProcess();
        p[2].arrivalTime = 23;
        p[2].burstTime = 34;

        Round_Robin test = new Round_Robin();

        Assert.assertEquals("9", test.scheduler(p, 15));
    }

    @Test
    public void roundRobinPriorityOne() {
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
