package com.jyjays.pojo;

import com.jyjays.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Userselect {
    public static void main(String[] args) throws IOException {
        //1.加载mybatis核心配置文件,获取SqlsessionFactory
        String username="李四";
        String password="123";
        User user1=new User();
        user1.setPassword(password);
        user1.setUsername(username);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取Sqlsession对象,执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.执行sql
        // List<User> users = sqlSession.selectList("UserMapper.selectAll");
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> users=userMapper.selectUser(user);
//        System.out.println(users);
        User user=userMapper.selectUser(user1);
        if(user!=null){
        System.out.println(user.getUsername());}
        //4.释放资源
        sqlSession.close();
    }
}
