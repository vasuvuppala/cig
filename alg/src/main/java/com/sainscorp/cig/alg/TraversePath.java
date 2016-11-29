/*
 * The MIT License
 *
 * Copyright 2016 vvuppala.
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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Find cycles by checking the connected set of each vertex
 * 
 * Connected set of a vertex v is all the vertices that can be reached from v. 
 * ConnectedSet(v) = { x | x is a vertex and there is a path from v to x}
 *   <ol>
 *       <li>For each vertex v, compute its connected set </li>
 *       <li>Check if v belongs to ConnectedSet(v). If it does then the graph has a cycle in it.</li>
 *       <li>If no vertex belongs to its own connected set then the graph does not have a cycle.</li>
 *   </ol>
 * 
 * @author <a href="mailto:vuppala@gmail.com">Vasu Vuppala</a>
 */
public class TraversePath implements CycleDetector {
    private static final Logger LOGGER = Logger.getLogger(TraversePath.class.getCanonicalName());
    
    @Override
    public boolean detectCycle(Graph graph) {
        LOGGER.log(Level.INFO, "Starting Traverse Path");
        for (int i = 0; i < graph.getNumOfVertices(); i++) {
            // LOGGER.log(Level.INFO, "Checking cycle at {0}", i);
            if (graph.hasPath(i,i)) return true;
        }
        return false;
    }
}
