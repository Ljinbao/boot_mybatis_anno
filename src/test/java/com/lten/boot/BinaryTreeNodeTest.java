package com.lten.boot;

import lombok.Data;

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/6/28 11:03
 */
public class BinaryTreeNodeTest {

    /**
     * 定义根节点
     */
    private String root;

    /**
     * 标识左节点or右节点
     */
    private String k = "0";

    /**
     * 把广义表转换成二叉树
     * @param data
     */
    public void initBinaryTreeeNode(String data) {


    }




    @Data
    private static class BinaryTreeNode {
        /**
         * 用广义表表达二叉树
         */
        private String data;

        /**
         * 左节点
         */
        private BinaryTreeNode leftNode;

        /**
         * 右节点
         */
        private BinaryTreeNode rightNode;

        /**
         * 父节点
         */
        private BinaryTreeNode parentNode;

        public BinaryTreeNode(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        String data = "A(B(D,E(G)),C(,F))#";
    }
}