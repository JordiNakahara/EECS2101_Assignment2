import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

/**
 * This class contains jUnit test cases in order to test the algorithms.
 * @author Jordi Nakahara
 * @version 1.0
 */
public class Testing {


    /**
     * This method tests the round-robin algorithm.
     */
    @Test
    public void roundRobin1() {
        boolean flag = false;
        RobinProcess[] p = new RobinProcess[3];
        p[0] = new RobinProcess(1,0,24);
        p[1] = new RobinProcess(2,0,3);
        p[2] = new RobinProcess(3,0,3);

        Round_Robin test = new Round_Robin(p,1);

        double output = test.robinWaitTime();

        Assert.assertEquals(5.66666667, output, 0.001);


    }

    /**
     * This method tests the round-robin algorithm.
     */
    @Test
    public void roundRobin2() {
        RobinProcess[] p = new RobinProcess[4];
        p[0] = new RobinProcess(1, 17, 56);
        p[1] = new RobinProcess(2, 400, 2);
        p[2] = new RobinProcess(3, 32, 8);
        p[3] = new RobinProcess(4, 128, 14);

        Round_Robin test = new Round_Robin(p,32);

        Assert.assertEquals(6.25, test.robinWaitTime(), 0.001);
    }

    /**
     * This method tests the round-robin algorithm.
     */
    @Test
    public void roundRobin3() {
        RobinProcess[] p = new RobinProcess[3];
        p[0] = new RobinProcess(1,0,3);
        p[1] = new RobinProcess(2,0,5);
        p[2] = new RobinProcess(3,0,2);


        Round_Robin test = new Round_Robin(p,1);

        Assert.assertEquals(4.3333335, test.robinWaitTime(), 0.001);
    }


    /**
     * This method tests the round-robin algorithm.
     */
    @Test
    public void roundRobin4() {
        RobinProcess[] p = new RobinProcess[5];
        p[0] = new RobinProcess(1,0,1);
        p[1] = new RobinProcess(2,0,2);
        p[2] = new RobinProcess(3,1,3);
        p[3] = new RobinProcess(4,2,4);
        p[4] = new RobinProcess(5,3,5);


        Round_Robin test = new Round_Robin(p,1);

        Assert.assertEquals(4.6, test.robinWaitTime(), 0.001);
    }


    /**
     * This method tests the round-robin algorithm.
     */
    @Test
    public void roundRobin5() {
        RobinProcess[] p = new RobinProcess[20];

        for (int i = 0; i < 20; i++) {
            p[i] = new RobinProcess(i,i,i % 3+1);
        }


        Round_Robin test = new Round_Robin(p,3);

        Assert.assertEquals("8.85", test.robinWaitTime(), 0.001);
    }


    /**
     * This method tests the round-robin algorithm.
     */
    @Test
    public void roundRobin6() {
        RobinProcess[] p = new RobinProcess[3];
        p[0] = new RobinProcess(1,7,6);
        p[1] = new RobinProcess(2,45,18);
        p[2] = new RobinProcess(3,23,34);

        Round_Robin test = new Round_Robin(p,15);

        Assert.assertEquals("9.0", test.robinWaitTime(),0.001);
    }


    /**
     * This method tests the round-robin-priority combined algorithm.
     */
    @Test
    public void roundRobinPriority1() {
        RRPriority test = new RRPriority(2);
        Process one = new Process(4, 3, "1");
        test.addProcess(one);
        Process two = new Process(5, 2, "2");
        test.addProcess(two);
        Process three = new Process(8, 2, "3");
        test.addProcess(three);
        Process four = new Process(7, 1, "4");
        test.addProcess(four);
        Process five = new Process(3, 3, "5");
        test.addProcess(five);
        test.printQueue();

        String log = "";
        while (log != null) {
            log = test.run();

        }

        System.out.println(test.getAverageWaitingTime());
        double temp = test.getAverageWaitingTime();
        String output = "" + test.getAverageWaitingTime();

        Assert.assertEquals("13.8", output);

        System.out.println("roundRobinPriority1 Test Passed\nAverage Waiting Time: " + output);
    }


