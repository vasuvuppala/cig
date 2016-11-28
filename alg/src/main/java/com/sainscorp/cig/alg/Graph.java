/*
 * The MIT License
 *
 * Copyright 2016 vasu.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.sainscorp.cig.alg;

import java.util.BitSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A directed graph
 * Graph represented as an Adjacency Matrix
 * 
 * @author vasu
 */
public class Graph {
    private static Logger LOGGER = Logger.getLogger(Graph.class.getCanonicalName());
    
    private int numOfVertices;
    // ToDo: Better to have an array of bitsets? 
    // An integer multiplication + a memory access vs 2 memory accesses.     
    private BitSet adjMatrix[]; 
    
    private Graph() {
        
    }
    
    /**
     * Generates an empty graph
     * 
     * @param vertices Number of vertices
     * @return 
     */
    public static Graph newInstance(int vertices) {
        Graph graph = new Graph();
        graph.numOfVertices = vertices;
        graph.adjMatrix = new BitSet[vertices];
        for(int i=0; i < vertices; i++) {
            graph.adjMatrix[i] = new BitSet(vertices); // all bits are set to zero
        } 
        
        return graph;
    }

    /**
     * Generate a random graph
     * 
     * @param vertices Number of vertices
     * @param noCycle  if true, graph must not have any cycles in it
     * @return 
     */
    public static Graph newInstance(int vertices, boolean noCycle) {
        Graph graph = Graph.newInstance(vertices);
        Random random = new Random();       
        
        for(int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (noCycle && i >= j) continue; // ToDo: Simple way to avoid cycles. Improve. 
                graph.adjMatrix[i].set(j, random.nextBoolean());
            }
        }
        
        return graph;
    }
    
    
    /**
     * Is there a path from 'v1' to 'v2'?
     * 
     * Connected Set of vertex x = { y | there is a path from x to y}
     * Compute the connected set of v1, and check if v2 is in it.
     * 
     * @param from
     * @param to
     * @return true if there is path from v1 to v2
     */
    public boolean hasPath(int from, int to) {
        BitSet old = new BitSet(numOfVertices); // previous value of the connected set
        BitSet connectedSet = new BitSet(numOfVertices); 
        BitSet difference = new BitSet(numOfVertices);

        connectedSet.or(adjMatrix[from]); difference.or(connectedSet);
        
        while (!difference.isEmpty()) {
            
            //difference.clear(); difference.or(connectedSet); difference.and(old); difference.xor(connectedSet);
            difference.clear(); difference.or(connectedSet);  difference.xor(old);
            // LOGGER.log(Level.INFO, "Difference size is {0}", difference.cardinality());
            // LOGGER.log(Level.INFO, "Difference is {0}", difference);
            old.clear(); old.or(connectedSet); // make a copy of the connected set
            for (int i = difference.nextSetBit(0); i >= 0; i = difference.nextSetBit(i + 1)) {
                connectedSet.or(adjMatrix[i]);
                if (connectedSet.get(to)) return true;
            }
        }
        return false;
    }
    
    /**
     * Is there an edge from v1 to v2?
     * 
     * @param v1 - from  
     * @param v2 - to
     * @return true if there is an edge
     */
    public boolean hasEdge(int v1, int v2) {
        return adjMatrix[v1].get(v2);
    }
    
    
    /**
     * Number of edges going out from a vertext
     * 
     * @param vertex
     * @return out-degree of given vertex
     */
    public int outDegree(int vertex) {
        return adjMatrix[vertex].cardinality();
    }
    
    /**
     * does the graph have any cycles in it?
     * 
     * @param detector
     * @return true if it does
     */
    public boolean hasCycle(CycleDetector detector) {
        boolean result = detector.detectCycle(this);
        // LOGGER.log(Level.INFO, "Cycle {0} detected in the graph", result? "": "not" );
       return result;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        
        for(int i = 0; i < numOfVertices; i++) {
            result.append("[ ");
            for (int j = 0; j < numOfVertices; j++) {              
                result.append(adjMatrix[i].get(j) ? "1 " : "0 ");
            }
            result.append("]\n");
        }
        return result.toString();
    }
    
    //  Getters and setters
    public int getNumOfVertices() {
        return numOfVertices;
    }        
}
