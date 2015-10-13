import java.util.ArrayList;
import java.util.Scanner;

/* 
	Spoj.com problem PT07Y, "Is it a tree"
	Submitted by cal2u on 9/25/15
*/

class Main {

	// Optimization to get input faster
	static int scan(Scanner in) throws Exception {
		return in.nextInt();
	}
	
	static boolean[] visited;
	
	// DFS marking vertices as visited
	static void traverse_graph(ArrayList<ArrayList<Integer>> graph, int current) {
		ArrayList<Integer> todo = graph.get(current);
		visited[current] = true;
		
		for (int i = 0; i <todo.size(); i++) {
			if (visited[todo.get(i)] == false)
				traverse_graph(graph, todo.get(i));
		}
	}
	
	public static void main(String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		

		int nodes = scan(in);
		int edges = scan(in);
		visited = new boolean[nodes];

		// Basic check to see if graph potentially could be tree
		if (edges != nodes-1) {
			System.out.println("NO");
			return;
		}

		// Construct our graph
		for (int i = 0; i < nodes; i++) {
			graph.add(new ArrayList<Integer>());
			visited[i] = false;
		}

		
		for (int i = 0; i < edges; i++) {
			int u = scan(in);
			int v = scan(in);
			graph.get(u-1).add(v-1);
			graph.get(v-1).add(u-1);
		}

		/* 
		 	Try exploring all adjacent verticies in graph, 
		 	if we visit all verticies it's a graph
		*/
		if (graph.get(0).size() > 0) { 
			traverse_graph(graph, graph.get(0).get(0));
			for (int i = 0; i < nodes; i++ ) {
				if (!visited[i]) {
					System.out.println("NO");
					return;

				}
			}
			System.out.println("YES");
			return;
		}
		System.out.println("NO");
		
		
	}
}