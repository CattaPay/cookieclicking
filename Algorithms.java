import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import helpers.*;

public class Algorithms {
    
    // full tree search (1,419 nodes at 500; 111,556 nodes at 1000; 3,020,093 nodes at 1500)
    public static Node treeSearch(Node startNode, int target, Counter counter) {
        // base case
        counter.calls++;
        if (startNode.getCookies() >= target) {
            // startNode.print();
            return startNode;
        }

        counter.nonBase++;
        
        ArrayList<Node> nodes = startNode.getNeighboursv2();
        ArrayList<Node> newNodes = new ArrayList<Node>();

        for (Node node : nodes) {
            newNodes.add(treeSearch(node, target, counter));
        }
        return Node.bestTime(newNodes);
    }

    // hashmap to track nodes visited
    // hashcode ignores path and time, allowing comparison between states
    // 1,801 nodes at 1000; 2,567,954 nodes at 10000; 379,908 nodes at 5000; 7,658,142 nodes at 15000
    public static Node hashSearch(Node startNode, int target, Counter counter, HashMap<GameState, Double> map) {
        // base case
        counter.calls++;
        if (startNode.getCookies() >= target) {
            // startNode.print();
            return startNode;
        }
        counter.nonBase++;

        GameState gs = startNode.getGameState();
        Double time = startNode.getTime();

        if (map.containsKey(gs)){
            counter.map_hits++;
            if (map.get(gs) <= time){
                return null;
            }
        }
        map.put(gs, time);

        ArrayList<Node> nodes = startNode.getNeighboursv2();
        ArrayList<Node> newNodes = new ArrayList<Node>();

        for (Node node : nodes) {
            newNodes.add(hashSearch(node, target, counter, map));
        }
        return Node.bestTime(newNodes);
    }

    // hashSearch with a stopping condition if node time greater than best finish time
    // implemented through counter
    // 1,092 nodes at 1000; 334,942 nodes at 10000; 1,431,015 nodes at 20000
    public static Node hashSearchStop(Node startNode, int target, Counter counter, HashMap<GameState, Double> map) {
        counter.calls++;
        Double time = startNode.getTime();

        if (time >= counter.stopTime) {
            counter.stopHits++;
            return null;
        }
        
        // base case
        if (startNode.getCookies() >= target) {
            // counter.calls++;
            // startNode.print();
            if (time < counter.stopTime) {
                counter.stopTime = time;
            }
            return startNode;
        }
        counter.nonBase++;
        
        GameState gs = startNode.getGameState();

        if (map.containsKey(gs)){
            counter.map_hits++;
            if (map.get(gs) <= time){
                return null;
            }
        }
        map.put(gs, time);

        ArrayList<Node> nodes = startNode.getNeighboursv2();
        ArrayList<Node> newNodes = new ArrayList<Node>();

        for (Node node : nodes) {
            newNodes.add(hashSearchStop(node, target, counter, map));
        }
        return Node.bestTime(newNodes);
    }

    // same as hashSearchStopv2 but with improved getActions
    public static Node hashSearchStopv2(Node startNode, int target, Counter counter, HashMap<GameState, Double> map) {
        counter.calls++;
        Double time = startNode.getTime();

        if (time >= counter.stopTime) {
            counter.stopHits++;
            return null;
        }
        
        // base case
        if (startNode.getCookies() >= target) {
            // counter.calls++;
            // startNode.print();
            if (time < counter.stopTime) {
                counter.stopTime = time;
            }
            return startNode;
        }
        counter.nonBase++;
        
        GameState gs = startNode.getGameState();

        if (map.containsKey(gs)){
            counter.map_hits++;
            if (map.get(gs) <= time){
                return null;
            }
        }
        map.put(gs, time);

        ArrayList<Node> nodes = startNode.getNeighboursv2();
        ArrayList<Node> newNodes = new ArrayList<Node>();

        for (Node node : nodes) {
            newNodes.add(hashSearchStopv2(node, target, counter, map));
        }
        return Node.bestTime(newNodes);
    }

    // next steps
    // some sort of priority for nodes (maybe just a simple start with the cheapest)
    // or set up proper priority queue for nodes instead of simple tree search thing


    // maybe implement A*... add score to Node, come up with heuristic
    public static Node A_Star(Node startNode, int target, Counter counter) {
        PriorityQueue<Node> openNodes = new PriorityQueue<Node>(new NodeSorter());
        HashMap<GameState, Double> closedSet = new HashMap<GameState, Double>();
        
        // put first node in open queue
        openNodes.add(startNode);

        Node newNode;
        double time;
        double maxEndTime = Double.POSITIVE_INFINITY;
        GameState gs;
        ArrayList<Node> nodes = new ArrayList<Node>();
        double nextTime;
        GameState nextgs;
        Node bestNode = null;


        // while open queue has nodes:
        while(!openNodes.isEmpty()) {
            counter.calls++;
            // get node from open queue
            newNode = openNodes.poll();
            time = newNode.getTime();
            gs = newNode.getGameState();
            

            // check if node is in closed set
            if (closedSet.containsKey(gs)){
                if (time >= closedSet.get(gs)){
                    continue;
                }
            }

            // 
            if (newNode.getCookies() >= target) {
                if (time < maxEndTime){
                    bestNode = newNode;
                    maxEndTime = time;
                    continue;
                }
            }

            // add node and time to closed set (hashmap)
            closedSet.put(gs, time);

            // for each neighbour:
            nodes = newNode.getNeighboursv2();

            for (Node node : nodes) {
                // check if in closed set
                nextTime = node.getTime();
                nextgs = node.getGameState();
                if (closedSet.containsKey(nextgs)) {
                    if (closedSet.get(nextgs) <= nextTime) {
                        counter.map_hits++;
                        continue;
                    }
                    else {
                        closedSet.remove(nextgs);
                    }
                }
                // if not or better, calc score and add to open queue
                node.setScore(node.heuristic0() + nextTime);
                openNodes.add(node);
            }


        }
        return bestNode;
    }

