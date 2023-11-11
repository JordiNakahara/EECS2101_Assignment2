import java.util.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
public class Round_Robin {



    public String scheduler(Process[] p, int tq){
        int n = p.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[100];
        boolean is_first_process = true;
        int current_time = 0, max_completion_time;
        int completed = 0, tq, total_idle_time = 0, length_cycle;
    float sum_tat = 0, sum_wt = 0, sum_rt = 0;
for (int i = 0; i < n; i++) {
    p[i] = new Process(i, at, 0);
}
for (int i = 0; i < n; i++) {
    p[i].bt = bt;
            p[i].bt_remaining = bt;
        }
        Arrays.sort(p, 0, n, new ProcessComparatorAT());
        q.add(0);
        visited[0] = true;
        while (completed != n) {
            index = q.poll();
            if (p[index].bt_remaining == p[index].bt) {
                p[index].start_time = Math.max(current_time, p[index].at);
                total_idle_time += (is_first_process == true) ? 0 : p[index].start_time - current_time;
                current_time = p[index].start_time;
                is_first_process = false;
            }
            if (p[index].bt_remaining - tq > 0) {
                p[index].bt_remaining -= tq;
                current_time += tq;
            } else {
                current_time += p[index].bt_remaining;
                p[index].bt_remaining = 0;
                completed++;
                p[index].ct = current_time;
                p[index].tat = p[index].ct - p[index].at;
                p[index].wt = p[index].tat - p[index].bt;
                p[index].rt = p[index].start_time - p[index].at;
                sum_tat += p[index].tat;
                sum_wt += p[index].wt;
                sum_rt += p[index].rt;
            }

            for (int i = 1; i < n; i++) {
                if (p[i].bt_remaining > 0 && p[i].at <= current_time && visited[i] == false) {
                    q.add(i);
                    visited[i] = true;
                }
            }

            if (p[index].bt_remaining > 0)
                q.add(index);

            if (q.isEmpty()) {
                for (int i = 1; i < n; i++) {
                    if (p[i].bt_remaining > 0) {
                        q.add(i);
                        visited[i] = true;
                        break;
                    }
                }
            }
        }

        max_completion_time = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            max_completion_time = Math.max(max_completion_time, p[i].ct);
        length_cycle = max_completion_time - p[0].at;
        //cpu_utilization = (int) ((float) (length_cycle - total_idle_time) / length_cycle * 100);

        Arrays.sort(p, 0, n, new ProcessComparatorPID());

        System.out.println("\nProcess No.\tAT\tCPU Burst Time\tStart Time\tCT\tTAT\tWT\tRT");
        for (int i = 0; i < n; i++)
            System.out.println(i + "\t\t" + p[i].at + "\t" + p[i].bt + "\t\t" + p[i].start_time + "\t\t" + p[i].ct + "\t" + p[i].tat + "\t" + p[i].wt + "\t" + p[i].rt);
        System.out.println();
        
        System.out.println("\nAverage Turn Around time= " + (float) sum_tat / n);
        System.out.println("\nAverage Waiting Time= " + (float) sum_wt / n);
        System.out.println("\nAverage Response Time= " + (float) sum_rt / n);
        return "" +(float) sum_tat/n;
    }
    
}

