import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Testing {
    @Test
    public void roundRobin1() {
        boolean flag = false;
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

        String output = test.scheduler(p, 1);

        flag = output.equals("5.6666665");

        if(flag) {
            try {
                File outputFile = new File("output.txt");

                if (outputFile.createNewFile()) {
                    FileWriter writer = new FileWriter("output.txt");
                    writer.write("RoundRobin1 test passed\nExpected: 5.6666665        " +
                            "Actual:" + output + "\n----------------------------------------------");
                    writer.close();
                }
                else {
                    FileWriter writer = new FileWriter("output.txt");
                    writer.write("RoundRobin1 test passed\nExpected: 5.6666665        " +
                            "Actual:" + output + "\n----------------------------------------------");
                    writer.close();
                }
            } catch (IOException e) {

            }
        }

        Assert.assertEquals(true, flag);
    }

    @Test
    public void roundRobin2() {
        RobinProcess[] p = new RobinProcess[4];
        p[0] = new RobinProcess();
        p[0].arrivalTime = 17;
        p[0].burstTime = 56;
        p[1] = new RobinProcess();
        p[1].arrivalTime = 400;
        p[1].burstTime = 2;
        p[2] = new RobinProcess();
        p[2].arrivalTime = 32;
        p[2].burstTime = 8;
        p[3] = new RobinProcess();
        p[3].arrivalTime = 128;
        p[3].burstTime = 14;

        Round_Robin test = new Round_Robin();

        Assert.assertEquals("6.25", test.scheduler(p, 32));
    }


    @Test
    public void roundRobin3() {
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
    public void roundRobin4() {
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
    public void roundRobin5() {
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
    public void roundRobin6() {
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

        Assert.assertEquals("9.0", test.scheduler(p, 15));
    }

    @Test
    public void roundRobinPriority1() {
        RRPriority test = new RRPriority(2);
        Process one = new Process(4,3,"1");
        test.addProcess(one);
        Process two = new Process(5,2,"2");
        test.addProcess(two);
        Process three = new Process(8,2,"3");
        test.addProcess(three);
        Process four = new Process(7,1,"4");
        test.addProcess(four);
        Process five = new Process(3,3,"5");
        test.addProcess(five);
        test.printQueue();

        String log = "";
        while (log != null){
            log = test.run();

        }

        System.out.println(test.getAverageWaitingTime());
        double temp = test.getAverageWaitingTime();
        String output = "" + test.getAverageWaitingTime();

        Assert.assertEquals("13.8", output);
    }

    @Test
    public void roundRobinPriority2() {
        RRPriority test = new RRPriority(15);
        Process one = new Process(1,1,"1");
        test.addProcess(one);
        Process two = new Process(1,2,"2");
        test.addProcess(two);
        Process three = new Process(1,3,"3");
        test.addProcess(three);
        test.printQueue();

        String log = "";
        while (log != null){
            log = test.run();

        }

        System.out.println(test.getAverageWaitingTime());
        double temp = test.getAverageWaitingTime();
        String output = "" + test.getAverageWaitingTime();

        Assert.assertEquals("1.0", output);
    }

    //@Test
    public void roundRobinPriority3() {
        RRPriority test = new RRPriority(1);
        Process one = new Process(1,1,"1");
        test.addProcess(one);



        String log = "";
        while (log != null){
            log = test.run();

        }

        System.out.println(test.getAverageWaitingTime());
        double temp = test.getAverageWaitingTime();
        String output = "" + test.getAverageWaitingTime();

        Assert.assertEquals("0.0", output);
    }

    @Test
    public void roundRobinPriority4() {
        RRPriority test = new RRPriority(1);
        Process one = new Process(4,1,"1");
        test.addProcess(one);
        Process two = new Process(2,1,"2");
        test.addProcess(two);
        Process three = new Process(7,1,"3");
        test.addProcess(three);
        Process four = new Process(1,1,"4");
        test.addProcess(four);
        Process five = new Process(8,1,"5");
        test.addProcess(five);
        Process six = new Process(6,1,"6");
        test.addProcess(six);




        String log = "";
        while (log != null){
            log = test.run();

        }

        System.out.println(test.getAverageWaitingTime());
        double temp = test.getAverageWaitingTime();
        String output = "" + test.getAverageWaitingTime();

        Assert.assertEquals("13.166666666666666", output);
    }

    @Test
    public void SJF1() {
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

    @Test
    public void SJF2() {
        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();


        toPass.add(new SJFProcess("SJFProcessOne", 7, 0));
        toPass.add(new SJFProcess("SJFProcessTwo", 4, 2));
        toPass.add(new SJFProcess("SJFProcessThree", 1, 4));
        toPass.add(new SJFProcess("SJFProcessFour", 4, 5));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        String output = "" + toRun.averageWaiting();
        Assert.assertEquals("3.0", output);
    }

    @Test
    public void SJF3() {
        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();


        toPass.add(new SJFProcess("SJFProcessOne", 1, 0));
        toPass.add(new SJFProcess("SJFProcessTwo", 2, 1));
        toPass.add(new SJFProcess("SJFProcessThree", 3, 2));
        toPass.add(new SJFProcess("SJFProcessFour", 4, 3));
        toPass.add(new SJFProcess("SJFProcessFive", 5, 4));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        String output = "" + toRun.averageWaiting();
        Assert.assertEquals("2.0", output);
    }

    @Test
    public void SJF4() {
        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();


        toPass.add(new SJFProcess("SJFProcessOne", 1, 17));
        toPass.add(new SJFProcess("SJFProcessTwo", 2, 20));
        toPass.add(new SJFProcess("SJFProcessThree", 3, 23));
        toPass.add(new SJFProcess("SJFProcessFour", 4, 26));
        toPass.add(new SJFProcess("SJFProcessFive", 5, 29));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        String output = "" + toRun.averageWaiting();
        Assert.assertEquals("0.2", output);
    }

    @Test
    public void SJF5() {
        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();


        for (int i = 0; i < 10; i++) {
            String name = "SJFProcess" + i;
            toPass.add(new SJFProcess(name, 15 - i, i));
        }

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        String output = "" + toRun.averageWaiting();
        Assert.assertEquals("39.0", output);
    }

    @Test
    public void SJF6() {
        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();



            toPass.add(new SJFProcess("One", 1,1));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        String output = "" + toRun.averageWaiting();
        Assert.assertEquals("0.0", output);
    }

}
