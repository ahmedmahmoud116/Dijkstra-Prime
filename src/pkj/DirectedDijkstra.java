package pkj;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
public class DirectedDijkstra
{
    private final List<Node> nodes;
    private final List<Edge> edges;
    private final Set<Node> settledNodes;
    private final Set<Node> unSettledNodes;
    private final Map<Node,Node> previousNodes;
    private final Map<Node,Integer> distance;

    {
        this.settledNodes = new HashSet<>();
        this.unSettledNodes = new HashSet<>();
        this.distance = new HashMap<>();
        this.previousNodes = new HashMap<>();
    }
    
    public DirectedDijkstra(Graph graph)
    {
        this.nodes = graph.getNodes();
        this.edges = graph.getEdges();
        for(Node n : this.nodes)
        {
            this.distance.put(n,Integer.MAX_VALUE);
        }
    }

    public void doDijkstra(Node src)
    {
        this.distance.put(src,0);
        this.unSettledNodes.add(src);
        while(!unSettledNodes.isEmpty())
        {
            // System.out.println("unSettledNodes size is: "+unSettledNodes.size());
            // System.out.println(unSettledNodes);
            // System.out.println(minNode);
            
            // to find which node is going to be executed on ( least cost ) from previous node
            Node minNode = this.getMinNode(unSettledNodes);
            //marking the node that is going to be executed on as visited
            // used also with find neighbours to not visit an already visited node again
            this.settledNodes.add(minNode);
            this.unSettledNodes.remove(minNode);
            //updating distances (cost) of neighbours and marking them unvisited 
            //for the loop to continue on them by finding which one of them has least cost first
            this.findLeastDist(minNode);
            //printing which node is currently being executed on
            System.out.println(minNode);
        }
        System.out.println(this);
    }
    // gets the path starting from given node till end of its previous nodes if exists then reverses it to be of correct sequence 
    public List<Node> getPath(Node nodePtr)
    {
        if(nodePtr == null)
            throw new IllegalArgumentException("Nodeptr in getPath can't be null!");
        List<Node> path = new LinkedList<>();
        Node temp = nodePtr;
        path.add(temp);
        while((temp=this.previousNodes.get(temp)) != null)
            path.add(temp);
        Collections.reverse(path);
        return path;
        

    }
    // loops on all edges till finds the distance between from node and to node
    private int getDistance(Node from, Node to)
    {
        for(Edge edge:this.edges)
        {
            if(edge.getSource().equals(from) && edge.getDestination().equals(to))
            {
                // System.out.println("Weight of node from: "+from+" to node to: "+to+" is: "+edge.getWeight());
                return edge.getWeight();
            }
        }
        throw new IllegalArgumentException("Invalid node argument(s)"); 
    }
    private void findLeastDist(Node nodePtr)
    {
        List<Node> neighbouringNodes = this.getNeighbours(nodePtr);
        int temp = -1; // holds value so Doesn't have to compute again
        for(Node n : neighbouringNodes)
        {
            if(this.getShortestDist(n) > (temp=(this.getShortestDist(nodePtr) + this.getDistance(nodePtr,n))))
            {
                // System.out.println("entered");   
                distance.put(n,temp);
                previousNodes.put(n,nodePtr);
                unSettledNodes.add(n);
            }
            // System.err.println("this.getShortestDist(n): "+this.getShortestDist(n));
        }
                    // System.err.println("temp: "+temp);
    }
    private List<Node> getNeighbours(Node nodePtr)
    {
        List<Node> neighbours = new ArrayList<>();
        Node tempNode = null; //for temporary holding of node so no extra computation
        for(Edge edge:this.edges)
        {
            if(edge.getSource().equals(nodePtr) && !this.settledNodes.contains((tempNode=edge.getDestination())))
            {
                neighbours.add(tempNode);
            }
        }
        return neighbours;
    }
    private Node getMinNode(Set<Node> unSettled)
    {
        if(this.nodes.isEmpty())
            throw new UnsupportedOperationException("Can't use getMinNode when there's no nodes present!");
        Node min = null;
		for (Node n : unSettled)
        {
			if (min == null) min = n;
			else if(getShortestDist(min) > getShortestDist(n)) 
					min = n;
        }
		return min;
    }
    private int getShortestDist(Node dest)
    {
        // System.err.println("Destination: "+ dest);
        // System.err.println("nodes: " +this.nodes);
        // System.err.println("distance: " +this.distance);
        // System.err.println("Node: "+dest+" distance: "+this.distance.get(dest));
        return this.distance.get(dest);
    }
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Distance: ");
        sb.append(this.distance.toString());
        sb.append(System.getProperty("line.separator"));
        sb.append("Paths: ");
        int i = 0;
        for(Node n: this.nodes)
            sb.append(i++ +" : "+this.getPath(n)+"\n");
        return sb.toString();
    }

}