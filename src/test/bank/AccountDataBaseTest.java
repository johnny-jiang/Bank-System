package test.bank;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import main.bank.AccountDataBase;

import org.junit.Before;
import org.junit.Test;

public class AccountDataBaseTest {
	private static AccountDataBase aDat;
	private static Date birth;

	@Before
	public void setUp() throws Exception {
		aDat = new AccountDataBase();
		aDat.setId(1);
		aDat.setType(1);
		aDat.setName("褚棋");
		aDat.setAddress("北京");
		aDat.setBirth("2001-11-16");
		aDat.setType(1);
		aDat.setCreditStatus(0.3);
		aDat.setPin("123456");
		aDat.setFund(0);
		aDat.setStat(1);
		birth = (new SimpleDateFormat("yyyy-MM-dd")).parse("2001-11-16");
	}

	@Test
	public void testGetAge() {
		Calendar a = Calendar.getInstance();
		int age = a.get(Calendar.YEAR) - birth.getYear() - 1900;
		if ((a.get(Calendar.DATE) - birth.getDate() < 0 && a
				.get(Calendar.MONTH) + 1 - birth.getMonth() == 0)
				|| a.get(Calendar.MONTH) + 1 - birth.getMonth() < 0)
			age--;
		int aage = aDat.getAge();
		assertEquals(age, aage);
	}

	@Test
	public void testChangeFund() {
		aDat.changeFund(123);
		assertEquals(123.0, aDat.getFund(),0.0005F);
		aDat.changeFund(-100);
		assertEquals(23.0, aDat.getFund(),0.0005F);
	}

	@Test
	public void testCheackPIN() {
		aDat.setPin("123456");
		assertEquals(false,aDat.CheackPIN("123457"));
		assertEquals(true,aDat.CheackPIN("123456"));
	}

	@Test
	public void testCheckOverdraft() {
		aDat.setFund(100);
		assertEquals(true, aDat.checkOverdraft(90));
		assertEquals(false, aDat.checkOverdraft(190));
	}

	@Test
	public void testIsClear() {
		aDat.setFund(0);
		assertEquals(true, aDat.isClear());
		aDat.setFund(100);
		assertEquals(false, aDat.isClear());
		
	}

}
