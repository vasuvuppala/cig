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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for CiG Algorithms and Graphs
 *
 * @author <a href="mailto:vuppala@gmail.com">Vasu Vuppala</a>
 */
public class GraphTest {

    public GraphTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getNumOfVertices method, of class Graph.
     */
    @Test
    public void testGetNumOfVertices() {
        System.out.println("getNumOfVertices");
        int vertices = 10000;
        Graph instance = Graph.newInstance(vertices);
        int expResult = vertices;
        int result = instance.getNumOfVertices();
        assertEquals(expResult, result);
    }

    /**
     * Test cycle detection
     * 
     */
    @Test
    public void testDetectCycle() {
        System.out.println("Detect cycle");
        int vertices = 10;
        Graph instance = Graph.newInstance(vertices, true);
        System.out.print(instance);
        boolean expResult = false;

        for (Algorithm algo : Algorithm.currentAlgorithms()) {
            System.out.println("Checking " + algo.getName());
            boolean result = instance.hasCycle(algo.getAlgorithm());
            assertEquals(expResult, result);
        }
    }
}
