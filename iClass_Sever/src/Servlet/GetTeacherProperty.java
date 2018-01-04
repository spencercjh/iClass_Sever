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
 * Servlet implementation class GetTeacherProperty
 */
@WebServlet("/GetTeacherProperty")
public class GetTeacherProperty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTeacherProperty() {
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
		System.out.println("教师id" + teacher_id);
		PrintWriter out = response.getWriter();
		String get_sql = "select * from teacher where teacher_id= '" + teacher_id + "'";
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
				jsonobj.put("teacher_id", resultset.getString("teacher_id"));
				jsonobj.put("teacher_name", resultset.getString("teacher_name"));
				jsonobj.put("teacher_sex", resultset.getInt("teacher_sex"));
				jsonobj.put("teacher_college", resultset.getString("teacher_college"));
			}
			// 输出结果
			System.out.println(jsonobj);
			out.println(URLEncoder.encode(jsonobj.toString(), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("get teacher property failed");
			out.println("get teacher property failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}