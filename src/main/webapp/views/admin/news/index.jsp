<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="../layout/_meta.jsp" />
	<title>Quản lý tin tức</title>
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
			<h3 class="section-title">Quản lý tin tức</h3>
			<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/newsManager/create" role="button" style="height: fit-content;">Thêm tin tức</a>
		</header>

		<main class="table-responsive-xl mb-5">
			<table id="abc" class="table table-bordered table-striped table-hover align-middle">
				<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">ID</th>
					<th scope="col">Tiêu đề</th>
					<th scope="col">Hình ảnh</th>
					<th scope="col">Tác giả</th>
					<th scope="col">Số lượt xem</th>
					<th scope="col">Mã loại tin</th>
					<c:if test="${sessionScope.currentUser.role}">
						<th scope="col">Trang nhất</th>
					</c:if>
					<th scope="col" style="width: 225px;">Thao tác</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="news" varStatus="loop" items="${requestScope.news}">
					<tr>
						<th scope="row">${loop.index + 1}</th>
						<td>${news.id}</td>
						<td>${news.title}</td>
						<td class="text-center">
							<c:choose>
								<c:when test="${empty news.image}">
									<img width="38" src="${pageContext.request.contextPath}/img/280px.png" alt="280px.png">
								</c:when>
								<c:otherwise>
									<img width="150" src="${pageContext.request.contextPath}/static/files/${news.image}" alt="${news.image}">
								</c:otherwise>
							</c:choose>
						</td>
						<td>${news.usersId}</td>
						<td>${news.viewCount}</td>
						<td>${news.categoriesId}</td>
						<c:if test="${sessionScope.currentUser.role}">
							<td>${news.home ? 'Có' : 'Không'}</td>
						</c:if>
						<td class="text-center text-nowrap">
							<a class="btn btn-success me-2" href="${pageContext.request.contextPath}/admin/newsManager/update?id=${news.id}" role="button">Sửa</a>
							<a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/newsManager/delete?id=${news.id}" role="button" onclick="return confirm('Bạn có muốn xóa?')">Xóa</a>
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