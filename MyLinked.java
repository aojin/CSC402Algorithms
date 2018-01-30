package algs13;
import stdlib.*;

public class MyLinked {
    static class Node {
        public Node() { } // the node constructor null as part of Node Class
        public double item; // two fields - item and next.
        public Node next;
    }

    int N; // length of list. (number of elements in the object list) Enforced by invariants.
    Node first; // N is NOT a field within the object.
    
    public MyLinked () { // constructor for outer class: Don't worry about this in assignment.
        first = null;
        N = 0;
        checkInvariants ();
    }

    private void myassert (String s, boolean b) { if (!b) throw new Error ("Assertion failed: " + s); }
    
    private void checkInvariants() { // Do N and the 'first' list maintain their connection? Are N and the list in sync?
        myassert("Empty <==> first==null", (N == 0) == (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                throw new Error ("List too short!");
            }
            x = x.next;
        }
        myassert("EndOfList == null", x == null);
    }

    public boolean isEmpty () { return first == null; }
    
    public int size () { return N; }
    
    public void add (double item) { // creates a new node and then flips the two item's pointer references
        Node newfirst = new Node (); // instantiate new node
        newfirst.item = item; // set item field of node
        newfirst.next = first; // links new node in first place
        first = newfirst; // re-assign first reference to the forward-most node
        N++; // accumulates N
    }

    // return Double.NEGATIVE_INFINITY if the linked list is empty
    // the maximum of an empty list is Double.NEGATIVE_INFINITY
    public double max () { return max (first); }
    private static double max (Node x) {
    	double localMax;
		if (x == null) {
		return localMax = Double.NEGATIVE_INFINITY; // the default for empty sets
		}
		else {
			localMax = x.item; // assignment for singleton set
		}
			while (x.next != null) {
				if (x.next.item >= localMax) { // logic for max assignment
					localMax = x.next.item;
					}
				x = x.next;
			}
			return localMax;
		}
    	// will just be a normal while loop. 

    public double maxRecursive () { return maxRecursive (first, Double.NEGATIVE_INFINITY); }
    private static double maxRecursive (Node x, double result) {    	
    	if (max(x) == Double.NEGATIVE_INFINITY) {
    		return Double.NEGATIVE_INFINITY; // base case
    	}
    	else {
    	result = max(x);
    	}
    	return result;
		}

    // delete the kth element
    public void delete (int k) {
        if (k < 0 || k >= N) throw new IllegalArgumentException ();

        if (first == null) {
        	return;
        }
        
        if (k == 0) {
        	first = first.next;
        	return;
        }
        
        Node current = first;
        
        int i = 0;
        while (current != null && i < k - 1) {
        	current = current.next;
        	i++;
        }
        if (current == null || current.next == null) {
        	return;
        	}
        Node next = current.next.next;
        
        current.next = next;
        }
        
    	

        

    // reverse the list "in place"... without creating any new nodes - DO NOT USE ANY CONSTRUCTORS. Use as many reference variables as you want.
    public void reverse () {
    	Node currNode = first;
    	Node nextNode = null;
    	Node prevNode = null;
    	
    	while(currNode!=null){
    	     nextNode = currNode.next;
    	     currNode.next = prevNode;
    	     prevNode = currNode;
    	     currNode = nextNode;
    	}
    	first = prevNode;
    	     
    	checkInvariants ();
    	}
    	


//    // remove 
//    public void remove (double item) { // don't need to do for week assignment 3
//        // TODO 1.3.26
//        checkInvariants ();
//    }


    private static void print (String s, MyLinked b) {
        StdOut.print (s + ": ");
        for (Node x = b.first; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println ();
    }
    private static void print (String s, MyLinked b, double i) {
        StdOut.print (s + ": ");
        for (Node x = b.first; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println (": " + i);
    }

    private static void testMax () {
        MyLinked b = new MyLinked ();
        print ("empty", b, b.max());
        b.add (-1);
        print ("singleton", b, b.max());
        b.add (-2);
        b.add (-3);
        b.add (-4);
        print ("at end", b, b.max());
        b.add (5);
        print ("at beginning", b, b.max());
        b.add (3);
        b.add (2);
        b.add (4);
        print ("in the middle", b, b.max());
    }
    private static void testMaxRecursive () {
        MyLinked b = new MyLinked ();
        print ("empty", b, b.maxRecursive());
        b.add (-1);
        print ("singleton", b, b.maxRecursive());
        b.add (-2);
        b.add (-3);
        b.add (-4);
        print ("at end", b, b.maxRecursive());
        b.add (5);
        print ("at beginning", b, b.maxRecursive());
        b.add (3);
        b.add (2);
        b.add (4);
        print ("in the middle", b, b.maxRecursive());
    }
    private static void testDelete () {
        MyLinked b = new MyLinked ();
        b.add (1);
        print ("singleton", b);
        b.delete (0);
        print ("deleted", b);
        for (double i = 1; i < 13; i++) {
            b.add (i);
        }
        print ("bigger list", b);
        b.delete (0);
        print ("deleted at beginning", b);
        b.delete (10);
        print ("deleted at end", b);
        b.delete (4);
        print ("deleted in middle", b);
    }
    private static void testReverse () {
        MyLinked b = new MyLinked ();
        b.reverse ();
        print ("reverse empty", b);
        b.add (1);
        print ("singleton", b);
        b.reverse ();
        print ("reverse singleton", b);
        b.add (2);
        print ("two", b);
        b.reverse ();
        print ("reverse two", b);
        b.reverse ();
        print ("reverse again", b);
        for (double i = 3; i < 7; i++) {
            b.add (i);
            b.add (i);
        }
        print ("bigger list", b);
        b.reverse ();
        print ("reversed", b);
    }
    private static void testRemove () {
        MyLinked b = new MyLinked ();
        b.remove (4);
        print ("removed 4 from empty", b);
        b.add (1);
        b.remove (4);
        print ("removed 4 from singelton", b);
        b.remove (1);
        print ("removed 1 from singelton", b);
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
        }
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
        }
        print ("longer list", b);
        b.remove (9);
        print ("removed all 9s", b); // does nothing
        b.remove (3);
        print ("removed all 3s", b);
        b.remove (1);
        print ("removed all 1s", b);
        b.remove (4);
        print ("removed all 4s", b);
        b.remove (2);
        print ("removed all 2s", b); // should be empty
    }

    public static void main (String args[]) {
        		  testMax ();
                testMaxRecursive ();
                testDelete ();
                testReverse ();
        //        testRemove ();
    }
}

































