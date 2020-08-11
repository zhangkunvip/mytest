package 力扣;

public class 两数之和 {
    public static int[]   twoSum(int[] nums, int target) {
//        if(nums.length<=0 || target<=0)
//            return null;
        for(int i=0;i<nums.length;i++){
//            if(nums[i]<=target){
                for(int j=0;j<nums.length;j++){
                    if(target-nums[i]==nums[j] && i!=j){
                        return new int[]{i, j};
                    }
//                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        twoSum(new int[]{-1,-2,-3,-4,-5},-8);
    }
}
