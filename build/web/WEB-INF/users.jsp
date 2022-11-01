<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    </head>
    <body>
        
        <div class="container">
            <div class="row">
                <div class="col">
                    <h1>User Management System</h1>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>E-Mail</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Active</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.email}</td>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.active ? "Y" : "N"}</td>
                                    <td>
                                        <a href="<c:url value="user">
                                            <c:param name="action" value="edit"></c:param>
                                            <c:param name="email" value="${user.email}"></c:param>
                                        </c:url>">Edit</a>
                                        <a href="<c:url value="user">
                                            <c:param name="action" value="delete"></c:param>
                                            <c:param name="email" value="${user.email}"></c:param>
                                        </c:url>">Delete</a>
                                        

                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                 </div>
            </div>
        </div>
    </body>
</html>
