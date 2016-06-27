/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfsshortestreach;

import java.util.*;

/**
 *
 * @author Abhinandan
 */
public class BFSShortestReach {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int T = keyboard.nextInt();
        
        for(int c=0;c<T;c++){
            int N = keyboard.nextInt();
            int M = keyboard.nextInt();
            GraphNode[] arr = new GraphNode[N+1]; 
            
            for(int i=1;i<N+1;i++)
                arr[i] = new GraphNode(i);
            
            for(int i=0;i<M;i++){
                int n1 = keyboard.nextInt();
                int n2 = keyboard.nextInt();
                
                if(arr[n1].neighbours == null){
                    ArrayList<GraphNode> neighbours = new ArrayList<GraphNode>();
                    neighbours.add(arr[n2]);
                    arr[n1].neighbours = neighbours;
                }
                else {
                    arr[n1].neighbours.add(arr[n2]);
                }
                
                if(arr[n2].neighbours == null){
                    ArrayList<GraphNode> neighbours = new ArrayList<GraphNode>();
                    neighbours.add(arr[n1]);
                    arr[n2].neighbours = neighbours;
                }
                else {
                    arr[n2].neighbours.add(arr[n1]);
                }
            }        
            
            int S = keyboard.nextInt();
            
            PriorityQueue<GraphNode> cost = new PriorityQueue<GraphNode>(new Comparator<GraphNode>(){
                public int compare(GraphNode node1, GraphNode node2){
                    return (node1.value - node2.value);
                }
            });

            for(int i=1;i<N+1;i++){
                cost.add(arr[i]);
            } 
            
            cost.remove(arr[S]);
            arr[S].value = 0;
            cost.add(arr[S]);

            while(!cost.isEmpty()){
                GraphNode minNode = cost.poll();  
                minNode.visited = true;
                if(minNode.value == Integer.MAX_VALUE) break;
                if(minNode.neighbours == null) continue;
                for(GraphNode neighbour : minNode.neighbours){
                    if(!neighbour.visited && (minNode.value+6 < neighbour.value)){
                        cost.remove(neighbour);
                        neighbour.value = minNode.value+6;
                        cost.add(neighbour);
                    }
                }
            }

            for(int i=1;i<N+1;i++){
                if(i!=S){
                    if(arr[i].value != Integer.MAX_VALUE)
                        System.out.print(arr[i].value+" ");
                    else
                        System.out.print("-1 ");
                }
            }
            System.out.println();
        } 
    }
    
}

class GraphNode {
    int index;
    int value;
    boolean visited;
    ArrayList<GraphNode> neighbours; 
    
    public GraphNode(int index){
        this.index = index;
        this.value = Integer.MAX_VALUE;
    }
}
