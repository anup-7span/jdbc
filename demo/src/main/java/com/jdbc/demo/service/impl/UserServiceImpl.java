package com.jdbc.demo.service.impl;

import com.jdbc.demo.entity.User;
import com.jdbc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private static String INSERT_USER_QUERY="INSERT INTO emp (id,first_name,last_name)values(?,?,?)";
    private static String UPDATE_USER_BY_ID_QUERY="UPDATE emp SET first_name=? WHERE id=?";
    private static String GET_USE_BY_ID_QUERY="SELECT * FROM emp WHERE id=?";
    private static String DELETE_USER_BY_ID_QUERY="DELETE FROM emp WHERE ID=?";
    private static String GET_USERS_QUERY="SELECT * FROM emp";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public User saveUser(User user) {
        jdbcTemplate.update(INSERT_USER_QUERY,user.getId(),user.getFirst_name(),user.getLast_name());
        return user;
    }

    @Override
    public User updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY,user.getFirst_name(),user.getId());
        return user;
    }

    @Override
    public User getUser(Integer id) {
        return jdbcTemplate.queryForObject(GET_USE_BY_ID_QUERY,(rs, rowNum) -> {
          return new User(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"));
        });
    }

    @Override
    public String deleteById(Integer id) {
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY,id);
        return "User deleted";
    }

 /*   @Override
    public String deleteById(Integer id) {
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY,id);
        return "User deleted";
    }*/

    @Override
    public List<User> allUser() {
        return jdbcTemplate.query(GET_USERS_QUERY, (rs, rowNum) -> {
            return new User(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"));
        });
    }
}
