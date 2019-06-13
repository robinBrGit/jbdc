package org.br.jdbc.dao.dal;
import static java.sql.Types.INTEGER;
import org.br.jdbc.dao.domain.Address;
import org.br.jdbc.dao.domain.Contact;

import java.sql.*;

public class ContactDAO {
    private static final String INSERT_QUERY = "INSERT INTO contact (email,first_name,last_name,id_address) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE contact SET (email=?,first_name=?,last_name=?,id_address=?) WHERE id_contact=?";
    private static final String DELETE_QUERY = "DELETE from contact WHERE id_contact=?";
    private static final String SELECT_QUERY = "SELECT * from contact WHERE id_contact=?";
    
    
    public void create(Contact c) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,c.getEmail());
            ps.setString(2,c.getFirstName());
            ps.setString(3,c.getLastName());
            Address address = c.getAddress();
            if (address != null){
                if(address.getIdAddress() == 0){
                   AddressDAO addressDAO = new AddressDAO();
                   addressDAO.create(address);
                }
                ps.setInt(4,address.getIdAddress());
            }
            else{
                ps.setNull(4,INTEGER);
            }
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                while (rs.next()){
                    c.setIdContact(rs.getInt(1));
                }
            }
        }
    }
    
    public void update(Contact c) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)){
            ps.setString(1,c.getEmail());
            ps.setString(2,c.getFirstName());
            ps.setString(3,c.getLastName());
            Address address = c.getAddress();
            if (address != null){
                if(address.getIdAddress() == 0){
                    AddressDAO addressDAO = new AddressDAO();
                    addressDAO.create(address);
                }
                ps.setInt(4,address.getIdAddress());
            }
            ps.setInt(5,c.getIdContact());
            ps.executeUpdate();
        }
    }
    
    public void delete(Contact c) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)){
            ps.setInt(1,c.getIdContact());
            ps.executeUpdate();
        }
    }
    
    public Contact findById(int id) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        Contact contact = new Contact();
        AddressDAO addressDAO = new AddressDAO();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_QUERY)){
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    contact.setIdContact(rs.getInt("id_contact"));
                    contact.setEmail(rs.getString("email"));
                    contact.setFirstName(rs.getString("first_name"));
                    contact.setLastName(rs.getString("last_name"));
                    contact.setAddress(addressDAO.findById(rs.getInt("id_address")));
                }
            }
            catch (SQLException e){
                System.out.println(e.getSQLState());
            }
        }
        return contact;
    }
    
}
