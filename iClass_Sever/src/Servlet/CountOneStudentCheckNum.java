package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
 * Servlet implementation class CheckOneStudentCheckNum
 */
@WebServlet("/CountOneStudentCheckNum")
public class CountOneStudentCheckNum extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CountOneStudentCheckNum() {
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
		String subject_id = request.getParameter("subject_id");
		String student_id = request.getParameter("student_id");
		String ischeck = request.getParameter("ischeck");
		int ischeck_num = Integer.parseInt(ischeck);
		System.out.println("课程ID" + subject_id);
		System.out.println("学生ID" + student_id);
		PrintWriter out = response.getWriter();
		String count_sql = "select count(student_id) as all_check_num from all_check_info where subject_id= '"
				+ subject_id + "' and student_id= '" + student_id + "' and ischeck = " + ischeck_num;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(count_sql);
			int all_check_num = 0;
			if (resultset.next()) {
				all_check_num = resultset.getInt("all_check_num");
			}
			// 输出结果'
			System.out.println("all_check_num:	" + all_check_num);
			out.println(URLEncoder.encode(String.valueOf(all_check_num), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("count failed");
			out.println("count failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}