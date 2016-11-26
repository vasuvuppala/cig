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
     * @param vertices
     * @return 
     */
    public Graph newInstance(int vertices) {
        Graph graph = new Graph();
        graph.numOfVertices = vertices;
        graph.adjMatrix = new BitSet(vertices * vertices);
        
        return graph;
    }

    public Graph newInstance(int vertices, boolean noCycle) {
        
    }
    
    public int getNumOfVertices() {
        return numOfVertices;
    }
    
    
}
