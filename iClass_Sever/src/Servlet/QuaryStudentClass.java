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
 * Servlet implementation class QuaryStudentClass
 */
@WebServlet("/QuaryStudentClass")
public class QuaryStudentClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuaryStudentClass() {
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
		System.out.println("学生ID" + student_id);
		PrintWriter out = response.getWriter();
		String quary_sql = "select * from student where student_id= '" + student_id + "'";
		response.setContentType("text/json; charset=utf-8");
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			ResultSet resultset = statement.executeQuery(quary_sql);
			String result_class = "";
			if (resultset.next()) {
				// 通过字段检索
				result_class = resultset.getString("student_class");
			}
			// 输出结果
			System.out.println(result_class);
			out.println(URLEncoder.encode(result_class, "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("get student class failed");
			out.println("NULL");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}
