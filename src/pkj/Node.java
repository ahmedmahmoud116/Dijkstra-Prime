package pkj;
import java.util.Set;
import java.util.HashSet;
public class Node
{
    private final String name;
    private static final Set<String> IDs= new HashSet<>();

    public static boolean validateExistance(Node from,Node to)
    {
        return IDs.contains(from.getName()) && IDs.contains(to.getName());
    }
    public Node(String name)
    {
        if(name == null)
            throw new IllegalArgumentException("Name of a node can't be null");
        this.name = name;
        Node.IDs.add(name);
    }
    public String getName()
    {
        return this.name;
    }
    @Override
    public int hashCode()
    {
        return this.name.hashCode()*31;
    }
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(o == null || Node.class != o.getClass())
            return false;
        Node anotherNode = (Node)o;
        return anotherNode.getName().equals(this.name);
    }
    @Override
    public String toString()
    {
        return String.valueOf(this.name);
    }
}