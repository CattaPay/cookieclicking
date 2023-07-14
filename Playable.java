
import helpers.*;
import java.util.*;
import java.io.*;

public class Playable {
    public static void main(String[] args) {
        Node game = new Node(1000000);
        ArrayList<Action> actions;
        Scanner sc = new Scanner(System.in); 
        int in;
        int counter = 1;

        while (true){
            actions = game.getGameState().getActionsv2();
            // System.out.println(counter);
            game.print();
            for (int i = 0; i < actions.size(); i++){
                System.out.print((i + 1) + ": ");
                actions.get(i).print();
            }
            System.out.println();
            in = sc.nextInt();
            System.out.println();

            if (in == 0){
                game.print();
            }
            else if (in == -1){
                break;
            }
            else {
                game.doAction(actions.get(in - 1));
                counter++;
            }
        }

        sc.close();
        
        
    }
    
}
