package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Contact;
import model.GroupEnumeration;

/**
 *
 * @author 57322
 */
public class ContactRepositoryImpl implements IContactRepository {

    private Connection con;

    public ContactRepositoryImpl() {
        this.con = ConnectionMySQL.connect();
    }

    @Override
    public Boolean saveContact(Contact request) {
        String sqlConsulta = "INSERT INTO CONTACT(name, number, groupContact) VALUES(?,?,?)";
        PreparedStatement query;
        try {
            query = con.prepareStatement(sqlConsulta);
            query.setString(1, request.getName());
            query.setLong(2, request.getNumber());
            query.setString(3, request.getGroup().toString());
            query.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Contact> findAllContact() {
        ArrayList<Contact> contacts = new ArrayList<>();
        String sqlConsulta = "SELECT * FROM CONTACT";
        PreparedStatement query;
        try {
            query = con.prepareStatement(sqlConsulta);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                Contact saved = new Contact();
                saved.setIdContact(result.getInt(1));
                saved.setName(result.getString(2));
                saved.setNumber(result.getLong(3));
                GroupEnumeration group = GroupEnumeration.valueOf(result.getString(4));
                saved.setGroup(group);
                contacts.add(saved);
            }
            return contacts;
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Contact findByName(String name) {
        return null;
    }

    @Override
    public Boolean updateContact(Integer id, Contact updateContact) {
        String sqlConsulta = "UPDATE CONTACT SET name = ?, number = ?, groupContact = ? WHERE id = ?";
        PreparedStatement query;
        try {
            query = con.prepareStatement(sqlConsulta);
            query.setString(1, updateContact.getName());
            query.setLong(2, updateContact.getNumber());
            query.setString(3, updateContact.getGroup().toString());
            query.setInt(4, id);
            query.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteContact(Integer id) {
        String sqlConsulta = "DELETE FROM CONTACT WHERE ID = ?";
        PreparedStatement query;
        try {
            query = con.prepareStatement(sqlConsulta);
            query.setInt(1, id);
            query.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex.getMessage());
            return false;
        }
    }



}
