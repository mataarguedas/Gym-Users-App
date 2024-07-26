package fit_zone.data;
import fit_zone.domain.Client;
import java.util.List;

public interface IClientDAO {
    List<Client> listClients();
    boolean searchClientById(Client client);
    boolean addClient(Client client);
    boolean modifyClient(Client client);
    boolean eliminateClient(Client client);
}
