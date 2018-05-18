package A06_Tiefensuche_fertig;

import java.util.LinkedList;
import java.util.List;

import A05_Breitensuche_fertig.BaseTree;
import A05_Breitensuche_fertig.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: Länge des Films
	 */
	protected int compare(Film a, Film b) {

		return Double.compare(a.getLänge(), b.getLänge()); 
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node) {

		List<String> filmsInOrder = new LinkedList<>(); 
		addNodesInOrder(node, filmsInOrder); 
		return filmsInOrder; 
	}
	
	public void addNodesInOrder(Node<Film> node, List<String> filmsInOrder) {

		if(node == null)
			return; 
		
		addNodesInOrder(node.getLeft(), filmsInOrder); 
		filmsInOrder.add(node.getValue().getTitel()); 
		addNodesInOrder(node.getRight(), filmsInOrder); 
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale Länge des Spielfilms
	 * @param max Maximale Länge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {

		List<String> minMaxPreOrder = new LinkedList<>(); 
		addMinMaxPreOrder(this.getRoot(), min, max, minMaxPreOrder); 
		return minMaxPreOrder; 
	}

	private void addMinMaxPreOrder(Node<Film> node, double min, double max, List<String> minMaxPreOrder) {
		
		if(node == null)
			return; 
		
		double laenge = node.getValue().getLänge(); 
		
		if(laenge <= max && laenge >= min)
			minMaxPreOrder.add(node.getValue().getTitel()); 
		
		if(min <= laenge) //left
			addMinMaxPreOrder(node.getLeft(), min, max, minMaxPreOrder); 
		
		if(max >= laenge) //right
			addMinMaxPreOrder(node.getRight(), min, max, minMaxPreOrder); 
	}

}
