package main.bank;

import java.util.Date;
/**
 * @author 褚棋
 * @Project bank
 * @Comments credit agency 
 * @JDKversionUsed JDK1.8
 * @CreateDate 2015-04-28 20:44:33
 * @version: 0.1
 */
public class CreditAgency {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		CreditAgency c=new CreditAgency();
		AccountDataBase aDat=new AccountDataBase();
		System.out.println(c.GetCreditByData("123","123",new Date()));
		System.out.println(c.GetCreditByData(aDat));
	}

	public double GetCreditByData(String name,String address,Date birth){
		return 0.3;
	}
	
	public double GetCreditByData(AccountDataBase aDat){
		return 0.5;
	}
	
}
