package nguyen.vn.bt1_2.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import nguyen.vn.bt1_2.service.CategoryService;
import nguyen.vn.bt1_2.service.impl.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(
        urlPatterns = "/admin/category/delete"
)
public class CategoryDeleteController
        extends HttpServlet {

    private final CategoryService cateService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        String id =
                req.getParameter("id");

        cateService.delete(
                Integer.parseInt(id)
        );

        resp.sendRedirect(
                req.getContextPath()
                        + "/admin/category/list"
        );
    }
}