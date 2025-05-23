package com.baeldung.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FormServlet", urlPatterns = "/calculateServlet")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        try {
            double bmi = calculateBMI(Double.parseDouble(weight), Double.parseDouble(height));
            request.setAttribute("bmi", bmi);
            response.setHeader("Test", "Success");
            response.setHeader("BMI", String.valueOf(bmi));
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        dispatcher.forward(request, response);
    }

    private Double calculateBMI(Double weight, Double height) {
        return weight / (height * height);
    }
}