package helpers;

import java.util.*;

public class Node {
    public GameTimer gametimer;
    ArrayList<Action> path;
    double score;

    public Node(int maxCookies) {
        this.gametimer = new GameTimer(maxCookies);
        path = new ArrayList<Action>(); 
        score = Double.POSITIVE_INFINITY;
    }

    public Node(int maxCookies, int cps) {
        this.gametimer = new GameTimer(maxCookies, cps);
        path = new ArrayList<Action>(); 
        score = Double.POSITIVE_INFINITY;
    }

    public Node(GameTimer gametimer){
        this.gametimer = gametimer;
        path = new ArrayList<Action>();
        score = Double.POSITIVE_INFINITY;
    }

    public void setTimer(double val){
        gametimer.time = val;
    }

    public void doAction(Action action) {
        getGameTimer().doAction(action);
        path.add(action);
    }

    public ArrayList<Node> getNeighboursv2(boolean canSell){
        ArrayList<Node> bois = new ArrayList<Node>();
        ArrayList<Action> actions = gametimer.getActionsv2(canSell);
        Node newNode;
        
        for (Action action : actions) {
            newNode = actAndCopy(action);
            bois.add(newNode);
        }
        return bois;
    }

    public Node copy() {
        Node newNode = new Node(gametimer.copy());
            for (Action action : path) {
                newNode.path.add(action);
            }
        return newNode;
    }

    public Node actAndCopy(Action action) {
        Node newNode = copy();
        newNode.path.add(action);
        newNode.gametimer.doAction(action);
        return newNode;
    }

    public void addAction(Action action) {
        this.path.add(action);
    }

    public ArrayList<Action> getPath(){
        return path;
    }

    public void purgePath(){
        path = new ArrayList<Action>();
    }

    public GameTimer getGameTimer(){
        return gametimer;
    }

    public void print(){
        gametimer.print();
    }

    public double getTime() {
        return gametimer.time;
    }

    public int getCookies(){
        return gametimer.gamestate.cookies;
    }

    public void setScore(double newScore) {
        score = newScore;
    }

    public double getScore() {
        return score;
    }

    // returns the difference in time between the nodes
    public static Node bestTime(ArrayList<Node> nodes) {
        double bestTime = Double.POSITIVE_INFINITY;
        Node bestNode = null;
        for (Node node : nodes) {
            if (node != null){
                if (node.getTime() < bestTime) {
                bestTime = node.getTime();
                bestNode = node;
                }
            }
            
        }
        return bestNode;
    }

    public GameState getGameState() {
        return gametimer.gamestate;
    }

    public void addToMap(HashMap<GameState, Double> map) {
        map.put(gametimer.gamestate, gametimer.time);
    }

    
    public double heuristic0() {
        return 0;
    }

    public double heuristic1() {
        double toGo = gametimer.gamestate.getTarget() - getCookies();
        return toGo / (50000 + gametimer.gamestate.cps_10);
        //return 0;
    }

    // fit exponential curve
    public double heuristic2() {
        int a = 300;
        double toGo = gametimer.gamestate.getTarget() - getCookies();
        double cps_10 = gametimer.gamestate.cps_10;
        // double fakecps = (double) getCookies() / 180;
        double t0 = a * Math.log(cps_10);
        double targ = cps_10 + toGo/a;
        double t1 = a * Math.log(targ);
        return t1-t0;
        //return 0;
    }

    // project cps at end, assume linear growth
    public double heuristic3() {
        double a = 10;
        double toGo = gametimer.gamestate.getTarget() - getCookies();
        double cpsAtEnd = (double)getGameState().getTarget() / a; // upper bound for cps_10 at target cookies
        double cps_10 = gametimer.gamestate.cps_10;
        double meancps = (cps_10 + cpsAtEnd) / 2;

        return toGo / meancps;

        //return 0;
    }

    public double heuristic4() {
        double out = 0;
        // for (int i = 0; i < 5; i++){
        //     if(getGameState().recentlySold[i]){
        //         out += i;
        //     }
        // }
        return out;
    }


    public void printMoves() {
        for (Action action : path) {
            action.print();
        }
    }

    public void printStats() {
        //System.out.println("Time: " + getTime() + "\tCPS: " + getGameState().cookies);
    }

    public void printPathNodes() {
        Node dummy = new Node(getGameState().targetCookies, getGameState().clicksPerSecond);
        dummy.printStats();
        for (Action action : path) {
            dummy.getGameTimer().doAction(action);
            action.print();
            dummy.print();
            System.out.println();
        }
    }

    public void printAsCoords() {
        Node dummy = new Node(getGameState().targetCookies);
        System.out.println("(" + dummy.getCookies() + "," + dummy.getGameState().cps_10 + ")");
        for (Action action : path) {
            dummy.getGameTimer().doAction(action);
            System.out.println("(" + dummy.getCookies() + "," + dummy.getGameState().cps_10 + ")");
        }
    }

    public void printAllMoves() {
        for (Action action : path) {
            action.printReadable();
        }
    }
}
