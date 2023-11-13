abstract class CPUPriority{
    class CustomPriorityQueue {
        private static class Node {
            //Variables-Node
            private Process process;
            private Node prev;
            private Node next;
            public Node (Process process, Node prev, Node next){
                this.process = process;
                this.prev = prev;
                this.next = next;
            }

            //Getters-Node
            public Process getProcess(){
                return process;
            }
            public Node getPrev(){
                return prev;
            }
            public Node getNext(){
                return next;
            }
            //Setters-Node
            public void setNext(Node next) {
                this.next = next;
            }
            public void setPrev(Node prev) {
                this.prev = prev;
            }
            public void setProcess(Process process) {
                this.process = process;
            }
        }

        //Variables-CustomPriorityQueue
        private Node header;
        private Node tail;
        private int size;

        CustomPriorityQueue(){
            header = new Node(null, null, null);
            tail = new Node(null, header, null);
            header.setNext(tail);
            size = 0;
        }

        public Node getHeader(){
            return header;
        }
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
        public int getSize() {
            return size;
        }

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

        public void printQueue(){
            Node currentNode = header;

            for (int i = 0; i < size; i++) {
                System.out.println(currentNode.getProcess().getName());
                currentNode = currentNode.getNext();
            }
        }

    }
    private CustomPriorityQueue queue;

    public CPUPriority(){
        this.queue = new CustomPriorityQueue();
    }

    public CPUPriority(CustomPriorityQueue queue){
        this.queue = queue;
    }

    public void addProcess(Process p){
        queue.add(p);
    }
    public void terminateProcess(Process p){
        queue.remove(p);
    }
    public boolean hasPriority(int Priority){
        return queue.hasPriority(Priority);
    }
    public Process findNextProcess(Process process){
        return queue.findNextProcess(process);
    }

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
    public CustomPriorityQueue getQueue() {
        return queue;
    }
    public int getSize(){
        return queue.getSize();
    }

    public void printQueue(){
        queue.printQueue();
    }

    public void waitAll(Process process){
        queue.waitAll(process);
    }

    abstract public String run();
}
