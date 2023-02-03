package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.DateLabelFormatter;

class TestJDatePicker {
	Date fecha=null;
	Calendar cal=Calendar.getInstance();
	@Test
	void test() {
		DateLabelFormatter d=new DateLabelFormatter();
		try {
			assertEquals(String.valueOf(d.stringToValue("15-04-2023")),"Sat Apr 15 00:00:00 CEST 2023");
			cal.set(Calendar.DAY_OF_MONTH, 20);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.YEAR, 2023);
			fecha = cal.getTime();
			assertEquals(d.valueToString(cal),"20-01-2023");
			assertEquals(d.valueToString(null),"");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
	}

}
