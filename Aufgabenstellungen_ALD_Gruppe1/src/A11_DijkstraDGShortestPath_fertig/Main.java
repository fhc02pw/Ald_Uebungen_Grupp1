package A11_DijkstraDGShortestPath_fertig;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Graph g = new ListGraph(8, false);
		g.addEdge(0, 4,  3);
		g.addEdge(0, 5,  4);
		g.addEdge(1, 3,  1);
		g.addEdge(1, 4,  6);
		g.addEdge(1, 6,  2);
		g.addEdge(2, 3,  3);
		g.addEdge(2, 4,  4);
		g.addEdge(2, 7,  4);
		g.addEdge(3, 6,  2);
		g.addEdge(3, 7,  1);
		g.addEdge(5, 6,  3);
		
		Graph g2 = new ListGraph(4, false);
		g2.addEdge(0, 1, 2, false);
		g2.addEdge(0, 2, 1, true);
		g2.addEdge(1, 3, 2, false);
		g2.addEdge(2, 3, 1, true);

		DijkstraDGShortestPath dfs = new DijkstraDGShortestPath(g);
		List<Integer> way = dfs.findWay(0, 7, false);
		printWay(way);

		System.out.println("*** Test useChargeRoads = false. Expected: 0 -> 1 -> 3 ***");
		dfs = new DijkstraDGShortestPath(g2);
		way = dfs.findWay(0, 3, false);
		printWay(way);
		
		System.out.println("*** Test useChargeRoads = true. Expected: 0 -> 2 -> 3 ***");
		dfs = new DijkstraDGShortestPath(g2);
		way = dfs.findWay(0, 3, true);
		printWay(way);
	}
	
	public static void printWay(List<Integer> way) {
		if (way == null) {
			System.out.println("Kein Weg gefunden.");
			return;
		}
		for (int i=0; i < way.size(); i++) {
			if (i != 0)
				System.out.print(" -> ");
			System.out.print(way.get(i));
		}
		
		System.out.print("\n\n\n");
	}
}
