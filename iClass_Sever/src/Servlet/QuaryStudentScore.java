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
 * Servlet implementation class QuaryStudentScore
 */
@WebServlet("/QuaryStudentScore")
public class QuaryStudentScore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuaryStudentScore() {
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
		String student_id = request.getParameter("student_id");
		System.out.println("学生ID：	" + student_id);
		PrintWriter out = response.getWriter();
		String quary_sql = "select score from all_check_info where student_id='" + student_id + "'and subject_id='"
				+ subject_id + "'and subject_th=" + subject_th_num;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			ResultSet resultset = statement.executeQuery(quary_sql);
			int score = 0;
			if (resultset.next()) {
				score=resultset.getInt("score");
			}
			System.out.println("quary student score success	" + score);
			out.println(score);
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("quary student score failed");
			out.println("quary student score failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}