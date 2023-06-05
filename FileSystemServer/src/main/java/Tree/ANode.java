package Tree;

public abstract class ANode {
    private String name;
    protected ANode prev;
    public ANode(String name) {
        this.name = name;
        prev = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public ANode getPrev() {
        return prev;
    }

    public void setPrev(ANode prev) {
        this.prev = prev;
    }
    */

}
