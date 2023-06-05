package Tree;

public class RootUser {
    //Será el drive de cada usuario
    private final NodeDirectory root;

    public RootUser(String user) {
        this.root = new NodeDirectory(user);
    }
    public void insertar(String virtualPath, ANode newNode) throws TreeError {
        NodeDirectory currentDirectory = searchDirectory(virtualPath);
        // Insertar el nuevo nodo en el directorio encontrado
        currentDirectory.add(newNode);
    }
    public NodeDirectory searchDirectory(String virtualPath) throws TreeError {
        String[] directories = virtualPath.split("/");
        NodeDirectory currentDirectory = root;
        // Navegar por los directorios en la ruta virtual
        for (String directory : directories) {
            if (directory.isEmpty() || directory.equals(root.getName()) || directory.equals(".")) {
                continue; // Ignorar si hay directorios vacíos en la ruta
            }
            ANode node = currentDirectory.search(directory);

            if (node instanceof NodeDirectory) {
                currentDirectory = (NodeDirectory) node;
            }
        }

        return currentDirectory;
    }
        

    public void remove(String virtualPath, ANode node) throws TreeError {
        NodeDirectory currentDirectory = searchDirectory(virtualPath);
        // Insertar el nuevo nodo en el directorio encontrado
        currentDirectory.remove(node);
    }
    public String showDirectory(String virualPath) throws TreeError {
        NodeDirectory currD = searchDirectory(virualPath);
        return currD.toString();
    }
    public void save(){};
    public String getUserName(){
        return root.getName();
    }

    public NodeDirectory getRoot() {
        return root;
    }

    public static void init_root(RootUser rootUser){
        NodeDirectory personal = new NodeDirectory("personal");
        NodeDirectory shared = new NodeDirectory("shared");
        rootUser.root.add(personal);
        rootUser.root.add(shared);
    }
}
