package A13_MergeSort_fertig;

import java.util.Arrays;


public class MergeSort implements PersonenSort {
	
	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		// Start des rekursiven Aufrufs
		sort(personen, 0, personen.length-1);
	}

	/**
	 * Rekursive Funktion zum Sortieren eines Teils des Arrays
	 * @param personen Zu sortierendes Array
	 * @param start    Startpunkt im Array
	 * @param end      Endpunkt im Array
	 */
	public void sort(Person[] personen, int start, int end)
	{
		if((end - start) < 1) //only one element. No sort
			return; 
		
		int mitte = start + (end - start)/2;
		
		//Sort the subhalfs before the parts are taken of whole array. 
		sort(personen, start, mitte); 
		sort(personen, mitte + 1, end); 
		
		// Für Merge: Hälften in eigene Arrays kopieren
		// Hinweis: bei copyOfRange ist Obergrenze exklusiv, deshalb "+ 1"
		Person[] teil1 = Arrays.copyOfRange(personen, start, mitte + 1);
		Person[] teil2 = Arrays.copyOfRange(personen, mitte + 1, end+1);
		
		// Beide Hälften zusammenfügen und in data-Array schreiben
		merge(teil1, teil2, personen, start);
	}

	/**
	 * Merge zweier Arrays in ein Ergebnis-Array
	 * @param pers1 Erstes Array
	 * @param pers2 Zweites Array
	 * @param result Ergebnisarray
	 * @param start  Position für Speicherung in Ergebnisarray
	 */
	public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) {

		int indexP1 = 0; 
		int indexP2 = 0; 
		
		int i = 0; 
		
		while(indexP1 < pers1.length || indexP2 < pers2.length) //do as long till both arrays have been sorted in result
		{
			Person p1 = null; 
			if(indexP1 < pers1.length)
				p1 = pers1[indexP1];
			
			Person p2 = null; 
			if(indexP2 < pers2.length)
				p2 = pers2[indexP2];
			
			if(p1 == null) //already all persons from first array in result. only add pers2 persons
			{
				result[start + i] = p2; 
				indexP2++; 
			}
			else if(p2 == null) //already all persons from second array in result. only add pers1 persons
			{
				result[start + i] = p1; 
				indexP1++; 
			}
			else //compare the 2 persons. 
			{
				int cmpVal = p1.compareTo(p2); 
				
				if(cmpVal < 0) //negative value. p2 is after p1 so take p1
				{
					result[start + i] = p1; 
					indexP1++; 
				}
				else if(cmpVal > 0) //positive value. p2 is before p1 so take p2
				{
					result[start + i] = p2; 
					indexP2++; 
				}
			}
			
			i++; 
		}
	}

}
