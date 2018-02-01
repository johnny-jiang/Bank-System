package main.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 褚棋
 * @Project bank
 * @Comments pending fund management
 * @JDKversionUsed JDK1.8
 * @CreateDate 2015-04-19 11:44:33
 * @ModifiedBy 褚棋
 * @ModifiedDate 2015-05-06 17:20:29
 * @WhyAndWhatIsModified 增加新构造函数
 * @version: 0.2
 */
public class PendingFundManagement {
	List<PendingFundDataBase> pendingFundList;
	String filePath = "pendingFund.dat";
	int nextFundID;

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

	public PendingFundManagement() {
		this.pendingFundList = new ArrayList<PendingFundDataBase>();
		this.GetDataFromFile();
	}

	/**
	 * 修改filePath的构造函数
	 * 
	 * @param s
	 *            文件保存路径
	 * @CreateDate 2015-05-06 16:48:41
	 */
	public PendingFundManagement(String s) {
		filePath=s;
		this.pendingFundList = new ArrayList<PendingFundDataBase>();
		this.GetDataFromFile();
	}

	protected void finalize() {
		this.SaveDataToFile();
	}

	public List<PendingFundDataBase> GetPendingFundListByAccountID(int accountID) {
		List<PendingFundDataBase> l = new ArrayList<PendingFundDataBase>();
		for (PendingFundDataBase pDat : this.pendingFundList) {
			if (pDat.getAccountID() == accountID)
				l.add(pDat);
		}
		return l;
	}

	public List<PendingFundDataBase> GetPendingFundListByDate(String s) {
		List<PendingFundDataBase> l = new ArrayList<PendingFundDataBase>();
		for (PendingFundDataBase pDat : this.pendingFundList) {
			if (pDat.checkExpire(s))
				l.add(pDat);
		}
		return l;
	}

	public PendingFundDataBase GetPendingFundByID(int id) {
		for (PendingFundDataBase pDat : this.pendingFundList) {
			if (pDat.getId() == id)
				return new PendingFundDataBase(pDat);
		}
		return null;
	}

	public boolean AddPendingFund(PendingFundDataBase pDat) {
		pDat.setId(nextFundID);
		if (this.pendingFundList.add(pDat) == true) {
			nextFundID++;
			return true;
		}
		return false;
	}

	public boolean DeletePendingFundByID(int id) {
		PendingFundDataBase pDat;
		for (int i = 0; i < this.pendingFundList.size(); i++) {
			pDat = this.pendingFundList.get(i);
			if (pDat.getId() == id) {
				this.pendingFundList.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean GetDataFromFile() {
		// 从文件中读取数据到pendingFundList中
		File pDatFile = new File(this.filePath);
		if (pDatFile.exists() == true) {
			this.pendingFundList = new ArrayList<PendingFundDataBase>();
			FileReader reader;
			try {
				reader = new FileReader(this.filePath);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line = null;
				PendingFundDataBase pDat = new PendingFundDataBase();
				line = bufferedReader.readLine();
				this.nextFundID = Integer.parseInt(line);
				while ((line = bufferedReader.readLine()) != null) {
					pDat = new PendingFundDataBase(line);
					pendingFundList.add(pDat);
				}
				bufferedReader.close();
				System.out.println("Pending Fund Data Read Success!");
				return true;
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else {
			System.out.println("Pending Fund Data File NOT Exists!");
			return false;
		}
		return false;
	}

	public boolean SaveDataToFile() {
		// 将pendingFundList中的数据保存到文件
		File pDatFile = new File(this.filePath);
		if (pDatFile.exists() == true) {
			// if file exists , delete it!
			System.out.println("Pending Fund Data File Exists!");
			if (pDatFile.delete() == true)
				System.out.println("Pending Fund Data File Delete Success!");
			else {
				System.out.println("Pending Fund Data File Delete Fail!");
				return false;
			}
		}
		FileWriter Writer;
		try {
			Writer = new FileWriter(this.filePath, true);
			BufferedWriter bufferedWriter = new BufferedWriter(Writer);
			bufferedWriter.write(String.valueOf(this.nextFundID + "\n"));
			for (PendingFundDataBase pDat : this.pendingFundList) {
				bufferedWriter.write(pDat.toString() + '\n');
			}
			bufferedWriter.close();
			System.out.println("Pending Fund Data Save Success!");
			return true;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}

	public List<PendingFundDataBase> getPendingFundList() {
		return pendingFundList;
	}

	/*
	 * edit time 2015-4-29 23:04:48. add new function . return true if accountID
	 * has no pending fund
	 */
	public boolean accountIsClear(int accountID) {
		if (this.GetPendingFundListByAccountID(accountID).isEmpty())
			return true;
		else
			return false;
	}
}
