package Server;

import java.util.HashMap;
import java.util.Random;

public class ClientManager {
    private HashMap<String, Client> mapManager = new HashMap<>();

    public void addClient(String key,Client client){
        mapManager.put(key,client);
    }
    public void removeClient(String key){
        mapManager.remove(key);
    }
    public Client getClient(String key){
        return  mapManager.get(key);
    }



    public String getNewKey(){
        String s = String.valueOf(getRand());
        while (mapManager.containsKey(s)){
            s = String.valueOf(getRand());
        }
        return s;
    }
    public int getRand(){
        Random random = new Random();
        int randomInt  = random.nextInt(9999)+1000;
        return randomInt;
    }
}
