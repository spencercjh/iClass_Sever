package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
 * Servlet implementation class QuaryStudentPassword
 */
@WebServlet("/QuaryStudentPassword")
public class QuaryStudentPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuaryStudentPassword() {
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
		String student_id = request.getParameter("student_id");
		String student_name = URLDecoder.decode(request.getParameter("student_name"), "UTF-8");
		System.out.println("学生ID：	" + student_id);
		System.out.println("学生姓名：	" + student_name);
		PrintWriter out = response.getWriter();
		String quary_sql = "select student_password from student where student_id='" + student_id
				+ "'and student_name='" + student_name + "'";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			ResultSet resultset = statement.executeQuery(quary_sql);
			String result_password = "";
			if (resultset.next()) {
				result_password = resultset.getString("student_password");
			}
			// 输出结果
			System.out.println("student password:	" + result_password);
			out.println(result_password);
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			out.println("get student password failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}