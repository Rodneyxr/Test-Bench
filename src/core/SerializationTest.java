package core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializationTest {

	public static void main(String[] args) {
		Event event = new Event();
		event.name = "Birthday Party 1";
		event.location = "SA";
		event.userList.add(new User("Rodney", 21));
		event.userList.add(new User("River", 15));

		// serialize event

	}

}

class Event {
	public String name; // name of the event
	public String location;
	public ArrayList<User> userList = new ArrayList<User>();

	public Event() {
		this.name = "noname";
		this.location = "somewhere";
	}
}

class User {
	public String name;
	public int age;

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class Tools {
	public static byte[] serializeObject(Object o) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("myfile");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(((Event) o).userList);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}
}

class FakeDatabase {
	public static String data;
}
