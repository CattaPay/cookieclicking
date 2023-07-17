import helpers.*;
import java.util.*;

public class SellingTesting {
    public static void main(String[] args) {
        // Node startNode = new Node(1000000);
        // ArrayList<Action> actions = new ArrayList<Action>();

        // for (int clickers = 0; clickers < 10; clickers++) {
        //     for (int toSell = 0; toSell <= clickers; toSell++){
        //         int grandmas = 0;
        //         int cps = clickers + grandmas * 10;

        //         int sellPrice = 0;
        //         for (int i = clickers; i > clickers - toSell; i--){
        //             sellPrice += (int) Math.floor(Math.ceil(15 * Math.pow(1.15, i)) / 4);
        //         }
        //         // System.out.println(sellPrice);
        //         double timeSaved = (double) sellPrice  / (double) cps;

        //         grandmas++;
        //         clickers -= toSell;
        //         cps = clickers + grandmas * 10;
        //         int returnCookies = 0;
        //         for (int i = clickers; i < clickers + toSell; i++){
        //             returnCookies += (int) Math.ceil(15 * Math.pow(1.15, i));
        //         }

        //         clickers += toSell;

        //         //System.out.println(returnCookies);
        //         double timeLost = (double) returnCookies / (double) cps;
        //         System.out.println(timeSaved - timeLost + ", " + clickers + ", " + toSell);

        //     }
        // }

        Node startNode = new Node(1000);

        ArrayList<Node> nextNodes = startNode.getNeighboursv2(true);
        for (Node node : nextNodes) {
            node.printAllMoves();
            node.print();
            
            System.out.println();
        }
    }
}
