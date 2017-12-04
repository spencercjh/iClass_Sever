package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginTeacher
 */
@WebServlet("/LoginTeacher")
public class LoginTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginTeacher() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String teacher_id = request.getParameter("teacher_id");
		String teacher_password = request.getParameter("teacher_password");
		System.out.println("教师ID:" + teacher_id);
		System.out.println("密码:" + teacher_password);
		PrintWriter out = response.getWriter();
		String quary_sql = "select * from teacher where teacher_id ='" + teacher_id + "' and teacher_password='"
				+ teacher_password + "'";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(quary_sql);
			String result_id="";
			String result_password="";
			if(resultset.next()) {
				result_id = resultset.getString("teacher_id");
				result_password = resultset.getString("teacher_password");
			}
			
			// 输出结果
			if (teacher_id.equals(result_id) && teacher_password.equals(result_password)) {
				System.out.println("Login state 100");
				out.println("100"); // success
			} else {
				System.out.println("Login state 200");
				out.println("200"); // failed
			}
			
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			out.println("teacher login failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}