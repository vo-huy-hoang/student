<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 24/03/2024
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fnt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Danh Sách Nhân Viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <div class="container mt-5">
        <div class="col-12 text-end">
            <span class="text-info" style="font-size: 20px; font-weight: bold;">${sessionScope.userDetail.username}</span>
            <a href="logout" class="btn btn-success">
                <i class="fa-solid fa-right-from-bracket"></i> Đăng Xuất
            </a>
        </div>

        <h2>Tìm kiếm nhân viên</h2>
        <form action="/employees" method="get">
            <div class="row">
                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="name" class="form-label">Tên (Tìm kiếm gần đúng)</label>
                        <input type="text" value="${employeeSearchDTO.name}" class="form-control" id="name" name="name">
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="fromBirthDay" class="form-label">Ngày sinh từ</label>
                        <input type="text" value="${employeeSearchDTO.fromBirthDay}" class="form-control" id="fromBirthDay" name="fromBirthDay">
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="toBirthDay" class="form-label">Ngày sinh đến</label>
                        <input type="text" value="${employeeSearchDTO.toBirthDay}" class="form-control" id="toBirthDay" name="gender">
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="gender" class="form-label">Giới tính</label>
                        <select class="form-select" id="gender" name="gender">
                            <option value="">Tất cả</option>
                            <option value="true" ${employeeSearchDTO.gender == 'true' ? 'selected': ''}>Nam</option>
                            <option value="false" ${employeeSearchDTO.gender == 'false' ? 'selected': ''}>Nữ</option>
                        </select>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="salary" class="form-label">Mức Lương</label>
                        <select class="form-select" id="salary" name="salary">
                            <option value="">Tất cả</option>
                            <option value="lt5" ${employeeSearchDTO.salary == 'lt5' ? 'selected': ''}>Dưới 5 triệu</option>
                            <option value="5-10" ${employeeSearchDTO.salary == '5-10' ? 'selected': ''}>Từ 5 triệu đến dưới 10 triệu</option>
                            <option value="10-15" ${employeeSearchDTO.salary == '10-15' ? 'selected': ''}>Từ 10 triệu đến dưới 15 triệu</option>
                            <option value="gt15" ${employeeSearchDTO.salary == 'gt15' ? 'selected': ''}>Trên 15 triệu</option>
                        </select>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Số Điện Thoại (Tìm kiếm gần đúng)</label>
                        <input type="text" value="${employeeSearchDTO.phoneNumber}" class="form-control" id="phoneNumber" name="phoneNumber">
                    </div>
                </div>

                <div class="col-md-4">
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
                </div>

                <div class="col-md-8">
                    <div class="col-12 text-end">
                        <button type="reset" class="btn btn-info mt-5"><i class="fa-solid fa-rotate-left"></i>Đặt lại</button>
                        <button type="submit" class="btn btn-primary mt-5"><i class="fa-solid fa-magnifying-glass"></i>Tìm kiếm</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="container mt-4">
        <h1 class="text-danger">${param.message}</h1>
        <c:if test="${sessionScope.userDetail.roles.contains('admin')}">
            <div class="col-12 text-end">
                <a href="employees?action=create" class="btn btn-success">Thêm Mới</a>
            </div>
        </c:if>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Tên</th>
                <th scope="col">Ngày Sinh</th>
                <th scope="col">Giới Tính</th>
                <th scope="col">Lương</th>
                <th scope="col">Số Điện Thoại</th>
                <th scope="col">Bộ Phận</th>
                <th scope="col">Thao Tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employee" items="${employeeList}" varStatus="varStatus">
                <tr>
                    <th scope="row">${varStatus.count}</th>
                    <td>${employee.name}</td>
                    <td>
                        <fmt:parseDate value="${employee.birthDay}" pattern="yyyy-MM-dd" var="parsedDate"/>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedDate}"/>

                    </td>
                    <td>${employee.gender ? 'Nam' : "Nữ"}</td>
                    <td>
                        <fmt:setLocale value="vi_VN"/>
                        <fmt:formatNumber value="${employee.salary}" pattern="#,##0.00đ" />
                    </td>
                    <td>${employee.phoneNumber}</td>
                    <td>${employee.departmentName}</td>
                    <td>
                        <c:if test="${sessionScope.userDetail.roles.contains('admin')}">
                            <a href="employees?action=edit&id=${employee.id}" class="btn btn-primary"><i class="fas fa-pencil-alt"></i>Cập nhật</a>
                            <button data-bs-toggle="modal" data-bs-target="#confirmDeleteModal" onclick="deleteEmployee(${employee.id}, '${employee.name}')" class="btn btn-danger"><i class="fas fa-trash"></i>Xoá</button>
                        </c:if>
                        <a href="employees?action=view&id=${employee.id}" class="btn btn-info"><i class="fas fa-eye"></i>Chi tiết</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title fs-5" id="confirmDeleteModalLabel">Xác nhận xoá nhân viên</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có chắc chắn muốn xoá nhân viên <span id="employeeNameToDelete" class="form-label text-danger"></span>
                </div>
                <form>
                    <div class="modal-footer">
                        <input type="hidden" id="employIdDelete" name="id">
                        <input type="hidden" name="action" value="delete">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                        <button type="submit" class="btn btn-danger" id="confirmDeleteButton">Xoá</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
        function deleteEmployee(id, name) {
            $("#employeeNameToDelete").text(name);
            $("#employIdDelete").text(name).val(id);
        }
    </script>

<%@include file="../common/footer.jsp"%>
</body>
</html>
