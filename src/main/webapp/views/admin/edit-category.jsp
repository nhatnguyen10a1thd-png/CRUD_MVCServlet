<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html lang="vi">

<head>

  <meta charset="UTF-8">

  <title>Chỉnh sửa danh mục</title>

  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/admin.css">

  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

</head>

<body>

<%@ include file="common/sidebar.jspf" %>

<div class="main-wrapper">

  <header class="topbar">

        <span>
            Xin chào Admin
        </span>

    <a href="#"
       class="logout">
      Đăng xuất
    </a>

  </header>

  <main class="content">

    <div class="card form-card">

      <div class="card-header">
        Chỉnh sửa danh mục
      </div>

      <div class="card-body">

        <h2 class="form-title">
          Danh mục:
        </h2>

        <c:url value="/admin/category/edit"
               var="editUrl"/>

        <form action="${editUrl}"
              method="post"
              enctype="multipart/form-data">

          <input type="hidden"
                 name="id"
                 value="${category.id}">

          <div class="form-group">

            <label>
              Tên danh mục:
            </label>

            <input type="text"
                   class="form-control"
                   name="name"
                   value="${category.name}"
                   required>

          </div>

          <div class="form-group">

            <label>
              Ảnh hiện tại
            </label>

            <c:choose>

              <c:when test="${not empty category.icon}">

                <c:url value="/image"
                       var="imgUrl">

                  <c:param name="fname"
                           value="${category.icon}"/>

                </c:url>

                <img src="${imgUrl}"
                     class="preview-image"
                     alt="${category.name}">

              </c:when>

              <c:otherwise>

                <div class="no-image">
                  No image
                </div>

              </c:otherwise>

            </c:choose>

          </div>

          <div class="form-group">

            <label>
              Ảnh đại diện mới
            </label>

            <input type="file"
                   name="icon"
                   accept="image/png,image/jpeg,image/webp">

          </div>

          <button type="submit"
                  class="btn btn-save">

            Edit

          </button>

          <button type="reset"
                  class="btn btn-reset">

            Reset

          </button>

          <c:url value="/admin/category/list"
                 var="listUrl"/>

          <a href="${listUrl}"
             class="btn btn-back">

            Quay lại

          </a>

        </form>

      </div>

    </div>

  </main>

</div>

</body>

</html>