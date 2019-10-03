package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.BankAccountDAO;
import dao.LoanAccountDAO;
import dao.SavingAccountDAO;
import service.BankAccountService;
import service.BankAccountServiceImpl;
import vo.BankAccountVO;
import vo.LoanAccountVO;
import vo.SavingAccountVO;

@WebServlet("/createAccount.do")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static String connectDB(BankAccountDAO dao, BankAccountVO vo){
		BankAccountService service = new BankAccountServiceImpl(dao);
		String accountnum=service.createAccount(vo);
		return service.getAccount(accountnum);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BankAccountVO vo = null;
		String account = null;
		switch (request.getParameter("type")) {
		case "saving":
			vo = new SavingAccountVO(request.getParameter("name"), Integer.parseInt(request.getParameter("term")),
					Integer.parseInt(request.getParameter("monthly")));
			account=connectDB(new SavingAccountDAO(),vo);
			break;
		case "loan":
			vo = new LoanAccountVO(request.getParameter("name"), Integer.parseInt(request.getParameter("term")),
					Integer.parseInt(request.getParameter("loan")));
			account=connectDB(new LoanAccountDAO(),vo);
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
