<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<footer class="bg-light">
  <section class="py-5 bg-secondary text-white">
    <div class="container">
      <div class="row">
        <div class="col-lg-3 col-md-6 mb-4">
          <h6 class="font-weight-bold">Về chúng tôi</h6>
          <ul class="list-unstyled">
            <li><a href="#" class="text-white">Giới thiệu</a></li>
            <li><a href="#" class="text-white">Tòa soạn</a></li>
            <li><a href="#" class="text-white">Đội ngũ phóng viên</a></li>
            <li><a href="#" class="text-white">Liên hệ quảng cáo</a></li>
            <li><a href="#" class="text-white">Điều khoản sử dụng</a></li>
            <li><a href="#" class="text-white">Chính sách bảo mật</a></li>
          </ul>
        </div>
        <div class="col-lg-3 col-md-6 mb-4">
          <h6 class="font-weight-bold">Chuyên mục</h6>
          <ul class="list-unstyled">
            <li><a href="#" class="text-white">Thời sự</a></li>
            <li><a href="#" class="text-white">Kinh tế</a></li>
            <li><a href="#" class="text-white">Thể thao</a></li>
            <li><a href="#" class="text-white">Giải trí</a></li>
            <li><a href="#" class="text-white">Công nghệ</a></li>
            <li><a href="#" class="text-white">Giáo dục</a></li>
          </ul>
        </div>
        <div class="col-lg-3 col-md-6 mb-4">
          <h6 class="font-weight-bold">Liên kết</h6>
          <ul class="list-unstyled">
            <li><a href="#" class="text-white">Báo in</a></li>
            <li><a href="#" class="text-white">Tạp chí</a></li>
            <li><a href="#" class="text-white">Ứng dụng mobile</a></li>
            <li><a href="#" class="text-white">RSS</a></li>
            <li><a href="#" class="text-white">Sitemap</a></li>
          </ul>
        </div>
        <div class="col-lg-3 col-md-6 mb-4">
          <h6 class="font-weight-bold">Đăng ký nhận tin</h6>
          <form action="${pageContext.request.contextPath}/subscribe" method="post">
            <div class="input-group">
              <input type="email" name="email" class="form-control" placeholder="Email của bạn ..." required>
              <button class="btn btn-primary" type="submit">Đăng ký</button>
            </div>
          </form>
          <div class="mt-3">
            <h6 class="pb-2 font-bold">Kết nối với chúng tôi</h6>
            <div class="flex space-x-2">
              <a href="#" class="bg-white p-2 rounded-full shadow hover:bg-gray-100">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="currentColor">
                  <path d="M22.675 0H1.325C.593 0 0 .593 0 1.325v21.351C0 23.407.593 24 1.325 24h11.495v-9.294H9.691v-3.622h3.129V8.413c0-3.1 1.893-4.788 4.659-4.788 1.325 0 2.463.099 2.795.143v3.24l-1.918.001c-1.504 0-1.794.715-1.794 1.763v2.31h3.587l-.467 3.622h-3.12V24h6.116c.73 0 1.324-.593 1.324-1.324V1.325C24 .593 23.407 0 22.675 0z"/>
                </svg>
              </a>
              <a href="#" class="bg-white p-2 rounded-full shadow hover:bg-gray-100">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="currentColor">
                  <path d="M23.954 4.569c-.885.392-1.83.656-2.825.775 1.014-.608 1.794-1.574 2.163-2.723-.951.564-2.005.974-3.127 1.195-.897-.957-2.178-1.555-3.594-1.555-2.717 0-4.92 2.203-4.92 4.917 0 .39.045.765.127 1.124-4.087-.205-7.713-2.165-10.141-5.144-.423.722-.666 1.561-.666 2.457 0 1.694.863 3.188 2.173 4.065-.803-.026-1.56-.247-2.22-.616v.062c0 2.364 1.684 4.337 3.918 4.785-.41.111-.84.171-1.285.171-.314 0-.615-.03-.916-.086.631 1.953 2.445 3.377 4.6 3.417-1.68 1.319-3.809 2.105-6.102 2.105-.396 0-.79-.023-1.175-.067 2.179 1.397 4.768 2.212 7.548 2.212 9.054 0 14.002-7.496 14.002-13.986 0-.21 0-.423-.015-.634.961-.695 1.8-1.562 2.46-2.549z"/>
                </svg>
              </a>
              <a href="#" class="bg-white p-2 rounded-full shadow hover:bg-gray-100">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="currentColor">
                  <path d="M12 2.163c3.204 0 3.584.012 4.85.07 1.366.062 2.633.334 3.608 1.31.975.975 1.248 2.242 1.31 3.608.058 1.266.07 1.646.07 4.85s-.012 3.584-.07 4.85c-.062 1.366-.334 2.633-1.31 3.608-.975.975-2.242 1.248-3.608 1.31-1.266.058-1.646.07-4.85.07s-3.584-.012-4.85-.07c-1.366-.062-2.633-.334-3.608-1.31-.975-.975-1.248-2.242-1.31-3.608-.058-1.266-.07-1.646-.07-4.85s.012-3.584.07-4.85c.062-1.366.334-2.633 1.31-3.608.975-.975 2.242-1.248 3.608-1.31 1.266-.058 1.646-.07 4.85-.07zm0-2.163C8.756 0 8.333.012 7.052.07 5.77.128 4.548.334 3.468 1.414 2.388 2.494 2.182 3.716 2.124 5c-.058 1.281-.07 1.704-.07 4.95s.012 3.669.07 4.95c.058 1.281.264 2.503 1.344 3.583 1.08 1.08 2.302 1.286 3.583 1.344 1.281.058 1.704.07 4.95.07s3.669-.012 4.95-.07c1.281-.058 2.503-.264 3.583-1.344 1.08-1.08 1.286-2.302 1.344-3.583.058-1.281.07-1.704.07-4.95s-.012-3.669-.07-4.95c-.058-1.281-.264-2.503-1.344-3.583-1.08-1.08-2.302-1.286-3.583-1.344-1.281-.058-1.704-.07-4.95-.07z"/>
                  <path d="M12 5.838a6.162 6.162 0 1 0 0 12.324 6.162 6.162 0 0 0 0-12.324zm0 10.162a3.999 3.999 0 1 1 0-7.998 3.999 3.999 0 0 1 0 7.998zm6.406-11.845a1.44 1.44 0 1 0 0 2.88 1.44 1.44 0 0 0 0-2.88z"/>
                </svg>
              </a>
              <a href="#" class="bg-white p-2 rounded-full shadow hover:bg-gray-100">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="currentColor">
                  <path d="M19.615 3.184c-1.2-.49-2.5-.82-3.84-.97-1.34-.15-2.7-.23-4.05-.23s-2.71.08-4.05.23c-1.34.15-2.64.48-3.84.97-1.2.49-2.29 1.15-3.24 2.1-.95.95-1.61 2.04-2.1 3.24-.49 1.2-.82 2.5-.97 3.84-.15 1.34-.23 2.7-.23 4.05s.08 2.71.23 4.05c.15 1.34.48 2.64.97 3.84.49 1.2 1.15 2.29 2.1 3.24.95.95 2.04 1.61 3.24 2.1 1.2.49 2.5.82 3.84.97 1.34.15 2.7.23 4.05.23s2.71-.08 4.05-.23c1.34-.15 2.64-.48 3.84-.97 1.2-.49 2.29-1.15 3.24-2.1.95-.95 1.61-2.04 2.1-3.24.49-1.2.82-2.5.97-3.84.15-1.34.23-2.7.23-4.05s-.08-2.71-.23-4.05c-.15-1.34-.48-2.64-.97-3.84-.49-1.2-1.15-2.29-2.1-3.24-.95-.95-2.04-1.61-3.24-2.1zm-7.615 15.816c-4.142 0-7.5-3.358-7.5-7.5s3.358-7.5 7.5-7.5 7.5 3.358 7.5 7.5-3.358 7.5-7.5 7.5zm0-13.5c-3.313 0-6 2.687-6 6s2.687 6 6 6 6-2.687 6-6-2.687-6-6-6zm7.5-1.5c-.828 0-1.5-.672-1.5-1.5s.672-1.5 1.5-1.5 1.5.672 1.5 1.5-.672 1.5-1.5 1.5z"/>
                </svg>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <section class="text-center bg-dark text-white py-3">
    <div class="container">
      <p class="mb-0">© 2024 Tên Báo Điện Tử. Toàn bộ bản quyền thuộc về Tên Báo Điện Tử.</p>
      <p class="mb-0">Cấm sao chép dưới mọi hình thức nếu không có sự chấp thuận bằng văn bản.</p>
    </div>
  </section>
</footer>