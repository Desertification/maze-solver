package msolver;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by thoma on 31-May-17.
 */
public class Maze {
	private boolean[][] boolMaze; // floor is true
	private Coordinate[][] nodeMaze;
	private Graph<Coordinate> coordinateGraph;
	private ArrayList<Coordinate> entries;

	public Maze(String path) throws IOException {
		Stream<String> lines = Files.lines(Paths.get(path), Charset.defaultCharset());
		int numOfLines = (int) lines.count();
		boolMaze = new boolean[numOfLines][numOfLines];
		nodeMaze = new Coordinate[numOfLines][numOfLines];

		FileReader fileReader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		coordinateGraph = new Graph<>();
		entries = new ArrayList<>();

		// store nodes
		for (int line = 0; line < numOfLines; line++) {
			String s = bufferedReader.readLine();
			for (int col = 0; col < s.length(); col++) {
				char c = s.charAt(col);
				if (c == ' ') {
					boolMaze[line][col] = true;
					Coordinate node = new Coordinate(line, col);
					coordinateGraph.addNode(node);
					nodeMaze[line][col] = node;

					if (line == 0 || line == numOfLines - 1 || col == 0 || col == numOfLines -1){
						entries.add(node);
					}

				}
			}
		}

		// find edges
		for (int row = 0; row < numOfLines; row++) {
			for (int col = 0; col < numOfLines; col++) {

				try { // if a node to the right
					if (boolMaze[row][col] && boolMaze[row][col + 1]) {
						coordinateGraph.addEdge(nodeMaze[row][col], nodeMaze[row][col + 1]);
					}
				} catch (IndexOutOfBoundsException ignored) {
				}

				try { // if a node below
					if (boolMaze[row][col] && boolMaze[row + 1][col]) {
						coordinateGraph.addEdge(nodeMaze[row][col], nodeMaze[row + 1][col]);
					}
				} catch (IndexOutOfBoundsException ignored) {
				}
			}
		}
	}

	public ArrayList<Coordinate> getEntries() {
		return entries;
	}

	public Graph<Coordinate> getCoordinateGraph() {
		return coordinateGraph;
	}
}
