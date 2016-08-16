package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> implements IGraph<T> {

	protected int numberOfNodes;
	private Map<T, List<T>> adj;

	public Graph(int n) {
		numberOfNodes = n;
		setAdj(new HashMap<T, List<T>>(numberOfNodes));
	}

	public void addEgde(T fromVertex, T toVertex) {
		if (!getAdj().containsKey(fromVertex)) {
			List<T> vertices = new ArrayList<T>();
			vertices.add(toVertex);
			getAdj().put(fromVertex, vertices);
		} else {
			getAdj().get(fromVertex).add(toVertex);
		}
	}

	public void removeEdge(T fromVertex, T toVertex) {
		if (getAdj().containsKey(fromVertex))
			getAdj().get(fromVertex).remove(toVertex);
	}

	public boolean hasEdge(T fromVertex, T toVertex) {
		return getAdj().containsKey(fromVertex) ? getAdj().get(fromVertex).contains(toVertex) : false;
	}

	public List<T> outEdges(T vertex) {
		return getAdj().get(vertex);
	}

	public List<T> inEgdes(T vertex) {
		List<T> edges = new ArrayList<T>();
		for (T v : getAdj().keySet()) {
			if (getAdj().get(v).contains(vertex))
				edges.add(v);
		}
		return edges;
	}

	public Map<T, List<T>> getAdj() {
		return adj;
	}

	public void setAdj(Map<T, List<T>> adj) {
		this.adj = adj;
	}

	

}