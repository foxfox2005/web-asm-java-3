<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Subscription Exists</title>
    <jsp:include page="layout/_meta.jsp" />
</head>
<body class="bg-light text-dark">
<jsp:include page="layout/_header.jsp" />
<div class="container py-5">
    <div class="bg-white shadow rounded p-4 text-center">
        <h1 class="text-warning mb-4">Subscription Exists!</h1>
        <p class="mb-4">You are already subscribed to our newsletter.</p>
        <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Go back to Home</a>
    </div>
</div>
<jsp:include page="layout/_footer.jsp" />
</body>
</html>