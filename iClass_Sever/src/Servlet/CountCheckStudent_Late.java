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
 * Servlet implementation class CountCheckStudent_Late
 */
@WebServlet("/CountCheckStudent_Late")
public class CountCheckStudent_Late extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CountCheckStudent_Late() {
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
		String subject_th = request.getParameter("subject_th");
		int subject_th_num = Integer.parseInt(subject_th);
		System.out.println("课程ID:	" + subject_id);
		System.out.println("课程节数:	" + subject_th);
		PrintWriter out = response.getWriter();
		String count_sql = "select count(student_id) as present_num from all_check_info where subject_id= '"
				+ subject_id + "' and subject_th = " + subject_th_num
				+ " and ischeck <> 0 and ischeck <> 1 and ischeck <> 5 and ischeck <> -1";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(count_sql);
			int present_student_num = 0;
			if (resultset.next()) {
				present_student_num = resultset.getInt("present_num");
			}
			// 输出结果
			System.out.println("Late Check Student num:	" + present_student_num);
			out.println(URLEncoder.encode(String.valueOf(present_student_num), "UTF-8"));
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