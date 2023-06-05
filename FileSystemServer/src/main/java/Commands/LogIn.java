package Commands;

import Server.ClientManager;
import Server.*;

public class LogIn implements ICommand{
    @Override
    public String execute(String[] args) {
        String idtempClient = args[1];
        String idForLogin = args[2];
        Client client;
        client = Server.getInstance().getUnLoggedClients().get(idtempClient);
        Server server = Server.getInstance();
        ClientManager manager = server.getManager();
        String idUser= "";

        if(false){
            //Search in XML if exist
        }else{
            idUser = manager.getNewKey();
            //Crear el directorio y el arbol aqui
            //Ademas de actualizar el XML
        }
        client = server.getUnLoggedClients().get(args[1]); //Obtiene el cliente temporal
        manager.addClient(idUser, client); //Lo agrega al manager
        server.getUnLoggedClients().remove(args[1]); //Remueve la key temporal
        return "id "+idUser+ " true"; //Retorna el usuario que ya existe o uno nuevo
    }
}
