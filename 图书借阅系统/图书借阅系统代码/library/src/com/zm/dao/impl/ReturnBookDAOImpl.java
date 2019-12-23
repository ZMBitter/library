package com.zm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zm.bean.ReturnBook;
import com.zm.dao.ReturnBookDAO;
import com.zm.util.ConvertUtil;
import com.zm.util.JDBCUtil;

public class ReturnBookDAOImpl implements ReturnBookDAO{
	
	//查询还书记录信息
	@Override
	public List<ReturnBook> selectReturnBook(Integer userId) {
		    List<ReturnBook> returnBooks = new ArrayList<ReturnBook>();
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				conn = JDBCUtil.getConn();
				String sql = "select rId,userId,bookNo,borrowTime,realReturnTime from returnbook where userId=?";
			    pst = conn.prepareStatement(sql);
			    pst.setInt(1, userId);
			    rs = pst.executeQuery();
			    
			    ReturnBook rBook = null;
			    while(rs.next()){
			    	rBook = new ReturnBook();
			    	rBook.setrId(rs.getInt("rId"));
			    	rBook.setUserId(rs.getInt("userId"));
			    	rBook.setBookNo(rs.getString("bookNo"));
			    	rBook.setBorrowTime(rs.getString("borrowTime"));
			    	rBook.setRealReturnTime(ConvertUtil.date2Str(rs.getDate("realReturnTime")));
			    	
			    	returnBooks.add(rBook);
			    }
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				try {
				   if(rs!=null){
					  rs.close();
				   }
				   
				   if(pst!=null){
					   pst.close();
				   }
				   
				   if(conn!=null){
					   conn.close();
				   }
				   
				  } catch (SQLException e) {
						e.printStackTrace();
				}
			}
		return returnBooks;
	}

	
	/*单个删除*/
	@Override
	public boolean deleById(int rId) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			conn = JDBCUtil.getConn();
			String sql = "delete from returnbook where rId=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, rId);
			long del = pst.executeUpdate();
			if(del>0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(rs!=null){
					rs.close();
					}
					
					if(pst!=null){
						pst.close();
					}
					
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}


	//批量删除：根据用户id和索书号删除还书记录信息
	@Override
	public void deleteBatch(String[] rIds) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConn();
			String strId ="";
			for(int i=0;i<rIds.length;i++){
				int rId = Integer.parseInt(rIds[i]);
				strId += rId+",";
			}
			strId = strId.trim();
			strId = strId.substring(0, strId.length()-1);
			System.out.println("strs="+strId);
			
			String sql = "delete from returnbook where rId in("+strId+")";
			System.out.println("批量删除的sql："+sql);
			
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(rs!=null){
					rs.close();
					}
					
					if(pst!=null){
						pst.close();
					}
					
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}	
	}

}
