package com.pharmacy.management.system.repository.mapper;

import com.pharmacy.management.system.domain.User;
import com.pharmacy.management.system.domain.enums.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("phone"),
                UserRole.valueOf(rs.getString("role")),
                rs.getTimestamp("created_date_time").toLocalDateTime()
        );
    }
}
