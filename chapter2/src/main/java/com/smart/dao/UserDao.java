package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private final static String MATCH_COUNT_SQL = "select count(*) from t_user  where user_name=? and password =? ";

    private final static String UPDATE_LOGIN_INFO_SQL = "update t_user set last_visit=?, last_ip=?,credits = ? where user_id = ?";

    private final static String QUERY_BY_USERNAME = "select user_id, user_name, credits from t_user where user_name=?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password) {

        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }


    public int updateLoginInfo(User user) {
        return jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
    }

    public User queryByUsername(String userName) {
        final User user = new User();
        jdbcTemplate.query(QUERY_BY_USERNAME, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

}
