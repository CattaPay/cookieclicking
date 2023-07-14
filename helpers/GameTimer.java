package helpers;

import java.util.ArrayList;

// GameTimer splits the gamestate from the timer
// allows for hashmap of gamestate to compare
public class GameTimer {
    GameState gamestate;
    double time;

    public GameTimer(int targetCookies) {
        gamestate = new GameState(targetCookies);
        time = 0;
    }

    public GameTimer copy() {
        GameTimer newGameTimer = new GameTimer(gamestate.getTarget());
        newGameTimer.gamestate = gamestate.copy();
        newGameTimer.time = time;
        return newGameTimer;
    }

    public void print() {
        System.out.println("Time: " + time);
        gamestate.print();
    }

    public void doAction(Action action) {
        // add time
        time += ((double) action.cost) / ((double)gamestate.cps_10);

        // rest of action
        gamestate.doAction(action);
    }


    public ArrayList<Action> getActionsv2() {
        return gamestate.getActionsv2();
    }

    
}
