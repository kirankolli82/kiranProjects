package com.kiran.trees;

/**
 * Created by Kiran Kolli on 25-09-2016.
 */
public class BinaryNode<KeyTypeT, ValueTypeT> {

    private final String name;
    private final KeyTypeT key;
    private final ValueTypeT value;
    private final BinaryNode left;
    private final BinaryNode right;

    public BinaryNode(String name, KeyTypeT key, ValueTypeT value, BinaryNode left, BinaryNode right) {
        this.name = name;
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
