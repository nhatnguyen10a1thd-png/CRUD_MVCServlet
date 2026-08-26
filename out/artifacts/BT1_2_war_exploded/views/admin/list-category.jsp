<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Quản lý danh mục</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/admin.css">

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

        <div class="card">

            <div class="card-body">

                <h1 class="page-title">
                    Quản lý danh mục
                </h1>

                <div class="page-description">
                    Nơi bạn có thể quản lý danh mục của mình
                </div>

                <div class="table-toolbar">

                    <c:url value="/admin/category/add"
                           var="addUrl"/>

                    <a href="${addUrl}"
                       class="add-button">
                        + Thêm danh mục mới
                    </a>

                    <div class="search-box">

                        Search:

                        <input type="text"
                               id="categorySearch"
                               placeholder="Tìm danh mục..."
                               oninput="filterCategories()">

                    </div>

                </div>

                <table class="category-table"
                       id="categoryTable">

                    <thead>

                    <tr>
                        <th style="width:70px">
                            STT
                        </th>

                        <th style="width:160px">
                            Hình ảnh
                        </th>

                        <th>
                            Tên danh mục
                        </th>

                        <th style="width:160px">
                            Hành động
                        </th>
                    </tr>

                    </thead>

                    <tbody>

                    <c:forEach items="${cateList}"
                               var="cate"
                               varStatus="STT">

                        <tr class="category-row">

                            <td>
                                    ${STT.index + 1}
                            </td>

                            <td>

                                <c:choose>

                                    <c:when test="${not empty cate.icon}">

                                        <c:url value="/image"
                                               var="imgUrl">

                                            <c:param name="fname"
                                                     value="${cate.icon}"/>

                                        </c:url>

                                        <img src="${imgUrl}"
                                             class="category-image"
                                             alt="${cate.name}">

                                    </c:when>

                                    <c:otherwise>

                                        <div class="no-image">
                                            No image
                                        </div>

                                    </c:otherwise>

                                </c:choose>

                            </td>

                            <td class="category-name">
                                <c:out value="${cate.name}"/>
                            </td>

                            <td>

                                <c:url value="/admin/category/edit"
                                       var="editUrl">

                                    <c:param name="id"
                                             value="${cate.id}"/>

                                </c:url>

                                <c:url value="/admin/category/delete"
                                       var="deleteUrl">

                                    <c:param name="id"
                                             value="${cate.id}"/>

                                </c:url>

                                <a href="${editUrl}"
                                   class="action-edit">
                                    Sửa
                                </a>

                                |

                                <a href="${deleteUrl}"
                                   class="action-delete"
                                   onclick="return confirm('Bạn chắc chắn muốn xóa danh mục này?')">
                                    Xóa
                                </a>

                            </td>

                        </tr>

                    </c:forEach>

                    <c:if test="${empty cateList}">

                        <tr>

                            <td colspan="4"
                                style="text-align:center">

                                Chưa có danh mục nào.

                            </td>

                        </tr>

                    </c:if>

                    </tbody>

                </table>

            </div>

        </div>

    </main>

</div>

<script>

    function filterCategories() {

        const keyword =
            document.getElementById("categorySearch")
                .value
                .toLowerCase();

        const rows =
            document.querySelectorAll(".category-row");

        rows.forEach(row => {

            const name =
                row.querySelector(".category-name")
                    .textContent
                    .toLowerCase();

            row.style.display =
                name.includes(keyword)
                    ? ""
                    : "none";
        });
    }

</script>

</body>

</html>