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
 *
 * @author vasu
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
     * Test of newInstance method, of class Graph.
     */
    @org.junit.Test
    public void testNewInstance_int() {
        System.out.println("newInstance");
        int vertices = 5;
        Graph expResult = null;
        Graph result = Graph.newInstance(vertices);
        // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        System.out.print(result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of newInstance method, of class Graph.
     */
    @org.junit.Test
    public void testNewInstance_int_boolean() {
        System.out.println("newInstance");
        int vertices = 10;
        boolean noCycle = true;
        Graph expResult = null;
        Graph result = Graph.newInstance(vertices, noCycle);
        // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        System.out.print(result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumOfVertices method, of class Graph.
     */
    @org.junit.Test
    public void testGetNumOfVertices() {
        System.out.println("getNumOfVertices");
        int vertices = 10000;
        Graph instance = Graph.newInstance(vertices);
        int expResult = vertices;
        int result = instance.getNumOfVertices();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
}
