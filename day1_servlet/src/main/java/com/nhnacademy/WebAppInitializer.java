package com.nhnacademy;

import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext)
        throws ServletException {
        servletContext.setInitParameter("Onion", "2");
        servletContext.setInitParameter("Egg", "5");
        servletContext.setInitParameter("Egg", "10");
        servletContext.setInitParameter("Egg", "20");
    }
}
