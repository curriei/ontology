package ontologyisa;

import static ontologyisa.IO.THRESHOLD;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import graph.Vertex;

public class OntologyISA implements IOntology {

	private final static Logger LOGGER = Logger.getLogger(OntologyISA.class.getName());
	
	@Override
	public boolean isWithinThreshold(Vertex lca, List<Vertex> vertices) {
		for (Vertex v : vertices) {
			if (!(Math.abs(v.getDistance() - lca.getDistance()) <= THRESHOLD)) {
				//LOGGER.info("NOT WITHIN THRESHHOLD");
				return false;
			}
		}
		//LOGGER.info("WITHIN THRESHHOLD");
		return true;
	}

	@Override
	public Vertex findLCA(List<Vertex> vertices) {
		Vertex result = null;
		List<Vertex> listOfAncestors = getListOfAncestors(vertices.get(0));
		ArrayList<Vertex> intersection = new ArrayList<Vertex>(listOfAncestors);
		for (Vertex vertex : vertices.subList(1, vertices.size())) {
			intersection.retainAll(getListOfAncestors(vertex));
		}
		Comparator<Vertex> byDistance = (e1, e2) -> Integer.compare(e1.getDistance(), e2.getDistance());
		intersection.stream().sorted(byDistance);
		result = intersection.get(0);
		//LOGGER.info(("LCA: " + result.getValue()));
		return result;
	}

	// TODO: Try to write a recursive function
	private static List<Vertex> getListOfAncestors(Vertex startVertex) {
		Vertex ancestor = startVertex.getAncestor();
		List<Vertex> ansList = new ArrayList<Vertex>();
		if (ancestor != null)
			ansList.add(ancestor);
		while (ancestor != null) {
			ancestor = ancestor.getAncestor();
			if (ancestor == null)
				break;
			ansList.add(ancestor);
		}
		return ansList;
	}
}
