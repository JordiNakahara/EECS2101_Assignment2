import java.util.*;

public class Main {

    public static void main(String[] args) {
        boolean isValidInput = false;
        Scanner sc = new Scanner(System.in);
        String algorithm = "None chosen";
        while (!isValidInput) {
            System.out.println("Please input either \"round robin\", \"shortest job first\", or \"round robin priority\":");
            algorithm = sc.nextLine().toLowerCase();

            if (algorithm.equals("round robin")) {
                isValidInput = true;

            }
            else if (algorithm.equals("shortest job first")) {
                isValidInput = true;
            }
            else if (algorithm.equals("round robin priority")) {
                isValidInput = true;
            }
            else {
                System.out.println("Please enter valid input\n-------------------------\n");
            }
        }

        System.out.println("Algorithm selected: " + algorithm);
        isValidInput = false;

        while (!isValidInput) {
            if (algorithm.equals("round robin")) {
                try {

                    System.out.println("Please enter the number of processes:");
                    int numProcess = Integer.parseInt(sc.nextLine());
                    int[] arrivalTimes = new int[numProcess];
                    int[] burstTimes = new int[numProcess];
                    RobinProcess[] processes = new RobinProcess[numProcess];
                    int timeQuantum = -1;


                    for (int i = 0; i < numProcess; i++) {
                        System.out.println("Please enter arrival time of process number " + (i + 1));
                        arrivalTimes[i] = Integer.parseInt(sc.nextLine());
                    }



                    for (int i = 0; i < numProcess; i++) {
                        System.out.println("Please enter burst time of process number " + (i + 1));
                        burstTimes[i] = Integer.parseInt(sc.nextLine());
                    }

                    System.out.println("Please enter the system time quantum: ");
                    timeQuantum = Integer.parseInt(sc.nextLine());



                    for (int i = 0; i < numProcess; i++) {
                        processes[i] = new RobinProcess(i,arrivalTimes[i], burstTimes[i]);
                    }

                    Round_Robin scheduler = new Round_Robin(processes, timeQuantum);
                    String output = "" + scheduler.robinWaitTime();


                    System.out.println("**********************************************\nRound Robin Priority combined" +
                            " waiting time = " + output);
                    isValidInput = true;
                } catch (Exception e) {
                    System.out.println("Please enter valid input!");
                    isValidInput = false;
                }

            } else if (algorithm.equals("shortest job first")) {
                try {

                    System.out.println("Please enter the number of processes:");
                    int numProcess = Integer.parseInt(sc.nextLine());
                    int[] arrivalTimes = new int[numProcess];
                    int[] burstTimes = new int[numProcess];
                    ArrayList<SJFProcess> toPass = new ArrayList<SJFProcess>();

                    for (int i = 0; i < numProcess; i++) {
                        System.out.println("Please enter arrival time of process number " + (i + 1));
                        arrivalTimes[i] = Integer.parseInt(sc.nextLine());
                    }

                    for (int i = 0; i < numProcess; i++) {
                        System.out.println("Please enter burst time of process number " + (i + 1));
                        burstTimes[i] = Integer.parseInt(sc.nextLine());
                    }

                    for (int i = 0; i < numProcess; i++) {
                        toPass.add(new SJFProcess("SJFProcess" + i,
                                burstTimes[i], arrivalTimes[i]));
                    }

                    SJFQueue toRun = new SJFQueue(toPass);
                    toRun.averageWaitingTime();
                    String output = "" + toRun.averageWaiting();
                    System.out.println("**********************************************\nSJF waiting time = " + output);
                    isValidInput = true;
                } catch (Exception e) {
                    System.out.println("Please enter valid input!");
                    isValidInput = false;
                }
            } else if (algorithm.equals("round robin priority")) {
                try {

                    System.out.println("Please enter the number of processes:");
                    int numProcess = Integer.parseInt(sc.nextLine());
                    int[] priorities = new int[numProcess];
                    int[] burstTimes = new int[numProcess];
                    int timeQuantum = -1;

                    for (int i = 0; i < numProcess; i++) {
                        System.out.println("Please enter priority of process number " + (i + 1));
                        priorities[i] = Integer.parseInt(sc.nextLine());
                    }

                    for (int i = 0; i < numProcess; i++) {
                        System.out.println("Please enter burst time number " + (i + 1));
                        burstTimes[i] = Integer.parseInt(sc.nextLine());
                    }

                    System.out.println("Please enter the system time quantum: ");
                    timeQuantum = Integer.parseInt(sc.nextLine());

                    RRPriority test = new RRPriority(timeQuantum);
                    Process[] toAdd = new Process[numProcess];

                    for (int i = 0; i < numProcess; i++) {
                        toAdd[i] = new Process(burstTimes[i], priorities[i], i + "");
                        test.addProcess(toAdd[i]);
                    }

                    test.printQueue();

                    String log = "";
                    while (log != null) {
                        log = test.run();

                    }

                    String output = "" + test.getAverageWaitingTime();
                    System.out.println("**********************************************\nRound Robin Priority combined" +
                            " waiting time = " + output);
                    isValidInput = true;
                } catch (Exception e) {
                    System.out.println("Please enter valid input!");
                    isValidInput = false;
                }
            }
        }



    }
}
