<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="layout/_meta.jsp" />
	<title>Trang chủ</title>
</head>
<body class="bg-light text-dark">
<jsp:include page="layout/_header.jsp" />
<section class="section-content mb-5">
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<c:forEach var="news" varStatus="loop" items="${newsHome}">
					<div class="card mb-4">
						<div class="row no-gutters">
							<div class="col-md-4">
								<c:choose>
									<c:when test="${empty news.image}">
										<img class="card-img" src="${pageContext.request.contextPath}/img/280px.png" alt="280px.png">
									</c:when>
									<c:otherwise>
										<img class="card-img" src="${pageContext.request.contextPath}/static/files/${news.image}" alt="${news.image}">
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<h5 class="card-title">
										<a href="${pageContext.request.contextPath}/newsDetails?id=${news.id}" class="text-primary">
												${news.title}
										</a>
									</h5>
									<p class="card-text text-muted">${news.summary}</p>
									<p class="card-text">
										<small class="text-secondary">Last updated |</small>
									</p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-4">
				<div class="bg-white shadow rounded p-4">
					<h4 class="h5 mb-2">5 Bản tin được xem nhiều nhất</h4>
					<c:forEach var="news" items="${topViewed}">
						<a href="${pageContext.request.contextPath}/newsDetails?id=${news.id}" class="d-block text-primary mb-2">${news.title}</a>
					</c:forEach>
					<h4 class="h5 mb-2">5 Bản tin mới nhất</h4>
					<c:forEach var="news" items="${latest}">
						<a href="${pageContext.request.contextPath}/newsDetails?id=${news.id}" class="d-block text-primary mb-2">${news.title}</a>
					</c:forEach>
					<h4 class="h5 mb-2">5 Bản tin bạn đã xem</h4>
					<c:forEach var="news" items="${userViewed}">
						<a href="${pageContext.request.contextPath}/newsDetails?id=${news.id}" class="d-block text-primary mb-2">${news.title}</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="layout/_footer.jsp"/>
</body>
</html>