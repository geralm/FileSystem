package Tree;

public class NodesFactory {
    public static ANode createNode(ENodes nodeType,String name){

        switch (nodeType){
            case FILE -> {
                return new NodeFile(name);
            }
            case DIRECTORY -> {
                return  new NodeDirectory(name);
            }
            default -> {
                return null;
            }
        }

    }
}
