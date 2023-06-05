package Tree;

import java.util.LinkedList;

public class NodeDirectory extends ANode{
     LinkedList<ANode> nodes;
     LinkedList<NodeDirectory> nextNodes;
     NodeDirectory(String name) {
        super(name);
        this.nodes = new LinkedList<>();
        this.nextNodes = new LinkedList<>();
    }
    public void add(ANode newNode){
        nodes.add(newNode);
        if(newNode instanceof NodeDirectory){
            nextNodes.add((NodeDirectory) newNode);
        }
    }
    protected void remove(ANode newNode){
        nodes.remove(newNode);
        if(newNode instanceof NodeDirectory){
            nextNodes.remove(newNode);
        }
    }
    public ANode search(String name) throws TreeError {
        for(ANode n : nodes){
            if(n.getName().equals(name)){
                return n;
            }
        }
        throw new TreeError("Archivo o directorio no se encuentra en la ruta especificada");
    }
    public LinkedList<NodeDirectory> getSubDirectory(){
        return nextNodes;
    }

    public LinkedList<ANode> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
         String r = "";
         for(ANode n : nodes){
            r += n.getName() + " ";
         }
        return r;
    }
}
