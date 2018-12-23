package cn.edu.bupt.sdmda.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.edu.bupt.sdmda.ds.linearlist.LinkedQueue;

public class GraphAlgorithm {

//     double[][] graph = new double[][]{
//     {0, 34, 46, -1,-1,19},
//     {34,0,-1,-1,12,-1},
//     {46,-1,0,17,-1,25},
//     {-1,-1,17,0,38,25},
//     {-1,12,-1,38,0,26},
//     {19,-1,25,25,26,0}
//     };

    public static void dfs(double[][] graph, int start) {
        // init an array of int to indicate which vertex is visited
        int[] visited = new int[graph.length];

        // start dfs recursively
        _dfs(graph, start, visited);
    }

    // dfs recursively
    private static void _dfs(double[][] graph, int start, int[] visited) {
        // print current vertex
        if (visited[start] == 1) {
            return;
        }
        visited[start] = 1;
        System.out.print(start + " ");
        // find next available vertex and dfs on it
        for (int i = 0, len = graph.length; i < len; i++) {
            if (graph[start][i] < 0 || start == i) {
                continue;
            }
            _dfs(graph, i, visited);
        }
    }

    public static void bfs(double[][] graph, int start) {
        // init an array of int to indicate which vertex is visited
        int[] visited = new int[graph.length];
        // build a queue and push start to it
        // change LinkedList to your own implementation
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
        queue.offer(start);
        // loop while queue is not empty
        while (queue.getSize() > 0) {
            // pop and print a vertex,
            int cur = queue.poll();
            if (visited[cur] == 1) {
                continue;
            }
            System.out.print(cur + " ");
            visited[cur] = 1;
            // then push its available next vertex to the queue
            for (int i = 0, len = graph.length; i < len; i++) {
                if (graph[cur][i] < 0 || cur == i) {
                    continue;
                }
                queue.offer(i);
            }
        }
    }

    public static double[][] prim(double[][] graph) {
        // init a double[][] for return
        // init U and V
        double[][] ret = new double[graph.length][graph.length];
        for(int i=0,len=graph.length;i<len;i++) {
            for(int j=0,len2=graph.length;j<len2;j++) {
                if (i==j) continue;
                ret[i][j] = -1;
            }
        }
        List<Integer> U = new ArrayList<Integer>();
        List<Integer> V = new ArrayList<Integer>();
        U.add(0);
        for(int i=1,len=graph.length;i<len;i++) {
            V.add(i);
        }
        // loop while V is not empty
        while(V.size()>0) {
            // loop on each vertex in U
            // find closest path from U to V(u-->v)
            Edge e = new Edge(0,0,Double.MAX_VALUE);
            for(int i=0,len=U.size();i<len;i++) {
                for(int j=0,len2=V.size();j<len2;j++) {
                    int u = U.get(i);
                    int v = V.get(j);
                    if(u==v || graph[u][v]<0) continue;
                    Edge cur = new Edge(u,v,graph[u][v]);
                    if(cur.compareTo(e) == -1) {
                        e = cur; 
                    }
                }
            }
            // move v to U
            int u = U.contains(e.v1)?e.v1:e.v2;
            int v = V.contains(e.v2)?e.v2:e.v1;
            V.remove(new Integer(v));
            U.add(v);
            ret[u][v] = ret[v][u] = e.weight;
        }
        return ret;
    }

    public static double[][] kruskal(double[][] graph) {
        // construct a list of edge from graph, and sort it
        List<Edge> edges = new ArrayList<Edge>();
        for(int i=0,len=graph.length;i<len;i++) {
            for(int j=0,len2=graph.length;j<len2;j++) {
                if(i==j || graph[i][j]<0) {
                    continue;
                }
                edges.add(new Edge(i,j,graph[i][j]));
            }
        }
        Collections.sort(edges);
        // init a array represent the root of each vertex
        int[] root = new int[graph.length];
        for(int i=0,len=graph.length;i<len;i++) {
            root[i] = i;
        }
        // init a double[][] for return
        double[][] ret = new double[graph.length][graph.length];
        for(int i=0,len=graph.length;i<len;i++) {
            for(int j=0,len2=graph.length;j<len2;j++) {
                if(i==j) {
                    continue;
                }
                ret[i][j] = -1;
            }
        }
        // loop on edges
        for(int i=0,len=edges.size();i<len;i++) {
            // check if the roots of two vertex of this edge the same
            // if is same, continue
            // if not, modify the double[][] for return
            // and update root
            Edge e = edges.get(i);
            int r1 = _getRoot(root,e.v1);
            int r2 = _getRoot(root,e.v2);
            if(r1==r2) {
                continue;
            }
            ret[r1][r2] = ret[r2][r1] = e.weight;
            root[r2] = r1;
        }
        return ret;
    }

    // get root of a vertex
    private static int _getRoot(int[] root, int query) {
        int ret = root[query];
        while(ret != root[ret]) {
            ret = root[ret];
        }
        return ret;
    }
    // a simple class to represent edge
    // it is comparable to itself
    static class Edge implements Comparable<Edge> {
        int v1, v2;
        double weight;

        public Edge(int v1, int v2, double w) {
            this.v1 = v1 < v2 ? v1 : v2;
            this.v2 = v1 < v2 ? v2 : v1;
            weight = w;
        }
        @Override
        public int compareTo(Edge o) {
            // TODO Auto-generated method stub
            if (weight < o.weight)
                return -1;
            if (weight > o.weight)
                return 1;
            if (weight == o.weight)
                return 0;
            return 0;
        }
    }

    public static double dijkstra(double[][] graph, int start, int end) {
        // init U and V
        List<Integer> U = new ArrayList<Integer>();
        List<Integer> V = new ArrayList<Integer>();
        // init an array indicating the shortest distance from start to each vertex
        double[] distance = new double[graph.length];
        for(int i=0;i<graph.length;i++) {
            distance[i] = graph[start][i]<=0?Double.MAX_VALUE:graph[start][i];
        }
        U.add(start);
        for(int i=0;i<graph.length;i++) {
            if(i == start){
                continue;
            }
            V.add(i);
        }
        // loop if end is not in U
        while(!U.contains(end)) {
            int v=start;
            double value=1e9;
            for(int i=0,len=V.size();i<len;i++) {
                if(U.contains(i)) {
                    continue;
                }
                if(distance[i]<value) {
                    value = distance[v];
                    v = i;
                }
            }
            // find shortest path from U to V (u--v)
            // update distance, U ,V
            V.remove(new Integer(v));
            U.add(v);
            for(int i=0,len=V.size();i<len;i++) {
                distance[i] = Math.min(distance[i],distance[v]+graph[v][i]);
            }
        }
        return distance[end];
    }
}