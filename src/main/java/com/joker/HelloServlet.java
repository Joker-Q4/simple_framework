package com.joker;

import com.joker.entity.bo.HeadLine;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        log.debug("初始化");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("主动调用");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "搭建简易框架";
        req.setAttribute("name", name);
        log.debug(name);
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
        HeadLine headLine = new HeadLine();
        headLine.setCreateTime(new Date());
    }

    @Override
    public void destroy() {
        super.destroy();
        log.debug("销毁方法");
    }
}
