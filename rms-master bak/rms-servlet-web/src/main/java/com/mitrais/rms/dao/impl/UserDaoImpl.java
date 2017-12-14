package com.mitrais.rms.dao.impl;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class UserDaoImpl implements UserDao
{
    @Override
    public Optional<User> find(Long id)
    {
        try (Connection connection = DataSourceFactory.getConnection())
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                User user = userMappingFromResultSet(rs);
                return Optional.of(user);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll()
    {
        List<User> result = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getConnection())
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next())
            {
                User user = userMappingFromResultSet(rs);
                result.add(user);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
    
	@Override
    public boolean save(User user)
    {
        try (Connection connection = DataSourceFactory.getConnection())
        {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO user (user_name, password, user_type) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserType());
            int i = stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next())
            {
            	long id = rs.getLong(1);
            	user.setId(id);
            }
            if(i == 1) return true;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User user)
    {
        try (Connection connection = DataSourceFactory.getConnection())
        {
            PreparedStatement stmt = connection.prepareStatement("UPDATE user SET user_name=?, password=?, user_type=? WHERE id=?");
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserType());
            stmt.setLong(4, user.getId());
            int i = stmt.executeUpdate();
            if(i == 1) {
                return true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(long id)
    {
        try (Connection connection = DataSourceFactory.getConnection())
        {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM user WHERE id=?");
            stmt.setLong(1, id);
            int i = stmt.executeUpdate();
            if(i == 1) {
                return true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<User> findByUserName(String userName)
    {
        try (Connection connection = DataSourceFactory.getConnection())
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE user_name=?");
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                User user = userMappingFromResultSet(rs);
                return Optional.of(user);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return Optional.empty();
    }
    
    @Override
    public Optional<User> findByUserNameAndPassword(String userName, String password)
    {
        try (Connection connection = DataSourceFactory.getConnection())
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE user_name=? AND password=?");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                User user = userMappingFromResultSet(rs);
                return Optional.of(user);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    public static class SingletonHelper
    {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    public static UserDao getInstance()
    {
        return SingletonHelper.INSTANCE;
    }
    
    private static User userMappingFromResultSet(ResultSet rs) throws SQLException {
    	return new User(rs.getLong("id"), 
        		rs.getString("user_name"), 
        		rs.getString("password"),
        		rs.getString("user_type"));
    }
}
