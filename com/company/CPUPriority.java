package com.company;

abstract class CPUPriority{
    class CustomPriorityQueue {
        private static class Node {
            //Variables-Node
            private Process process;
            private Node prev;
            private Node next;

            /**
             * Instantiates a Node object
             *
             * @param process a given process
             * @param prev the previous node
             * @param next the next node
             */
            public Node (Process process, Node prev, Node next){
                this.process = process;
                this.prev = prev;
                this.next = next;
            }

            //Getters-Node

            /**
             * @return process in Node
             */
            public Process getProcess(){
                return process;
            }

            /**
             * @return previous Node
             */
            public Node getPrev(){
                return prev;
            }

            /**
             * @return next Node
             */
            public Node getNext(){
                return next;
            }

            //Setters-Node

            /**
             * Changes the next Node to @next
             * @param next the new next Node
             */
            public void setNext(Node next) {
                this.next = next;
            }

            /**
             * Changes the previous Node to @prev
             * @param prev the new previous Node
             */
            public void setPrev(Node prev) {
                this.prev = prev;
            }

            /**
             * Changes the process to @process
             * @param process the new Process
             */
            public void setProcess(Process process) {
                this.process = process;
            }
        }

        //Variables-CustomPriorityQueue
        private Node header;
        private Node tail;
        private int size;

        /**
         * Instantiates a new CustomPriorityQueue object. It assumes an empty queue where the header will point to
         * the tail and will not have any processes assigned to them other than null. Since the queue is empty
         * the size is also set to 0.
         */
        CustomPriorityQueue(){
            header = new Node(null, null, null);
            tail = new Node(null, header, null);
            header.setNext(tail);
            size = 0;
        }

        /**
         * @return the header of the queue
         */
        public Node getHeader(){
            return header;
        }

        /**
         * This method will count the number of processes in the queue with the same priority as @priority
         * and returns that amount.
         *
         * @param priority the priority we want to search for
         * @return the number of processes with @priority
         */
        public int getPriorityCount(int priority){
            int count = 0;

            Node currentNode = header;
            while (currentNode != null){

                if (currentNode.getProcess().getPriority() == priority){
                    count++;
                }

                currentNode = currentNode.getNext();
            }

            return count;
        }

        public Process getNextValidProcess(Process p){
            return null;
        }

        /**
         * @return current size of queue
         */
        public int getSize() {
            return size;
        }

        /**
         * Checks if the queue has a node whose process has the same priority as @priority
         * @param priority the priority we want to check
         * @return true if the queue has priority
         */
        public boolean hasPriority(int priority){
            boolean hasPriority = false;
            int priorityCount = 0;
            Node currentNode = header;
            while (currentNode != null){
                if (currentNode.getProcess() != null){
                    if (currentNode.getProcess().getPriority() == priority){
                        priorityCount++;
                    }
                }
                currentNode = currentNode.getNext();
            }

            return priorityCount > 1;
        }

        /**
         * The method removes the Node that contains the given process.
         * @param process the process we want to remove
         * @return the process that was passed
         */
        public Process remove(Process process){
            Node currentNode = header;
            while (currentNode != null){
                if (currentNode.getProcess() == process){
                    if (currentNode == header){
                        if (size == 1){
                            header.setProcess(null);
                            header.setNext(tail);
                        }else{
                            Node next = currentNode.getNext();
                            next.setPrev(null);
                            header = next;
                        }
                    }else if (currentNode == tail){
                        Node pre = currentNode.getPrev();
                        pre.setNext(null);
                        tail = pre;
                    }else{
                        Node pre = currentNode.getPrev();
                        Node next = currentNode.getNext();
                        pre.setNext(next);
                        next.setPrev(pre);
                    }
                    size--;
                    return currentNode.getProcess();
                }
                currentNode = currentNode.getNext();
            }
            return null;
        }

        /**
         * Adds a process in the queue according to a priority queue based on its priority in process, from higher
         * to lower priority.
         * @param p the process we want to add
         */
        public void add(Process p) {
            if (size == 0){
                header.setProcess(p);
                size++;
                return;
            }else if (size == 1){
                if (p.getPriority() < header.getProcess().getPriority()){
                    tail.setProcess(header.getProcess());
                    header.setProcess(p);
                }else {
                    tail.setProcess(p);
                }
                size++;
                return;
            }

            Node currentNode = header;
            for(int i = 0; i < size; i++){
                if (p.getPriority() < currentNode.getProcess().getPriority()){
                    if (currentNode == header){
                        Node newNode = new Node(p, null, currentNode);
                        currentNode.setPrev(newNode);
                        header = newNode;
                    }else {
                        Node prev = currentNode.getPrev();
                        Node newNode = new Node(p, prev, currentNode);
                        prev.setNext(newNode);
                        currentNode.setPrev(newNode);
                    }
                    size++;
                    return;
                }else if((currentNode == tail)){
                    Node newNode = new Node(p, currentNode, null);
                    currentNode.setNext(newNode);
                    tail = newNode;
                    size++;
                    return;
                }
                currentNode = currentNode.getNext();
            }
        }

