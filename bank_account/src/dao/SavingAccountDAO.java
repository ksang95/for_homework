package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import util.JDBCUtil;
import vo.BankAccountVO;
import vo.SavingAccountVO;

public class SavingAccountDAO implements BankAccountDAO {

	@Override
	public String createAccount(BankAccountVO vo) {
		// TODO Auto-generated method stub
		String accountnum=null;
		if (vo instanceof SavingAccountVO) {
			SavingAccountVO saving = (SavingAccountVO) vo;
			String sql = "insert into savingaccount(name,term,monthly,interest,rate,accountnum,total,refund) "
					+ "values(?,?,?,?,?,(select concat('001-',LPAD(nvl(max(substr(accountnum,5)),0)+1, 6, '0')) from savingaccount),?,?)";
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs=null;
			int result = 0;
			try {
				con = JDBCUtil.getConnection();
				ps = con.prepareStatement(sql,new String[] {"accountnum"});
				ps.setString(1, saving.getName());
				ps.setInt(2, saving.getTerm());
				ps.setInt(3, saving.getMonthly());
				ps.setInt(4, saving.getInterest());
				ps.setDouble(5, saving.getRate());
				ps.setInt(6, saving.getTotal());
				ps.setInt(7, saving.getRefund());
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
		String sql = "select * from savingaccount where accountnum=?";
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
				map.put("monthly",rs.getString("monthly"));
				map.put("interest",rs.getString("interest"));
				map.put("rate",rs.getString("rate"));
				map.put("accountnum",rs.getString("accountnum"));
				map.put("monthly",rs.getString("monthly"));
				map.put("total",rs.getString("total"));
				map.put("refund",rs.getString("refund"));
				map.put("type", "saving");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

		return JSONObject.toJSONString(map);
	}

}
