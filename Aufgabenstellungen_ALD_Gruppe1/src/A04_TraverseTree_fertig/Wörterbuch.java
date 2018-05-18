package A04_TraverseTree_fertig;

import java.util.HashSet;
import java.util.Set;


public class Wörterbuch {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;
	
	public Wort getRoot() {
		return root;
	}

	/**
	 * Zählt alle Wörter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der Wörter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {
		if(w == null)
			return 0; 
		
		String wordToSearch = w.getWort(); 
		Wort neededWort = getNeededWord(getRoot(), wordToSearch); 
		return getWordsUnderWord(neededWort);
	}
	
	private int getWordsUnderWord(Wort w)
	{
		if(w == null)
			return 0; 
		
		int currentWordCount = 1; 
		currentWordCount += getWordsUnderWord(w.getLeft()); 
		currentWordCount += getWordsUnderWord(w.getRight());
		return currentWordCount; 
	}
	
	private Wort getNeededWord(Wort w, String wordToSearch)
	{
		if(w == null)
			return null; 
		
		int cmpVal = wordToSearch.compareTo(w.getWort());
		if(cmpVal == 0) //found
			return w; 
		
		Wort foundOnLeft = getNeededWord(w.getLeft(), wordToSearch); 
		if(foundOnLeft != null)
			return foundOnLeft; 
		else //return null or found word from right side
			return getNeededWord(w.getRight(), wordToSearch); 
	}
	
	

	/**
	 * Liefert die Menge aller Wörter retour, die ein spezifisches Präfix haben.
	 * @param prefix Wörter müssen diesen Präfix haben
	 * @return Menge aller zutreffenden Wörter
	 */
	public Set<String> getWordsWithPrefix(String prefix) {
		Set<String> foundWords = new HashSet<>(); 
		
		addWordsWithPrefix(getRoot(), prefix, foundWords); 
		return foundWords;
	}
	
	private void addWordsWithPrefix(Wort w, String prefix, Set<String> set)
	{
		if(w == null)
			return; 
		
		String prefixOfCurrentWord = w.getWort().substring(0, prefix.length());
		int cmpVal = prefix.compareTo(prefixOfCurrentWord);
		
		if(cmpVal == 0)
			set.add(w.getWort());
		
		addWordsWithPrefix(w.getLeft(), prefix, set); 
		addWordsWithPrefix(w.getRight(), prefix, set); 
	}
	

	/**
	 * Neues Wort hinzufügen
	 * @param wort Hinzuzufügendes Wort
	 */
	public void add(String wort) {
		Wort neu = new Wort(wort);
		if (root == null) {			// Fall 1: Baum ist leer
			root = neu;
			return;
		}
		Wort w = root;				// Fall 2: Baum ist nicht leer
		while (true) {
			int vgl = wort.compareTo(w.getWort());
			if (vgl < 0) {			// Neues Wort ist lexikographisch kleiner
				if (w.getLeft() == null) {
					w.setLeft(neu);
					neu.setParent(w);
					return;
				}
				w = w.getLeft();
			}
			else if (vgl > 0) {		// Neues Wort ist lexikographisch größer
				if (w.getRight() == null) {
					w.setRight(neu);
					neu.setParent(w);
					return;
				}
				w = w.getRight();
			}
			else {					// Neues Wort ist lexikographisch gleich
				return;
			}
		}
	}

	public Wort find(String s) {
		return find(root, s);
	}
	
	private Wort find(Wort current, String s) {
		if (current == null) {
			return null;
		}
		int vgl = s.compareTo(current.getWort());
		if (vgl == 0) {		// Gefunden
			return current;
		}
		else if (vgl < 0) {	// Links
			return find(current.getLeft(), s);
		}
		else {				// Rechts
			return find(current.getRight(), s);
		}
	}
	
}
