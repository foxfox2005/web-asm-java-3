<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="servletPath" scope="page" value="${requestScope['javax.servlet.forward.servlet_path']}" />
<header class="section-header">
	<section class="header-main border-bottom">
		<div class="container">
			<div class="row align-items-center">
				<div class="col py-3">
					<a class="text-body" href="${pageContext.request.contextPath}/admin">
						<h3>
							<span class="badge bg-primary"></span>
							Tin Tức
						</h3>
					</a>
				</div>
				<div class="col-sm-1">
					<ul class="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
						<li><a href="${pageContext.request.contextPath}/" class="nav-link text-body" target="_blank">
							<i class="bi bi-house d-block text-center fs-3"></i> Home
						</a></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
</header>

<nav class="navbar navbar-main navbar-expand-lg navbar-light border-bottom">
	<div class="container">
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link ${fn:startsWith(servletPath, '/admin') ? 'active' : ''}"
										href="${pageContext.request.contextPath}/admin"> <i class="bi bi-people"></i> Quản lý tin tức
				</a></li>
				<c:if test="${sessionScope.currentUser.role}">
					<li class="nav-item"><a class="nav-link ${fn:startsWith(servletPath, '/admin/categoryManager') ? 'active' : ''}"
											href="${pageContext.request.contextPath}/admin/categoryManager"> <i class="bi bi-tags"></i> Quản lý loại tin
					</a></li>
				</c:if>
				<c:if test="${sessionScope.currentUser.role}">
					<li class="nav-item"><a class="nav-link ${fn:startsWith(servletPath, '/admin/usersManager') ? 'active' : ''}"
											href="${pageContext.request.contextPath}/admin/usersManager"> <i class="bi bi-book"></i> Quản lý người dùng
					</a></li>
				</c:if>
				<c:if test="${sessionScope.currentUser.role}">
					<li class="nav-item"><a class="nav-link ${fn:startsWith(servletPath, '/admin/reviewManager') ? 'active' : ''}"
											href="${pageContext.request.contextPath}/admin/newslettersManager"> <i class="bi bi-star"></i> Quản lý Newsletter
					</a></li>
				</c:if>
			</ul>
			<c:choose>
				<c:when test="${not empty sessionScope.currentUser}">
					<a class="btn btn-light ms-2" href="${pageContext.request.contextPath}/signout" role="button"> Đăng xuất </a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/signin" role="button"> Đăng nhập </a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>