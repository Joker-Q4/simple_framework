package com.joker.controller;

import com.joker.controller.frontend.MainPageController;
import com.joker.controller.superadmin.HeadLineOperationController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("req path:" + req.getServletPath());
        log.debug("req method:" + req.getMethod());
        if(req.getServletPath().equals("/frontend/getMainPageInfo") && req.getMethod().equals("GET")){
            new MainPageController().getMainPageInfo(req, resp);
        }else if(req.getServletPath().equals("/superadmin/addHeadLine") && req.getMethod().equals("POST")){
            new HeadLineOperationController().addHeadLine(req, resp);
        }
    }
}
