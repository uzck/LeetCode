package medium;

import java.util.List;
import java.util.Stack;

/**
 * Rooms and Keys
 * 能否用钥匙打开所有门
 */
public class Problem841 {

    private int[] room;
    Stack<Integer> keys;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        room = new int[rooms.size()];
        keys = new Stack<>();
        visitRoom(rooms, 0);
        while(!keys.isEmpty()){
            int key = keys.pop();
            if(room[key] == 0) {
                visitRoom(rooms, key);
            }
        }
        for(int i : room) if(i == 0) return false;
        return true;
    }

    private void visitRoom(List<List<Integer>> rooms, Integer roomNum){
        for(int key: rooms.get(roomNum)){
            keys.push(key);
        }
        room[roomNum] = 1;
    }
}
