package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@WebServlet(name = "foodsServlet", urlPatterns = "/food", initParams = {
    @WebInitParam(name = "Onion", value = "2"),
    @WebInitParam(name = "Egg", value = "5"),
    @WebInitParam(name = "WelshOnion", value = "10"),
    @WebInitParam(name = "Apple", value = "20")
})
@Slf4j
public class FoodsServlet extends HttpServlet {
    ServletContext servletContext;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try(PrintWriter out = resp.getWriter()) {
            out.println("<html><head>FoodStands List</head>");
            out.println("<body><p>"+MartUtil.getFoodStands().get(0)+"</p>");
            out.println("<p>"+MartUtil.getFoodStands().get(1)+"</p>");
            out.println("<p>"+MartUtil.getFoodStands().get(2)+"</p>");
            out.println("<p>"+MartUtil.getFoodStands().get(3)+"</p>");
            out.println("<form method=\"post\" action=\"/cart\">\n" +
                "    <label><input type=\"text\" name=\"class\" id = \"Onion\" size=\"10\">양파</label><br>\n" +
                "    <label><input type=\"text\" name=\"class\" id = \"Egg\" size=\"10\">계란</label><br>\n" +
                "    <label><input type=\"text\" name=\"class\" id = \"WelshOnion\" size=\"10\">파</label><br>\n" +
                "    <label><input type=\"text\" name=\"class\" id = \"Apple\" size=\"10\">사과</label><br>\n" +
                "    <input type=\"submit\" />\n" +
                "</form>");
            out.println("</body></html>");
        }
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
        servletContext = config.getServletContext();
        List<Food> foodStands = new ArrayList<>();
        MartUtil.setFood(foodStands);
        String [] names = {"Onion","Egg","WelshOnion","Apple"};
        int [] prices = {1000,2000,500,2000};
        for (int i = 0; i < names.length ; i++) {
            MartUtil.getFoodStands().add(
                new Food(names[i], Integer.parseInt(config.getInitParameter(names[i])),
                    prices[i]));
        }

    }
}
