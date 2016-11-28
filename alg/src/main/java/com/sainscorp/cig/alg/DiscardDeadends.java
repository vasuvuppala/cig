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

import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Detects cycles in a graph by removing deadends (vertices with no out going edges)
 * 
 * @author vvuppala
 */
public class DiscardDeadends implements CycleDetector {
    private static final Logger LOGGER = Logger.getLogger(DiscardDeadends.class.getCanonicalName());
    
    @Override
    public boolean detectCycle(Graph graph) {
        LOGGER.log(Level.INFO, "Starting discard deadends");
        int numOfVertices = graph.getNumOfVertices();
        BitSet deadends = new BitSet(numOfVertices);
        BitSet nonDeadends = new BitSet(numOfVertices);
        int cardinality[] = new int[numOfVertices];

        for (int i = 0; i < numOfVertices; i++) {
            cardinality[i] = graph.outDegree(i);
            if (cardinality[i] == 0) {
                deadends.set(i, true);
            } else {
                nonDeadends.set(i, true);
            }
        }

        int totalDeadends = 0;
        while (!deadends.isEmpty()) {
            totalDeadends += deadends.cardinality();
            for (int i = deadends.nextSetBit(0); i >= 0; i = deadends.nextSetBit(i + 1)) {
                // LOGGER.log(Level.INFO, "Removing vertex {0}", i);
                for (int j = 0; j >= 0; j = nonDeadends.nextSetBit(j + 1)) {
                    if (graph.hasEdge(j, i)) {
                        if (--cardinality[j] == 0) {
                            deadends.set(j);
                            nonDeadends.clear(j);
                        }
                    }
                }
                deadends.clear(i);
            }
        }

        return totalDeadends != numOfVertices;
    }
}
