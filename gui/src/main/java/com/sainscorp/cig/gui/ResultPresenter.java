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
package com.sainscorp.cig.gui;

import com.sainscorp.cig.alg.CycleDetector;
import com.sainscorp.cig.alg.DiscardDeadends;
import com.sainscorp.cig.alg.Graph;
import com.sainscorp.cig.alg.TraversePath;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author vvuppala
 */
@Named
@ViewScoped
public class ResultPresenter implements Serializable {
    private final static Logger LOGGER = Logger.getLogger(ResultPresenter.class.getCanonicalName());
    
    public static class Result {
        private String algorithmName;
        private CycleDetector algorithm;
        private boolean cycleFound = false;
        private double execTime = 0.0;
        
        private Result() {
            
        }
        
        public static Result newInstance(String name, CycleDetector algo) {
            Result result = new Result();
            result.algorithm = algo;
            result.algorithmName = name;
            
            return result;
        }
        
        // getters

        public String getAlgorithmName() {
            return algorithmName;
        }

        public CycleDetector getAlgorithm() {
            return algorithm;
        }

        public double getExecTime() {
            return execTime;
        }

        public void setExecTime(double execTime) {
            this.execTime = execTime;
        }

        public boolean isCycleFound() {
            return cycleFound;
        }

        public void setCycleFound(boolean cycleFound) {
            this.cycleFound = cycleFound;
        }
        
    }
    
    
    private Graph graph;
    private List<Result> resultList;
    // inputs
    private int inputVertices = 1000;
    private boolean inputCycleChoice = false;
    
    // results
    private boolean result;
    
    @PostConstruct
    public void init() {
        resultList = new ArrayList<>();
        
        resultList.add(Result.newInstance("Traverse Paths", new TraversePath()));
        resultList.add(Result.newInstance("Discard Deadends", new DiscardDeadends()));
    }
    
    public void generateGraph() {
        try {
            graph = Graph.newInstance(inputVertices, inputCycleChoice);
            showMessage(FacesMessage.SEVERITY_INFO, "Graph generated", "Phew!");
        } catch (Exception e) {
            showMessage(FacesMessage.SEVERITY_FATAL, "Could not create graph", e.getMessage());
            LOGGER.log(Level.SEVERE, "Could not create greaph");
        }
    }
    
    public void findCycle() {
        result = graph.hasCycle(new TraversePath());
        //result = graph.hasCycle(new DiscardDeadends());
    }
    
    /**
     * Display a message on the browser
     * 
     * @param severity
     * @param summary
     * @param message 
     */
    public static void showMessage(FacesMessage.Severity severity, String summary, String message) {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(severity, summary, message));
    }
    // getters and setters

    public int getInputVertices() {
        return inputVertices;
    }

    public void setInputVertices(int inputVertices) {
        this.inputVertices = inputVertices;
    }

    public boolean isInputCycleChoice() {
        return inputCycleChoice;
    }

    public void setInputCycleChoice(boolean inputCycleChoice) {
        this.inputCycleChoice = inputCycleChoice;
    }

    public boolean isResult() {
        return result;
    }
    
}
