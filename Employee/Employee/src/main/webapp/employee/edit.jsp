<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 24/03/2024
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Màn Hình Chỉnh Sửa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1 class="text-center">Chỉnh Sửa Nhân Viên</h1>
    <form action="/employees?action=edit" method="post">
        <input type="hidden" name="id" value="${employee.id}">
        <div class="mb-3">
            <label for="name" class="form-label">Họ Tên</label>
            <input type="text" class="form-control" id="name" name="name" value="${employee.name}" required>
        </div>

        <div class="mb-3">
            <label for="birthDay" class="form-label">Ngày sinhs</label>
            <input type="text" class="form-control" id="birthDay" name="birthDay" value="${employee.birthDay}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Giới tính</label>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="male" name="gender" value="true" ${employee.gender ? 'checked' : ''}>
                <label for="male" class="form-check-label">Nam</label>
            </div>

            <div class="form-check">
                <input type="radio" class="form-check-input" id="female" name="gender" value="true" ${!employee.gender ? 'checked' : ''}>
                <label for="female" class="form-check-label">Nữ</label>
            </div>
        </div>

        <div class="mb-3">
            <label for="salary" class="form-label">Lương</label>
            <input type="text" class="form-control" id="salary" name="salary" value="${employee.salary}" required>
        </div>

        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Số Điện Thoại (Tìm kiếm gần đúng)</label>
            <input type="text" value="${employeeSearchDTO.phoneNumber}" class="form-control" id="phoneNumber" name="phoneNumber">
        </div>

        <div class="mb-3">
            <label for="departmentId" class="form-label">Bộ phận</label>
            <select class="form-select" id="departmentId" name="departmentId">
                <option value="">Tất cả</option>
                <c:forEach var="department" items="${departmentList}">
                    <option value="${department.id}"
                        ${employeeSearchDTO.departmentId == department.id ? 'selected': ''}>${department.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <a href="/employees" class="btn btn-danger">Huỷ</a>
        <button type="submit" class="btn btn-primary">Lưu</button>
    </form>
    <br>
</div>
</body>
</html>
