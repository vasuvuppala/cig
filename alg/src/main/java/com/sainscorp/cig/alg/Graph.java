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

/**
 * A directed graph
 * Graph represented as an Adjacency Matrix
 * 
 * @author vasu
 */
public class Graph {
    private int numOfVertices;
    // ToDo: Better to have an array of bitsets? 
    // An integer multiplication + a memory access vs 2 memory accesses.     
    private BitSet adjMatrix; 
    
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
        graph.adjMatrix = new BitSet(vertices * vertices); // all bits are set to zero
        
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
                graph.adjMatrix.set(i * vertices + j, random.nextBoolean());
            }
        }
        
        return graph;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        
        for(int i = 0; i < numOfVertices; i++) {
            result.append("[ ");
            for (int j = 0; j < numOfVertices; j++) {              
                result.append(adjMatrix.get(i * numOfVertices + j) ? "1 " : "0 ");
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
