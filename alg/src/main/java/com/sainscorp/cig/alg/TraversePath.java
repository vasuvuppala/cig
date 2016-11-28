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
 *
 * @author vvuppala
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
