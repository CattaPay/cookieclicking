package helpers;
import java.util.*;

public class PrettyPrinter {
    Node node;
    ArrayList<Action> actions;
    
    public PrettyPrinter(Node node){
        this.node = node;
    }

    public void printAllMoves(){
        Node interim = new Node(node.getCookies(), node.getGameState().clicksPerSecond);
        for (Action action : node.path) {
            interim.doAction(action);
            String out = "";
            switch (action.flag) {
                case 3:
                    out = "wait";
                    break;
                case 4:
                    out = "Super_up";
                    break;
                case 2:
                    out = "Thou. Fingers";
                    break;
                case 5:
                    out = "Sell_";
                    switch (action.building) {
                        case 0:
                            out += "Cursor";
                            break;
                        case 1:
                            out += "Grandma";
                            break;
                        case 2:
                            out += "Farm";
                            break;
                        case 3:
                            out += "Mine";
                            break;
                        case 4:
                            out += "Factory";
                            break; 
                        default:
                            break;
                }
                case 6:
                    out = "ClickUpgrade";
                    break;
                case 0:
                    switch (action.building) {
                        case 0:
                            out += "Cursor";
                            break;
                        case 1:
                            out += "Grandma";
                            break;
                        case 2:
                            out += "Farm";
                            break;
                        case 3:
                            out += "Mine";
                            break;
                        case 4:
                            out += "Factory";
                            break; 
                        default:
                            break;
                    }
                    out += " ";
                    out += Integer.toString(interim.getGameState().buildings[action.building]);
                    break;

                default:
                    switch (action.building) {
                    case 0:
                        out += "Cursor";
                        break;
                    case 1:
                        out += "Grandma";
                        break;
                    case 2:
                        out += "Farm";
                        break;
                    case 3:
                        out += "Mine";
                        break;
                    case 4:
                        out += "Factory";
                        break; 
                    default:
                        break;
                }
                    break;
            }
            if (action.flag == 1) {
                out += "_up";
            }

            int cost = action.cost;
            int cookies = interim.getCookies();
            double time = interim.getTime();
            System.out.format("%-15s%10d%10d\t%.3f\n", out, cost, cookies, time*10);

        }

    }
    
}
