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
 * Servlet implementation class QuaryTeacherDeviceCode
 */
@WebServlet("/QuaryTeacherDeviceCode")
public class QuaryTeacherDeviceCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuaryTeacherDeviceCode() {
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
		System.out.println("教师id：	" + teacher_id);
		PrintWriter out = response.getWriter();
		String quary_sql = "select device_code from teacher where teacher_id='" + teacher_id + "'";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			ResultSet resultset = statement.executeQuery(quary_sql);
			String result_device_code = "";
			if (resultset.next()) {
				result_device_code = resultset.getString("device_code");
			}
			// 输出结果
			System.out.println("device_code:	" + result_device_code);
			out.println(result_device_code);
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			out.println("quary device_code failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}