package msolver;

import java.util.*;

/**
 * Created by thoma on 31-May-17.
 * Based on http://algs4.cs.princeton.edu/41graph/Graph.java.html
 */
class Graph<T> {
	private LinkedHashMap<T, HashSet<T>> adjacencyList;
	private int edges = 0;

	public Graph() {
		adjacencyList = new LinkedHashMap<>();
	}

	public void addNode(T node) {
		adjacencyList.put(node, new HashSet<>());
	}

	public void addEdge(T node1, T node2) {
		HashSet<T> set = adjacencyList.get(node1);
		if (!set.contains(node2)) {
			set.add(node2);
			adjacencyList.get(node2).add(node1);
			edges++;
		}
	}

	public int nodes(){
		return adjacencyList.size();
	}

	public Set<T> getNodes(){
		return adjacencyList.keySet();
	}

	public int edges(){
		return edges;
	}

	public Iterable<T> getAdjecentNodes(T node){
		return adjacencyList.get(node);
	}

}