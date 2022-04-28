package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "cartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    List<Food> basket = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException
        , IOException {
        int sum = 0;
        resp.setCharacterEncoding("UTF-8");
        try(PrintWriter out = resp.getWriter()) {
            out.println("장바구니 목록");
            for (int i = 0; i < basket.size(); i++) {
                out.println(basket.get(i).toString());
                sum+=basket.get(i).getAmount()*basket.get(i).getCnt();
            }
            out.println("전체 금액 : "+sum);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String[] basketResult = req.getParameterValues("class");
        if (!isResidualQuantityChecked(basketResult)){
            // 푸드스탠드 잔여량 보다 많은 수량이 주문 되었을때
        }
        removeFoodStandsAmount(basketResult);
        addBasket(basketResult);
        try(PrintWriter out = resp.getWriter()) {
            out.println("장바구니 상품\n");
            for (int i = 0; i < basketResult.length; i++) {
                out.println(basketResult[i]);
            }

            out.println("<html><head>NHN Mart</head><body><br><a href = " +
                "\"http://localhost:8080/cart\">" +
                "Go To Foods</a></body></html>");
        }
    }

    public boolean isResidualQuantityChecked(String[] basketResult){
        for (int i = 0; i < basketResult.length; i++) {
            int foodStandsQuantity = MartUtil.getFoodStands().get(i).getCnt();
            int basketQuantity = Integer.parseInt(basketResult[i]);
            if(basketQuantity>foodStandsQuantity){
                return false;
            }
        }
        return true;
    }
    public void removeFoodStandsAmount(String[] basketResult){
        for (int i = 0; i < basketResult.length; i++) {
            MartUtil.getFoodStands().get(i).setCnt(MartUtil.getFoodStands().get(i).getCnt()-Integer.parseInt(basketResult[i]));
        }
    }

    public void addBasket(String[] basketResult){
        String names[] = {"Onion", "Egg", "WelshOnion", "Apple"};
        int prices[] = {1000,2000,500,2000};
        for (int i = 0; i < basketResult.length; i++) {
            basket.add(new Food(names[i],Integer.parseInt(basketResult[i]),prices[i]));
        }
    }
}
