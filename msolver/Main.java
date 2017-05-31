package msolver;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void testPathFinding(){
		Graph<Character> graph = new Graph<>();

		char a = 'a';
		char b = 'b';
		char c = 'c';
		char d = 'd';
		char e = 'e';

		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);

		graph.addEdge(e, a);
		graph.addEdge(c, a);
		graph.addEdge(c, b);
		graph.addEdge(c, d);
		graph.addEdge(d, e);

		BreadthFirst<Character> breadthFirst = new BreadthFirst<>(graph, b);
		Iterable<Character> characters = breadthFirst.pathTo(e);

		for (char ch : characters) {
			System.out.println(ch);
		}
	}

    public static void main(String[] args) throws IOException {
		//testPathFinding();

		Maze maze = new Maze(args[0]);
		Graph<Coordinate> coordinateGraph = maze.getCoordinateGraph();
		ArrayList<Coordinate> entries = maze.getEntries();
		Coordinate begin = entries.get(0);
		Coordinate end = entries.get(1);
		BreadthFirst<Coordinate> breadthFirst = new BreadthFirst<>(coordinateGraph, begin);
		Iterable<Coordinate> coordinates = breadthFirst.pathTo(end);

		for (Coordinate coordinate: coordinates) {
			System.out.println(coordinate);
		}
	}
}
