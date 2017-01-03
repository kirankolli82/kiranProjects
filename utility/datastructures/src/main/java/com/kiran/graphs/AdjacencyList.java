package com.kiran.graphs;

import com.kiran.lists.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created by Kiran Kolli on 24-09-2016.
 */
public class AdjacencyList {

    private final List<Vertex> vertices;

    public AdjacencyList(List<Vertex> vertices) {
        this.vertices = Lists.createListWith(vertices);
    }

    public List<Vertex> getVertices() {
        return Collections.unmodifiableList(vertices);
    }
}
