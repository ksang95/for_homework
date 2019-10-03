package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import util.JDBCUtil;
import vo.BankAccountVO;
import vo.LoanAccountVO;

public class LoanAccountDAO implements BankAccountDAO {

	@Override
	public String createAccount(BankAccountVO vo) {
		// TODO Auto-generated method stub
		String accountnum=null;
		if (vo instanceof LoanAccountVO) {
			LoanAccountVO loan = (LoanAccountVO) vo;
			String sql = "insert into loanaccount(name,term,loan,interest,rate,accountnum,monthly) "
					+ "values(?,?,?,?,?,(select concat('002-',LPAD(nvl(max(substr(accountnum,5)),0)+1, 6, '0')) from loanaccount),?)";
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs=null;
			int result = 0;
			try {
				con = JDBCUtil.getConnection();
				ps = con.prepareStatement(sql,new String[] {"accountnum"});
				ps.setString(1, loan.getName());
				ps.setInt(2, loan.getTerm());
				ps.setInt(3, loan.getLoan());
				ps.setInt(4, loan.getInterest());
				ps.setDouble(5, loan.getRate());
				ps.setInt(6, loan.getMonthly());
				result = ps.executeUpdate();
				rs=ps.getGeneratedKeys();
				while(rs.next()) {
					accountnum=rs.getString(1);
				}
				if(result==0) {
					System.out.println("계좌 개설 실패");
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				JDBCUtil.close(con, ps, null);
			}
		}
		return accountnum;
	}

	@Override
	public String getAccount(String accountnum) {
		// TODO Auto-generated method stub
		String sql = "select * from loanaccount where accountnum=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> map=new HashMap<String, String>();
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, accountnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				map.put("name",rs.getString("name"));
				map.put("term",rs.getString("term"));
				map.put("loan",rs.getString("loan"));
				map.put("interest",rs.getString("interest"));
				map.put("rate",rs.getString("rate"));
				map.put("accountnum",rs.getString("accountnum"));
				map.put("monthly",rs.getString("monthly"));
				map.put("type", "loan");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

		return JSONObject.toJSONString(map);
	}

}
