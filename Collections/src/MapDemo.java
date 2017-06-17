import java.io.PrintStream;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
	public static void main(String[] args) {
		PrintStream out = System.out;
		String[] iNumbers = {
	"16-000-1000", "60-000-1000", "25-000-1000", "11-000-1000", "42-000-1000",
	"99-000-1000", "20-000-1000", "38-000-1000", "77-000-1000", "95-000-1000"
		};
		String[] names = {
				"Agustin", "Benito", "Blaine", "Curt", "Ernie",
				"Rosaura", "Shannan", "Sibyl", "Verona", "Yetta"
		};

		// Create a hash map to store I-Number name pairs.
		Map<String, String> hashMap = new HashMap<>();

		// Add all I-Numbers and names to the hash map.
		for (int i = 0;  i < iNumbers.length;  ++i) {
			hashMap.put(iNumbers[i], names[i]);
		}

		// Print the size and contents of the hash map.
		out.println("Size: " + hashMap.size());
		out.println(hashMap);
		out.println();

		// Pseudo randomly choose five of the I-Numbers and find their
		// corresponding names.
		for (int i = 0;  i < 5;  ++i) {
			String iNum = choose(iNumbers);
			String name = hashMap.get(iNum);
			out.println(iNum + " = " + name);
		}
		out.println();

		// Try to find an I-Number that is not stored in the map.
		String name = hashMap.get("55-555-0000");
		out.println(name);
	}


	public static String choose(String[] array) {
		int length = array.length;
		int index = (int)Math.floor(Math.random() * length);
		return array[index];
	}
}
