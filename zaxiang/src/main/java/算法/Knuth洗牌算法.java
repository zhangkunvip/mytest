package 算法;

import java.util.Arrays;

public class Knuth洗牌算法 {

    public static void main(String[] args) {

        int n=10; //数组长度
        int[] nums=new int[n];
        for (int i = 0; i <nums.length ; i++) {
            nums[i]=i+1;
        }
        System.out.println("洗牌前："+ Arrays.toString(nums));
        knuthSort(nums);
        System.out.println("洗牌后："+Arrays.toString(nums));

    }

    //Knuth洗牌算法
    public static void knuthSort(int[] nums){
        //从后向前遍历，和数组中当前和之前随机一个数交换位置。
        for (int i =nums.length-1; i >=0 ; i--) {
            swap(nums,i,(int)(Math.random()*(i+1)));
        }
    }

    //数组根据下标交换值
    private static void swap(int[] nums,int a,int b){
        int c=nums[b];
        nums[b]=nums[a];
        nums[a]=c;
    }
}
