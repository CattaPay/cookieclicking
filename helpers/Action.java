package helpers;

public class Action {
    int flag; // 0 if is a building, 1 if normal upgrade, 2 if thousand_fingers, 3 if wait until end, 4 is super (grandma upgra)
    public int cost; // cost of action
    int building; // the building in question
    int sellCount;

    // flag == 0: building
    // flag == 1: normal upgrade
    // flag == 2: thousand_fingers
    // flag == 3: wait 
    // flag == 4: super upgrade
    // flag == 5: sell
    public Action(int flag, int cost, int building){
        this.flag = flag;
        this.cost = cost;
        this.building = building;
        this.sellCount = 0;
    }

    public Action(int flag, int cost, int building, int sellCount){
        this.flag = flag;
        this.cost = cost;
        this.building = building;
        this.sellCount = sellCount;
    }
    
    public void print(){
        System.out.print(flag + " " + building + " " + cost + " ");
        System.out.println(sellCount);
    }

    public void printReadable() {
        String out = "";
        switch (flag) {
            case 3:
                out = "wait";
                break;
            case 4:
                out = "Super_up";
                break;
            case 2:
                out = "thousand_fingers";
                break;
            case 5:
                out = "Sell_";
            default:
                switch (building) {
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
        if (flag == 1) {
            out += "_up";
        }
        System.out.println(out);
    }

}
