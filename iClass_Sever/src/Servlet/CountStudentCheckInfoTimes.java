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
 * Servlet implementation class CountStudentCheckInfoTimes
 */
@WebServlet("/CountStudentCheckInfoTimes")
public class CountStudentCheckInfoTimes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CountStudentCheckInfoTimes() {
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
		String subject_id = request.getParameter("subject_id");
		String subject_th = request.getParameter("subject_th");
		int subject_th_num;
		try {
			subject_th_num = Integer.parseInt(subject_th);
		} catch (NumberFormatException e) {
			subject_th_num = -1;
		}
		System.out.println("学生ID：	" + student_id);
		System.out.println("课程ID:	" + subject_id);
		System.out.println("课程节数:	" + subject_th);
		PrintWriter out = response.getWriter();
		String count_sql = "SELECT COUNT(student_id) AS CHECKED FROM all_check_info WHERE student_id ='" + student_id
				+ "' and subject_id='" + subject_id + "' and subject_th=" + subject_th_num;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(count_sql);
			int present_check_info_num = 0;
			if (resultset.next()) {
				present_check_info_num = resultset.getInt("CHECKED");
			}
			// 输出结果
			System.out.println("Check Student num:	" + present_check_info_num);
			out.println(URLEncoder.encode(String.valueOf(present_check_info_num), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			out.println("count failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}