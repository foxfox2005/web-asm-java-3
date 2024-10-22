<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="vi">

<head>
	<jsp:include page="../layout/_meta.jsp" />
	<title>Sửa sản phẩm</title>
</head>

<body class="bg-light text-dark">
<jsp:include page="../layout/_headerAdmin.jsp" />

<section class="section-content">
	<div class="container">
		<header class="section-heading py-4">
			<h3 class="section-title">Sửa tin tức</h3>
		</header>

		<main class="row mb-5">
			<form class="col-lg-6" method="POST" action="${pageContext.request.contextPath}/admin/newsManager/update" enctype="multipart/form-data">
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
				<div class="mb-3">
					<label for="title" class="form-label">Tiêu đề <span class="text-danger">*</span></label>
					<input type="text" class="form-control" id="title" name="title" value="${requestScope.news.title}" required>
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">Nội dung <span class="text-danger">*</span></label>
					<textarea class="form-control" id="content" name="content">${requestScope.news.content}</textarea>
					<div class="invalid-feedback d-none">
						<ul class="list-unstyled">
							<li>Vui lòng nhập nội dung</li>
						</ul>
					</div>
				</div>
				<div class="mb-3">
					<label for="image" class="form-label d-block">Hình bài viết</label>
					<c:choose>
						<c:when test="${not empty requestScope.news.image}">
							<img width="500" class="img-thumbnail mb-3" src="${pageContext.request.contextPath}/static/files/${requestScope.news.image}" alt="${requestScope.news.image}" title="${requestScope.news.image}">
						</c:when>
						<c:otherwise>
							<div class="fst-italic mb-3">Không có hình</div>
						</c:otherwise>
					</c:choose>
					<input type="file" class="form-control" id="image" name="image" accept="image/*">
				</div>
				<div class="mb-3">
					<label for="categoriesId" class="form-label">Loại tin <span class="text-danger">*</span></label>
					<select class="form-select" id="categoriesId" name="categoriesId" required>
						<c:forEach var="category" varStatus="loop" items="${requestScope.categories}">
							<option value="${category.id}" ${requestScope.news.categoriesId == category.id ? 'selected' : ''}>${category.name}</option>
						</c:forEach>
					</select>
				</div>
				<c:if test="${sessionScope.currentUser.role}">
					<div class="mb-3">
						<label class="form-label d-block">Lên trang nhất <span class="text-danger">*</span></label>
						<div class="form-check d-inline-block">
							<input class="form-check-input" type="radio" name="home" id="home-false" value="false" ${!requestScope.news.home ? 'checked' : ''}>
							<label class="form-check-label" for="home-false">Có</label>
						</div>
						<div class="form-check d-inline-block me-4">
							<input class="form-check-input" type="radio" name="home" id="home-true" value="true" ${requestScope.news.home ? 'checked' : ''}>
							<label class="form-check-label" for="home-true">Không</label>
						</div>
					</div>
				</c:if>
				<input type="hidden" name="id" value="${requestScope.news.id}">
				<button type="submit" class="btn btn-primary me-2 mb-3">Sửa</button>
				<a class="btn btn-danger mb-3" href="${pageContext.request.contextPath}/admin" role="button" onclick="return confirm('Bạn có muốn hủy?')">Hủy</a>
			</form>
		</main>
	</div>
</section>

<jsp:include page="../layout/_footerAdmin.jsp" />
<script type="text/javascript">
	$(function() {
		$('#abc').DataTable({
			"paging": true,
			"lengthChange": false,
			"searching": false,
			"ordering": true,
			"info": true,
			"autoWidth": false,
			"responsive": true,
			"pageLength": 7,
		});
		$('#content').summernote({
			height: 450,
			focus: true
		});
		$('form').on('submit', function(e) {
			var content = $('#content').summernote('code');
			if (content === '' || content === '<p><br></p>') {
				e.preventDefault();
				$('.invalid-feedback').removeClass('d-none').addClass('d-block');
			} else {
				$('.invalid-feedback').removeClass('d-block').addClass('d-none');
			}
		});
	});
</script>
</body>

</html>