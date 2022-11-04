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
                    <button onclick="window.location.href='<c:url value="user">
                    <c:param name="action" value="add"></c:param></c:url>';"  class="btn btn-outline-primary">Create New User</button>
                    
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
        <br>
        
        <c:set var = "showDiv" scope = "session" value = "${showDiv}"/>
        <div class="container" <c:if test = "${ ( showDiv != 'edit') }"> hidden </c:if> > 
            <div class="row">
                <div class="col-4">
                    <h2>Edit User</h2>
                    <form action="user" method="post">
                        <p><label for="email" class="form-label">Email:</label><input id="email" name="email" type="text" value="${user.email}" readonly class="form-control" ></p>
                        <p><label for="firstName" class="form-label">First Name:</label><input id="firstName" name="firstName" type="text" value="${user.firstName}"  class="form-control" ></p>
                        <p><label for="lastName" class="form-label">Last Name:</label><input id="lastName" name="lastName" type="text" value="${user.lastName}"  class="form-control" ></p>
                        <p><label for="password" class="form-label">Password:</label><input id="password" name="password" type="text" value="${user.password}"  class="form-control" ></p>
                        <p><label for="role" class="form-label">Role:</label><select name="roleId" id="roleId" class="form-select" >
                            <c:forEach var="roleList" items="${roles}">
                                <c:set var = "id1" scope = "session" value = "${roleList.id}"/>
                                <c:set var = "id2" scope = "session" value = "${user.role.id}"/>
                                <option value="${roleList.id}"  <c:if test = "${ (id1 == id2) }"> selected </c:if> >${roleList.name}</option>

                            </c:forEach>
                            </select>
                        </p>

                        <button type="submit"  class="btn btn-outline-primary">Save</button>
                        <br>
                    </form>
                </div>
            </div>
        </div>
        
        <c:set var = "showDiv" scope = "session" value = "${showDiv}"/>
        <div class="container" <c:if test = "${ ( showDiv != 'add') }"> hidden </c:if>> 
            <div class="row">
                <div class="col-4">
                    <h2>Add User</h2>
                    <form action="user" method="post">
                        
                        <input type="hidden" id="action" name="action" value="add">
                        <p><label for="email" class="form-label">Email:</label><input id="email" name="email" type="text" class="form-control"></p>
                        <p><label for="firstName" class="form-label">First Name:</label><input id="firstName" name="firstName" type="text" class="form-control"></p>
                        <p><label for="lastName" class="form-label">Last Name:</label><input id="lastName" name="lastName" type="text" class="form-control"></p>
                        <p><label for="password" class="form-label">Password:</label><input id="password" name="password" type="text" class="form-control"></p>
                        <p><label for="role" class="form-label">Role:</label><select name="roleId" id="roleId" class="form-select" >
                            <c:forEach var="roleList" items="${roles}">
                                <option value="${roleList.id}">${roleList.name}</option>
                            </c:forEach>
                            </select>
                        </p>

                        <button type="submit"  class="btn btn-outline-primary">Save</button>
                        <br>
                    </form>
                </div>
            </div>
        </div>
                        
    </body>
</html>
