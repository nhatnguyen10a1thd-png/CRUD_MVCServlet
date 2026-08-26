package nguyen.vn.bt1_2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import nguyen.vn.bt1_2.util.Constant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(
        urlPatterns = "/image"
)
public class DownloadImageController
        extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        String fileName =
                req.getParameter("fname");

        if (fileName == null ||
                fileName.isBlank()) {

            resp.sendError(
                    HttpServletResponse.SC_NOT_FOUND
            );

            return;
        }

        Path root =
                Paths.get(Constant.DIR)
                        .toAbsolutePath()
                        .normalize();

        Path file =
                root.resolve(fileName)
                        .normalize();

        /*
         * Không cho ../../ truy cập ra ngoài
         * thư mục upload.
         */
        if (!file.startsWith(root) ||
                !Files.exists(file)) {

            resp.sendError(
                    HttpServletResponse.SC_NOT_FOUND
            );

            return;
        }

        String contentType =
                Files.probeContentType(file);

        if (contentType == null) {
            contentType =
                    "application/octet-stream";
        }

        resp.setContentType(contentType);

        Files.copy(
                file,
                resp.getOutputStream()
        );
    }
}