    public static Node A_Starv2(Node startNode, int target, Counter counter) {
        PriorityQueue<Node> openNodes = new PriorityQueue<Node>(new NodeSorter());
        HashMap<GameState, Double> closedSet = new HashMap<GameState, Double>();
        
        // put first node in open queue
        openNodes.add(startNode);

        Node newNode;
        double time;
        double maxEndTime = Double.POSITIVE_INFINITY;
        GameState gs;
        ArrayList<Node> nodes = new ArrayList<Node>();
        double nextTime;
        GameState nextgs;
        Node bestNode = null;


        // while open queue has nodes:
        while(!openNodes.isEmpty()) {
            counter.calls++;
            // get node from open queue
            newNode = openNodes.poll();
            time = newNode.getTime();
            gs = newNode.getGameState();
            

            // check if node is in closed set
            if (closedSet.containsKey(gs)){
                if (time >= closedSet.get(gs)){
                    continue;
                }
            }

            // 
            if (newNode.getCookies() >= target) {
                if (time < maxEndTime){
                    bestNode = newNode;
                    maxEndTime = time;
                    continue;
                }
            }

            // add node and time to closed set (hashmap)
            closedSet.put(gs, time);

            // for each neighbour:
            nodes = newNode.getNeighboursv2();

            for (Node node : nodes) {
                // check if in closed set
                nextTime = node.getTime();
                nextgs = node.getGameState();
                if (closedSet.containsKey(nextgs)) {
                    if (closedSet.get(nextgs) <= nextTime) {
                        counter.map_hits++;
                        continue;
                    }
                    else {
                        closedSet.remove(nextgs);
                    }
                }
                // if not or better, calc score and add to open queue
                // currently using heuristic0
                node.setScore(node.heuristic0() + nextTime);
                openNodes.add(node);
            }


        }
        return bestNode;
    }

    public static Node stonkFish() {
        int[] depthIncrements = {20000, 60000};
        int[] keepIncrements = {10000, 20000};
        int maxVal = 10000000;
        Counter counter = new Counter();
        
        Node solution = new Node(15);
        Node startNode = new Node(15);
        Node midNode;
        long startTime;
        long endTime;

        int keepVal = 0;
        int jumpVal = 0;

        int depthInc;
        int keepInc;
        int depthKept = 0;


        for (int i = 0; i < depthIncrements.length; i++) {
            depthInc = depthIncrements[i];
            keepInc = keepIncrements[i];
            keepVal += keepInc;
            jumpVal = depthKept + depthInc;

            startNode.getGameState().setTarget(jumpVal);
            startNode.purgePath();

            startTime = System.nanoTime();
            midNode = Algorithms.A_Starv2(startNode, jumpVal, counter);
            endTime = System.nanoTime();

            for (Action action: midNode.getPath()) {
                if (solution.getCookies() + action.cost >= keepVal) {
                    depthKept = solution.getCookies();
                    break;
                }
                solution.doAction(action);
                // System.out.print(i + ": ");
                // action.print();
            }

            startNode = solution.copy();

            // System.out.print(midNode.getPath().size() + ", ");
            // System.out.println((double)(endTime - startTime) / 1000000);
            // System.out.println("----------");
        }

        // first 23466 cookies optimal up to 80000ish
        // solution.print();

        // search then make one move
        int nextDepth = 0;
        int currentCookies;
        Action oneAction;
        int depthChange;
        double smallCookies;
        
        // double targetTime = 100;

        while (true){
            startNode = solution.copy();
            startNode.purgePath();
            currentCookies = solution.getCookies();
            smallCookies = (double) (currentCookies) / 30000;

            depthChange = (int)(200000 * Math.pow(smallCookies, 0.28));

            // depthChange = (int)(110000 * Math.pow(smallCookies, 0.36));
            nextDepth = currentCookies + depthChange;


            if (nextDepth > maxVal - 100000){
                nextDepth = maxVal;
                break;
            }

            startNode.getGameState().setTarget(nextDepth);

            startTime = System.nanoTime();
            midNode = Algorithms.A_Starv2(startNode, nextDepth, counter);
            endTime = System.nanoTime();
            System.out.print(currentCookies + ", " + depthChange + ": ");
            System.out.print((double) (endTime-startTime) / 1000000 + ", ");
            
            oneAction = midNode.getPath().get(0);

            solution.doAction(oneAction);
            oneAction.print();
        }


        solution.getGameState().setTarget(nextDepth);

        startTime = System.nanoTime();
        solution = Algorithms.A_Starv2(solution, nextDepth, counter);
        endTime = System.nanoTime();

        System.out.println((double) (endTime-startTime) / 1000000);

        solution.print();
        solution.printMoves();

        return solution;

        // int tests = maxVal;
        // solution = Tester.A_Starv2Tester(tests, true);
        // solution.print();
        // solution.printMoves();

        
    }

}
