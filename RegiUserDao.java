package com.revature.jdbcdao;

import java.sql.SQLException;
import java.util.List;

import com.revature.bankexception.UserDoesntExistException;
import com.revature.jdbcbankmodel.RegisteredAccount;
import com.revature.jdbcbankmodel.RegisteredUser;

public interface RegiUserDao {
		
	
		
		RegisteredUser getUser(String username);
		RegisteredUser getUserId(int userid);
		RegisteredAccount getAccntId(int bankaccountid);
		
		List<RegisteredUser> allUsers();
		List<RegisteredAccount> allAccnt();
		
		boolean insertRegiUser(RegisteredUser ru);
		boolean insertRegiAccnt(RegisteredAccount ra);
		
		
		boolean deleteAccount(RegisteredUser ru);
		void depositBalance(RegisteredAccount ra,float depositamnt) throws Exception;
		void withdrawBalance(RegisteredAccount ra,float withdrawamnt) throws Exception;
		RegisteredAccount checkBalance(RegisteredAccount ra) throws UserDoesntExistException;
		
		boolean updateUser(RegisteredUser ru) throws UserDoesntExistException, SQLException;
		boolean updateAccnt(RegisteredAccount ra) throws SQLException;
	
}
