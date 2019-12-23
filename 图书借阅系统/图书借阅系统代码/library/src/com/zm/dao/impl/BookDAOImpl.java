package com.zm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zm.bean.Book;
import com.zm.dao.BookDAO;
import com.zm.util.JDBCUtil;

public class BookDAOImpl implements BookDAO {

	@Override
	public List<Book> getAllBook() {
		List<Book> bookList = new ArrayList<Book>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "select book.bookNo bookNo,book.bookName bookName,"
					+ "book.bookNum bookNum,book.publishAddr publishAddr,book.author author,bookroom.roomAddr roomAddr from book,bookroom where book.roomId=bookroom.roomId";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			Book book = null;
			while (rs.next()) {
				book = new Book();
				book.setBookNo(rs.getString("bookNo"));
				book.setBookName(rs.getString("bookName"));
				book.setBookNum(rs.getInt("bookNum"));
				book.setPublishAddr(rs.getString("publishAddr"));
				book.setAuthor(rs.getString("author"));
				book.setRoomAddr(rs.getString("roomAddr"));
				bookList.add(book);
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

		return bookList;
	}

	@Override
	public boolean deleBookById(String bookNo) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "delete from book where bookNo=?";
			pst = conn.prepareStatement(sql);
			int del = pst.executeUpdate();
			if (del > 0) {
				return true;
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
		return false;
	}

	//添加图书信息
	@Override
	public boolean insertBook(Book book) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "insert into book(bookNo,bookName,publishAddr,roomId,bookNum,author) values(?,?,?,?,?,?)";

		try {
			conn = JDBCUtil.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, book.getBookNo());
			pst.setString(2, book.getBookName());
			pst.setString(3, book.getPublishAddr());
			pst.setInt(4, book.getRoom().getRoomId());
			pst.setInt(5, book.getBookNum());
			pst.setString(6, book.getAuthor());

			int add = pst.executeUpdate();
			if (add > 0) {
				return true;
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
		return false;
	}

	// 根据索书号查询图书信息
	@Override
	public Book getBookById(String bookNo) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Book book = null;

		try {
			conn = JDBCUtil.getConn();
			String sql = "select bookNo,bookName from book where bookNo=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, bookNo);
			rs = pst.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setBookNo(rs.getString("bookNo"));
				book.setBookName(rs.getString("bookName"));
			}
			return book;
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
		return book;
	}

	// 根据图书编号修改馆藏数量
	@Override
	public boolean updateById(String bookNo, String key) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = JDBCUtil.getConn();
			if (key.equals("借阅")) {
				sql = "update book set bookNum = bookNum-1 where bookNo=?";
			} else if (key.equals("归还")) {
				sql = "update book set bookNum = bookNum+1 where bookNo=?";
			}
			pst = conn.prepareStatement(sql);
			pst.setString(1, bookNo);
			int update = pst.executeUpdate();
			if (update > 0) {
				return true;
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
		return false;
	}

	// 获取总记录条数
	@Override
	public int getTotalCount(String bookNameWithKey) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0; // 总记录条数，默认为0条
		try {
			conn = JDBCUtil.getConn();
			sql = "select count(*) totalCount from book where 1=1 ";
			StringBuilder sb = null;
			if (bookNameWithKey != null) {
				sb = new StringBuilder(sql);
				sb.append(" and bookName like ?");
				sql = sb.toString();
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + bookNameWithKey + "%");
			} else {
				
				pst = conn.prepareStatement(sql);
			}

			rs = pst.executeQuery();
			while (rs.next()) {
				totalCount = rs.getInt("totalCount");
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

		return totalCount;
	}

	/* 分页查询 */
	@Override
	public List<Book> getBookByPage(int start, int rows, String bookNameWithKey) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		List<Book> list = null;
		try {
			conn = JDBCUtil.getConn();
			sql = "select book.bookNo bookNo,book.bookName bookName,"
					+ "book.bookNum bookNum,book.publishAddr publishAddr,"
					+ "book.author author,bookroom.roomAddr roomAddr from book,"
					+ "bookroom where book.roomId=bookroom.roomId ";

			StringBuilder sb = new StringBuilder(sql);
			if (bookNameWithKey != null) {

				sb.append(" and bookName like ?");
				sb.append(" limit ?,?");

				sql = sb.toString();
				//System.out.println(sql);

				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + bookNameWithKey + "%");
				pst.setInt(2, start);
				pst.setInt(3, rows);
			} else {
				sb.append(" limit ?,?");
				sql = sb.toString();
				//System.out.println(sql);

				pst = conn.prepareStatement(sql);
				pst.setInt(1, start);
				pst.setInt(2, rows);
			}

			rs = pst.executeQuery();

			list = new ArrayList<>();
			while (rs.next()) {
				Book book = new Book();
				book.setBookNo(rs.getString("bookNo"));
				book.setBookName(rs.getString("bookName"));
				book.setRoomAddr(rs.getString("roomAddr"));
				book.setBookNum(rs.getInt("bookNum"));
				book.setAuthor(rs.getString("author"));
				book.setPublishAddr(rs.getString("publishAddr"));
				list.add(book);
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
		return list;
	}
}
