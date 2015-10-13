/*
    Spoj.com Problem EZDIJKST, "Easy Dijkstra Problem"
    Solved by cal2u 10/08/15
*/


import java.util.*;
import java.io.*;

/*
    Faster input-getter than java.util.Scanner
    Code provided to me by my programming team, but
    I'm unsure who originally wrote it.
*/
class Parser {
    final private int BUFFER_SIZE = 1 << 16;

    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Parser(InputStream in)
    {
     din = new DataInputStream(in);
     buffer = new byte[BUFFER_SIZE];
     bufferPointer = bytesRead = 0;
    }

    public int nextInt() throws Exception
    {
     int ret = 0;
     byte c = read();
     while (c <= ' ') c = read();
     boolean neg = c == '-';
     if (neg) c = read();
     do
     {
         ret = ret * 10 + c - '0';
         c = read();
     } while (c > ' ');
     if (neg) return -ret;
     return ret;
    }

    public double nextDouble() throws Exception {
     double toRet = 0.0;
     int ret = 0;
     byte c = read();
     while (c <= ' ') c = read();
     do
     {
         ret = ret * 10 + c - '0';
         c = read();
     } while (c > ' ' && c != '.');
     int ret2 = 0;
     double mult = 1.0;
     if (c == '.') {
         c = read();
         do {
             ret2 = ret2 * 10 + c - '0';
             mult *= .1;
             c = read();
         } while ( c > ' ');
         toRet += ret2*mult;
     }
     return toRet + ret;
    }

    public String nextString(int length) throws Exception {
     StringBuilder br = new StringBuilder();
     byte c = read();
     while(c <= ' ') c = read();
     for(int i = 0; i < length; ++i) {
         br.append((char)c);
         c = read();
     }
     return br.toString();
    }

    public String next() throws Exception{
     StringBuilder br = new StringBuilder();
     byte c = read();
     while(c <= ' ') c = read();
     while(c > ' ') {
         br.append((char)c);
         c = read();
     }
     return br.toString();
    }

    private void fillBuffer() throws Exception
    {
     bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
     if (bytesRead == -1) buffer[0] = -1;
    }

    private byte read() throws Exception
    {
     if (bufferPointer == bytesRead) fillBuffer();
     return buffer[bufferPointer++];
    }
}


class Vertex implements Comparable<Vertex>{
    int dist, id;
    ArrayList<Integer> edges;
    
    public Vertex (int id) {
        this.dist = Integer.MAX_VALUE;
        this.id = id;
        this.edges = new ArrayList<Integer>();
    }

    public int compareTo(Vertex other) {
        return (this.dist < other.dist) ? -1 : 1;    
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Parser sc = new Parser(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int v = sc.nextInt();
            int k = sc.nextInt();

            Vertex[] graph = new Vertex[v+1];
            int weights[][] = new int[v+1][v+1];
            // Add vertices
            for (int j = 1; j < v+1; j++) {
                graph[j] = new Vertex(j);
            }

            // Add edges
            for (int j = 0; j < k; j++) {
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                int w = sc.nextInt();

                graph[v1].edges.add(v2);
                weights[v1][v2] = w;
            }

            int start = sc.nextInt();
            int end = sc.nextInt();
            
            int ans = -1;
            
            // Solve
            PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
            graph[start].dist = 0;
            pq.add(graph[start]);

            while (!pq.isEmpty()) {
                Vertex current = pq.remove();

                // If we found our destination, we guarantee it's the shortest path
                if (current.id == end) {
                    ans = current.dist;
                    break;
                }

                // Otherwise, see if we can travel to any vertices in a new shortest dist
                for (Integer neighbor : current.edges) {
                    int newDist = current.dist + weights[current.id][neighbor];

                    if (newDist < graph[neighbor].dist) {
                        // Put the vertex in the queue if it's not already, with the appropriate priority
                        pq.remove(graph[neighbor]);
                        graph[neighbor].dist = newDist;
                        pq.add(graph[neighbor]);
                    }
                }
            }
            System.out.println((ans != -1) ? ans : "NO" );
        }
    }   
}