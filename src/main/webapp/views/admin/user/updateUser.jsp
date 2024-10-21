<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="vi">

<head>
	<jsp:include page="../layout/_meta.jsp" />
	<title>Thêm tài khoản</title>
</head>

<body class="bg-light text-dark">
<jsp:include page="../layout/_headerAdmin.jsp" />

<section class="section-content">
	<div class="container">
		<header class="section-heading py-4">
			<h3 class="section-title">Cập nhật người dùng</h3>
		</header>

		<main class="row mb-5">
			<form class="col-lg-6" method="POST" action="${pageContext.request.contextPath}/admin/usersManager/update">
				<c:if test="${not empty requestScope.successMessage}">
					<div class="alert alert-success mb-3" role="alert">
							${requestScope.successMessage}
					</div>
				</c:if>
				<c:if test="${not empty requestScope.errorMessage}">
					<div class="alert alert-danger mb-3" role="alert">
							${requestScope.errorMessage}
					</div>
				</c:if>
				<input type="hidden" class="form-control" id="id" name="id" value="${requestScope.user.id}">
				<div class="mb-3">
					<label for="password" class="form-label">Mật khẩu <span class="text-danger">*</span></label>
					<input type="password" class="form-control" id="password" name="password" value="${requestScope.user.password}" required>
				</div>
				<div class="mb-3">
					<label for="fullname" class="form-label">Họ tên <span class="text-danger">*</span></label>
					<input type="text" class="form-control" id="fullname" name="fullname" value="${requestScope.user.fullname}" required>
				</div>
				<div class="mb-3">
					<label for="birthday" class="form-label">Ngày sinh</label>
					<input type="date" class="form-control" id="birthday" name="birthday" value="${requestScope.user.birthday}" required>
				</div>
				<div class="mb-3">
					<label class="form-label d-block">Giới tính <span class="text-danger">*</span></label>
					<div class="form-check d-inline-block">
						<input class="form-check-input" type="radio" name="gender" id="gender-false" value="false" ${!requestScope.user.gender ? 'checked' : ''} required>
						<label class="form-check-label" for="gender-false">Nữ</label>
					</div>
					<div class="form-check d-inline-block me-4">
						<input class="form-check-input" type="radio" name="gender" id="gender-true" value="true" ${requestScope.user.gender ? 'checked' : ''} required>
						<label class="form-check-label" for="gender-true">Nam</label>
					</div>
				</div>
				<div class="mb-3">
					<label for="mobile" class="form-label">Số điện thoại <span class="text-danger">*</span></label>
					<input type="text" class="form-control" id="mobile" name="mobile" value="${requestScope.user.mobile}" required>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Email <span class="text-danger">*</span></label>
					<input type="email" class="form-control" id="email" name="email" value="${requestScope.user.email}" required>
				</div>
				<div class="mb-3">
					<label class="form-label d-block">Chức vụ? <span class="text-danger">*</span></label>
					<div class="form-check d-inline-block">
						<input class="form-check-input" type="radio" name="role" id="role-employee" value="0" ${!requestScope.user.role ? 'checked' : ''} required>
						<label class="form-check-label" for="role-employee">Phóng viên</label>
					</div>
					<div class="form-check d-inline-block me-4">
						<input class="form-check-input" type="radio" name="role" id="role-admin" value="true" ${requestScope.user.role ? 'checked' : ''} required>
						<label class="form-check-label" for="role-admin">Quản trị</label>
					</div>
				</div>
				<button type="submit" class="btn btn-primary me-2 mb-3">Sửa</button>
				<a class="btn btn-danger mb-3" href="${pageContext.request.contextPath}/admin/usersManager" role="button" onclick="return confirm('Bạn có muốn hủy?')">Hủy</a>
			</form>
		</main>
	</div>
</section>

<jsp:include page="../layout/_footerAdmin.jsp" />
</body>

</html>