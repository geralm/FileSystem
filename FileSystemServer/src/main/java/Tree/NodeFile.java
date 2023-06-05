package Tree;

import org.w3c.dom.Node;

public class NodeFile extends ANode{
    String content;
    NodeFile(String name) {
        super(name);
    }
    public NodeFile(String name, String content){
        super(name);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
