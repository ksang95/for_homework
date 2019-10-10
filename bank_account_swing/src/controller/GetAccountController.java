package controller;

import java.util.Map;

import dao.BankAccountDAO;
import dao.LoanAccountDAO;
import dao.SavingAccountDAO;
import service.BankAccountService;
import service.BankAccountServiceImpl;
import vo.BankAccountVO;
import vo.LoanAccountVO;
import vo.SavingAccountVO;

public class GetAccountController {
	static BankAccountVO connectDB(BankAccountDAO dao, String accountnum){
		BankAccountService service = new BankAccountServiceImpl(dao);
		return service.getAccount(accountnum);
	}
	public static BankAccountVO service(String accountnum) {
		BankAccountVO vo = null;
		BankAccountVO result = null;
		switch (accountnum.substring(0,3)) {
		case "001":
			result=connectDB(new SavingAccountDAO(),accountnum);
			break;
		case "002":
			result=connectDB(new LoanAccountDAO(),accountnum);
			break;
		default:
			System.out.println("존재하지 않는 계좌 타입");
			break;
		}
		return result;
	}
}
