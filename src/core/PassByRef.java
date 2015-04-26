package core;

public class PassByRef {

	public static void main(String[] args) {
		int base = 0;
		int amount = 5;
		Pointer<Integer> above = new Pointer<Integer>(0);
		Pointer<Integer> below = new Pointer<Integer>(0);

		aboveAndBelow(base, amount, above, below);

		System.out.format("base=%d, amount=%d, above=%d, below=%d\n", base, amount, above.getValue(), below.getValue());

	}

	public static void aboveAndBelow(int base, int amount, Pointer<Integer> above, Pointer<Integer> below) {
		above.setValue(base + amount);
		below.setValue(base - amount);
	}

}

class Pointer<T> {

	private T p = null;

	public Pointer(T t) {
		setValue(t);
	}

	public T getValue() {
		return p;
	}

	public void setValue(T t) {
		p = t;
	}

}
