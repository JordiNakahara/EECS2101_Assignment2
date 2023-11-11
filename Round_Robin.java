import java.util.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Scanner;
public class Round_Robin {
    static class Process {
        int pid;
        int at;
        int bt;
        int ct, wt, tat, rt, start_time;
        int bt_remaining;
        Process(int pid, int at, int bt) {
            this.pid = pid;
            this.at = at;
            this.bt = bt;
            this.ct = 0;
            this.wt = 0;
            this.tat = 0;
            this.rt = 0;
            this.start_time = 0;
            this.bt_remaining = bt;
        }
    }
    static class ProcessComparatorAT implements Comparator<Process> {
        public int compare(Process a, Process b) {
            int x = a.at;
            int y = b.at;
            return Integer.compare(x, y);
        }
    }
    static class ProcessComparatorPID implements Comparator<Process> {
        public int compare(Process a, Process b) {
            int x = a.pid;
            int y = b.pid;
            return Integer.compare(x, y);
            //    if(x > y)
//      return false;  // Apply sorting
//    return true;   // no sorting
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n, index;
        //int cpu_utilization;
        Process[] ps = new Process[50];
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[100];
        boolean is_first_process = true;
        int current_time = 0, max_completion_time;
        int completed = 0, tq, total_idle_time = 0, length_cycle;
        System.out.print("Enter total number of processes: ");
        n = scanner.nextInt();
        float sum_tat = 0, sum_wt = 0, sum_rt = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter Process " + i + " Arrival Time: ");
            int at = scanner.nextInt();
            ps[i] = new Process(i, at, 0);
        }
        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter Process " + i + " Burst Time: ");
            int bt = scanner.nextInt();
            ps[i].bt = bt;
            ps[i].bt_remaining = bt;
        }
        System.out.print("\nEnter time quantum: ");
        tq = scanner.nextInt();
//sort structure on the basis of Arrival time in increasing order
        Arrays.sort(ps, 0, n, new ProcessComparatorAT());
        q.add(0);
        visited[0] = true;
        while (completed != n) {
            index = q.poll();
            if (ps[index].bt_remaining == ps[index].bt) {
                ps[index].start_time = Math.max(current_time, ps[index].at);
                total_idle_time += (is_first_process == true) ? 0 : ps[index].start_time - current_time;
                current_time = ps[index].start_time;
                is_first_process = false;
            }
            if (ps[index].bt_remaining - tq > 0) {
                ps[index].bt_remaining -= tq;
                current_time += tq;
            } else {
                current_time += ps[index].bt_remaining;
                ps[index].bt_remaining = 0;
                completed++;
                ps[index].ct = current_time;
                ps[index].tat = ps[index].ct - ps[index].at;
                ps[index].wt = ps[index].tat - ps[index].bt;
                ps[index].rt = ps[index].start_time - ps[index].at;
                sum_tat += ps[index].tat;
                sum_wt += ps[index].wt;
                sum_rt += ps[index].rt;
            }
            //check which new Processes needs to be pushed to Ready Queue from Input list
            for (int i = 1; i < n; i++) {
                if (ps[i].bt_remaining > 0 && ps[i].at <= current_time && visited[i] == false) {
                    q.add(i);
                    visited[i] = true;
                }
            }
//check if Process on CPU needs to be pushed to Ready Queue
            if (ps[index].bt_remaining > 0)
                q.add(index);
//if queue is empty, just add one process from list, whose remaining burst time > 0
            if (q.isEmpty()) {
                for (int i = 1; i < n; i++) {
                    if (ps[i].bt_remaining > 0) {
                        q.add(i);
                        visited[i] = true;
                        break;
                    }
                }
            }
        }
//end of while
        //Calculate Length of Process completion cycle
        max_completion_time = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            max_completion_time = Math.max(max_completion_time, ps[i].ct);
        length_cycle = max_completion_time - ps[0].at;
        //ps[0].start_time;

        Arrays.sort(ps, 0, n, new ProcessComparatorPID());
        //Output
        System.out.println("\nProcess No.\tAT\tCPU Burst Time\tStart Time\tCT\tTAT\tWT\tRT");
        for (int i = 0; i < n; i++)
            System.out.println(i + "\t\t\t\t" + ps[i].at + "\t\t\t" + ps[i].bt + "\t\t\t\t" + ps[i].start_time + "\t\t\t\t" + ps[i].ct + "\t\t\t" + ps[i].tat + "\t\t\t" + ps[i].wt + "\t\t\t\t" + ps[i].rt);
        System.out.println();
        System.out.println("\nAverage Turn Around time= " + (float) sum_tat / n);
        System.out.println("\nAverage Waiting Time= " + (float) sum_wt / n);

    }
}
