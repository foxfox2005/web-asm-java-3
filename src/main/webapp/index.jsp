<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tailwind.css">
</head>
<body>
    <jsp:include page="views/client/layout/_header.jsp" />
    <td class="text-center">
        <c:choose>
            <c:when test="${empty news.image}">
                <img width="38"
                     src="${pageContext.request.contextPath}/img/280px.png"
                     alt="280px.png">
            </c:when>
            <c:otherwise>
                <img width="150"
                     src="${pageContext.request.contextPath}/static/files/${news.image}"
                     alt="${news.image}">
            </c:otherwise>
        </c:choose>
    </td>
    <jsp:include page="views/client/layout/_footer.jsp" />
</body>
</html>