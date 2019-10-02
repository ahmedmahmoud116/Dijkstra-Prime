package pkj;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Prim {
	static Scanner scan;
	public static void primhandler() {
		
		int graph[][] = inputMST();//to get the input graph
		int startvertix = inputHandler(graph);// to get the starting vertix of MST
		
		int numofvertices = graph.length; //number of vertices
		final int INFINITE = Integer.MAX_VALUE; //assume infinite
		
		//to represent the vertices that are not included yet in minimum spanning tree
		//graph length return number of rows which is the number of vertices
		boolean mstset[] = new boolean [numofvertices];
		
		//array to have values of the edges
		int[] value = new int[numofvertices];
		
		//array to have the predecessor or parent of every vertices
		int[] predecessor = new int[numofvertices];
		
		 //Initialize
        for (int i = 0; i < numofvertices; i++) 
        { 
            value[i] = INFINITE;  // Initialize all values as INFINITE
            mstset[i] = false; 	  //initialize MST as all false because there is no element in it yet
        }
        
        value[startvertix] = 0;   		//to make the first vertix 0 to start with it
        predecessor[startvertix] = -1; 	//it doesn't have a predecessor because its the root
        
        //numberofvertices - 1 because the last element the rest will be already in MST so it doesn't need to check it
        for (int i = 0; i < numofvertices - 1 ; i++)
        {
        	// return the index of the minimum key vertex from the set of vertices not yet included in MST 
            int u = minValue(value, mstset); 
        	if(u == -1)
        		continue;
            mstset[u] = true;  //mark it as visited and put in MST
            
            //we see the adjacent of u and if its value smaller than its value from the vertix before it and its not in MSTset
            //we update its value so we can get it by minValue when we repeat again 
            for(int v = 0 ; v<numofvertices ; v++)
            {
            	if(graph[u][v] !=0 && mstset[v] == false && graph[u][v] < value[v])
            	{
            		predecessor[v] = u;	//update its parent that get it its smallest value
            		value[v] = graph[u][v];
            	}
            }
        }
        outputMST(predecessor , graph, startvertix); // to print the output MST
        
	}
	
	public static void outputMST(int predecessor[] , int graph[][],int startvertix){
		System.out.println(); 
		System.out.println("Edge \t\tWeight"); 
	        for (int i = startvertix + 1; ;i++)
	        	{
	        	if(i >= graph.length) //to check if it reaches the boundaries of the graph to return and print the before the vertix
	        		i = 0;
	            if(i == startvertix) //if it reaches the start vertix go out because its the root has no parents
	            	break;
	            if(graph[i][predecessor[i]] == Integer.MAX_VALUE)
	            	System.out.println(i + " ---> \t\t" +"INFINITY");
	            else
	            System.out.println(predecessor[i]+" ---> "+ i+"\t"+ graph[i][predecessor[i]]);
	        	}
	}
	
	   private static int minValue(int[] value, boolean[] mstset) 
	    { 
	        // Initialize min value 
	        int min = Integer.MAX_VALUE; 
	        int min_index = -1; 
	        
	        for (int v = 0; v < mstset.length; v++) //loop to all of vertices that are not included in mstset to decide 
	            if (mstset[v] == false && value[v] < min) //any aro7 la2nhy asgher vertices
	            { 
	                min = value[v]; 
	                min_index = v; 
	            } 
	  
	        return min_index; 
	    }
	   
		public static int[][] inputMST() {
			int INFINITE = Integer.MAX_VALUE;
			 scan =  new Scanner(System.in);
			
			try
			{
				System.out.print("Enter the number of vertices: ");
				int numberofvertices = scan.nextInt();
				
				int[][] graph = new int[numberofvertices][numberofvertices];

				System.out.println("Enter the Weighted Matrix for the graph: ");
				for (int i = 0; i < numberofvertices; i++)
				{
					for (int j = 0; j < numberofvertices; j++)
					{
						graph[i][j] = scan.nextInt();
						if (i == j) //because there is no self path
						{
							graph[i][j] = 0;
							continue;
						}
						if (graph[i][j] == 0)
						{
							graph[i][j] = INFINITE;
						}
					}
				}
				return graph;

			} catch (InputMismatchException ex)
			{
				int[][]graph = new int[0][0];
				System.out.println("Please enter integer number!!!");
				return graph;
			}
		
		}
		
		public static int inputHandler(int[][] graph) {
			 scan =  new Scanner(System.in);
			int vertix = -999;
			
			while(vertix >= graph.length || vertix < 0)
			{
				try {
					System.out.print("Please enter the starting vertix: ");
					vertix = scan.nextInt();
				}
				catch(NoSuchElementException ex){
					System.out.println("Please enter integer number!!!");
				}
			}
			return vertix;
		}
	
}
