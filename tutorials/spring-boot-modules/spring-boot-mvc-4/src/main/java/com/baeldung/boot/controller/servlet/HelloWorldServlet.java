package com.baeldung.boot.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HelloWorldServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println("HelloWorldServlet: GET METHOD");
            out.flush();
        } finally {
            if (!Objects.isNull(out))
                out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println("HelloWorldServlet: POST METHOD");
            out.flush();
        } finally {
            if (!Objects.isNull(out))
                out.close();
        }
    }

}
