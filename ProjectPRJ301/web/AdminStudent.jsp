<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="SidebarAdmin.jsp"/>
<%@page import="java.util.List"%>
<%@page import="models.Student"%>
<%@page  import="models.Account" %>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null || account.getRoleId() != 1) {
        response.sendRedirect("Login.jsp"); // Chuyển về trang đăng nhập nếu không phải giáo viên
        return;
    }
%>


<div class="content">



    <title>Quản lý Sinh viên</title>
    <style>
       body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
}

.content {
    margin-left: 250px; /* Để phù hợp với sidebar */
    padding: 20px;
}

h1 {
    color: #333;
}

form {
    margin-bottom: 15px;
}

input[type="text"] {
    padding: 8px;
    width: 200px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

button {
    padding: 8px 12px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}

input[type="submit"] {
    padding: 8px 12px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;
}

input[type="submit"]:hover {
    background-color: #218838;
}

table {
    width: 100%;
    border-collapse: collapse;
    background-color: white;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table th, table td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: left;
}

table th {
    background-color: #007bff;
    color: white;
}

table tr:nth-child(even) {
    background-color: #f9f9f9;
}

a.btn {
    padding: 5px 10px;
    text-decoration: none;
    border-radius: 3px;
}

a.edit {
    background-color: #ffc107;
    color: black;
}

a.delete {
    background-color: #dc3545;
    color: white;
}

a.edit:hover {
    background-color: #e0a800;
}

a.delete:hover {
    background-color: #c82333;
}

    </style>

    <h2 align="center">Quản lý Sinh viên</h2>


    <form action="Admin_StudentController" method="GET">
        <input type="text" name="searchID" placeholder="Nhập Mã Sinh Viên" 
               value="<%= request.getParameter("searchID") != null ? request.getParameter("searchID") : "" %>">
        <button type="submit">Tìm kiếm</button>
    </form>



    <table>
        <tr>
            <th>Student ID</th>
            <th>Full Name</th>
            <th>Birth Year</th>
            <th>Gender</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Address</th>
            <th>Class ID</th>
            <th>Account ID</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="student" items="${listP}">
            <tr>
                <td>${student.stuId}</td>
                <td>${student.stuName}</td>
                <td>${student.birthyear}</td>
                <td>${student.gender}</td>
                <td>${student.phone}</td>
                <td>${student.email}</td>
                <td>${student.address}</td>
                <td>${student.claId}</td>
                <td>${student.accId}</td>
                <td>
                    <a href="editStudent.jsp?id=${student.stuId}" class="btn edit">Sửa</a>
                    <a href="javascript:void(0);" class="btn delete" onclick="confirmDelete('${student.stuId}')">Xóa</a>
                </td>
            </tr>
        </c:forEach>

        <script>
            function confirmDelete(studentId) {
                if (confirm("Bạn có chắc chắn muốn xóa sinh viên này không?")) {
                    window.location.href = "Admin_StudentController?action=delete&id=" + studentId;
                }
            }
        </script>

        <c:if test="${not empty message}">
            <p style="color: green;">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

 
    </table>


</div>

</body>
</html>
