package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author xiaxiang
 * @date 2021/4/27 16:45
 * @description
 */
public class RangeSumBST {

    @Test
    public void test(){
        TreeNode node = new TreeNode(10,new TreeNode(5),new TreeNode(15));
        System.out.println(rangeSumBST(node,7,15));
    }

    private int result = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (!Objects.isNull(root)){
            isBetween(root, low, high);
        }
        return result;
    }

    public void isBetween(TreeNode node, int low, int high) {
        if (node.val >= low && node.val <= high) {
            result = result + node.val;
            if (!Objects.isNull(node.left)) {
                isBetween(node.left, low, high);
            }
            if (!Objects.isNull(node.right)) {
                isBetween(node.right, low, high);
            }
        }
        if (node.val < low) {
            if (!Objects.isNull(node.right)) {
                isBetween(node.right, low, high);
            }
        }
        if (node.val > high) {
            if (!Objects.isNull(node.left)) {
                isBetween(node.left, low, high);
            }
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
