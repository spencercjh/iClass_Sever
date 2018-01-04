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

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetStudentCheckInfo
 */
@WebServlet("/GetStudentCheckInfo")
public class GetStudentCheckInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetStudentCheckInfo() {
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
		String str_subject_th = request.getParameter("subject_th");
		int subject_th = Integer.parseInt(str_subject_th);
		String student_id = request.getParameter("student_id");
		System.out.println("课程id:	" + subject_id);
		System.out.println("学生id:	" + student_id);
		PrintWriter out = response.getWriter();
		String get_sql = "select * from all_check_info where subject_id='" + subject_id + "' and subject_th="
				+ subject_th + " and student_id='" + student_id + "'";
		response.setContentType("text/json; charset=utf-8");
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// JSON
			JSONObject jsonobj = new JSONObject();
			// 执行SQL语句
			ResultSet resultset = statement.executeQuery(get_sql);
			if (resultset.next()) {
				// 通过字段检索
				jsonobj.put("ischeck", resultset.getInt("ischeck"));
				jsonobj.put("check_time", resultset.getString("check_time"));
				jsonobj.put("seat_index", resultset.getInt("seat_index"));
			}
			// 输出结果
			System.out.println(jsonobj);
			out.println(URLEncoder.encode(jsonobj.toString(), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("get student check info failed");
			out.println("get student check info failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}
