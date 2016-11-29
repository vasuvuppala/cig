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

import java.util.ArrayList;
import java.util.List;

/**
 * The implemented cycle detection algorithms
 *
 * @author <a href="mailto:vuppala@gmail.com">Vasu Vuppala</a>
 */
public class Algorithm {

    private final String name; // name of the algorithm
    private final CycleDetector algorithm; // the implmentation

    public Algorithm(String name, CycleDetector algorithm) {
        this.name = name;
        this.algorithm = algorithm;
    }

    public String getName() {
        return name;
    }

    public CycleDetector getAlgorithm() {
        return algorithm;
    }

    /**
     * Current implemented algorithms
     * 
     * @return list of algorithms
     */
    public static List<Algorithm> currentAlgorithms() {
        List<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new Algorithm("Traverse Paths", new TraversePath()));
        algorithms.add(new Algorithm("Discard Deadends", new DiscardDeadends()));
        
        return algorithms;
    }
}
