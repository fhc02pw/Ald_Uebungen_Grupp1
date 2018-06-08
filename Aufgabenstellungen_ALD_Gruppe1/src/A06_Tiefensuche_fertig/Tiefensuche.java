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
		addNodesInOrder(node, filmsInOrder); //starte rekursiven Aufruf um Filme aus Teilbaum des Übergabefilms zu retournieren
		return filmsInOrder; 
	}
	
	public void addNodesInOrder(Node<Film> node, List<String> filmsInOrder) {

		if(node == null) //Brich ab, wenn kein Film gefunden
			return; 
		
		//Die Reihenfolge der aufgerufenen Methoden entspricht 'in-order' (links - Node - rechts)
		addNodesInOrder(node.getLeft(), filmsInOrder);  	//Suche (REKURSION) zuerst im linken Teilbaum und füge dort alle Elemente ein
		filmsInOrder.add(node.getValue().getTitel()); 		//füge aktuelles Element ein
		addNodesInOrder(node.getRight(), filmsInOrder); 	//Suche (REKURSION) im rechten Teilbaum und füge von dort alle Elemente ein 
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale Länge des Spielfilms
	 * @param max Maximale Länge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {

		List<String> minMaxPreOrder = new LinkedList<>(); 
		addMinMaxPreOrder(this.getRoot(), min, max, minMaxPreOrder); //Starte rekursiven Aufruf
		return minMaxPreOrder; 
	}

	private void addMinMaxPreOrder(Node<Film> node, double min, double max, List<String> minMaxPreOrder) {
		
		if(node == null) //Brich ab, wenn kein Node
			return; 
		
		//Hole die Länge des Filmtitels (Es werden ja nur Filme retouniert, welche innerhalb von min und max liegen)
		double laenge = node.getValue().getLänge(); 
		
		//Ist der aktuelle Filmtitel innerhalb von min und max, so füge diesen dem Titel hinzu
		if(laenge <= max && laenge >= min)
			minMaxPreOrder.add(node.getValue().getTitel()); 
		
		//Wenn die aktuelle Länge noch größer ist, als min, dann Suche im linken Teilbaum weiter, bis min unterschritten wurde
		if(min <= laenge)
			addMinMaxPreOrder(node.getLeft(), min, max, minMaxPreOrder); 
		
		//Wenn die aktuelle Länge noch kleiner ist, als max, dann Suche im rechten Teilbaum weiter, bis max überschritten wurde
		if(max >= laenge) 
			addMinMaxPreOrder(node.getRight(), min, max, minMaxPreOrder); 
	}

}
