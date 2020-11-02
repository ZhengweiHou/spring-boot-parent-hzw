package com.hzw.learn.springboot.javabase.leetcode;

/*
我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：

        B.length >= 3
        存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
        （注意：B 可以是 A 的任意子数组，包括整个数组 A。）

        给出一个整数数组 A，返回最长 “山脉” 的长度。

        如果不含有 “山脉” 则返回 0。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-mountain-in-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import org.junit.Assert;

public class Leetcode845 {
    public int longestMountain(int[] A) {
        if (A.length<3) return 0;
        int maxLength=0;
        int tmpLength=1;

        int flag=1;
        int current=-1;

        for(int a: A){
            if (current<0){
                current=a; continue;
            }

            if((a - current) * flag > 0){
//                if (flag == 1) flag=2;
                flag = flag>0?2:-2;
                tmpLength++;

            }else if((a - current) * flag < 0){
                if (flag == 2) {
                    flag = -1;
                    tmpLength++;
                }else if (flag < 0){
                    flag = 2;
                    tmpLength=2;
                }
            }else{
                tmpLength = 1;
                flag      = 1;
            }

            maxLength = (tmpLength > maxLength && flag < 0) ? tmpLength : maxLength;
            current = a;
        }
        return maxLength;
    }


    public static void main(String[] args) {
        Leetcode845 leetcode845 = new Leetcode845();

        int[] a = new int[]{2,1,4,7,3,2,5};
        System.out.println(leetcode845.longestMountain(a)); Assert.assertEquals(leetcode845.longestMountain(a),5);
        a = new int[]{1,3,2};
        System.out.println(leetcode845.longestMountain(a)); Assert.assertEquals(leetcode845.longestMountain(a),3);
        a = new int[]{2,0,2,0};
        System.out.println(leetcode845.longestMountain(a)); Assert.assertEquals(leetcode845.longestMountain(a),3);
        a = new int[]{2,2,2};
        System.out.println(leetcode845.longestMountain(a)); Assert.assertEquals(leetcode845.longestMountain(a),0);
        a = new int[]{5,4,3,2,1};
        System.out.println(leetcode845.longestMountain(a)); Assert.assertEquals(leetcode845.longestMountain(a),0);
        a = new int[]{1,2,3,4,5};
        System.out.println(leetcode845.longestMountain(a)); Assert.assertEquals(leetcode845.longestMountain(a),0);
//        Assert.assertEquals(leetcode845.longestMountain(a),0);
        a = new int[]{0,1,2,3,4,5,6,7,8,9};
        System.out.println(leetcode845.longestMountain(a)); Assert.assertEquals(leetcode845.longestMountain(a),0);

        a = new int[]{1,2,0,2,0,2};
        System.out.println(leetcode845.longestMountain(a)); Assert.assertEquals(leetcode845.longestMountain(a),3);



    }
}
