package helpers;

import java.util.*;


public class GameState {

    static int[] CURSOR_REQS = {1, 1, 10};
    static int[] UPGRADE_REQS = {1, 5, 25};
    static int FINGER_REQ = 25;
    static int FINGER_COST = 100000;
    static int[] BASE_COST = {15, 100, 1100, 12000, 130000};
    static int[] GRANDMA_UPGRADE_COSTS = {55000, 600000};
    static double SELL_EFFICIENCY = 0.25;

    // public static int MAX_COOKIES = 1000;
    static int[][] UPGRADE_COSTS = {{100, 500, 10000},
                                    {1000, 5000, 50000},
                                    {11000, 55000, 550000},
                                    {120000, 600000, 6000000}};
    static int[] BASE_MODIFIER = {1, 10, 80, 470, 2600}; // multiplied by 10

    int[] buildings; // array of number of each building
    public double[] modifiers; // value in cps of each building
    int cps_10; // 10x the value of cps
    public int[] double_upgrades; // counts the doubling upgrades for each building
    boolean thousand_fingers;
    int grandmaUpgrades; // require 15 of building, 1 grandma to upgrade
    int[] recentlySold;

    int non_clickers;
    int cookies; // total cookies
    int cookiesBanked; // used for 
    int targetCookies; // target for number of cookies
    boolean done; // flag for if game is finished
    // double time; // time in 10ths of seconds
    

    public GameState(int targetCookies) {
        buildings = new int[5];
        modifiers = new double[5];
        for (int i = 0; i < 5; i++) {
            modifiers[i] = BASE_MODIFIER[i];
        }
        //modifiers = BASE_MODIFIER; // multiplied by 10
        double_upgrades = new int[5];
        non_clickers = 0;
        cps_10 = 1;
        cookies = 15;
        buildings[0] = 1;
        this.targetCookies = targetCookies;
        this.recentlySold = new int[5];
        done = false;
        // time = 0;
    }

    public GameState copy() {
        GameState out = new GameState(targetCookies);
        for (int i = 0; i < 5; i++){
            out.buildings[i] = this.buildings[i];
            out.double_upgrades[i] = this.double_upgrades[i];
            out.modifiers[i] = this.modifiers[i];
        }
        out.cps_10 = this.cps_10;
        out.cookies = this.cookies;
        out.grandmaUpgrades = this.grandmaUpgrades;
        out.thousand_fingers = this.thousand_fingers;
        out.cookiesBanked = this.cookiesBanked;
        // out.time = this.time;

        return out;
    }

    public int getTarget() {
        return targetCookies;
    }

    public void updateModifiers() {
        for (int i = 0; i < 5; i++) {
            modifiers[i] = BASE_MODIFIER[i];
        }
        non_clickers = 0;
        for (int i = 0; i < 5; i++){
            modifiers[i] *= Math.pow(2, double_upgrades[i]);
            non_clickers += buildings[i];
        }
        non_clickers -= buildings[0];

        if (thousand_fingers) {
            modifiers[0] += non_clickers;
        }

        for (int i = 1; i <= grandmaUpgrades; i++){
            modifiers[1] *= 2;
            modifiers[i+1] *= (1 + (double) buildings[1] / 100 / i);
        }
    }

    public void updateCPS() {
        updateModifiers();
        cps_10 = 0;
        for (int i = 0; i < 5; i++){
            cps_10 += buildings[i] * modifiers[i];
        } 
    }

    public void setTarget(int newTarget) {
        targetCookies = newTarget;
    }

