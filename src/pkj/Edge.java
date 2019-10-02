package pkj;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
public class Edge
{
    private final int id,weight;
    private final Node src,dest;
    private final static Set<Integer> IDs;
    private final static Random rnd;
    static
    {
        IDs = new HashSet<>();
        rnd = new Random();
    }
    public static int generateRandomIDs()
    {
        int result;
        while(!Edge.IDs.add((result=rnd.nextInt())));
        return result;
    }
    public Edge(int id,Node src,Node dest, int weight)
    {
        this.id = id;
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
    public int getId()
    {
        return this.id;
    }
    public Node getDestination()
    {
        return this.dest;
    }
    public Node getSource()
    {
        return this.src;
    }
    public int getWeight()
    {
        return this.weight;
    }
    @Override
    public int hashCode()
    {
        return this.id*31;
    }
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(o == null || Edge.class != o.getClass())
            return false;
        Edge anotherEdge = (Edge)o;
        return anotherEdge.getId() == this.id;
    }
    @Override
    public String toString()
    {
        return this.src + "-->" + this.dest;
    }
}