/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dao.Admin_StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name="EditStudentController", urlPatterns={"/EditStudentController"})
public class EditStudentController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditStudentController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditStudentController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            response.sendRedirect("Admin_StudentController");
            return;
        }
        request.setAttribute("studentId", id);
        request.getRequestDispatcher("editStudent.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    try {
        // Lấy dữ liệu từ form
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        // Kiểm tra và chuyển đổi số
        int birthyear = request.getParameter("birthyear") != null ? Integer.parseInt(request.getParameter("birthyear")) : 0;
        int claId = request.getParameter("claId") != null ? Integer.parseInt(request.getParameter("claId")) : 0;
        int accId = request.getParameter("accId") != null ? Integer.parseInt(request.getParameter("accId")) : 0;

        // Kiểm tra ID hợp lệ
        if (id == null || id.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Mã sinh viên không được để trống!");
            request.setAttribute("studentId", id);
            request.getRequestDispatcher("editStudent.jsp").forward(request, response);
            return;
        }

        // Gọi DAO để cập nhật thông tin sinh viên
        Admin_StudentDAO dao = new Admin_StudentDAO();
        boolean success = dao.updateStudent(id, name, birthyear, gender, phone, email, address, claId, accId);

        if (success) {
            response.sendRedirect("Admin_StudentController"); // Cập nhật thành công
        } else {
            request.setAttribute("errorMessage", "Cập nhật không thành công!");
            request.setAttribute("studentId", id);
            request.getRequestDispatcher("editStudent.jsp").forward(request, response);
        }

    } catch (NumberFormatException e) {
        request.setAttribute("errorMessage", "Dữ liệu không hợp lệ!");
        request.setAttribute("studentId", request.getParameter("id"));
        request.getRequestDispatcher("editStudent.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("errorMessage", "Lỗi hệ thống!");
        request.setAttribute("studentId", request.getParameter("id"));
        request.getRequestDispatcher("editStudent.jsp").forward(request, response);
    }
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
