package 力扣;

import org.apache.commons.collections.list.TreeList;

import java.util.*;

import static java.util.Comparator.comparingInt;

public class 三数之和 {
    public static List<List<Integer>> threeSum(int[] nums) {
        Map<String, Integer> temp = new HashMap();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1; j++)
                if (j > i) {
                    if (nums[i] <= nums[j]) {
                        temp.put(nums[i] + "," + nums[j], nums[i] + nums[j]);
                    } else {
                        temp.put(nums[j] + "," + nums[i], nums[i] + nums[j]);
                    }
                }
        }
        Map<String, List<Integer>> retmap = new HashMap();
        for (Map.Entry<String, Integer> i : temp.entrySet()) {
            for (int num : nums) {
                if (num + i.getValue() == 0) {
                    String[] local = i.getKey().split(",");
                    List<Integer> ls = new ArrayList<>();
                    ls.add(num);
                    ls.add(Integer.valueOf(local[0]));
                    ls.add(Integer.valueOf(local[1]));
                    ls.sort((o1, o2) -> o1 - o2);
                    retmap.put(ls.get(0) + "-" + ls.get(1) + "" + ls.get(2), ls);
                }
            }
        }
        List<List<Integer>> arr = new ArrayList();
        arr.addAll(retmap.values());
        for (List ls : arr) {
            System.out.println(ls);
        }
        return arr;
    }

    public static void main(String[] args) {
        threeSum(new int[]{1, 2, -2, -1});
    }
}
