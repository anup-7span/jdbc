package com.jdbc.demo.service;

import com.jdbc.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
public class UserServiceImpl implements UserService {

    private static String INSERT_USER_QUERY="INSERT INTO USER (id,firstName,lastname)values(?,?,?)";
    private static String UPDATE_USER_BY_ID_QUERY="UPDATE USER SET first_name=? WHERE id=?";
    private static String GET_USE_BY_ID_QUERY="SELECT * FROM USER WHERE id=?";
    private static String DELETE_USER_BY_ID_QUERY="DELETE FROM USER WHERE ID=?";
    private static String GET_USERS_QUERY="SELECT * FROM USER";
    @Autowired
    public UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public User saveUser(User user) {
        jdbcTemplate.update(INSERT_USER_QUERY,user.getId(),user.getFirstName(),user.getLastName());
        return user;
    }

    @Override
    public User updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY,user.getFirstName(),user.getId());
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
