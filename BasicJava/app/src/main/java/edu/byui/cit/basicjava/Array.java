package edu.byui.cit.basicjava;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

public class Array {
	public static void main(String[] args) {
		PrintStream out = java.lang.System.out;

		{
			double[] data = new double[8];
			double[] sizes = { 4.7, 5.8, 2.1, 3.9, 7.2 };
			out.println(data.length);  // prints out 8
			out.println(sizes.length); // prints out 5
		}

		{
			double[] data = new double[6];
			data[0] = 2.6;
			data[1] = -4.3;

			System.out.println(data[4]);
			if (data[5] > 6.3) {
				out.println("Data value is too high!");
			}
		}

		{
			String[] list = { "Radish", "Carrot", "Tomato", "Pea" };
			out.println(Arrays.toString(list));
			Arrays.sort(list);
			out.println(Arrays.toString(list));
		}
	}

	public static void fill(double[] list, double x) {
		for (int i = 0;  i < list.length;  ++i) {
			list[i] = x;
		}
	}

	public static void ramp(int[] list) {
		for (int i = 0;  i < list.length;  ++i) {
			list[i] = i;
		}
	}

	public static void reverseRamp(int[] list) {
		int high = list.length - 1;
		for (int i = 0;  i < list.length;  ++i) {
			list[i] = high - i;
		}
	}

	public static void reverse(float[] list) {
		int left = 0;
		int right = list.length - 1;

		while (left < right) {
			// Exchange two elements.
			float swap = list[left];
			list[left] = list[right];
			list[right] = swap;

			// Move the indices toward the center.
			left++;
			right--;
		}
	}

	public static int linearSearch(double[] list,double key){
		for (int i = 0;  i < list.length;  ++i) {
			if (list[i] == key) {
				return i;
			}
		}
		return -1;
	}

	public static double getDiscountedAmount1(double purchaseAmt) {
		double rate = 0;
		if (purchaseAmt >= 0 && purchaseAmt < 300)
			rate = 0;
		else if (purchaseAmt >= 300 && purchaseAmt < 600)
			rate = .02;
		else if (purchaseAmt >= 600 && purchaseAmt < 1000)
			rate = .025;
		else if (purchaseAmt >= 1000)
			rate = .03;
		double discount = purchaseAmt * rate;
		return purchaseAmt - discount;
	}

	public static double getDiscountedAmount2(double purchaseAmt) {
		double rate;
		if (purchaseAmt < 300)
			rate = 0;
		else if (purchaseAmt < 600)
			rate = .02;
		else if (purchaseAmt < 1000)
			rate = .025;
		else
			rate = .03;
		double discount = purchaseAmt * rate;
		return purchaseAmt - discount;
	}

	// The values in these arrays can be hard coded
	// into your program, or even better, they can be
	// read from a file or database.
	private static final double[] limits = { 300, 600, 1000 };
	private static final double[] rates = { 0, .02, .025, .03 };

	public static double getDiscountedAmount(double purchaseAmt) {
		int i;
		for (i = 0;  i < limits.length;  ++i) {
			if (purchaseAmt < limits[i]) {
				break;
			}
		}
		double rate = rates[i];
		double discount = purchaseAmt * rate;
		return purchaseAmt - discount;
	}

	public static int binarySearch(float[] list, float key) {
		int left = 0;
		int right = list.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			float cmp = key - list[mid];
			if (cmp < 0)
				right = mid - 1;
			else if (cmp > 0)
				left = mid + 1;
			else
				return mid;
		}
		return -(left + 1);
	}

	// Insertion sort
	public static void sort(double[] list) {
		int first = 0;
		int last = list.length - 1;
		for (int i = last - 1;  i >= first;  --i) {
			double swap = list[i];
			int j;
			for (j = i + 1;  j <= last;  ++j) {
				if (swap <= list[j])
					break;
				list[j - 1] = list[j];
			}
			list[j - 1] = swap;
		}
	}
}


class Student {
	private String name;
	private int age;

	public Student(String n, int a) {
		name = n;
		age = a;
	}

	@Override
	public String toString() {
		return name + " " + age;
	}

	public static void main(String[] args) {
		PrintStream out = java.lang.System.out;
		Student[] students = {
				new Student("Jane", 18),
				new Student("Sam", 17),
				new Student("Nigel", 14)
		};

		out.println(Arrays.toString(students));
		Arrays.sort(students, new NameComptor());
		out.println(Arrays.toString(students));
		Arrays.sort(students, new AgeComptor());
		out.println(Arrays.toString(students));
	}

	public static class NameComptor implements Comparator<Student> {
		@Override
		public int compare(Student s1, Student s2) {
			int r = s1.name.compareTo(s2.name);
			// If two students have the same
			// name, order them by their ages.
			if (r == 0) {
				r = s1.age - s2.age;
			}
			return r;
		}
	}

	public static class AgeComptor implements Comparator<Student> {
		@Override
		public int compare(Student s1, Student s2) {
			int r = s1.age - s2.age;
			// If two students have the same
			// age, order them by their names.
			if (r == 0) {
				r = s1.name.compareTo(s2.name);
			}
			return r;
		}
	}
}
