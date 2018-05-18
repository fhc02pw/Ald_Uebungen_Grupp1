package A10_DijkstraPQShortestPath;

import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernung initialisieren
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i = 0; i < numv; i++) {
			dist[i] = 9999; // Summen im Graph dürfen nie mehr ergeben
		}
	}

	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected boolean calculatePath(int from, int to) {

		
		VertexHeap heap = new VertexHeap(graph.numVertices());
		pred = new int[graph.numVertices()];
		dist[from] = 0;
		for (int i = 0; i < dist.length; i++) {
			Vertex v = new Vertex(i, dist[i]);
			heap.insert(v);
			// Vorgänger initialisieren
			pred[i] = -1;
		}
		heap.setCost(from, 0);
		while (!heap.isEmpty()) {
			Vertex current = heap.remove();
			List<WeightedEdge> nachbarn = graph.getEdges(current.vertex);
			for (WeightedEdge nachbar : nachbarn) {
				int distToHere = dist[current.vertex];
				int distToNext = nachbar.weight;
				int distInsg = distToHere + distToNext;
				if (distInsg < dist[nachbar.to_vertex]) {
					dist[nachbar.to_vertex] = distInsg;
					heap.setCost(nachbar.to_vertex, distInsg);
					pred[nachbar.to_vertex] = current.vertex;
				}
			}
		}

		return true;
	}
}
