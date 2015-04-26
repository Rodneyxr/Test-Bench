package core;

public class Base2 {

	public static void main(String[] args) {
		for (int i = 0; i < 15; i++) {
			System.out.println((int) Math.pow(2, i) + ": log2 = " + log((int) Math.pow(2, i), 2));
		}
	}

	public static int log(int x, int base) {
		return (int) (Math.log(x) / Math.log(base));
	}

}
