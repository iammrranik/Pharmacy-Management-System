package com.pharmacy.management.system.repository.implementation;

import com.pharmacy.management.system.domain.User;
import com.pharmacy.management.system.repository.IUserRepository;
import com.pharmacy.management.system.repository.mapper.UserMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserMapper userMapper;

    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.userMapper = new UserMapper();
    }

    @Override
    public int save(User user) {
        String sql = """
                INSERT INTO users (name, email, username, password, phone, role)
                VALUES (:name, :email, :username, :password, :phone, :role)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", user.getName());
        params.addValue("email", user.getEmail());
        params.addValue("username", user.getUsername());
        params.addValue("password", user.getPassword());
        params.addValue("phone", user.getPhone());
        params.addValue("role", user.getRole().name());

        System.out.println("[UserRepository] save called for username: " + user.getUsername());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<User> findById(int id) {
        String sql = """
                SELECT *
                FROM users
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        List<User> users = namedParameterJdbcTemplate.query(sql, params, userMapper);
        System.out.println("[UserRepository] findById called for id: " + id);
        return users.stream().findFirst();
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        String sql = """
                SELECT *
                FROM users
                WHERE phone = :phone
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("phone", phone);
        List<User> users = namedParameterJdbcTemplate.query(sql, params, userMapper);
        System.out.println("[UserRepository] findByPhone called for phone: " + phone);
        return users.stream().findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = """
                SELECT *
                FROM users
                WHERE username = :username
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("username", username);
        List<User> users = namedParameterJdbcTemplate.query(sql, params, userMapper);
        System.out.println("[UserRepository] findByUsername called for username: " + username);
        return users.stream().findFirst();
    }

    @Override
    public List<User> findAll() {
        String sql = """
                SELECT *
                FROM users
                ORDER BY id
                """;
        System.out.println("[UserRepository] findAll called");
        return namedParameterJdbcTemplate.query(sql, userMapper);
    }

    @Override
    public List<User> findAllByPage(int pageNo, int pageSize) {
        String sql = """
                SELECT *
                FROM users
                ORDER BY id
                LIMIT :limit
                OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("limit", pageSize);
        params.addValue("offset", (pageNo - 1) * pageSize);
        System.out.println("[UserRepository] findAllByPage called - page: " + pageNo + ", size: " + pageSize);
        return namedParameterJdbcTemplate.query(sql, params, userMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM users
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        System.out.println("[UserRepository] count called");
        return count != null ? count : 0;
    }

    @Override
    public int update(User user) {
        String sql = """
                UPDATE users
                SET name = :name,
                    email = :email,
                    username = :username,
                    password = :password,
                    phone = :phone,
                    role = :role
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());
        params.addValue("name", user.getName());
        params.addValue("email", user.getEmail());
        params.addValue("username", user.getUsername());
        params.addValue("password", user.getPassword());
        params.addValue("phone", user.getPhone());
        params.addValue("role", user.getRole().name());

        System.out.println("[UserRepository] update called for id: " + user.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = """
                DELETE
                FROM users
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        System.out.println("[UserRepository] delete called for id: " + id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteByPhone(String phone) {
        String sql = """
                DELETE
                FROM users
                WHERE phone = :phone
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("phone", phone);
        System.out.println("[UserRepository] deleteByPhone called for phone: " + phone);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteByUsername(String username) {
        String sql = """
                DELETE
                FROM users
                WHERE username = :username
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("username", username);
        System.out.println("[UserRepository] deleteByUsername called for username: " + username);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
