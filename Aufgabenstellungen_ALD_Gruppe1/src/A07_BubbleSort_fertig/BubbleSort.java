package A07_BubbleSort_fertig;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		boolean swapped = false; 
		do
		{
			swapped = false; 
			for(int i = 0; i < personen.length - 1; i++)
			{
				if(personen[i].compareTo(personen[i + 1]) > 0) //if > 0 swap has to be made
				{
					swapped = true; 
					Person helpPerson = personen[i]; 
					personen[i] = personen[i + 1]; 
					personen[i + 1] = helpPerson; 
				}
			}
		}while(swapped); 
		
	}
	
}
