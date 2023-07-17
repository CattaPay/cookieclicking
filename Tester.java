
import helpers.*;
import java.util.*;

public class Tester {
    public static Node treeSearchTester(int target, boolean verbose, boolean canSell){
        Node startNode = new Node(target);
        Counter counter = new Counter();
        Long startTime = System.nanoTime();

        Node solution = Algorithms.treeSearch(startNode, target, counter, canSell);
        Long stopTime = System.nanoTime();
        Long duration = stopTime-startTime;

        double ms = duration / 1000000;
        int rate = (int) (counter.calls / ms * 1000);

        if (verbose) {
            System.out.println("## Full Tree Search ##");
            System.out.println("Cookies: " + target);
            System.out.println("All Calls: " + counter.calls);
            System.out.println("Non Base Case: " + counter.nonBase);
            System.out.println("Time(ms): " + ms);
            System.out.println("Nodes per Second: " + rate);
            System.out.println();
        }
        return solution;
    } 

    public static Node hashSearchTester(int target, boolean verbose, boolean canSell){
        Node startNode = new Node(target);
        Counter counter = new Counter();
        HashMap<GameState,Double> map = new HashMap<GameState,Double>();

        Long startTime = System.nanoTime();

        Node solution = Algorithms.hashSearch(startNode, target, counter, map, canSell);

        Long stopTime = System.nanoTime();
        Long duration = stopTime-startTime;

        double ms = duration / 1000000;
        int rate = (int) (counter.calls / ms * 1000);
        int ratenb = (int) (counter.nonBase / ms * 1000);

        if (verbose) {
            System.out.println("## Search With Hashmap ##");
            System.out.println("Cookies: " + target);
            System.out.println("All Calls: " + counter.calls);
            System.out.println("Non Base Case: " + counter.nonBase);
            System.out.println("Map Hits: " + counter.map_hits);
            System.out.println("Time(ms): " + ms);
            System.out.println("Nodes per Second: " + rate);
            System.out.println("Non Base Nodes per Second: " + ratenb);
            System.out.println();
        }
        return solution;
    } 

    public static Node hashSearchStopv2Tester(int target, boolean verbose, boolean canSell){
        Node startNode = new Node(target);
        Counter counter = new Counter();
        HashMap<GameState,Double> map = new HashMap<GameState,Double>();

        Long startTime = System.nanoTime();

        Node solution = Algorithms.hashSearchStopv2(startNode, target, counter, map, canSell);

        Long stopTime = System.nanoTime();
        Long duration = stopTime-startTime;

        double ms = duration / 1000000;
        int rate = (int) (counter.calls / ms * 1000);
        int ratenb = (int) (counter.nonBase / ms * 1000);

        if (verbose) {
            System.out.println("## Search With Hashmap and Stop Conditions ##");
            System.out.println("Cookies: " + target);
            System.out.println("All Calls: " + counter.calls);
            System.out.println("Stop Hits: " + counter.stopHits);
            System.out.println("Non Base Case: " + counter.nonBase);
            System.out.println("Map Hits: " + counter.map_hits);
            System.out.println("Time(ms): " + ms);
            System.out.println("Nodes per Second: " + rate);
            System.out.println("Non Base Nodes per Second: " + ratenb);
            System.out.println();
        }
        return solution;
    } 

    public static Node A_Starv2Tester(int target, boolean verbose, boolean canSell){
        Node startNode = new Node(target);
        Counter counter = new Counter();
        Long startTime = System.nanoTime();

        Node solution = Algorithms.A_Starv2(startNode, target, counter, canSell);

        Long stopTime = System.nanoTime();
        Long duration = stopTime-startTime;

        double ms = duration / 1000000;
        int rate = (int) (counter.calls / ms * 1000);
        int ratenb = (int) (counter.nonBase / ms * 1000);

        if (verbose) {
            System.out.println("## A_Star Algorithm with Heuristic 0 ##");
            System.out.println("Cookies: " + target);
            System.out.println("All Calls: " + counter.calls);
            System.out.println("Stop Hits: " + counter.stopHits);
            System.out.println("Non Base Case: " + counter.nonBase);
            System.out.println("Map Hits: " + counter.map_hits);
            System.out.println("Time(ms): " + ms);
            System.out.println("Nodes per Second: " + rate);
            System.out.println("Non Base Nodes per Second: " + ratenb);
            System.out.println();
        }
        return solution;
    } 

    public static Node A_Starv2Tester(int target, boolean verbose, boolean canSell, Node startNode){
        startNode.getGameState().setTarget(target);
        Counter counter = new Counter();
        Long startTime = System.nanoTime();

        Node solution = Algorithms.A_Starv2(startNode, target, counter, canSell);

        Long stopTime = System.nanoTime();
        Long duration = stopTime-startTime;

        double ms = duration / 1000000;
        int rate = (int) (counter.calls / ms * 1000);
        int ratenb = (int) (counter.nonBase / ms * 1000);

        if (verbose) {
            System.out.println("## A_Star Algorithm with Heuristic 0 ##");
            System.out.println("Cookies: " + target);
            System.out.println("All Calls: " + counter.calls);
            System.out.println("Stop Hits: " + counter.stopHits);
            System.out.println("Non Base Case: " + counter.nonBase);
            System.out.println("Map Hits: " + counter.map_hits);
            System.out.println("Time(ms): " + ms);
            System.out.println("Nodes per Second: " + rate);
            System.out.println("Non Base Nodes per Second: " + ratenb);
            System.out.println();
        }
        return solution;
    } 

    public static Node A_Starv2MaxTester(int target, boolean verbose, boolean canSell, Node startNode, double maxTime){
        startNode.getGameState().setTarget(target);
        Counter counter = new Counter();
        Long startTime = System.nanoTime();

        Node solution = Algorithms.A_Starv3(startNode, target, counter, canSell, maxTime);

        Long stopTime = System.nanoTime();
        Long duration = stopTime-startTime;

        double ms = duration / 1000000;
        int rate = (int) (counter.calls / ms * 1000);
        int ratenb = (int) (counter.nonBase / ms * 1000);

        if (verbose) {
            System.out.println("## A_Star Algorithm with Heuristic 0 ##");
            System.out.println("Cookies: " + target);
            System.out.println("All Calls: " + counter.calls);
            System.out.println("Stop Hits: " + counter.stopHits);
            System.out.println("Non Base Case: " + counter.nonBase);
            System.out.println("Map Hits: " + counter.map_hits);
            System.out.println("Time(ms): " + ms);
            System.out.println("Nodes per Second: " + rate);
            System.out.println("Non Base Nodes per Second: " + ratenb);
            System.out.println();
        }
        return solution;
    } 

}