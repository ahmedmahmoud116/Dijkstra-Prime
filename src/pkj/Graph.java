package pkj;
import java.util.List;
import java.util.Collections;
public class Graph
{
    private final List<Node> nodes;
    private final List<Edge> edges;
    public Graph(List<Node> nodes,List<Edge> edges)
    {
        this.nodes = nodes;
        this.edges = edges;
    }
    public List<Node> getNodes()
    {
        return Collections.unmodifiableList(this.nodes);
    }
    public List<Edge> getEdges()
    {
        return Collections.unmodifiableList(this.edges);
    }
}