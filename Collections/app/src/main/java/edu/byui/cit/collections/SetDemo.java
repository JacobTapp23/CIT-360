package edu.byui.cit.collections;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class SetDemo {
	public static void main(String[] args) {
		PrintStream out = System.out;
		String[] names = {
				"Agustin", "Benito", "Blaine", "Curt", "Ernie",
				"Gladis", "Hans", "Hilda", "Jamal", "Kylee",
				"Lupita", "Michelle", "Monte", "Murray", "Quentin",
				"Rosaura", "Shannan", "Sibyl", "Verona", "Yetta"
		};

		// Create a hash set to store unique names.
		Set<String> hashSet = new HashSet<>();

		// Pseudo randomly choose names to add to the set.
		for (int i = 0; i < 15; ++i) {
			hashSet.add(choose(names));
		}

		// The size of this set is likely less than 15 even though the
		// computer chose a pseudo random name and added it to the set
		// 15 times. Why is the size likely less than 15?
		out.println("Size: " + hashSet.size());
		out.println(hashSet);

		// Loop through all the names and check if each name is stored
		// in the set or not.
		for (String name : names) {
			String message;
			if (hashSet.contains(name)) {
				message = "hashSet contains " + name;
			}
			else {
				message = "hashSet doesn't contain " + name;
			}
			out.println(message);
		}
		out.println();

		// Create a tree set to store unique names.
		SortedSet<String> treeSet = new TreeSet<>();

		// Pseudo randomly choose names to add to the set.
		for (int i = 0; i < 15; ++i) {
			treeSet.add(choose(names));
		}

		// Print the size and contents of the tree set. Notice that the
		// contents is stored in sorted order.
		out.println("Size: " + treeSet.size());
		out.println(treeSet);
	}


	private static String choose(String[] array) {
		int length = array.length;
		int index = (int)Math.floor(Math.random() * length);
		return array[index];
	}
}
