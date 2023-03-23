package co.kr.minzero.dev.config;

import jakarta.servlet.*;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MainWebAppInitializer implements WebApplicationInitializer {
    private String TMP_FOLDER = "/tmp";
    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        ServletRegistration.Dynamic appServlet = context.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));

        appServlet.setLoadOnStartup(1);

        MultipartConfigElement element = new MultipartConfigElement(TMP_FOLDER, MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2L, MAX_UPLOAD_SIZE / 2);

        appServlet.setMultipartConfig(element);
    }
}
