package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Task;
import dao.ToDoDAOImpl;
import dao.ToDoDAOIntf;


@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		ServletContext context=getServletContext();
		
		String taskName=request.getParameter("taskName").trim();
		String taskDate=request.getParameter("taskDate").trim();
		int taskStatus=Integer.parseInt(request.getParameter("taskStatus").trim());
		int regId=Integer.parseInt(session.getAttribute("regId").toString());
		Task task=new Task(0,taskName,taskDate,taskStatus,regId);
		
		ToDoDAOIntf dao=ToDoDAOImpl.getInstance();
		int taskId=dao.addTask(regId, task);
		if(taskId>0) {
			context.getRequestDispatcher("/ViewTasks.jsp").forward(request,  response);
		}
	}

}
