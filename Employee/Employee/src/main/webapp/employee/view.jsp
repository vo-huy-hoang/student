<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 24/03/2024
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Màn Hình Chi Tiết</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Chi Tiết Nhân Viên</h1>
        <div class="row">
            <div class="col-md-3"><label for="employeeId">Mã nhân viên:</label></div>
            <div class="col-md-3"><p id="employeeId">${employee.id}</p></div>
        </div>

        <div class="row">
            <div class="col-md-3"><label for="employeeName">Họ tên:</label></div>
            <div class="col-md-3"><p id="employeeName">${employee.name}</p></div>
        </div>

        <div class="row">
            <div class="col-md-3"><label for="employeeBirthDate">Ngày sinh:</label></div>
            <div class="col-md-3"><p id="employeeBirthDate">${employee.birthDay}</p></div>
        </div>

        <div class="row">
            <div class="col-md-3"><label for="employeeGender">Giới tính:</label></div>
            <div class="col-md-3"><p id="employeeGender">${employee.gender ? 'Nam' : 'Nữ'}</p></div>
        </div>

        <div class="row">
            <div class="col-md-3"><label for="employeeSalary">Lương:</label></div>
            <div class="col-md-3"><p id="employeeSalary">${employee.salary}</p></div>
        </div>

        <div class="row">
            <div class="col-md-3"><label for="phoneNumber">Số Điện Thoại:</label></div>
            <div class="col-md-3"><p id="phoneNumber">${employee.phoneNumber}</p></div>
        </div>

        <div class="row">
            <div class="col-md-3"><label for="departmentId">Bộ Phận:</label></div>
            <div class="col-md-3">
                <p id="departmentId">
                    <c:forEach var="department" items="${departmentList}">
                        <c:if test="${department.id == employee.departmentId}">
                            ${department.name}
                        </c:if>
                    </c:forEach>
                </p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3"><a href="/employees" class="btn btn-danger">Trở về</a></div>
        </div>
    </div>
</body>
</html>
