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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetAllStudent
 */
@WebServlet("/GetAllStudent")
public class GetAllStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subject_id=request.getParameter("subject_id");
		System.out.println("课程ID：	" + subject_id);
		PrintWriter out = response.getWriter();
		String get_sql = "select * from subject_"+subject_id;
		response.setContentType("text/json; charset=utf-8");
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// JSON
			JSONArray jsonarray = new JSONArray();
			JSONObject jsonobj = new JSONObject();
			// 执行SQL语句
			ResultSet resultset = statement.executeQuery(get_sql);
			// 展开结果集数据库
			while (resultset.next()) {
				// 通过字段检索
				jsonobj.put("student_id", resultset.getString("student_id"));
				jsonobj.put("student_name", resultset.getString("student_name"));
				jsonobj.put("student_sex", resultset.getInt("student_sex"));
				jsonobj.put("student_class", resultset.getString("student_class"));
				jsonobj.put("student_college", resultset.getString("student_college"));
				jsonarray.add(jsonobj);
			}
			// 输入结果
			System.out.println(jsonarray.toString());
			out.println(URLEncoder.encode(jsonarray.toString(), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("get all student list failed");
			out.println("get all student list failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}