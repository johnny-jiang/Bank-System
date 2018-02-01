package test.bank;

import static org.junit.Assert.*;
import main.bank.PendingFundDataBase;

import org.junit.Before;
import org.junit.Test;

public class PendingFundDataBaseTest {
	private static PendingFundDataBase pDat = new PendingFundDataBase();

	@Before
	public void setUp() throws Exception {
		String s = "0,0,100,2014-02-03";
		pDat = new PendingFundDataBase(s);
	}

	@Test
	public void testCheckExpire() {
		pDat.setExpire("2014-02-04");
		assertEquals(true, pDat.checkExpire("2014-02-04"));
		assertEquals(false, pDat.checkExpire("2014-02-03"));
	}

}
