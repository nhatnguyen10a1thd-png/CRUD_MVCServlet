<c:url value="/admin/category/list"
       var="categoryListUrl"/>

<c:url value="/admin/category/add"
       var="categoryAddUrl"/>

<aside class="sidebar">

    <div class="sidebar-title">
        Dashboard
    </div>

    <div class="profile">

        <div class="avatar">
            AD
        </div>

        <div class="profile-name">
            Bạn là Admin
        </div>

    </div>

    <nav class="sidebar-menu">

        <a href="${categoryListUrl}"
           class="menu-item dashboard-item">
            <span>◉</span>
            Dashboard
        </a>

        <div class="menu-item category-title active">
            <span>▱</span>
            Quản lý Danh mục
        </div>

        <a href="${categoryAddUrl}"
           class="submenu-item">
            + Thêm danh mục mới
        </a>

        <a href="${categoryListUrl}"
           class="submenu-item">
            Danh sách danh mục
        </a>

        <div class="menu-item">
            <span>▣</span>
            Quản lý sản phẩm
        </div>

        <div class="menu-item">
            <span>▦</span>
            Quản lý tài khoản
        </div>

    </nav>

</aside>