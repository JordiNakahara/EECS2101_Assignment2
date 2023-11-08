import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
public class Round_Robin {
    static class Process {
        int arrivalTime;
        int burstTime;
        int completionTime;
        int turnTime;
        int waitingTime;
        int startTime;
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int n, tq;
        Process[] p = new Process[50];
        float avgWaiting;
        int totalTurn = 0;
        int[] burstArr = new int[50];
        float avgTurn;
        int totalWaiting = 0;
        int index = 0;
        System.out.print("Enter the number of processes: ");
        n = input.nextInt();
        System.out.print("Enter time quantum of CPU: ");
        tq = input.nextInt();
        // Use for loop to enter the details of the process
        //  like Arrival time and the Burst Time
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time of process " + (i + 1) + ": ");
            p[i] = new Process();
            p[i].arrivalTime = input.nextInt();
            System.out.print("Enter burst time of process " + (i + 1) + ": ");
            p[i].burstTime = input.nextInt();
            burstArr[i] = p[i].burstTime;
            System.out.println();
        }
        // We will be using a FIFO queue to implement this algorithm
        Queue<Integer> q = new LinkedList<>();
        int current_time = 0;
        // keep track of the time using a variable-current_time
        q.add(0);
        int completed = 0;
        int[] mark = new int[100];
        Arrays.fill(mark, 0);
        mark[0] = 1;
        while (completed != n) {
            //Give quantum unit of time to the process that is in the front of the
            // queue and pop this process from the queue
            index = q.peek();
            q.poll();
            if (burstArr[index] == p[index].burstTime) {
                p[index].startTime = Math.max(current_time, p[index].arrivalTime);
                current_time = p[index].startTime;
            }
            if (0 < burstArr[index] - tq) {
                burstArr[index] -= tq;
                current_time += tq;
            } else {
                current_time += burstArr[index];
                p[index].completionTime = current_time;
                p[index].turnTime = p[index].completionTime - p[index].arrivalTime;
                p[index].waitingTime = p[index].turnTime - p[index].burstTime;
                totalWaiting += p[index].waitingTime;
                totalTurn += p[index].turnTime;
                completed++;
                burstArr[index] = 0;
            }
            // if some process has arrived when this process was executing,
            // insert them into the queue
            for (int i = 1; i < n; i++) {
                if (burstArr[i] > 0 && p[i].arrivalTime <= current_time && mark[i] == 0) {
                    mark[i] = 1;
                    q.add(i);
                }
            }
            //if the current process has burst time remaining,
            //push the process into the queue again
            if (0 < burstArr[index]) {
                q.add(index);
            }
            //if the queue is empty
            //pick the first process from the list that is not completed
            if (q.isEmpty()) {
                for (int i = 1; i < n; i++) {
                    if (0 < burstArr[i]) {
                        mark[i] = 1;
                        q.add(i);
                        break;
                    }
                }
            }
            avgWaiting = (float) totalWaiting / n;
            avgTurn = (float) totalTurn / n;
            System.out.println();
            // to get the data from each process we
            System.out.println("Average Waiting Time = " + avgWaiting);
            System.out.println("Average Turnaround Time = " + avgTurn);
        }
    }
}