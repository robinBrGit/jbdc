package org.br.jdbc.dao.dal;

import com.mysql.cj.xdevapi.AddResult;
import org.br.jdbc.dao.domain.Address;
import org.br.jdbc.dao.domain.Contact;

import java.sql.*;

public class AddressDAO {
    private static final String INSERT_QUERY = "INSERT INTO address (rue,code_postale,ville) VALUES (?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE address SET (rue=?,code_postale=?,ville=?) WHERE id_address=?";
    private static final String DELETE_QUERY = "DELETE from address WHERE id_address=?";
    private static final String SELECT_QUERY = "SELECT * from address WHERE id_address=?";


    public void create(Address a) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,a.getRue());
            ps.setString(2,a.getCodePostale());
            ps.setString(3,a.getVille());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                while (rs.next()){
                    a.setIdAddress(rs.getInt(1));
                }
            }

        }
    }

    public void update(Address a) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)){
            ps.setString(1,a.getRue());
            ps.setString(2,a.getCodePostale());
            ps.setString(3,a.getVille());
            ps.setInt(4,a.getIdAddress());
            ps.executeUpdate();
        }
    }

    public void delete(Address a) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)){
            ps.setInt(1,a.getIdAddress());
            ps.executeUpdate();
        }
    }

    public Address findById(int id) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        Address address = new Address();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_QUERY)){
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    address.setIdAddress(rs.getInt("id_address"));
                    address.setRue(rs.getString("rue"));
                    address.setCodePostale(rs.getString("code_postale"));
                    address.setVille(rs.getString("ville"));
                }
            }
            catch (SQLException e){
                System.out.println(e.getSQLState());
            }
        }
        return address;
    }
}
