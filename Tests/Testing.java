import org.junit.Assert;
import org.junit.Test;

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

}
