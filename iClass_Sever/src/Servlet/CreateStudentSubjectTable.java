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
 * Servlet implementation class CreateStudentSubjectTable
 */
@WebServlet("/CreateStudentSubjectTable")
public class CreateStudentSubjectTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateStudentSubjectTable() {
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
		String student_id=request.getParameter("student_id");
		System.out.println("学生id：	"+student_id);
		PrintWriter out = response.getWriter();
		String create_sql ="CREATE TABLE IF NOT EXISTS "
				+ "`iclass`.`student_"+student_id
				+"`(`subject_name` CHAR(15) NOT NULL,"
				+ "`subject_id` CHAR(15) NOT NULL,"
				+ "`classroom` CHAR(15) NOT NULL,"
				+ "`teacher_name` CHAR(15) NOT NULL,"
				+ "PRIMARY KEY (`subject_id`),"
				+ "UNIQUE INDEX `subject_id_UNIQUE` (`subject_id` ASC))";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.execute(create_sql);
			// 输出结果
			System.out.println("create table student_"+student_id+" success");
			out.println("create table student_"+student_id+" success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("create table student_"+student_id+" failed");
			out.println("create table student_"+student_id+" failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}