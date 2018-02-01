package test.bank;

import static org.junit.Assert.*;
import main.bank.Bank;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	private  Bank b;

	@Before
	public void setUp() throws Exception {
		b = new Bank();
	}

	@Test
	public void testUpdate() {
		b.update();
	}


}
