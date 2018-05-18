package A12_DijkstraLand;

import java.util.List;

public class Main {

	public static void main(String[] args){
		// TODO Auto-generated constructor stub
		
		ListGraph g = new ListGraph(4, true); 
		g.addEdge(0, 1, 3);
		g.addEdge(0, 2, 3);
		g.addEdge(1, 3, 4);
		g.addEdge(2, 3, 4);
		
		g.setLand("AUT", 0);
		g.setLand("AUT", 1);
		g.setLand("SLO", 2);
		g.setLand("ITL", 3);
		
		List<Integer> result = Dijkstra.dijkstra(g,  0, 3); 
		
		
		System.out.println("Shortest path (Expected: 0 -> 1 -> 3"); 
		printWay(result); 
	}
	
	//von Beispiel 11 kopiert. 
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
