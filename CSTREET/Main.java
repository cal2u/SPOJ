/* 
	Spoj.com Problem CSTREET, "Cobbled streets"
	Solved by cal2u 10/09/15
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Vertex implements Comparable<Vertex>{
	int dist, id;
	ArrayList<Integer> edges;
	boolean visited;

	public Vertex (int id) {
		this.id = id;
		this.edges = new ArrayList<Integer>();
		this.dist = Integer.MAX_VALUE;
	}

	public int compareTo(Vertex other) {
		return (this.dist < other.dist) ? -1 : 1;	 
	}
}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int i = 0; i < t; i++) {
			int p = sc.nextInt();
			int v = sc.nextInt();
			int k = sc.nextInt();

			Vertex[] graph = new Vertex[v+1];
			
			// Add vertices
			for (int j = 1; j < v+1; j++) {
				graph[j] = new Vertex(j);
			}

			int[][] weights = new int[v+1][v+1];

			// Add edges
			for (int j = 0; j < k; j++) {
				int v1 = sc.nextInt();
				int v2 = sc.nextInt();
				int w = sc.nextInt();

				graph[v1].edges.add(v2);
				graph[v2].edges.add(v1);
				weights[v1][v2] = w;
				weights[v2][v1] = w;
			}

			int ans = -1;
			
			// Solve
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
			graph[2].dist = 0;
			pq.add(graph[2]);

			int total = 0;


			while (!pq.isEmpty()) {
				// Mark as part of the subgraph
				Vertex current = pq.remove();
				current.visited = true;

				// Add the edge length to the total
				total += current.dist;

				// Make sure all adjacent vertices are being considered for exploration
				for (Integer vid : current.edges) {
					if (!graph[vid].visited && weights[current.id][vid] < graph[vid].dist) {
						pq.remove(graph[vid]);
						graph[vid].dist = weights[current.id][vid];
						pq.add(graph[vid]);
					}
				}
			}
			System.out.println(p*total);
		}
	}	
}