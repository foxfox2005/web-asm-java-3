<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../layout/_meta.jsp" />
    <title>Update Category</title>
</head>
<body class="bg-light text-dark">
<jsp:include page="../layout/_headerAdmin.jsp" />

<section class="section-content">
    <div class="container">
        <header class="section-heading py-4">
            <h3 class="section-title">Update Category</h3>
        </header>

        <main class="row mb-5">
            <form class="col-lg-6" method="POST" action="${pageContext.request.contextPath}/admin/categoryManager/update">
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success mb-3" role="alert">
                            ${successMessage}
                    </div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger mb-3" role="alert">
                            ${errorMessage}
                    </div>
                </c:if>
                <div class="mb-3">
                    <label for="name" class="form-label">Category Name <span class="text-danger">*</span></label>
                    <input type="text" class="form-control ${not empty requestScope.violations.nameViolations ? 'is-invalid' : (not empty category.name ? 'is-valid' : '')}" id="name" name="name" value="${category.name}" required>
                    <c:if test="${not empty requestScope.violations.nameViolations}">
                        <div class="invalid-feedback">
                            <ul class="list-unstyled">
                                <c:forEach var="violation" items="${requestScope.violations.nameViolations}">
                                    <li>${violation}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-primary me-2 mb-3">Update</button>
                <a class="btn btn-danger mb-3" href="${pageContext.request.contextPath}/admin/categoryManager" role="button" onclick="return confirm('Bạn có muốn hủy?')">Cancel</a>
            </form>
        </main>
    </div>
</section>

<jsp:include page="../layout/_footerAdmin.jsp" />
</body>
</html>