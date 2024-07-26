package fit_zone.data;

import fit_zone.conexion.Conexion;
import fit_zone.domain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import static fit_zone.conexion.Conexion.getConexion;

public class ClientDAO implements IClientDAO {
    @Override
    public List<Client> listClients() {
        List<Client> clients = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM client ORDER BY id";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setLastName(rs.getString("lastName"));
                client.setMembership(rs.getInt("membership"));
                clients.add(client);
            }
        }
        catch(Exception e) {
            System.out.println("Error listing clients:" + e.getMessage());

        }
        finally{
            try{
                con.close();
            }
            catch(Exception e){
                System.out.println("Error while listing clients = " + e.getMessage());
            }

        }
        return clients;
    }

    @Override
    public boolean searchClientById(Client client) {
        PreparedStatement ps;
        ResultSet rs;
        var con = getConexion();
        var sql = "SELECT * FROM client WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, client.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                client.setName(rs.getString("name"));
                client.setLastName(rs.getString("lastName"));
                client.setMembership(rs.getInt("membership"));
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Error searching client by id = " + e.getMessage());
        }
        finally{
            try{
                con.close();
            }
            catch(Exception e){
                System.out.println("Error closing conection = " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean addClient(Client client) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO client (name, lastName, membership)" + "VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getMembership());
            ps.execute();
            return true;
        }
        catch (Exception e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
        finally{
            try{
                con.close();
            }
            catch(Exception e){
                System.out.println("Error closing conection = " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modifyClient(Client client) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE client SET name=?, lastName=?, membership=? WHERE id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getMembership());
            ps.setInt(4, client.getId());
            ps.execute();
            return true;
        }
        catch(Exception e){
            System.out.println("Error updating client by id = " + e.getMessage());

        }
        finally{
            try{
                con.close();
            }
            catch(Exception e){
                System.out.println("error closing conection = " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminateClient(Client client) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM client WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, client.getId());
            ps.execute();
            return true;
        }
        catch(Exception e){
            System.out.println("Error eliminating client" + e.getMessage());
        }
        finally{
            try{
                con.close();
            }
            catch(Exception e){
                System.out.println("Error while closing connection " + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IClientDAO clientDao = new ClientDAO();

//       List clients
//        System.out.println("List clients");
//        var clients = clientDao.listClients();
//        clients.forEach(System.out::println);

        //Search by id
//        var client1 = new Client(2);
//        System.out.println("Client before the search" + client1);
//        var found = clientDao.searchClientById(client1);
//        if(found){
//            System.out.println("Client found" + client1);
//        }
//        else{
//            System.out.println("Client not found" + client1.getId());
//        }

        //add client
//        var newClient = new Client("Daniel","Ortiz",300);
//        var added = clientDao.addClient(newClient);
//        if(added){
//            System.out.println("Client successfully added" + newClient);
//        }
//        else{
//            System.out.println("Client not added" + newClient);
//        }
//
//                System.out.println("List clients");
//        var clients = clientDao.listClients();
//        clients.forEach(System.out::println);

            //modify client
//        var modifyClient = new Client(3,"Jose Daniel","Ortiz", 300);
//        var modified = clientDao.modifyClient(modifyClient);
//        if(modified){
//            System.out.println("Client modified= " + modifyClient);
//        }
//        else{
//            System.out.println("Client not modified= " + modifyClient);
//        }
//        System.out.println("List clients");
//        var clients = clientDao.listClients();
//        clients.forEach(System.out::println);

            //Delete client
        var eliminateClient = new Client(3);
        var eliminated = clientDao.eliminateClient(eliminateClient);
        if(eliminated){
            System.out.println("Client eliminated = " + eliminateClient);
        }
        else{
            System.out.println("Client not eliminated = " + eliminateClient);
        }

        System.out.println("List clients");
        var clients = clientDao.listClients();
        clients.forEach(System.out::println);
    }
}
