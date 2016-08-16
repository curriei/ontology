package ontologyisa;

import java.util.List;

import graph.Vertex;

public interface IOntology {

	boolean isWithinThreshold(Vertex lca, List<Vertex> vertices);

	Vertex findLCA(List<Vertex> vertices);

}