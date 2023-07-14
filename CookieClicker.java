
import helpers.*;

public class CookieClicker {

    public static void main(String[] args) {
        
        Node solution;
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

        // solution = Tester.treeSearchTester(250, true);
        // solution.print();
        // solution.printAllMoves();

        solution = Tester.hashSearchTester(500, true);
        solution.print();
        solution.printAllMoves();
        solution.printMoves();

    }

    
}
