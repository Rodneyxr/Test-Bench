package core;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {

	public static void main(String[] args) {
		String sTime = "12:29"; // string to convert to time object
		Time time;

		Date date = null;
		try {
			date = new SimpleDateFormat("hh:mm").parse(sTime);
		} catch (ParseException e) {
			System.out.println("Invalid time format.");
			e.printStackTrace();
		}

		if (date != null) {
			System.out.println(date);
			time = new Time(date.getTime());
			System.out.println(time);
			String formattedTime = new SimpleDateFormat("h:mm a").format(new Date(time.getTime()));
			System.out.println(formattedTime);
		}
		
	}
	
}
