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

public class CreateAccountController {
	static BankAccountVO connectDB(BankAccountDAO dao, BankAccountVO vo){
		BankAccountService service = new BankAccountServiceImpl(dao);
		String accountnum=service.createAccount(vo);
		return service.getAccount(accountnum);
	}
	public static BankAccountVO service(Map<String, String> map) {
		BankAccountVO vo = null;
		BankAccountVO result = null;
		switch (map.get("type")) {
		case "saving":
			vo = new SavingAccountVO(map.get("name"), Integer.parseInt(map.get("term")),
					Integer.parseInt(map.get("monthly")));
			result=connectDB(new SavingAccountDAO(),vo);
			break;
		case "loan":
			vo = new LoanAccountVO(map.get("name"), Integer.parseInt(map.get("term")),
					Integer.parseInt(map.get("loan")));
			result=connectDB(new LoanAccountDAO(),vo);
			break;
		default:
			System.out.println("존재하지 않는 계좌 타입");
			break;
		}
		return result;
	}
}
