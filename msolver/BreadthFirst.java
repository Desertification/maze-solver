package msolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by thoma on 31-May-17.
 * Based on http://algs4.cs.princeton.edu/41graph/BreadthFirstPaths.java.html
 */
public class BreadthFirst<T> {
	private Graph<T> graph;
	private Map<T, Boolean> marked;    // marked = is there an s-n path
	private Map<T, T> edgeTo;        // edgeTo = previous edge on shortest s-n path
	private Map<T, Integer> distTo;    // distTo = number of edges shortest s-n path

	public BreadthFirst(Graph<T> graph, T source) {
		this.graph = graph;

		marked = new HashMap<>();
		edgeTo = new HashMap<>();
		distTo = new HashMap<>();
		for (T node : graph.getNodes()) {
			marked.put(node, false);
			edgeTo.put(node, null);
			distTo.put(node, -1);
		}

		bfs(graph, source);
	}

	private void bfs(Graph G, T source) {
		Queue<T> q = new LinkedBlockingQueue<>();

		distTo.put(source, 0);
		marked.put(source, true);
		q.add(source);

		while (!q.isEmpty()) {
			T node = q.remove();
			for (T adjacentNode : (Iterable<T>) G.getAdjecentNodes(node)) {
				if (!marked.get(adjacentNode)) {
					edgeTo.put(adjacentNode, node);
					distTo.put(adjacentNode, distTo.get(node) + 1);
					marked.put(adjacentNode, true);
					q.add(adjacentNode);
				}
			}
		}
	}

	public boolean hasPathTo(T node) {
		return marked.get(node);
	}

	public Iterable<T> pathTo(T node) {
		if (!hasPathTo(node)) return null;
		Stack<T> path = new Stack<>();
		T x;
		for (x = node; distTo.get(x) != 0; x = edgeTo.get(x))
			path.push(x);
		path.push(x);
		return path;
	}
}
