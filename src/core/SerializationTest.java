package core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SerializationTest {

	public static void main(String[] args) {
		Event event = new Event();
		event.name = "Birthday Party 1";
		event.location = "SA";
		event.userList.add(new User("Rodney", 21));
		event.userList.add(new User("River", 15));

		// serialize event
		byte[] eventBytes = Tools.getBytesToSend(new User("Test", 1));
		System.out.println(Arrays.toString(eventBytes));

		// de-serialize event
		User user = (User) Tools.createObjectFromBytes(eventBytes);
		System.out.println(user.name);
		System.out.println(user.age);
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

class User implements Serializable {
	private static final long serialVersionUID = -7637848027633062485L;
	public String name;
	public int age;

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class Tools {
	public static byte[] getBytesToSend(Object o) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		byte[] bytes;
		try {
			try {
				out = new ObjectOutputStream(bos);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			try {
				out.writeObject(o);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			bytes = bos.toByteArray();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				return null;
			}
			try {
				bos.close();
			} catch (IOException ex) {
				return null;
			}
		}
		return bytes;
	}

	public static Object createObjectFromBytes(byte[] bytes) {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		Object o;
		try {
			in = new ObjectInputStream(bis);
			o = in.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			System.err.println("Class not found.");
			cnfe.printStackTrace();
			return null;
		} finally {
			try {
				bis.close();
			} catch (IOException ex) {
				return null;
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				return null;
			}
		}

		return o;
	}
}

class FakeDatabase {
	public static String data;
}
