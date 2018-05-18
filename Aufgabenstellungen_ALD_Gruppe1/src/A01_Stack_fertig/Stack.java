package A01_Stack_fertig;


public class Stack<T>
{
	 private Node<T> first;
    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws StackEmptyException 
     */
    public T pop() throws StackEmptyException {

    	if(first == null)
    		throw new StackEmptyException(); 
    	
    	T val = first.getData(); 
    	first = first.getNext(); 
    	return val; 
    }
    
    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i) {
    	Node<T> node = new Node(i); 
    	node.setNext(first);; 
    	first = node;
    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
    	
    	Node<T> node = first; 
    	int count = 0; 
    	
    	while(node != null)
    	{
    		count++; 
    		node = node.getNext(); 
    	}
    	
    	return count; 
    }
}
