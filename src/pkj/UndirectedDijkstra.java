package pkj;
import java.util.Scanner;
import java.util.InputMismatchException;
public class UndirectedDijkstra
{
    private static final Scanner input;
    private int[][] graph; //adjacency matrix
    private int[] distance; // initial weights between given src and other nodes
    private boolean[] visited; // to know if they were visited or not
    private int[] previousIdx; // to know the previous node
    private int min; // to know the min distance
    private int nextNode; // to know which node is next
    private int size; // size of adjacency matrix
    private int src;
    static
    {
        input = new Scanner(System.in);
    }
    {
        this.nextNode = 0; //next node is the start by default
        this.min = Integer.MAX_VALUE; // min value of distances is infinity initially
    }
    public UndirectedDijkstra()
    {
        System.out.println("=========================== Initiating input process for Dijkstra ===============================");
        this.init();
    }
    private void init()
    {
        //takes user input of adjacency matrix
        this.graph = this.getGraph();
    }
    public void doDijkstra(int src)
    {
        if(src >= this.size || src < 0)
            throw new IllegalArgumentException("Source index can't exceed size of graph and can't be less than zero.");
        // get the weights concerning the given src index (given node position) according to other nodes 
        this.distance = this.graph[src];
        // initializing the distance of the src node with itself to 0
        this.distance[src] = 0;
        // marking the src node as visited
        this.visited[src] = true;
        this.src =src;
        java.util.Arrays.fill(this.previousIdx, src); // initializing previous nodes to 0 (will change)
        // dijkstra 
        for(int i = 0 ; i < this.size ; ++i)
        {
            this.min = Integer.MAX_VALUE; // min value is infinity
            // loop the distance array to check if less than infinity and not visited put the new distance in min and set next node as the least distance node
            for(int j = 0 ; j < this.size ; ++j)
                if(this.min > this.distance[j] && !visited[j])
                {   
                    // to get min distance from source at that iteration
                    this.min = this.distance[j];
                    // to get node of min distance from source
                    this.nextNode = j;
                }
            // mark the least distance node found to be visited
            this.visited[this.nextNode] = true;

            for(int z = 0 ,computed = 0; z < this.size ; ++z)
            {
                // if not visited
                if(!visited[z])
                {
                    // and the min value changed from infinity and has an edge between the node and the other nodes and the distance between the min distance
                    // and the next node is less than distance that exists, update new distance and put the previous idx as the node we're in
                    if(this.min != Integer.MAX_VALUE && this.graph[nextNode][z] != Integer.MAX_VALUE && (computed = this.min + this.graph[nextNode][z]) < this.distance[z])
                    {
                        this.distance[z] = computed;
                        this.previousIdx[z] = this.nextNode;
                    }
                }
            }
        }
        System.out.println(this);

    }
    @Override
    public String toString()
    {
        return "Distance Array: " + this.getDistanceArrString() + "\n" + this.getPreviousArrString();
    }
    private String getPreviousArrString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0,j=i; i < this.size ; ++i)
        {
            if(this.distance[i]==Integer.MAX_VALUE)
            {
                sb.append(i+" <- -1\n");
                continue;
            }
            sb.append(i);
            j = i;
            do{
                j = this.previousIdx[j];
                
                sb.append(" <- "+j);
            }while(j!=this.src);
            sb.append("\n");
        }
        return sb.toString();
    }
    private String getDistanceArrString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < this.size ; ++i)
        {
            sb.append(((i==0)?"{":"")+this.distance[i]+((i!=size-1)?", ":"}"));
        }
        return sb.toString();
    }
    private int[][] getGraph()
    {
        try{
            System.out.print("Enter number of rows or columns : ");
            this.size = input.nextInt();
            if(this.size < 2){ System.out.println("Size can't be less than 2, try again"); return this.getGraph();}
            int[][] graph = new int[this.size][this.size]; // inistansiate adjacency matrix
            this.visited = new boolean[this.size]; // initansiate an array of fixed length of actual size of nodes
            this.previousIdx = new int[this.size]; // inistansiate an array of fixed length of actual size of nodes to know the previous index
            for(int i = 0 ; i < this.size ; ++i)
            {    
                this.visited[i] = false; // initializing the array nodes to unvisited
                
                for(int j = 0 ; j < this.size ; ++j)
                {
                    System.out.print("Enter Element "+i+", "+j+" : ");
                    graph[i][j] = input.nextInt();
                    if(graph[i][j] < 0){System.err.println(" must be positive! , try again"); return this.getGraph();}
                    if(graph[i][j] == 0) graph[i][j]=Integer.MAX_VALUE; // if no weight between them then put as infinity
                }
             }
             
             return graph;
            // return new int[][]{{,,,,},{,,,,},{,,,,},{,,,,},{,,,,}};
        }catch(InputMismatchException ex)
        {
            System.err.println("Wrong format, try again");
            return this.getGraph();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }catch(Throwable th)
        {
            th.printStackTrace();
        }
        return null;
    }
    

}