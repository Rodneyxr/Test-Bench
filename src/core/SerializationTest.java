package core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;

public class SerializationTest {

	public static void main(String[] args) {
		Event event = new Event();
		event.name = "Birthday Party 1";
		event.location = "SA";
		event.userList.add(new User("Rodney", 21));
		event.userList.add(new User("River", 15));

		// serialize event
		progress("Event Blob");
		String eventBlob = BLOBUtils.blobify(new User("Test", 1));
		System.out.println(eventBlob);

		// de-serialize event
		progress("de-serialize event");
		User user = (User) BLOBUtils.deblobify(eventBlob);
		System.out.println(user.name);
		System.out.println(user.age);
	}

	private static void progress(String message) {
		System.out.println("\n\n********** " + message + " **********");
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

class BLOBUtils {

	public static String blobify(Serializable o) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		byte[] bytes;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(o);
			bytes = bos.toByteArray();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} finally {
			try {
				if (out != null)
					out.close();
				bos.close();
			} catch (IOException ioe) {
				return null;
			}
		}
		return Base64.getEncoder().encodeToString(bytes);// new String(bytes);
	}

	public static Object deblobify(String blob) {
		byte[] bytes = Base64.getDecoder().decode(blob);
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream in = null;
		Object o;
		try {
			in = new ObjectInputStream(bis);
			o = in.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		} finally {
			try {
				bis.close();
				if (in != null)
					in.close();
			} catch (IOException ioe) {
				return null;
			}
		}
		return o;
	}
}

class FakeDatabase {
	public static String data;
}
