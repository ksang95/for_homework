package service;

import dao.BankAccountDAO;
import vo.BankAccountVO;

public class BankAccountServiceImpl implements BankAccountService{

	BankAccountDAO dao;
	
	public BankAccountServiceImpl() {
		this.dao=new BankAccountDAO() {
			
			@Override
			public String getAccount(String accountnum) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String createAccount(BankAccountVO vo) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	public BankAccountServiceImpl(BankAccountDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public String createAccount(BankAccountVO vo) {
		// TODO Auto-generated method stub
		return dao.createAccount(vo);
	}

	@Override
	public String getAccount(String accountnum) {
		// TODO Auto-generated method stub
		return dao.getAccount(accountnum);
	}

}
