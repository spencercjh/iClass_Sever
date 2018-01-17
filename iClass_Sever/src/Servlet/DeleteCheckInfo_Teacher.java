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
 * Servlet implementation class DeleteCheckInfo_Teacher
 */
@WebServlet("/DeleteCheckInfo_Teacher")
public class DeleteCheckInfo_Teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCheckInfo_Teacher() {
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
		String teacher_id = request.getParameter("teacher_id");
		int subject_th_num = Integer.parseInt(subject_th);
		System.out.println("课程id：	" + subject_id);
		System.out.println("课程节数：	" + subject_th);
		System.out.println("教师id：	" + teacher_id);
		PrintWriter out = response.getWriter();
		String delete_sql = "DELETE FROM `iclass`.`all_check_info` WHERE `subject_id`='" + subject_id
				+ "' and`subject_th`=" + subject_th_num;

		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(delete_sql);
			System.out.println("delete check_info success");
			out.println("delete check_info success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("delete check_info failed");
			out.println("delete check_info failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}