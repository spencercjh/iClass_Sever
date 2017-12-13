package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertCheckInfo_Teacher_Help
 */
@WebServlet("/InsertCheckInfo_Teacher_Help")
public class InsertCheckInfo_Teacher_Help extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCheckInfo_Teacher_Help() {
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
		String student_id = request.getParameter("student_id");
		String seat_index = request.getParameter("seat_index");
		int seat_index_num = Integer.parseInt(seat_index);
		String ischeck= request.getParameter("ischeck");
		int ischeck_num=Integer.parseInt(ischeck);
		System.out.println("课程ID:	" + subject_id);
		System.out.println("课程节数:	" + subject_th);
		System.out.println("学生ID:	" + student_id);
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		String check_time = formatter2.format(new Date());
		System.out.println("当前时间:	" + check_time);
		PrintWriter out = response.getWriter();
		String insert_sql = "insert into all_check_info"
				+ "(subject_id,subject_th,student_id,seat_index,ischeck,check_time)" + "values" + "('" + subject_id
				+ "'," + subject_th_num + ",'" + student_id + "','" + seat_index_num + "'," + ischeck_num + ",'"
				+ check_time + "')";
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