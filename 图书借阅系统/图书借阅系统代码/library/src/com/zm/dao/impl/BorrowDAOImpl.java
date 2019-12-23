package com.zm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.zm.bean.Borrow;
import com.zm.dao.BorrowDAO;
import com.zm.util.ConvertUtil;
import com.zm.util.JDBCUtil;

public class BorrowDAOImpl implements BorrowDAO {

	// 查看借阅信息
	@Override
	public List<Borrow> getBorrowList(Integer userId) {
		List<Borrow> borrows = new ArrayList<Borrow>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "select bId,userId,bookNo,borrowTime,returnTime from borrow where userId=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			rs = pst.executeQuery();
			Borrow borrow = null;	
			while (rs.next()) {
				borrow = new Borrow();
				borrow.setbId(rs.getInt("bId"));
				borrow.setUserId(rs.getInt("userId"));
				borrow.setBookNo(rs.getString("bookNo"));
				borrow.setBorrowTime(ConvertUtil.date2Str(rs.getDate("borrowTime")));
			    borrow.setReturnTime(ConvertUtil.date2Str(rs.getDate("returnTime")));
				borrows.add(borrow);
			}
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return borrows;
	}

	
	// 往数据库的borrow表中插入一条借阅信息。true：借阅成功，false：借阅失败
	@Override
	public boolean insertBorrow(Borrow borrow) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			conn = JDBCUtil.getConn();
			String sql = "insert into borrow(userId,bookNo,borrowTime,returnTime) values(?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, borrow.getUserId());
			pst.setString(2, borrow.getBookNo());
		    pst.setDate(3, ConvertUtil.util2sql(new Date()));
		    pst.setDate(4, ConvertUtil.util2sql(ConvertUtil.str2Date(ConvertUtil.datePlus(new Date(), 30))));

			int in = pst.executeUpdate();

			if (in > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	// 还书操作
	@Override
	public boolean deleBookById(int userId, String bookNo) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Boolean flag = false;
		try {
			conn = JDBCUtil.getConn();
			String sql = "delete from borrow where userId=? and bookNo=?";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setString(2, bookNo);
			int del = pst.executeUpdate();
			if (del > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	// 根据用户Id和索书号bookNo从借阅表(borrow)删除借阅信息
	@Override
	public boolean deleByBId(int bId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Boolean flag = false;
		try {
			conn = JDBCUtil.getConn();
			String sql = "delete from borrow where bId=?";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, bId);
			//pst.setString(2,bookNo );
			int del = pst.executeUpdate();
			if (del > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}
