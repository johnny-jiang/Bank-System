package main.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 褚棋
 * @Project bank
 * @Comments account data management
 * @JDKversionUsed JDK1.8
 * @CreateDate 2015-04-19 11:44:33
 * @ModifiedBy 褚棋
 * @ModifiedDate 2015-05-06 17:20:29
 * @WhyAndWhatIsModified 增加新构造函数
 * @version: 0.2
 */
public class AccountManagement {
	List<AccountDataBase> accountList;
	String filePath = "account.dat";
	int nextAccountID;

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
	}

	public AccountManagement() {
		this.accountList = new ArrayList<AccountDataBase>();
		this.GetDataFromFile();
	}

	/**
	 * 修改filePath的构造函数
	 * 
	 * @param s
	 *            文件保存路径
	 * @CreateDate 2015-05-06 16:48:41
	 */
	public AccountManagement(String s) {
		this.filePath = s;
		this.accountList = new ArrayList<AccountDataBase>();
		this.GetDataFromFile();
	}

	protected void finalize() {
		this.SaveDataToFile();
	}

	public List<AccountDataBase> getAccountList() {
		return accountList;
	}

	public static String pinToMD5(String pin) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(pin.getBytes());
			byte b[] = md5.digest();
			StringBuffer buf = new StringBuffer("");
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// System.out.println(buf.toString());
			return buf.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public AccountDataBase GetAccountByIDAndPIN(int id, String pin) {
		for (AccountDataBase aDat : accountList) {
			if (aDat.id == id && aDat.CheackPIN(pin) && aDat.getStat() != 2)
				return aDat;
		}
		return null;
	}

	public boolean CheckAccountByAccountID(int id) {
		for (AccountDataBase aDat : this.accountList)
			if (aDat.getId() == id && aDat.getStat() != 2)
				return true;
		return false;
	}

	public int CheckAccountStatByAccountID(int id) {
		for (AccountDataBase aDat : this.accountList)
			if (aDat.getId() == id)
				return aDat.getStat();
		return -1;
	}

	public boolean CheckAccountByAccountIDAndPIN(int id, String pin) {
		for (AccountDataBase aDat : this.accountList)
			if (aDat.getId() == id && aDat.CheackPIN(pin)
					&& aDat.getStat() == 1)
				return true;
		return false;
	}

	public boolean GetDataFromFile() {
		// 从文件中读取数据到accountList中
		File pDatFile = new File(this.filePath);
		if (pDatFile.exists() == true) {
			this.accountList = new ArrayList<AccountDataBase>();
			FileReader reader;
			try {
				reader = new FileReader(this.filePath);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line = null;
				AccountDataBase pDat = new AccountDataBase();
				line = bufferedReader.readLine();
				this.nextAccountID = Integer.parseInt(line);
				while ((line = bufferedReader.readLine()) != null) {
					pDat = new AccountDataBase(line);
					accountList.add(pDat);
				}
				bufferedReader.close();
				System.out.println("Account Data Read Success!");
				return true;
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else {
			System.out.println("Account Data File NOT Exists!");
			return false;
		}
		return false;
	}

	public boolean SaveDataToFile() {
		// 将accountList中的数据保存到文件
		File pDatFile = new File(this.filePath);
		if (pDatFile.exists() == true) {
			// if file exists , delete it!
			System.out.println("Account Data File Exists!");
			if (pDatFile.delete() == true)
				System.out.println("Account Data File Delete Success!");
			else {
				System.out.println("Account Data File Delete Fail!");
				return false;
			}
		}
		FileWriter Writer;
		try {
			Writer = new FileWriter(this.filePath, true);
			BufferedWriter bufferedWriter = new BufferedWriter(Writer);
			bufferedWriter.write(String.valueOf(this.nextAccountID + "\n"));
			for (AccountDataBase pDat : this.accountList) {
				bufferedWriter.write(pDat.toString() + '\n');
			}
			bufferedWriter.close();
			System.out.println("Account Data Save Success!");
			return true;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}

	public boolean AddNewAccount(AccountDataBase aDat) {
		aDat.setStat(1);
		aDat.setId(nextAccountID);
		if (this.accountList.add(aDat) == true) {
			nextAccountID++;
			return true;
		}
		return false;
	}

	public boolean CloseAccountByID(int accountID) {
		for (AccountDataBase aDat : this.accountList)
			if (aDat.id == accountID) {
				aDat.setStat(2);
				return true;
			}
		return false;
	}

	public boolean SuspendAccountByID(int accountID) {
		for (AccountDataBase aDat : this.accountList)
			if (aDat.id == accountID) {
				aDat.setStat(0);
				return true;
			}
		return false;
	}

	public boolean ChangeFundByID(int accountID, double amount) {
		for (AccountDataBase aDat : this.accountList)
			if (aDat.id == accountID) {
				return aDat.changeFund(amount);
			}
		return false;
	}

	/*
	 * 
	 */
	public boolean update() {
		for (AccountDataBase aDat : this.accountList) {
			if (aDat.getFund() < 0)
				System.out.println("account id: " + aDat.getId() + ", "
						+ aDat.name + " is in overdraft, a letter is sent.");
		}
		return false;
	}

}
