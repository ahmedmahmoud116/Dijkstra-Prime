package pkj;
import java.util.List;
import java.util.ArrayList;
public class Test {
	private enum DijkstraType{ DIRECTED,UNDIRECTED;}

	public static void main(String[] args) {
//		Prim.primhandler();
		//UndirectedDijkstra dij = new UndirectedDijkstra();
		testDijkstra(0,DijkstraType.UNDIRECTED);
	}
	private static void testDijkstra(int src,DijkstraType type)
	{
		switch(type)
		{
			case UNDIRECTED:UndirectedDijkstra dij = new UndirectedDijkstra();
							dij.doDijkstra(src);
							break;
			case DIRECTED:
			List<Node> nodes = new ArrayList<>();
			List<Edge> edges = new ArrayList<>();
			for(int i = 0 ; i<=7 ;++i)
				nodes.add(new Node(String.valueOf(i)));
			/*
			  0 1 2 3 4 5 6 7
			0 0 0 9 0 0 0 0 0                
			1 0 0 0 9 0 0 0 0         
			2 0 0 0 0 0 6 3 0                    
			3 0 0 0 0 0 0 0 0              
			4 0 0 0 0 0 0 0 0               
			5 0 6 0 5 0 0 0 0              
			6 0 0 0 0 2 0 0 0                
			7 0 0 0 0 0 0 4 0                  
			 */
			edges.add(new Edge(Edge.generateRandomIDs(), nodes.get(0), nodes.get(2), 9));
			edges.add(new Edge(Edge.generateRandomIDs(), nodes.get(2), nodes.get(5), 6));
			edges.add(new Edge(Edge.generateRandomIDs(), nodes.get(2), nodes.get(6), 3));
			edges.add(new Edge(Edge.generateRandomIDs(), nodes.get(6), nodes.get(4), 2));
			edges.add(new Edge(Edge.generateRandomIDs(), nodes.get(5), nodes.get(1), 6));
			edges.add(new Edge(Edge.generateRandomIDs(), nodes.get(5), nodes.get(3), 5));
			edges.add(new Edge(Edge.generateRandomIDs(), nodes.get(1), nodes.get(3), 9));
			edges.add(new Edge(Edge.generateRandomIDs(), nodes.get(7), nodes.get(6), 4));
			DirectedDijkstra dirDij = new DirectedDijkstra(new Graph(nodes,edges));
			dirDij.doDijkstra(nodes.get(src));
			break;
		}

	}
}
