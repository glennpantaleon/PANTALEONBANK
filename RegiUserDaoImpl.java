package com.revature.jdbcdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankexception.UserDoesntExistException;
import com.revature.jdbcbankconnect.JdbcBankConnect;
import com.revature.jdbcbankmodel.RegisteredAccount;
import com.revature.jdbcbankmodel.RegisteredUser;


public class RegiUserDaoImpl implements RegiUserDao{
	
	
	public static RegiUserDaoImpl regiuserDao;
	
	private RegiUserDaoImpl() {
	}
	public static RegiUserDaoImpl getDao() {
		if(regiuserDao == null) {
			regiuserDao = new RegiUserDaoImpl();
		}
		return regiuserDao;
	}
	
	
	public RegisteredUser getUser(String username) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcBankConnect.getConnection();
			
			String sql = "SELECT * from RegiUsers where username = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				return new RegisteredUser(
						rs.getInt("userid"),
						rs.getString("fullname"),
						rs.getString("username"),
						rs.getString("userpassword"),
						rs.getBoolean("superuser"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<RegisteredUser> allUsers() {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcBankConnect.getConnection();
			String sql = "SELECT * FROM RegiUsers";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<RegisteredUser> ruser = new ArrayList<RegisteredUser>();
			
			while(rs.next()) {
				ruser.add(new RegisteredUser(
						rs.getInt("userid"),
						rs.getString("fullname"),
						rs.getString("username"),
						rs.getString("userpassword"),
						rs.getBoolean("superuser")));
			}
			return ruser;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertRegiUser(RegisteredUser ru) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcBankConnect.getConnection();
			String sql = "CALL ADD_USER(?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, ru.getFullname());
			cs.setString(2, ru.getUsername());
			cs.setString(3, ru.getPassword());
			cs.setBoolean(4,ru.isSuperuser());
			if(cs.executeUpdate() > 0) {
				return true;
			}
		}catch (SQLException e) {
			e.getMessage();
		}
		return false;
	}

	public boolean deleteAccount(RegisteredUser ru) {
		// TODO Auto-generated method stub
		try {
            Connection conn = JdbcBankConnect.getConnection();
            String sql = "DELETE FROM RegiUsers WHERE userid = ?"; 
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,ru.getUserid() );
            if (ps.executeUpdate() < 0) {
                return true;
            }
            else {
                return false;
            }
        }
        catch(SQLException e) {
        }
        return false;
	}

	public void depositBalance(RegisteredAccount ra,float depoamnt) throws Exception {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcBankConnect.getConnection();
			String sql = "CALL DEPOSIT(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setDouble(1, ra.getAccountbalan());
			cs.setDouble(2,ra.depositBalan(depoamnt));
		}catch (SQLException e) {
			e.getMessage();
		}
	}

	public void withdrawBalance(RegisteredAccount ra,float withdrawamnt) throws Exception {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcBankConnect.getConnection();
			String sql = "CALL WITHDRAW(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setDouble(1, ra.getAccountbalan());
			cs.setDouble(2,ra.withdrawBalan(withdrawamnt));
			
			
		}catch (SQLException e) {
			e.getMessage();
		}
	}
	
	public RegisteredAccount checkBalance(RegisteredAccount ra) throws UserDoesntExistException {
		// TODO Auto-generated method stub
			try {
			
			Connection conn = JdbcBankConnect.getConnection();
			String sql = "SELECT * from RegiBankAccnt where bankaccntid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,ra.getBankaccountid());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new RegisteredAccount(
						rs.getInt("useridid"),
						rs.getInt("bankaccntid"),
						rs.getDouble("accntbalan"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateUser(RegisteredUser ru) throws UserDoesntExistException, SQLException {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcBankConnect.getConnection();
            
            String sql = "UPDATE RegiUsers SET  userid = ?, fullname = ?, username = ?, userpassword = ?, superuser = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ru.getUserid());
            ps.setString(2, ru.getFullname());
            ps.setString(3, ru.getUsername());
            ps.setString(4, ru.getPassword());
            ps.setBoolean(5, ru.isSuperuser());
            
            return ps.executeUpdate() < 0;        
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	public RegisteredUser getUserId(int userid) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcBankConnect.getConnection();
			
			String sql = "SELECT * from RegiUsers where username = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,userid);
			
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				return new RegisteredUser(
						rs.getInt("userid"),
						rs.getString("fullname"),
						rs.getString("username"),
						rs.getString("userpassword"),
						rs.getBoolean("superuser"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public RegisteredAccount getAccntId(int bankaccountid) {
		// TODO Auto-generated method stub
			try {
			
			Connection conn = JdbcBankConnect.getConnection();
			
			String sql = "SELECT * from RegiBankAccnt where bankaccntid = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,bankaccountid);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				return new RegisteredAccount(
						rs.getInt("userid"),
						rs.getInt("bankaccountid"),
						rs.getDouble("accountbalan"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertRegiAccnt(RegisteredAccount ra) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcBankConnect.getConnection();
			String sql = "CALL ADD_BANK(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1,ra.getUserid());
			cs.setDouble(2,ra.getAccountbalan()); //Account Balance);
			cs.executeUpdate();
		}catch (SQLException e) {
			return false;
		}
		return true;
	}
	public boolean updateAccnt(RegisteredAccount ra) throws SQLException {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcBankConnect.getConnection();
            
            String sql = "UPDATE RegiBankAccnt SET  accntbalan = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, ra.getAccountbalan());
            
            return ps.executeUpdate() > 0;        
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	public List<RegisteredAccount> allAccnt() {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcBankConnect.getConnection();
			String sql = "SELECT * FROM RegiBankAccnt";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<RegisteredAccount> accntuser = new ArrayList<RegisteredAccount>();
			
			while(rs.next()) {
				accntuser.add(new RegisteredAccount(
						rs.getInt("userid"),
						rs.getInt("bankaccntid"),
						rs.getDouble("accntbalan")));
			}
			return accntuser;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