    public ArrayList<Action> getActionsv2() {
        ArrayList<Action> actions = new ArrayList<Action>();
        int maxCost = targetCookies - cookies;
        int cost;

        // add each building as action
        for (int i = 0; i < 5; i++){
            // convert this to lookup table
            if (recentlySold[i] == 0){
                cost = (int) Math.ceil(BASE_COST[i] * Math.pow(1.15, buildings[i])) - cookiesBanked;
                Counter2.count++;
                if (cost <= maxCost && cost >= 0) {
                    actions.add(new Action(0, cost, i));
                }
            }
        }

        // add clicker upgrade if possible
        if (double_upgrades[0] < 3) {
            if (CURSOR_REQS[double_upgrades[0]] <= buildings[0]){
                cost = UPGRADE_COSTS[0][double_upgrades[0]] - cookiesBanked;
                if (cost <= maxCost && cost >= 0){
                    actions.add(new Action(1, cost, 0));
                }
            }
        }

        // add other upgrades if possible
        for (int i = 1; i < 4; i++){
            if (double_upgrades[i] < 3) {
                if (UPGRADE_REQS[double_upgrades[i]] <= buildings[i]){
                    cost = UPGRADE_COSTS[i][double_upgrades[i]] - cookiesBanked;
                    if (cost <= maxCost && cost >= 0){
                        actions.add(new Action(1, cost, i));
                    }  
                }
            }
        }

        // add grandma upgrades if possible
        if (grandmaUpgrades < 2 && buildings[1] > 0) {
            
            // if enough buildings to unlock upgrade
            if (buildings[grandmaUpgrades + 2] >= 15){
                cost = GRANDMA_UPGRADE_COSTS[grandmaUpgrades] - cookiesBanked;
                if (cost <= maxCost && cost >= 0){
                    if (grandmaUpgrades == 1) {
                        grandmaUpgrades = 1;
                    }
                    actions.add(new Action(4, cost, -1));
                }
            }
        }

        // add thousand fingers if possible
        if (!thousand_fingers) {
            if (FINGER_REQ <= buildings[0]){
                cost = FINGER_REQ - cookiesBanked;
                if (FINGER_COST <= maxCost && FINGER_COST >= 0) {
                    actions.add(new Action(2, FINGER_COST, -1));
                }
            }
        }

        // only add wait if no other actions and didn't just sell
        boolean canWait = true;
        for (int i = 0; i < 5; i++){
            if (recentlySold[i] > 0){
                canWait = false;
                break;
            }
        }
        if (actions.isEmpty() & canWait) {
            actions.add(new Action(3, maxCost, -1));
        }
        else {
            // add sell actions
            for (int i = 0; i < 5; i++){
                if (buildings[i] > 0){
                    actions.add(new Action(5, 0, i, (int) Math.floor(Math.ceil(15 * Math.pow(1.15, buildings[i])) * SELL_EFFICIENCY)));
                }
            }

        }     
        return actions;
    }

    public void doAction(Action action){
        // add cost
        cookies += action.cost;
        done = cookies == targetCookies;

        if (action.flag == 5) { // sell
            cookiesBanked += action.sellCount;
            buildings[action.building]--;
            recentlySold[action.building]++;
            return;
        }
        switch (action.flag) {
            case 1:
                double_upgrades[action.building]++;
                break;
            case 0:
                buildings[action.building]++;
                break;
            case 2:
                thousand_fingers = true;
                break;
            case 4:
                grandmaUpgrades++;
                break;
            default:
                break;
            
        }
        updateCPS();
        for (int i = 0; i < 5; i++){
            recentlySold[i] = 0;
        }
        cookiesBanked = 0;  
    }

    public void print() {
        System.out.println("Cookies: " + cookies);
        System.out.println("Banked: " + cookiesBanked);
        // System.out.println("Time: " + time * 10);
        System.out.print("Buildings: ");
        for (int i = 0; i < 5; i++){
            System.out.print(buildings[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("CPS: " + cps_10);
        System.out.print("Upgrades: ");
        for (int i = 0; i < 5; i++){
            System.out.print(double_upgrades[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("Weird Upgrades: ");
        System.out.print(grandmaUpgrades + " ");
        System.out.println(thousand_fingers);
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GameState)){
            return false;
        }

        GameState gamestate = (GameState) o;

        for (int i = 0; i < 5; i++) {
            if (buildings[i] != gamestate.buildings[i]) {
                return false;
            }
            if (double_upgrades[i] != gamestate.double_upgrades[i]) {
                return false;
            }
        }

        if (thousand_fingers != gamestate.thousand_fingers) {
            return false;
        }

        if (grandmaUpgrades != gamestate.grandmaUpgrades) { 
            return false;
        }

        if (cookies != gamestate.cookies) {
            return false;
        }

        if (cookiesBanked != gamestate.cookiesBanked){
            return false;
        }

        return true;

    }

    @Override
    public int hashCode() {
        int temp = (double_upgrades[0] << 8) | (double_upgrades[1] << 6) | (double_upgrades[2] << 4) | (double_upgrades[3] << 2) | (double_upgrades[4]);
        for (int i = 0; i < 5; i++) {
            temp *= 13;
            temp += buildings[i];
        }
        temp *= 13;
        temp += grandmaUpgrades;
        temp *= 17;
        temp += cookiesBanked;
        temp *= 13;
    
        if (thousand_fingers) {
            temp += 4;
        }
        if (done) {
            temp += 8;
        }
        return temp;
    }

}
