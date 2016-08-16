package graph;

import static graph.Color.BLACK;
import static graph.Color.GRAY;
import static graph.Color.WHITE;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Traversal {

	public static Map<Vertex, Vertex> BFS(Graph<Vertex> g, Vertex v){
		
		Map<Vertex,Vertex> result = new HashMap<Vertex, Vertex>();
		Map<Vertex, List<Vertex>> adj = g.getAdj();
		v.setDistance(0);
		v.setAncestor(null);
		Queue<Vertex> vertexQueue = new LinkedList<Vertex>();
		vertexQueue.add(v);
		while(!vertexQueue.isEmpty()){
			Vertex currentVertex = vertexQueue.poll();
			List<Vertex> neighbours = adj.get(currentVertex);
			if(neighbours.isEmpty()){
				currentVertex.setColor(BLACK);
				result.put(currentVertex, currentVertex);
			}
			for (Vertex nbr : neighbours) {
				if(nbr.getColor().equals(WHITE)){
					nbr.setColor(GRAY);
					nbr.setAncestor(currentVertex);
					nbr.setDistance(currentVertex.getDistance()+1);
					vertexQueue.add(nbr);
				}
			currentVertex.setColor(BLACK);
			result.put(currentVertex, currentVertex);
		    }
		}
		return result;
	}

}
