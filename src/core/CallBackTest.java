package core;

import java.util.ArrayList;
import java.util.List;

interface Executable {
	void execute();
}

class Delegate {

	private List<Executable> executables;

	public Delegate() {
		this(null);
	}

	public Delegate(Executable e) {
		executables = new ArrayList<Executable>();
		add(e);
	}

	public void add(Executable e) {
		if (e == null)
			return;
		executables.add(e);
	}

	public void set(Executable e) {
		clear();
		add(e);
	}

	public void clear() {
		executables.clear();
	}

	public void run() {
		for (Executable e : executables)
			e.execute();
	}

}

class DelayedTask implements Runnable {

	private Delegate delegate;
	private double delay = 0;

	public DelayedTask(Delegate delegate) {
		this.delegate = delegate;
	}
	
	public DelayedTask setDelay(double delay) {
		this.delay = delay;
		return this;
	}

	@Override
	public void run() {
		System.out.println("Task set for " + delay + " seconds.");
		try {
			Thread.sleep((long) delay * 1000L);
			delegate.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class CallBackTest {
	private static int counter = 0;

	public static void main(String[] args) throws InterruptedException {
		Delegate d = new Delegate();
		d.add(() -> { counter += 1; });
		System.out.println("Before Task: " + counter); // print counter before task
		new DelayedTask(d).setDelay(3.145).run(); // wait 3 seconds
		System.out.println("After Task: " + counter); // print counter after task
	}

}