        /**
         * Calculates the next process in the queue with the same priority as the given process. If no other processes
         * with the same priority return null.
         * @param process the reference process
         * @return the next valid process
         */
        public Process findNextProcess(Process process){
            //Find node
            Node tempNode = findNode(process);
            Process returnProcess = null;

            //Check after Node - return first
            Node checkerNode = tempNode.getNext();
            while (checkerNode != null){
                if (tempNode.getProcess().getPriority() == checkerNode.getProcess().getPriority()){
                    return checkerNode.getProcess();
                }
                checkerNode = checkerNode.getNext();
            }

            //Check before Node - return last
            checkerNode = tempNode.getPrev();
            while (checkerNode != null){
                if (tempNode.getProcess().getPriority() == checkerNode.getProcess().getPriority()){
                    returnProcess = checkerNode.getProcess();
                }
                checkerNode = checkerNode.getPrev();
            }

            return returnProcess;
        }

        /**
         * Finds the Node that contains the given process and returns it. Otherwise return null (no Node contains
         * the given process).
         * @param process the reference process
         * @return the respective node
         */
        private Node findNode(Process process){
            Node currentNode = header;

            while (currentNode != null){
                if (currentNode.getProcess() == process){
                    return currentNode;
                }
                currentNode = currentNode.getNext();
            }

            return null;
        }

        /**
         * Runs the addWaitingTime() for each process in the queue except for the process given
         * @param process the process we don't want to wait
         */
        public void waitAll(Process process){
            Node currentNode = header;

            while (currentNode != null){
                if (currentNode.getProcess() != null){
                    if (currentNode.getProcess() != process){
                        currentNode.getProcess().addWaitingTime();
                    }
                }
                currentNode = currentNode.getNext();
            }
        }

        /**
         * Prints the queue
         */
        public void printQueue(){
            Node currentNode = header;

            for (int i = 0; i < size; i++) {
                System.out.println(currentNode.getProcess().getName());
                currentNode = currentNode.getNext();
            }
        }

    }
    private CustomPriorityQueue queue;

    /**
     * Instantiates a CPUPriority object with an empty queue
     */
    public CPUPriority(){
        this.queue = new CustomPriorityQueue();
    }

    public CPUPriority(CustomPriorityQueue queue){
        this.queue = queue;
    }

    /**
     * Adds the given process to queue
     * @param p the process we want to add
     */
    public void addProcess(Process p){
        queue.add(p);
    }

    /**
     * Removes the process from the queue
     * @param p the process we want to remove
     */
    public void terminateProcess(Process p){
        queue.remove(p);
    }

    /**
     * Checks if the queue has a node whose process has the same priority as @priority
     * @param Priority the priority we want to check
     * @return true if the queue has priority
     */
    public boolean hasPriority(int Priority){
        return queue.hasPriority(Priority);
    }

    /**
     * Calculates the next process in the queue with the same priority as the given process. If no other processes
     * with the same priority return null.
     * @param process the reference process
     * @return the next valid process
     */
    public Process findNextProcess(Process process){
        return queue.findNextProcess(process);
    }

    /**
     * @return the front of the queue
     */
    public Process getHeadProcess(){
        return queue.getHeader().getProcess();
    }

    public Process getNextValidProcess(Process p){
        return queue.getNextValidProcess(p);
    }
    public int getLowestPriority(){
        return queue.getHeader().getProcess().getPriority();
    }
    public int getLowestPriorityCount(int priority){
        return queue.getPriorityCount(priority);
    }

    /**
     * @return the queue
     */
    public CustomPriorityQueue getQueue() {
        return queue;
    }

    /**
     * @return the size of the queue
     */
    public int getSize(){
        return queue.getSize();
    }

    /**
     * Prints the queue
     */
    public void printQueue(){
        queue.printQueue();
    }

    /**
     * Runs the addWaitingTime() for each process in the queue except for the process given
     * @param process the process we don't want to wait
     */
    public void waitAll(Process process){
        queue.waitAll(process);
    }

    abstract public String run();
}
