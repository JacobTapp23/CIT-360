package edu.byui.cit.collections;

import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


/* A Short Introduction to Java Collections
 *
 * A collection is an object that contains other objects. Java contains
 * these different types of collections: list, queue, deque, set, sorted
 * set, map, and sorted map.
 *
 * A list is the simplest type of collection. A list allows duplicate
 * objects to be stored inside it, and a list normally stores objects in
 * the order that they were added to the list. However, Java includes
 * built-in functions that can sort the objects in a list.
 *
 * A queue is a list that is designed and written to efficiently allow
 * objects to be added at the end and removed from the beginning. A
 * queue is like a line of people waiting to check out at a grocery
 * store. People enter the queue at the end of the line and leave the
 * queue at the beginning. In fact, what we call a line of people in the
 * U.S. is called a queue in the U.K.
 *
 * A deque is a double ended queue. Notice how the name deque is an
 * abbreviation for Double Ended QUEue. A deque is designed an written
 * in a way to efficiently allow objects to be added and removed at both
 * the beginning and the end.
 *
 * A set does not allow duplicate elements to be stored in it. In other
 * words, every object stored in a set is unique from the other objects
 * stored in the same set.
 *
 * A sorted set is a set (does not allow duplicate elements) that is
 * designed and written to automatically store objects in a sorted
 * order.
 *
 * A map is not a geographic map. It is a collection that stores key
 * value pairs. A map is designed and written to allow a computer to
 * lookup a key and find that key's corresponding value. In other words,
 * a map is useful for "mapping" from keys to values. A map is similar
 * to a set because a map doe not allow duplicate keys. However, a map
 * does allow duplicate values. In other words, two unique keys may map
 * to the same value. As an example, consider a map that stores
 * I-Numbers as keys and first names as the corresponding values. It is
 * likely that the map will contain data about two people with the same
 * first name. Therefore the I-Number 92-477-8340 and the I-Number
 * 63-408-6001 may have the same value: "Jeff".
 *
 * A sorted map is a map (stores key value pairs) that is designed and
 * written to automatically store the keys in sorted order.
 */
public class CollectionDemo {
	public static void main(String[] args) {
		PrintStream out = System.out;

		// Create a list and add five I-Numbers to it. Then print the
		// list to see its contents. Notice that List is the general
		// interface and ArrayList is the specific implementation.
		List<String> arrList = new ArrayList<>();
		arrList.add("92-477-8340");
		arrList.add("63-408-6001");
		arrList.add("70-550-5505");
		arrList.add("42-112-1338");
		arrList.add("70-550-5505");
		arrList.add("22-556-4424");
		out.println("ArrayList allows duplicate objects and stores" +
				" objects in the order that they were added.");
		out.println(arrList);
		out.println();

		// Create a linked list and add five names to it. Then print the
		// list to see its contents. Notice that a list allows duplicate
		// objects. This linked list allows the name Jeff to be stored
		// twice.
		List<String> linkList = new LinkedList<>();
		linkList.add("Hans");
		linkList.add("Monica");
		linkList.add("Jeff");
		linkList.add("Suyeon");
		linkList.add("Monica");
		linkList.add("Jeff");
		out.println("LinkedList allows duplicate objects and stores" +
				" objects in the order that they were added.");
		out.println(linkList);
		out.println();

		// Create a priority queue and add five names to it. A queue
		// allows duplicate objects. A priority queue stores objects in
		// an order that allows the highest priority object to be
		// removed first. This order is likely different from the order
		// that the objects were added.
		Queue<String> queue = new PriorityQueue<>();
		queue.add("Hans");
		queue.add("Monica");
		queue.add("Jeff");
		queue.add("Suyeon");
		queue.add("Jeff");
		out.println("PriorityQueue allows duplicate objects and stores" +
				" objects in a priority order.");
		out.println(queue);
		out.println();

		// Create a double ended queue and add five names to it.
		Deque<String> deque = new ArrayDeque<>();
		deque.add("Hans");
		deque.add("Monica");
		deque.add("Jeff");
		deque.add("Suyeon");
		deque.add("Jeff");
		out.println("ArrayDeque allows duplicate objects and stores" +
				" objects in the order that they were added.");
		out.println(deque);
		out.println();

		// Create a set and store four unique names in it. Notice that
		// the code adds the name Jeff to this set twice. However, the
		// set stores the name Jeff once only.
		Set<String> set = new HashSet<>();
		set.add("Hans");
		set.add("Monica");
		set.add("Jeff");
		set.add("Suyeon");
		set.add("Jeff");
		out.println("HashSet does not store duplicate objects and" +
				" stores objects in an order so that the computer" +
				" can quickly find them.");
		out.println(set);
		out.println();

		// Create a sorted set and add four unique names to it.
		SortedSet<String> sortSet = new TreeSet<>();
		sortSet.add("Hans");
		sortSet.add("Monica");
		sortSet.add("Jeff");
		sortSet.add("Suyeon");
		sortSet.add("Jeff");
		out.println("TreeSet does not store duplicate objects and" +
				" stores objects in sorted order.");
		out.println(sortSet);
		out.println();

		// Create a map that stores I-Numbers as keys and first names as
		// corresponding values. Notice that a map will not store
		// duplicate keys, but it will store duplicate values.
		Map<String, String> map = new HashMap<>();
		map.put("92-477-8340", "Hans");
		map.put("63-408-6001", "Monica");
		map.put("70-550-5505", "Jeff");
		map.put("42-112-1338", "Suyeon");
		map.put("22-556-4424", "Jeff");
		out.println("HashMap stores key value pairs in an order that" +
				" allows the computer to find a key and its" +
				" corresponding value very quickly.");
		out.println(map);
		out.println();

		// Create a sorted map that stores I-Numbers and first names
		// sorted by the keys.
		SortedMap<String, String> sortMap = new TreeMap<>();
		sortMap.put("92-477-8340", "Hans");
		sortMap.put("63-408-6001", "Monica");
		sortMap.put("70-550-5505", "Jeff");
		sortMap.put("42-112-1338", "Suyeon");
		sortMap.put("22-556-4424", "Jeff");
		out.println("TreeMap stores key value pairs sorted automatically" +
				" by the keys.");
		out.println(sortMap);
	}
}
