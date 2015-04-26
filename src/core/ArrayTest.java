package core;

import java.util.Arrays;

public class ArrayTest {

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4, 5 };
		int[] copy1 = a1;
		copy1[0] = -1;
		
		print(Arrays.toString(a1));
		print(Arrays.toString(copy1));
	}
	
	private static void print(String s) {
		System.out.println(s);
	}
	
	private static void printf(String format, Object... args) {
		System.out.printf(format, args);
	}

}
