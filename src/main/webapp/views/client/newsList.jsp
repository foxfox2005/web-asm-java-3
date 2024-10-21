<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>News List</title>
    <jsp:include page="layout/_meta.jsp" />
</head>
<body class="bg-light text-dark">
<jsp:include page="layout/_header.jsp" />
<div class="container mt-5">
    <h1 class="text-primary mb-5">News</h1>
    <div class="row">
        <c:forEach var="news" items="${newsList}">
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h2 class="card-title text-primary">${news.title}</h2>
                        <p class="card-text text-muted">${news.summary}</p>
                        <a href="${pageContext.request.contextPath}/newsDetails?id=${news.id}" class="btn btn-primary">Read more</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="layout/_footer.jsp" />
</body>
</html>