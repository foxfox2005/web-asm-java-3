<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="../layout/_meta.jsp" />
	<title>Update Newsletter</title>
</head>
<body class="bg-light text-dark">
<jsp:include page="../layout/_headerAdmin.jsp" />

<section class="section-content">
	<div class="container">
		<header class="section-heading py-4">
			<h3 class="section-title">Update Newsletter</h3>
		</header>

		<main class="row mb-5">
			<form class="col-lg-6" method="POST" action="${pageContext.request.contextPath}/admin/newslettersManager/update">
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
					<label for="email" class="form-label">Email <span class="text-danger">*</span></label>
					<input type="email" class="form-control ${not empty requestScope.nameViolations ? 'is-invalid' : (not empty requestScope.newsletter.email ? 'is-valid' : '')}" id="email" name="email" value="${requestScope.newsletter.email}" required>
					<c:if test="${not empty requestScope.nameViolations}">
						<div class="invalid-feedback">
							<ul class="list-unstyled">
								<li>${requestScope.nameViolations}</li>
							</ul>
						</div>
					</c:if>
				</div>
				<div class="mb-3">
					<label class="form-label d-block">Trạng thái <span class="text-danger">*</span></label>
					<div class="form-check d-inline-block">
						<input class="form-check-input" type="radio" name="enabled" id="enabled-false" value="false" ${!requestScope.newsletter.enabled ? 'checked' : ''}>
						<label class="form-check-label" for="enabled-false">Không</label>
					</div>
					<div class="form-check d-inline-block me-4">
						<input class="form-check-input" type="radio" name="enabled" id="enabled-true" value="true" ${requestScope.newsletter.enabled ? 'checked' : ''}>
						<label class="form-check-label" for="enabled-true">Có</label>
					</div>
				</div>
				<button type="submit" class="btn btn-primary me-2 mb-3">Update</button>
				<a class="btn btn-danger mb-3" href="${pageContext.request.contextPath}/admin/newslettersManager" role="button" onclick="return confirm('Bạn có muốn hủy?')">Hủy</a>
			</form>
		</main>
	</div>
</section>

<jsp:include page="../layout/_footerAdmin.jsp" />
</body>
</html>