    /**
     * This method tests the round-robin-priority combined algorithm.
     */
    @Test
    public void roundRobinPriority2() {
        RRPriority test = new RRPriority(15);
        Process one = new Process(1, 1, "1");
        test.addProcess(one);
        Process two = new Process(1, 2, "2");
        test.addProcess(two);
        Process three = new Process(1, 3, "3");
        test.addProcess(three);
        test.printQueue();

        String log = "";
        while (log != null) {
            log = test.run();

        }

        System.out.println(test.getAverageWaitingTime());
        double temp = test.getAverageWaitingTime();
        String output = "" + test.getAverageWaitingTime();

        Assert.assertEquals("1.0", output);
        System.out.println("roundRobinPriority2 Test Passed\nAverage Waiting Time: " + output);
    }

    /**
     * This method tests the round-robin-priority combined algorithm.
     */
    @Test
    public void roundRobinPriority3() {
        RRPriority test = new RRPriority(1);
        Process one = new Process(1, 1, "1");
        test.addProcess(one);


        String log = "";
        while (log != null) {
            log = test.run();

        }

        System.out.println(test.getAverageWaitingTime());
        String output = "" + test.getAverageWaitingTime();

        Assert.assertEquals("0.0", output);
        System.out.println("roundRobinPriority3 Test Passed\nAverage Waiting Time: " + output);
    }


    /**
     * This method tests the round-robin-priority combined algorithm.
     */
    @Test
    public void roundRobinPriority4() {
        RRPriority test = new RRPriority(1);
        Process one = new Process(4, 1, "1");
        test.addProcess(one);
        Process two = new Process(2, 1, "2");
        test.addProcess(two);
        Process three = new Process(7, 1, "3");
        test.addProcess(three);
        Process four = new Process(1, 1, "4");
        test.addProcess(four);
        Process five = new Process(8, 1, "5");
        test.addProcess(five);
        Process six = new Process(6, 1, "6");
        test.addProcess(six);


        String log = "";
        while (log != null) {
            log = test.run();

        }

        System.out.println(test.getAverageWaitingTime());
        double temp = test.getAverageWaitingTime();
        String output = "" + test.getAverageWaitingTime();

        Assert.assertEquals("13.166666666666666", output);
        System.out.println("roundRobinPriority4 Test Passed\nAverage Waiting Time: " + output);
    }



    /**
     * This method tests the shortest-job-first algorithm.
     */
    @Test
    public void SJF1() {
        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();
        boolean flag = false;


        toPass.add(new SJFProcess("SJFProcessOne", 8, 0));
        toPass.add(new SJFProcess("SJFProcessTwo", 4, 1));
        toPass.add(new SJFProcess("SJFProcessThree", 9, 2));
        toPass.add(new SJFProcess("SJFProcessFour", 5, 3));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        String output = "" + toRun.averageWaiting();

        Assert.assertEquals("6.5", output);
        System.out.println("SJF1 Test Passed\nAverage Waiting Time: " + output);
    }


