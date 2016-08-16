package graph;

import java.util.List;

public interface IGraph<T> {

	void addEgde(T fromVertex, T toVertex);

	void removeEdge(T fromVertex, T toVertex);

	boolean hasEdge(T fromVertex, T toVertex);

	List<T> outEdges(T vertex);

	List<T> inEgdes(T vertex);
}
