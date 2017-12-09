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
 * Servlet implementation class InsertCheckInfo_Teacher
 */
@WebServlet("/InsertCheckInfo_Teacher")
public class InsertCheckInfo_Teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCheckInfo_Teacher() {
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
		String subject_id = request.getParameter("subject_id");
		String subject_th = request.getParameter("subject_th");
		int subject_th_num = Integer.parseInt(subject_th);
		String teacher_id = request.getParameter("teacher_id");
		String seat_index = request.getParameter("seat_index");
		int seat_index_num = Integer.parseInt(seat_index);
		String start_time = request.getParameter("start_time");
		System.out.println("课程ID:	" + subject_id);
		System.out.println("课程节数:	" + subject_th);
		System.out.println("教师ID:	" + teacher_id);
		System.out.println("签到开始时间：	"+start_time);
		int ischeck=0;
		PrintWriter out = response.getWriter();
		String insert_sql = "insert into all_check_info"
				+ "(subject_id,subject_th,student_id,seat_index,ischeck,check_time)" + "values" + "('" + subject_id
				+ "'," + subject_th_num + ",'" + teacher_id + "','" + seat_index_num + "'," + ischeck + ",'"
				+ start_time + "')";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(insert_sql);
			System.out.println("insert check_info success");
			out.println("insert check_info success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("insert check_info failed");
			out.println("insert check_info failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}