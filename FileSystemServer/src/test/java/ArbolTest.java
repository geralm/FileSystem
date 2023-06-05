import Tree.*;

public class ArbolTest {

    public static void main(String[] args) throws TreeError {
        RootUser rootUser = new RootUser("prueba1");
        RootUser.init_root(rootUser);
        NodeDirectory directorio1 = (NodeDirectory) NodesFactory.createNode(ENodes.DIRECTORY, "directorio1");
        /*Prueba de inserción en una ruta no existente;
        * */


        //Prueba de inserción correcta
        rootUser.insertar("prueba1",directorio1 );
        rootUser.insertar("prueba1/directorio1", (NodeDirectory) NodesFactory.createNode(ENodes.DIRECTORY, "directorio2"));
        rootUser.insertar("prueba1/directorio1/directorio2", NodesFactory.createNode(ENodes.FILE, "archivo1.txt"));
        rootUser.insertar("prueba1/directorio1/directorio2", NodesFactory.createNode(ENodes.FILE, "aCachete.txt"));
        rootUser.insertar("prueba1/directorio1/", NodesFactory.createNode(ENodes.FILE, "soyUn.txt"));

        System.out.println(rootUser.showDirectory("prueba1/directorio1/"));
        System.out.println(rootUser.showDirectory("prueba1"));

        //Prueba de ls
    }
}
