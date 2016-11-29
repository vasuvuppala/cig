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
package com.sainscorp.cig.gui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Result of an experiment.
 * 
 * @author vasu
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {
    private int experiment; // experiment number
        private String algorithmName;
        private int graphSize; // size of randomly generated graph
        private boolean withCycles = false; // was the randomly generated graph created with cycles?
        private boolean hasCycles = false; // does the graph have cycles i.e. result from the algorithm
        private double execTime = 0.0;

        private Result() {

        }

        public static Result newInstance(int exp, String name, int size, boolean withCycles) {
            Result result = new Result();
            result.experiment = exp;
            result.graphSize = size;
            result.withCycles = withCycles;
            result.algorithmName = name;

            return result;
        }

        // getters & setters
        
        public String getAlgorithmName() {
            return algorithmName;
        }

        public int getExperiment() {
            return experiment;
        }

        public int getGraphSize() {
            return graphSize;
        }

        public boolean getWithCycles() {
            return withCycles;
        }
        
        public boolean getHasCycles() {
            return hasCycles;
        }

        public void setHasCycles(boolean result) {
            this.hasCycles = result;
        }

        public double getExecTime() {
            return execTime;
        }

        public void setExecTime(double execTime) {
            this.execTime = execTime;
        }
}
