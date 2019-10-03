package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankAccountDAO;
import dao.LoanAccountDAO;
import dao.SavingAccountDAO;
import service.BankAccountService;
import service.BankAccountServiceImpl;
import vo.BankAccountVO;

@WebServlet("/getAccount.do")
public class GetAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static String connectDB(BankAccountDAO dao, String accountnum){
		BankAccountService service = new BankAccountServiceImpl(dao);
		return service.getAccount(accountnum);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BankAccountVO vo = null;
		String account = null;
		String accountnum=request.getParameter("accountnum");
		String type=null;
		switch (accountnum.substring(0,3)) {
		case "001":
			account=connectDB(new SavingAccountDAO(),accountnum);
			type="saving";
			break;
		case "002":
			account=connectDB(new LoanAccountDAO(),accountnum);
			type="loan";
			break;
		default:
			System.out.println("존재하지 않는 계좌 타입");
			break;
		}
		PrintWriter out=response.getWriter();
		out.print(account);
		out.flush();

	}

}
