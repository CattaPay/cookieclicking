
import helpers.*;

public class CookieClicker {

    public static void main(String[] args) {
        
        // // // solution = Tester.treeSearchTester(tests, true);
        // // // solution = Tester.hashSearchTester(tests, true);
        // // // solution = Tester.hashSearchStopTester(tests, true);
        // // // solution.print();

        // // //solution = Tester.hashSearchStopTester(tests, true);
        // // solution.print();
        // // solution = Tester.A_StarTester(tests, true);
        // // solution.print();
        // int tests = 80000;
        // solution = Tester.A_Starv2Tester(tests, true);
        // solution.print();
        // solution.printMoves();

        // System.out.println(Counter2.count);


        // depth increment + sum of all previous keep increments must be < maxVal
        // keep increments must be beeg
        // depth increments must be as big as possible

        // solution = Algorithms.stonkFish();
        // solution.printAllMoves();

        // solution.printPathNodes();


        // solution = Tester.A_Starv2Tester(650, true);
        // solution.print();
        // solution.printAllMoves();
        // solution.printMoves();

        // next steps:
        // more selling optimizations?
        // lock in reasonable starting actions

        int target = 1000000;
        // Node clickNode = new Node(target, 8);

        Node clickNode = Algorithms.stonkFishStart(false, 7);
        
        Node solution =  Algorithms.stonkFish(false, clickNode, target);

        // solution.print();
        solution.printAllMoves();


        
        // solution.printPathNodes();


        // Node solution = Tester.A_Starv2Tester(target, true, true, startNode);     
        // solution.print();
        // solution.printMoves();

    }

    
}
