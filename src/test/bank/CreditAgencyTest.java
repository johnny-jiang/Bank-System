package test.bank;

import static org.junit.Assert.*;

import java.util.Date;

import main.bank.AccountDataBase;
import main.bank.CreditAgency;

import org.junit.Before;
import org.junit.Test;

public class CreditAgencyTest {
	private static CreditAgency c;

	@Before
	public void setUp() throws Exception {
		c=new CreditAgency();
	}

	@Test
	public void testGetCreditByDataStringStringDate() {
		assertEquals(0.3,c.GetCreditByData("123","123",new Date()),0.01F);
	}

	@Test
	public void testGetCreditByDataAccountDataBase() {
		AccountDataBase aDat=new AccountDataBase();
		assertEquals(0.5,c.GetCreditByData(aDat),0.01F);
	}

}
