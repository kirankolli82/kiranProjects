package com.kiran.graphs;

import com.kiran.lists.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kiran Kolli on 24-09-2016.
 */
public class Path {

    private final List<Edge> edges;

    public Path(List<Edge> edges) {
        this.edges = Lists.createListWith(edges);
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public Path addEdge(Edge edge) {
        List<Edge> edges = new ArrayList<>();
        edges.add(edge);
        edges.addAll(this.edges);
        return new Path(edges);
    }


    public Long getDistance() {
        //noinspection OptionalGetWithoutIsPresent
        return edges.stream().map(Edge::getWeight)
                .reduce((weight, weight2) -> weight + weight2).get();
    }
}
