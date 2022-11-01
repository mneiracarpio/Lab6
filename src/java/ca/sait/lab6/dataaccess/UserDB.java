package ca.sait.lab6.dataaccess;

import ca.sait.lab6.models.Role;
import ca.sait.lab6.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UserDB {

    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT email, active, first_name, last_name, password, role_id, role_name " + 
                     "FROM user a, role b WHERE a.role = b.role_id";
        
        try {
            
            // with prepare statement
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // with create statement
            //rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                String email = rs.getString("email");
                boolean active = rs.getBoolean("active");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String password = rs.getString("password");
                int roleId = rs.getInt("role_id");
                String roleName = rs.getString("role_name");
                
                Role role = new Role(roleId, roleName);
                User user = new User(email, active, firstName, lastName, password, role);
                
                users.add(user);
                
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return users;
    }

    public User get(String email) throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT active, first_name, last_name, password, role_id, role_name FROM user a, role b WHERE a.role = b.role_id AND email=? LIMIT 1";
        
        try {
            boolean active = rs.getBoolean("active");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String password = rs.getString("password");
            int roleId = rs.getInt("role_id");
            String roleName = rs.getString("role_name");

            Role role = new Role(roleId, roleName);
            user = new User(email, active, firstName, lastName, password, role);             
                
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return user;
    }

    public boolean insert(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO user (email, first_name, last_name, password, role ) " + 
                     "VALUES (?, ?, ?, ?, ?)";
        boolean inserted;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole().getId());
            inserted = (ps.executeUpdate() != 0);
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return inserted;
    }

    public boolean update(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user SET first_name=?, last_name=?, password=?, role=? WHERE email=?";
        boolean updated;
        try {
            ps = con.prepareStatement(sql);
            //ps.setString(1, user.getEmail());
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRole().getId());
            ps.setString(5, user.getEmail());
            updated = (ps.executeUpdate() != 0);
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return updated;
    }

    public boolean delete(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        //String sql = "DELETE FROM user WHERE email=?";
        String sql = "UPDATE user SET active = 0 WHERE email=?";
        boolean deleted = false;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            deleted = ps.executeUpdate() != 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return deleted;
    }

}
