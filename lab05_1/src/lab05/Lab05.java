/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 *
 * @author aelittaezugbaa
 */
public class Lab05 {

    private static int mazeSolution(int from, int to, int pred[], int steps[]) {
        int i, n, node;
        //System.out.println(Arrays.toString(pred));
        // first count how many edges between the end and the start
        node = to;
        n = 1;
        while ((node = pred[node]) != from) {
            n++;
        }

        // then reverse pred[] array to steps[] array
        node = to;
        i = n;
        while (i >= 0) {
            steps[i--] = node;
            node = pred[node];
        }

        // include also the end vertex
        return (n + 1);

    }

    private static boolean contains(int[] array, int value){
        int answer = 0;
        for(int i=0; i<array.length;i++){
            if(array[i]==value){
                answer+=1;
            }
        }

        if(answer>0){
            return true;
        }
        else{
            return false;
        }
    }

    /* find a maze solution */
//    private static int mazeSolution(int from, int to, int pred[], int steps[]) {
//        int i, n, node;
//        // first count how many edges between the end and the start
//        node = to;
//        n = 1;
//        while (pred[n] != to) {
//            n++;
//
//        }
//        System.out.println("Edges: "+n);
//        // then reverse pred[] array to steps[] array
//        node = to;
//        i = n;
//        for (int j = 0; j < n; j++) {
//            steps[j] = pred[i];
//            i--;
//        }
//        System.out.println(Arrays.toString(steps));
//
//        // include also the end vertex
//        return (n + 1);
//    }
    private final static int FROM = 0;
    private final static int TO = 9;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Graph g = new Graph();
      	String FILE = args[0];

        // read the graph. and do the depth-first search
       // System.out.println("Graph Adjacent list");
        g.readGraph(new File(FILE));
        g.printGraph();

        boolean visited[] = new boolean[g.nodes()];
        int pred[] = new int[g.nodes()];
        g.dfs(FROM, visited, pred);

        // then check if there is a solution by looking from the backwards to the start
        int steps[] = new int[g.nodes()];
        System.out.println("\nMaze solution from " + FROM + " to " + TO);;
        int n = mazeSolution(FROM, TO, pred, steps);
        //System.out.println("n"+n);
        //System.out.println(Arrays.toString(steps));
        if(g.getStory().contains(TO)){

                for (int i = 0; i < n; i++) {
                    System.out.print(steps[i] + " ");
                }
		System.out.println(" ");             
        }
        else{
            System.out.println("This maze doesn't have solution");
        }
    }


}
