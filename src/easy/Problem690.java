package easy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 输出employee自身和所有下属的importance value和
 */
public class Problem690 {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    /**
     * 4ms 100%
     * 思想挺简单 这里的耗时操作主要在list.get(id).id != id 也就是存放的位置不在指定的id上
     * 这样就多了一个循环查找的过程 题目限制了最大数量为2000个雇员
     * 因此用一个int[2001]的数组来将employees里的数据按id来存放
     * 最后求和的时候用了递归的方法
     * 也可以用队列的方法来实现
     * @param employees
     * @param id
     * @return
     */
    public int getImportanceRecursive(List<Employee> employees, int id) {
        Employee[] employeesArray = employees.toArray(new Employee[employees.size()]);
        int maxId = Integer.MIN_VALUE;
        Employee[] employeesMax = new Employee[2001];
        for (Employee e : employees) {
            employeesMax[e.id] = e;
        }
        return getImportanceVal(employeesMax, id);
    }

    public int getImportanceVal(Employee[] employees, int id) {
        int sum = 0;
        sum += employees[id].importance;
        for (int sub : employees[id].subordinates) {
            sum += getImportanceVal(employees, sub);
        }
        return sum;
    }
}
