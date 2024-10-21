<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>News Details</title>
    <jsp:include page="layout/_meta.jsp" />
</head>
<body class="bg-light text-dark">
<jsp:include page="layout/_header.jsp" />
<div class="container mt-5">
    <c:if test="${not empty news}">
        <h1 class="text-primary mb-4">${news.title}</h1>
        <c:choose>
            <c:when test="${empty news.image}">
                <img class="img-fluid mb-4" src="${pageContext.request.contextPath}/img/280px.png" alt="280px.png">
            </c:when>
            <c:otherwise>
                <img class="img-fluid mb-4" src="${pageContext.request.contextPath}/static/files/${news.image}" alt="${news.image}">
            </c:otherwise>
        </c:choose>
        <p class="text-muted">${news.content}</p>
    </c:if>
    <c:if test="${empty news}">
        <p class="text-danger">No news details available.</p>
    </c:if>
</div>
<jsp:include page="layout/_footer.jsp" />
</body>
</html>