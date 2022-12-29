package com.jyjays.logdemo;

import com.jyjays.mapper.UserMapper;
import com.jyjays.pojo.User;
import com.jyjays.util.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/log1")
public class logServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //1.接受
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user1 = new User();
        user1.setPassword(password);
        user1.setUsername(username);
        //2.调用mybatis
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSessionFactory sqlSessionFactory= SqlSessionFactoryUtils.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUser(user1);
        sqlSession.close();
//        String a1=user.getUsername();
//        System.out.println(a1);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (user != null) {
            //成功
            writer.write("登陆成功");
        }else{//失败
            writer.write("登陆失败");
        }
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
