<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../layout/_meta.jsp" />
	<title>Quản lý loại tin</title>
</head>
<body class="bg-light text-dark">
<jsp:include page="../layout/_headerAdmin.jsp" />
<section class="section-content">
	<div class="container">
		<c:if test="${not empty sessionScope.successMessage}">
			<div class="alert alert-success mb-0 mt-4" role="alert">
					${sessionScope.successMessage}
			</div>
		</c:if>
		<c:if test="${not empty sessionScope.errorMessage}">
			<div class="alert alert-danger mb-0 mt-4" role="alert">
					${sessionScope.errorMessage}
			</div>
		</c:if>
		<c:remove var="successMessage" scope="session" />
		<c:remove var="errorMessage" scope="session" />

		<header class="section-heading py-4 d-flex justify-content-between">
			<h3 class="section-title">Quản lý loại tin</h3>
			<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/categoryManager/create" role="button" style="height: fit-content;">Thêm loại tin</a>
		</header>

		<main class="table-responsive-xl mb-5">
			<table id="abc" class="table table-bordered table-striped table-hover align-middle">
				<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Tên loại</th>
					<th scope="col" style="width: 225px;">Thao tác</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="category" varStatus="loop" items="${requestScope.categories}">
					<tr>
						<th scope="row">${loop.index + 1}</th>
						<td>${category.name}</td>
						<td class="text-center text-nowrap">
							<a class="btn btn-success me-2" href="${pageContext.request.contextPath}/admin/categoryManager/update?id=${category.id}" role="button">Sửa</a>
							<a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/categoryManager/delete?id=${category.id}" role="button" onclick="return confirm('Bạn có muốn xóa?')">Xóa</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</main>
	</div>
</section>

<jsp:include page="../layout/_footerAdmin.jsp" />
</body>
</html>