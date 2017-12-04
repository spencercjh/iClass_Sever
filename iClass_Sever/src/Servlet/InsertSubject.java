package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertSubject
 */
@WebServlet("/InsertSubject")
public class InsertSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertSubject() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) 提供subject属性信息插入表student_(student_id)中
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String student_id = request.getParameter("student_id");
		String subject_id = request.getParameter("subject_id");
		String subject_name = URLDecoder.decode(request.getParameter("subject_name"), "UTF-8");
		String teacher_name = URLDecoder.decode(request.getParameter("teacher_name"), "UTF-8");
		String classroom = request.getParameter("classroom");
		System.out.println("学生ID：	" + student_id);
		System.out.println("课程ID:	" + subject_id);
		System.out.println("课程名字:	" + subject_name);
		System.out.println("教师名字:	" + teacher_name);
		PrintWriter out = response.getWriter();
		String insert_sql = "insert into student_" + student_id + "(subject_name,subject_id,classroom,teacher_name)"
				+ "values" + "('" + subject_name + "','" + subject_id + "','" + classroom + "','" + teacher_name + "')";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(insert_sql);
			System.out.println("student insert subject success");
			out.println(URLEncoder.encode("student insert subject success", "UTF-8"));
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			out.println("student insert subject failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}