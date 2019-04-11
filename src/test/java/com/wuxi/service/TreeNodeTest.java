package com.wuxi.service;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: 青铜
 * @create: 2019-03-04
 **/
public class TreeNodeTest {
    TreeNode root;


    @Before
    public void create() {
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
    }

    /**
     * 镜像二叉树
     */
    @Test
    public void mirror() {
        root = solve(root);
        levelVisit();
    }

    private TreeNode solve(TreeNode root) {
        if (root == null)
            return root;
        // 递归先把左右节点镜像化
        TreeNode left = solve(root.left);
        TreeNode right = solve(root.right);
        // 对左右子树进行交换。
        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * 按层访问
     */
    @Test
    public void levelVisit() {
        ArrayBlockingQueue<TreeNode> queue = new ArrayBlockingQueue<>(10);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

        }
    }

    /**
     * 按层先左到右再右到左
     */
    @Test
    public void lrlevelVisit() {
        ArrayDeque<TreeNode> singleQueue = new ArrayDeque<>(10);
        ArrayDeque<TreeNode> doubleQueue = new ArrayDeque<>(10);
        singleQueue.offer(root);
        System.out.println(root.value);
        int h = 1;
        while (!singleQueue.isEmpty() || !doubleQueue.isEmpty()) {
            System.out.println("层数 ：" + h);
            TreeNode node;
            if (h % 2 == 1) {
                while (!singleQueue.isEmpty()) {
                    node = singleQueue.poll();
                    if (node.right != null) {
                        System.out.println(node.right.value);
                        doubleQueue.offer(node.right);
                    }
                    if (node.left != null) {
                        System.out.println(node.left.value);
                        doubleQueue.offer(node.left);
                    }
                }
            } else {
                while (!doubleQueue.isEmpty()) {
                    node = doubleQueue.pollLast();
                    if (node.left != null) {
                        System.out.println(node.left.value);
                        singleQueue.offer(node.left);
                    }

                    if (node.right != null) {
                        System.out.println(node.right.value);
                        singleQueue.offer(node.right);
                    }

                }
            }
            h++;

        }
    }

}


class TreeNode {

    public TreeNode() {
    }

    ;

    public TreeNode(int value) {
        this.value = value;
    }

    int value;
    TreeNode left;
    TreeNode right;
}