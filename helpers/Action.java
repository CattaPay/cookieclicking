package helpers;

public class Action {
    int flag; // 0 if is a building, 1 if normal upgrade, 2 if thousand_fingers, 3 if wait until end
    public int cost; // cost of action
    int building; // the building in question

    public Action(int flag, int cost, int building){
        this.flag = flag;
        this.cost = cost;
        this.building = building;
    }
    
    public void print(){
        System.out.print(flag + " " + building + " ");
        System.out.println(cost);
    }
}
