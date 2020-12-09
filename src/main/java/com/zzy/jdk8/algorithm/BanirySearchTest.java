package com.zzy.jdk8.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BanirySearchTest {

    @Test
    public void test1(){
        int[] array={25,21,41,58,45,78,89,56,25,12,23,14};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(banirySearch(21, array));
        System.out.println(banirySearch(13, array));
    }

    /**
     * 折半查找发，也叫二分法
     *
     * 折半查找法是效率较高的一种查找方法。假设有已经按照从小到大的顺序排列好的五个整数a0~a4，要查找的数是X，其基本思
     * 想是： 设查找数据的范围下限为l=0，上限为h=4，求中点m=（l+h）/2，用X与中点元素am比较，若X等于am，即找到，停
     * 止查找；否则，若X大于am，替换下限l=m+1，到下半段继续查找；若X小于am，换上限h=m-1，到上半段继续查找；如此重复
     * 前面的过程直到找到或者l>h为止。如果l>h，说明没有此数，打印找不到信息，程序结束。
     * 该方法是查找的范围不断缩小一半，所以查找效率较高
     * @param target
     * @param array
     * @return
     */

    public static int banirySearch(int target,int [] array){
        int first = 0;
        int last = array.length - 1;
        int mid;
        while (first <= last){
            mid = (first + last)/2;
            int middle = array[mid];
            if (middle < target){
                first = mid +1;
            }else if (middle > target){
                last = mid -1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
