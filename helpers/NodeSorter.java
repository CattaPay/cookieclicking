package helpers;

import java.util.*;

public class NodeSorter implements Comparator<Node> {

    public int compare(Node a, Node b) {
        if (a.score > b.score){
            return 1;
        }
        if (a.score == b.score) {
            return 0;
        }
        return -1;
    }
}
