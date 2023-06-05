import FileXML.XML;
import Tree.*;

public class XMLTest {
    public static void main(String[] args) throws TreeError {
        RootUser rootUser = new RootUser("prueba1");

        NodeDirectory directorio1 = (NodeDirectory) NodesFactory.createNode(ENodes.DIRECTORY, "directorio1");
        rootUser.insertar("prueba1/personal", directorio1);

        NodeDirectory directorio2 = (NodeDirectory) NodesFactory.createNode(ENodes.DIRECTORY, "directorio2");
        rootUser.insertar("prueba1/personal/directorio1", directorio2);

        NodeFile archivo1 = (NodeFile) NodesFactory.createNode(ENodes.FILE, "archivo1.txt");
        rootUser.insertar("prueba1/personal/directorio1/directorio2", archivo1);

        NodeFile archivo2 = (NodeFile) NodesFactory.createNode(ENodes.FILE, "archivo2.txt");
        rootUser.insertar("prueba1/personal/directorio1/directorio2", archivo2);

        NodeDirectory directorio3 = (NodeDirectory) NodesFactory.createNode(ENodes.DIRECTORY, "directorio3");
        rootUser.insertar("prueba1/personal/directorio1", directorio3);

        NodeFile archivo3 = (NodeFile) NodesFactory.createNode(ENodes.FILE, "archivo3.txt");
        rootUser.insertar("prueba1/personal/directorio1/directorio3", archivo3);

        NodeFile archivo4 = (NodeFile) NodesFactory.createNode(ENodes.FILE, "archivo4.txt");
        rootUser.insertar("prueba1/personal/directorio1", archivo4);

        System.out.println(rootUser.showDirectory("prueba1/personal/directorio1"));
        XML.save(rootUser);

    }
}

