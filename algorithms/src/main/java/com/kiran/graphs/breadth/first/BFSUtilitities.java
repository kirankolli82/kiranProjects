package com.kiran.graphs.breadth.first;


import com.kiran.graphs.Path;
import com.kiran.graphs.Vertex;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Kiran Kolli on 24-09-2016.
 */
public class BFSUtilitities {

    private static final String HAS_BEEN_VISITED = "HAS_BEEN_VISITED";

    public static Path getShortestPath(Vertex from, Vertex to) {
        LinkedList<SearchNode> stack = new LinkedList<>();
        SearchNode start = new SearchNode(from, new Path(null));
        stack.offer(start);
        while (stack.peek() != null &&
                !Objects.equals(stack.peek().vertex, to)) {
            SearchNode node = stack.poll();
            if (!hasVertexBeenVisited(node.vertex)) {
                markVisited(node.vertex);
                node.vertex.getEdgesOriginatingFrom()
                        .forEach(edge ->
                                stack.push(new SearchNode(edge.getHead(), node.path.addEdge(edge))));
            }
        }

        if (stack.peek() != null &&
                Objects.equals(stack.peek().vertex, to)) {
            return stack.poll().path;
        }

        return new Path(null);
    }

    private static boolean hasVertexBeenVisited(Vertex vertex) {
        Map<String, String> values = vertex.getVertexValues();
        if (values.containsKey(HAS_BEEN_VISITED) &&
                Boolean.valueOf(values.get(HAS_BEEN_VISITED))) {
            return true;
        }

        return false;
    }

    private static void markVisited(Vertex vertex) {
        vertex.addVertexValue(HAS_BEEN_VISITED, Boolean.TRUE.toString());
    }

    private static class SearchNode {
        Vertex vertex;
        Path path;

        SearchNode(Vertex vertex, Path path) {
            this.vertex = vertex;
            this.path = path;
        }
    }
}
