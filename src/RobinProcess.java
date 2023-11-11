class Process {
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

        static class ProcessComparatorAT implements Comparator<Process> {
            public int compare(Process a, Process b) {
                int x = a.at;
                int y = b.at;
                return Integer.compare(x, y);
            }
        }

        static class ProcessComparatorPID implements Comparator<Round_Robin.RobinProcess> {
            public int compare(Process a,Process b) {
                int x = a.pid;
                int y = b.pid;
                return Integer.compare(x, y);
            }
        }
}
