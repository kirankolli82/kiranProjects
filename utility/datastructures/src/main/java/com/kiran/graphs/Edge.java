package com.kiran.graphs;

/**
 * Created by Kiran Kolli on 24-09-2016.
 */
public class Edge {

    private final Vertex head;
    private final Vertex tail;
    private final Long weight;

    public Edge(Vertex head, Vertex tail) {
        this(head, tail, 1L);
    }

    private Edge(Vertex head, Vertex tail, Long weight) {
        this.head = head;
        this.tail = tail;
        this.weight = weight;
    }

    public Vertex getHead() {
        return head;
    }

    public Vertex getTail() {
        return tail;
    }

    Long getWeight() {
        return weight;
    }
}
