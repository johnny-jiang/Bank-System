package test.bank;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.bank.PendingFundDataBase;
import main.bank.PendingFundManagement;

import org.junit.Before;
import org.junit.Test;

public class PendingFundManagementTest {
	private static PendingFundManagement pManage;
	String filePath = "testPendingFund.dat";

	@Before
	public void setUp() throws Exception {
		pManage = new PendingFundManagement(filePath);
		pManage.getPendingFundList().clear();
	}

	@Test
	public void testGetPendingFundListByAccountID() {
		List<PendingFundDataBase> pList=new ArrayList<PendingFundDataBase>();
		pList=pManage.GetPendingFundListByAccountID(0);
		assertEquals(0, pList.size());
		PendingFundDataBase pDat = new PendingFundDataBase("0,0,100,2014-02-03");
		pManage.getPendingFundList().add(pDat);
		pDat = new PendingFundDataBase("1,0,100,2014-02-03");
		pManage.getPendingFundList().add(pDat);
		pDat = new PendingFundDataBase("2,0,100,2014-02-03");
		pManage.getPendingFundList().add(pDat);
		pList=pManage.GetPendingFundListByAccountID(0);
		assertEquals(3, pList.size());
	}

	@Test
	public void testGetPendingFundListByDate() {
		List<PendingFundDataBase> pList=new ArrayList<PendingFundDataBase>();
		pList=pManage.GetPendingFundListByDate("2014-02-03");
		assertEquals(0, pList.size());
		PendingFundDataBase pDat = new PendingFundDataBase("0,0,100,2014-02-03");
		pManage.getPendingFundList().add(pDat);
		pDat = new PendingFundDataBase("1,0,100,2014-02-03");
		pManage.getPendingFundList().add(pDat);
		pDat = new PendingFundDataBase("2,0,100,2014-02-03");
		pManage.getPendingFundList().add(pDat);
		pList=pManage.GetPendingFundListByDate("2014-02-03");
		assertEquals(3, pList.size());
	}

	@Test
	public void testGetPendingFundByID() {
		PendingFundDataBase pDat = new PendingFundDataBase("0,0,100,2014-02-03");
		pManage.getPendingFundList().add(pDat);
		assertEquals(pDat.getAccountID(), pManage.GetPendingFundByID(0)
				.getAccountID());
		assertEquals(pDat.getId(), pManage.GetPendingFundByID(0).getId());
		assertEquals(pDat.getExpire(), pManage.GetPendingFundByID(0)
				.getExpire());
		assertEquals(pDat.getExpire(), pManage.GetPendingFundByID(0)
				.getExpire());

	}

	@Test
	public void testAddPendingFund() {
		PendingFundDataBase pDat = new PendingFundDataBase("0,0,100,2014-02-03");
		pManage.AddPendingFund(pDat);
		assertEquals(1, pManage.getPendingFundList().size());
	}

	@Test
	public void testDeletePendingFundByID() {
		PendingFundDataBase pDat = new PendingFundDataBase("0,0,100,2014-02-03");
		pManage.AddPendingFund(pDat);
		assertEquals(1, pManage.getPendingFundList().size());
		pManage.DeletePendingFundByID(pDat.getId());
		assertEquals(0, pManage.getPendingFundList().size());
	}

	@Test
	public void testGetDataFromFile() {
		PendingFundDataBase pDat = new PendingFundDataBase("0,0,100,2014-02-03");
		File pDatFile = new File(filePath);
		pDatFile.delete();
		if (pDatFile.exists() == true) {
			// if file exists , delete it!
			pDatFile.delete();
		}
		FileWriter Writer;
		try {
			Writer = new FileWriter(filePath, true);
			BufferedWriter bufferedWriter = new BufferedWriter(Writer);
			bufferedWriter.write(1 + "\n");
			bufferedWriter.write(pDat.toString() + '\n');
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		pManage.GetDataFromFile();
		assertEquals(1, pManage.getPendingFundList().size());
	}

	@Test
	public void testSaveDataToFile() {
		PendingFundDataBase pDat = new PendingFundDataBase("0,0,100,2014-02-03");
		pManage.getPendingFundList().clear();
		pManage.AddPendingFund(pDat);
		File pDatFile = new File(filePath);
		pDatFile.delete();
		pManage.SaveDataToFile();
		
		if (pDatFile.exists() == true) {
			FileReader reader;
			try {
				reader = new FileReader(filePath);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line = null;
				line = bufferedReader.readLine();
				assertEquals("1", line);
				line = bufferedReader.readLine();
				assertEquals(pDat.toString(), line);
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

}
