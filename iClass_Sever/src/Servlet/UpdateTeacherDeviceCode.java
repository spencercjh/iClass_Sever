package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateTeacherDeviceCode
 */
@WebServlet("/UpdateTeacherDeviceCode")
public class UpdateTeacherDeviceCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTeacherDeviceCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String teacher_id = request.getParameter("teacher_id");
		String device_code = request.getParameter("device_code");
		System.out.println("教师id:	" + teacher_id);
		System.out.println("设备码:	" + device_code);
		PrintWriter out = response.getWriter();
		String update_sql = "UPDATE teacher SET device_code= '" + device_code + "' WHERE teacher_id ='" + teacher_id
				+ "'";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(update_sql);
			System.out.println("update device_code success");
			out.println("update device_code success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("update device_code failed");
			out.println("update device_code failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}