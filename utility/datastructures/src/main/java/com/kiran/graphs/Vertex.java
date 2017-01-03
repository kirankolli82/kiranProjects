package com.kiran.graphs;

import com.kiran.lists.Lists;
import com.kiran.maps.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Kiran Kolli on 24-09-2016.
 */
public class Vertex {
    private final String vertexName;
    private final Map<String, String> vertexValues;
    private final List<Edge> edgesOriginatingFrom;
    private final List<Edge> edgesIncidentUpon;

    public Vertex(String vertexName, Map<String, String> vertexValues,
                  List<Edge> edgesOriginatingFrom, List<Edge> edgesIncidentUpon) {
        this.vertexName = vertexName;
        this.vertexValues = Maps.createMapWith(vertexValues);
        this.edgesOriginatingFrom = Lists.createListWith(edgesOriginatingFrom);
        this.edgesIncidentUpon = Lists.createListWith(edgesIncidentUpon);
    }

    public String getVertexName() {
        return vertexName;
    }

    public Map<String, String> getVertexValues() {
        return Collections.unmodifiableMap(vertexValues);
    }

    public List<Edge> getEdgesOriginatingFrom() {
        return Collections.unmodifiableList(edgesOriginatingFrom);
    }

    public List<Edge> getEdgesIncidentUpon() {
        return Collections.unmodifiableList(edgesIncidentUpon);
    }

    public void addVertexValue(String key, String value) {
        vertexValues.put(key, value);
    }

    public void removeVertexValue(String key) {
        vertexValues.remove(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (vertexName != null ? !vertexName.equals(vertex.vertexName) : vertex.vertexName != null) return false;
        if (vertexValues != null ? !vertexValues.equals(vertex.vertexValues) : vertex.vertexValues != null)
            return false;
        if (edgesOriginatingFrom != null ? !edgesOriginatingFrom.equals(vertex.edgesOriginatingFrom) : vertex.edgesOriginatingFrom != null)
            return false;
        return edgesIncidentUpon != null ? edgesIncidentUpon.equals(vertex.edgesIncidentUpon) : vertex.edgesIncidentUpon == null;

    }

    @Override
    public int hashCode() {
        int result = vertexName != null ? vertexName.hashCode() : 0;
        result = 31 * result + (vertexValues != null ? vertexValues.hashCode() : 0);
        result = 31 * result + (edgesOriginatingFrom != null ? edgesOriginatingFrom.hashCode() : 0);
        result = 31 * result + (edgesIncidentUpon != null ? edgesIncidentUpon.hashCode() : 0);
        return result;
    }
}
