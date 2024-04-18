<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 24/03/2024
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Màn Hình Thêm Mới</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<%@include file="../common/footer.jsp"%>
    <div class="container">
        <h1 class="text-center">Thêm Mới Nhân Viên</h1>
        <form action="/employees?action=create" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Họ Tên</label>
                <input type="text" value="${name}" class="form-control" id="name" name="name">
                <p class="text-danger">${massageError.name}</p>
            </div>

            <div class="mb-3">
                <label for="birthDay" class="form-label">Ngày sinh</label>
                <input type="text" value="${birthDay}" class="form-control" id="birthDay" name="birthDay">
                <p class="text-danger">${massageError.birthDay}</p>
            </div>

            <div class="mb-3">
                <label for="gender" class="form-label">Giới tính</label>
                <select class="form-select" id="gender" name="gender">
                    <option value="true" ${gender == 'true' ? 'selected': ''}>Nam</option>
                    <option value="false" ${gender == 'false' ? 'selected': ''}>Nữ</option>
                </select>
                <p class="text-danger">${massageError.gender}</p>
            </div>

            <div class="mb-3">
                <label for="salary" class="form-label">Lương</label>
                <input type="text" value="${salary}" class="form-control" id="salary" name="salary">
                <p class="text-danger">${massageError.salary}</p>
            </div>

            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Số Điện Thoại</label>
                <input type="text" value="${phoneNumber}" class="form-control" id="phoneNumber" name="phoneNumber">
                <p class="text-danger">${massageError.phoneNumber}</p>
            </div>

            <div class="mb-3">
                <label for="departmentId" class="form-label">Bộ phận</label>
                <select class="form-select" id="departmentId" name="departmentId">
                    <option value="">Tất cả</option>
                    <c:forEach var="department" items="${departmentList}">
                        <option value="${department.id}">${department.name}</option>
                    </c:forEach>
                </select>
                <p class="text-danger">${massageError.departmentId}</p>
            </div>

            <a href="/employees" class="btn btn-danger">Huỷ</a>
            <button type="submit" class="btn btn-primary">Lưu</button>
        </form>
        <br>
    </div>
<%@include file="../common/header.jsp"%>
</body>
</html>