    /**
     * This method tests the shortest-job-first algorithm.
     */
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
        System.out.println("SJF2 Test Passed\nAverage Waiting Time: " + output);
    }


    /**
     * This method tests the shortest-job-first algorithm.
     */
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
        System.out.println("SJF3 Test Passed\nAverage Waiting Time: " + output);
    }


    /**
     * This method tests the shortest-job-first algorithm.
     */
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
        System.out.println("SJF4 Test Passed\nAverage Waiting Time: " + output);
    }


    /**
     * This method tests the shortest-job-first algorithm.
     */
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
        System.out.println("SJF5 Test Passed\nAverage Waiting Time: " + output);
    }


    /**
     * This method tests the shortest-job-first algorithm.
     */
    @Test
    public void SJF6() {
        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();


        toPass.add(new SJFProcess("One", 1, 0));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        String output = "" + toRun.averageWaiting();
        Assert.assertEquals("0.0", output);
        System.out.println("SJF6 Test Passed\nAverage Waiting Time: " + output);
    }



    /**
     * This method tests the shortest-job-first algorithm.
     */
    @Test
    public void SJF7() {
        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();


        toPass.add(new SJFProcess("One", 1, 0));
        toPass.add(new SJFProcess("Two", 2, 0));
        toPass.add(new SJFProcess("Three", 3, 0));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        String output = "" + toRun.averageWaiting();
        Assert.assertEquals("1.3333333333333333", output);
        System.out.println("SJF7 Test Passed\nAverage Waiting Time: " + output);
    }


    /**
     * This method tests all the algorithms with the same test case.
     */
    @Test
    public void TestAll1() {
        RRPriority test = new RRPriority(1);
        Process one = new Process(1, 1, "1");
        test.addProcess(one);

        String log = "";
        while (log != null) {
            log = test.run();

        }

        String output = "" + test.getAverageWaitingTime();
        Assert.assertEquals("0.0", output);
        System.out.println("TestAll1 RRPriority Portion Passed\nAverage Waiting Time: " + output);

        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();


        toPass.add(new SJFProcess("One", 1, 1));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        output = "" + toRun.averageWaiting();
        Assert.assertEquals("0.0", output);
        System.out.println("TestAll1 SJF Portion Passed\nAverage Waiting Time: " + output);


        RobinProcess[] p = new RobinProcess[1];
        p[0] = new RobinProcess(1, 1, 0);

        Round_Robin testRobin = new Round_Robin(p,1);

        Assert.assertEquals(0.0, testRobin.robinWaitTime());
    }


    /**
     * This method tests all the algorithms with the same test case.
     */
    @Test
    public void TestAll2() {
        RRPriority test = new RRPriority(2);
        Process one = new Process(5, 1, "1");
        test.addProcess(one);
        Process two = new Process(3,2, "2");
        test.addProcess(two);
        Process three = new Process(7,3, "3");
        test.addProcess(three);

        String log = "";
        while (log != null) {
            log = test.run();

        }

        String output = "" + test.getAverageWaitingTime();
        Assert.assertEquals("4.333333333333333", output);
        System.out.println("TestAll2 RRPriority Portion Passed\nAverage Waiting Time: " + output);

        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();


        toPass.add(new SJFProcess("One", 5, 0));
        toPass.add(new SJFProcess("Two", 3, 0));
        toPass.add(new SJFProcess("Three", 7, 0));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        output = "" + toRun.averageWaiting();
        Assert.assertEquals("3.6666666666666665", output);
        System.out.println("TestAll2 SJF Portion Passed\nAverage Waiting Time: " + output);


        RobinProcess[] p = new RobinProcess[3];
        p[0] = new RobinProcess(1, 5, 0);
        p[1] = new RobinProcess(2, 3, 0);
        p[2] = new RobinProcess(3, 7, 0);

        Round_Robin testRobin = new Round_Robin(p,2);

        Assert.assertEquals(7.0, testRobin.robinWaitTime());
    }


    /**
     * This method tests all the algorithms with the same test case.
     */
    @Test
        public void TestAll3() {
        RRPriority test = new RRPriority(2);
        Process one = new Process(9, 0, "1");
        test.addProcess(one);
        Process two = new Process(3,0, "2");
        test.addProcess(two);
        Process three = new Process(1,3, "3");
        test.addProcess(three);
        Process four = new Process(8,4, "4");
        test.addProcess(four);
        Process five = new Process(2,7, "5");
        test.addProcess(five);
        Process six = new Process(5,7, "6");
        test.addProcess(six);
        Process seven = new Process(1,7, "7");
        test.addProcess(seven);

        String log = "";
        while (log != null) {
            log = test.run();

        }

        String output = "" + test.getAverageWaitingTime();
        Assert.assertEquals("14.571428571428571", output);
        System.out.println("TestAll3 RRPriority Portion Passed\nAverage Waiting Time: " + output);

        ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();


        toPass.add(new SJFProcess("One", 9, 0));
        toPass.add(new SJFProcess("Two", 3, 0));
        toPass.add(new SJFProcess("Three", 1, 0));
        toPass.add(new SJFProcess("Four", 8, 0));
        toPass.add(new SJFProcess("Five", 2, 0));
        toPass.add(new SJFProcess("Six", 5, 0));
        toPass.add(new SJFProcess("Seven", 1, 0));

        //Running process to find average waiting time of the above processes.
        SJFQueue toRun = new SJFQueue(toPass);
        toRun.averageWaitingTime();
        output = "" + toRun.averageWaiting();
        Assert.assertEquals("6.571428571428571", output);
        System.out.println("TestAll3 SJF Portion Passed\nAverage Waiting Time: " + output);

        RobinProcess[] p = new RobinProcess[7];
        p[0] = new RobinProcess(1, 9, 0);
        p[1] = new RobinProcess(2, 3, 0);
        p[2] = new RobinProcess(3, 1, 0);
        p[3] = new RobinProcess(4, 8, 0);
        p[4] = new RobinProcess(5, 2, 0);
        p[5] = new RobinProcess(6, 5, 0);
        p[6] = new RobinProcess(7, 1, 0);

        Round_Robin testRobin = new Round_Robin(p,2);

        Assert.assertEquals(13.286, testRobin.robinWaitTime());
    }


}
