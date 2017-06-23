package ro.ubb.l5.server.repository;



import ro.ubb.l5.common.*;

import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.validators.Validator;
import ro.ubb.l5.common.domain.validators.ValidatorException;
import ro.ubb.l5.server.util.SqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrapop on 2017-03-19.
 */
public class ClientDBRepository implements IRepository<Integer, Client> {
    private Validator<Client> validator;
    private SqlUtil sqlUtil;

    public ClientDBRepository(Validator<Client> clientValidator){
        this.validator = clientValidator;
        sqlUtil = new SqlUtil();
    }



    @Override
    public Optional<Client> findOne(Integer integer) {
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;
        Client client = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM clients;");
            while (rs.next()) {
                int id = rs.getInt("id");
                if (id == integer) {

                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    String phoneNr = rs.getString("phonenr");
                    String address = rs.getString("address");
                    client = new Client(id, firstName, lastName, phoneNr, address);
                }
            }
            stmt.close();
            c.close();
        }
            catch(SQLException e){
                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                System.exit(0);
            }



            return Optional.ofNullable(client);
    }

    @Override
    public Iterable<Client> findAll() {
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;
        List<Client> clients = new ArrayList<>();

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM clients;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phoneNr = rs.getString("phonenr");
                String address = rs.getString("address");
                Client client = new Client(id,firstName,lastName,phoneNr,address);
                clients.add(client);
            }
            stmt.close();
            c.close();
        }
        catch(SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }



        return clients;


    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null");
        validator.validate(entity);
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;
        //c.prepareCall("select name from Movies");

        try {
            stmt = c.createStatement();

            String sql = "INSERT INTO clients (Id,firstName,lastName,phoneNr,address) "
                    + "VALUES ('"+entity.getId()+"','"+entity.getFirstName()+"', '"+entity.getLastName()+"','"+entity.getPhoneNr()+"', '"+entity.getAddress()+"');";

            stmt.executeUpdate(sql);
            c.commit();
            stmt.execute("select * from clients;");
            c.close();
        }
        catch(SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Integer integer) {
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            String sql = "DELETE from clients where id='"+integer+"'";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        }
        catch(SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);

        }



        return Optional.empty();

        }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            if(!findOne(entity.getId()).isPresent())
                throw new ValidatorException("The client does not exist!");
            else {
                String sql = "UPDATE clients set firstname = '" + entity.getFirstName() + "', lastname = '" + entity.getLastName() +
                        "', phonenr ='" + entity.getPhoneNr() + "', address = '" + entity.getAddress() + "' " +
                        "where ID='" + entity.getId() + "';";
                stmt.executeUpdate(sql);
                c.commit();
            }
            stmt.close();
            c.close();
        }
        catch(SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }



        return Optional.empty();

    }


}
