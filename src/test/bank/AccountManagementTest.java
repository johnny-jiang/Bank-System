package test.bank;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.bank.AccountDataBase;
import main.bank.AccountManagement;

import org.junit.Before;
import org.junit.Test;

public class AccountManagementTest {
	private static AccountManagement aManage;
	String filePath = "testAccount.dat";

	@Before
	public void setUp() throws Exception {
		aManage = new AccountManagement(filePath);
		aManage.getAccountList().clear();
	}

	@Test
	public void testAddNewAccount() {
		AccountDataBase aDat = new AccountDataBase();
		aDat.setId(1);
		aDat.setType(2);
		aDat.setName("褚棋");
		aDat.setAddress("北京");
		aDat.setBirth("2001-11-16");
		aDat.setType(1);
		aDat.setCreditStatus(0.3);
		aDat.setPin("123456");
		aDat.setFund(0);
		aDat.setStat(1);
		aDat.setOverdraft(50);
		aManage.AddNewAccount(aDat);
		assertEquals(1, aManage.getAccountList().size());
	}

	@Test
	public void testCheckAccountByAccountID() {
		AccountDataBase aDat = new AccountDataBase();
		aDat.setId(1);
		aDat.setType(2);
		aDat.setName("褚棋");
		aDat.setAddress("北京");
		aDat.setBirth("2001-11-16");
		aDat.setType(1);
		aDat.setCreditStatus(0.3);
		aDat.setPin("123456");
		aDat.setFund(0);
		aDat.setStat(1);
		aDat.setOverdraft(50);
		aManage.getAccountList().add(aDat);
		assertEquals(true, aManage.CheckAccountByAccountID(aDat.getId()));
	}

	@Test
	public void testCheckAccountStatByAccountID() {
		AccountDataBase aDat = new AccountDataBase();
		aDat.setId(1);
		aDat.setType(2);
		aDat.setName("褚棋");
		aDat.setAddress("北京");
		aDat.setBirth("2001-11-16");
		aDat.setType(1);
		aDat.setCreditStatus(0.3);
		aDat.setPin("123456");
		aDat.setFund(0);
		aDat.setStat(1);
		aDat.setOverdraft(50);
		aManage.getAccountList().add(aDat);
		assertEquals(1, aManage.CheckAccountStatByAccountID(aDat.getId()));
		aDat.setStat(2);
		assertEquals(2, aManage.CheckAccountStatByAccountID(aDat.getId()));
		aManage.getAccountList().remove(aDat);
		assertEquals(-1, aManage.CheckAccountStatByAccountID(aDat.getId()));
	}

	@Test
	public void testCheckAccountByAccountIDAndPIN() {
		AccountDataBase aDat = new AccountDataBase();
		aDat.setId(1);
		aDat.setType(2);
		aDat.setName("褚棋");
		aDat.setAddress("北京");
		aDat.setBirth("2001-11-16");
		aDat.setType(1);
		aDat.setCreditStatus(0.3);
		aDat.setPin("123456");
		aDat.setFund(0);
		aDat.setStat(1);
		aDat.setOverdraft(50);
		aManage.getAccountList().add(aDat);
		assertEquals(true,
				aManage.CheckAccountByAccountIDAndPIN(aDat.getId(), "123456"));
		assertEquals(false,
				aManage.CheckAccountByAccountIDAndPIN(aDat.getId(), "123466"));
	}

	@Test
	public void testGetDataFromFile() {
		AccountDataBase aDat = new AccountDataBase();
		aDat.setId(1);
		aDat.setType(2);
		aDat.setName("褚棋");
		aDat.setAddress("北京");
		aDat.setBirth("2001-11-16");
		aDat.setType(1);
		aDat.setCreditStatus(0.3);
		aDat.setPin("123456");
		aDat.setFund(0);
		aDat.setStat(1);
		aDat.setOverdraft(50);

		File aDatFile = new File(filePath);
		aDatFile.delete();
		if (aDatFile.exists() == true) {
			// if file exists , delete it!
			aDatFile.delete();
		}
		FileWriter Writer;
		try {
			Writer = new FileWriter(filePath, true);
			BufferedWriter bufferedWriter = new BufferedWriter(Writer);
			bufferedWriter.write(1 + "\n");
			bufferedWriter.write(aDat.toString() + '\n');
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		aManage.GetDataFromFile();
		assertEquals(1, aManage.getAccountList().size());
	}

	@Test
	public void testSaveDataToFile() {
		AccountDataBase aDat = new AccountDataBase();
		aDat.setId(1);
		aDat.setType(2);
		aDat.setName("褚棋");
		aDat.setAddress("北京");
		aDat.setBirth("2001-11-16");
		aDat.setType(1);
		aDat.setCreditStatus(0.3);
		aDat.setPin("123456");
		aDat.setFund(0);
		aDat.setStat(1);
		aDat.setOverdraft(50);
		aManage.AddNewAccount(aDat);
		File aDatFile = new File(filePath);
		aDatFile.delete();
		aManage.SaveDataToFile();

		if (aDatFile.exists() == true) {
			FileReader reader;
			try {
				reader = new FileReader(filePath);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line = null;
				line = bufferedReader.readLine();
				assertEquals("1", line);
				line = bufferedReader.readLine();
				assertEquals(aDat.toString(), line);
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testCloseAccountByID() {
		AccountDataBase aDat = new AccountDataBase();
		aDat.setId(1);
		aDat.setType(2);
		aDat.setName("褚棋");
		aDat.setAddress("北京");
		aDat.setBirth("2001-11-16");
		aDat.setType(1);
		aDat.setCreditStatus(0.3);
		aDat.setPin("123456");
		aDat.setFund(0);
		aDat.setStat(1);
		aDat.setOverdraft(50);
		aManage.getAccountList().add(aDat);
		aManage.CloseAccountByID(aDat.getId());
		assertEquals(2, aManage.CheckAccountStatByAccountID(aDat.getId()));

	}

	@Test
	public void testSuspendAccountByID() {
		AccountDataBase aDat = new AccountDataBase();
		aDat.setId(1);
		aDat.setType(2);
		aDat.setName("褚棋");
		aDat.setAddress("北京");
		aDat.setBirth("2001-11-16");
		aDat.setType(1);
		aDat.setCreditStatus(0.3);
		aDat.setPin("123456");
		aDat.setFund(0);
		aDat.setStat(1);
		aDat.setOverdraft(50);
		aManage.getAccountList().add(aDat);
		aManage.SuspendAccountByID(aDat.getId());
		assertEquals(0, aManage.CheckAccountStatByAccountID(aDat.getId()));
	}

	@Test
	public void testChangeFundByID() {
		AccountDataBase aDat = new AccountDataBase();
		aDat.setId(1);
		aDat.setType(2);
		aDat.setName("褚棋");
		aDat.setAddress("北京");
		aDat.setBirth("2001-11-16");
		aDat.setType(1);
		aDat.setCreditStatus(0.3);
		aDat.setPin("123456");
		aDat.setFund(0);
		aDat.setStat(1);
		aDat.setOverdraft(50);
		aManage.getAccountList().add(aDat);
		aManage.ChangeFundByID(aDat.getId(), 50);
		assertEquals(50, aDat.getFund(), 0.01F);
		aManage.ChangeFundByID(aDat.getId(), -100);
		assertEquals(-50, aDat.getFund(), 0.01F);
	}

}
