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
 * Presenter for results
 *
 * @author vvuppala
 */
@Named
@ViewScoped
public class ResultPresenter implements Serializable {

    private final static Logger LOGGER = Logger.getLogger(ResultPresenter.class.getCanonicalName());

    private static class Algorithm {

        private final String name;
        private final CycleDetector algorithm;

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

    }

    private Graph graph;
    private List<Algorithm> algorithms = new ArrayList<>();

    // settings
    private int maxIterations = 2;
    private int multiplier = 2;
    private int initialGraphSize = 1000;
    private int repetitions = 1;

    // results
    private List<Result> experimentResults = new ArrayList<>();
    private List<Result> filteredResults;
    
    // parameters to show progress
    private int progress = 0;
    private double realProgress = 0.0;
    private double progressDelta = 0.0;
    private boolean cancelExperiment = false;
    private final static int PROGRESS_MAX = 100;

    @PostConstruct
    public void init() {
        algorithms.add(new Algorithm("Traverse Paths", new TraversePath()));
        algorithms.add(new Algorithm("Discard Deadends", new DiscardDeadends()));

        progressDelta = (double) PROGRESS_MAX / (maxIterations * 2 * repetitions * algorithms.size());
    }

    /**
     * Execute the given algorithm on the given graph a few ('repetitions')
     * times.
     *
     * @param exp - the experiment number
     * @param graph - the graph
     * @param noCycles - was the graph generated without cycles?
     * @param algo - the algorithm to use
     * @return - result of the experiment
     */
    private Result runExperiment(int exp, Graph graph, boolean noCycles, Algorithm algo) {
        long startTime, endTime;
        long elapsedTime = 0L;
        boolean hasCycles = false;
        final double MEGA = 1000000.0;

        for (int i = 0; i < repetitions; i++) {
            if (cancelExperiment) {
                return null;
            }
            realProgress += progressDelta;
            progress += (int) realProgress;

            startTime = System.nanoTime();
            hasCycles = graph.hasCycle(algo.getAlgorithm());
            endTime = System.nanoTime();

            elapsedTime += (endTime - startTime);
            if (hasCycles == noCycles) {
                LOGGER.log(Level.SEVERE, "Invalid results from {0}", algo.getName());
            }
        }

        elapsedTime /= repetitions; // take the average
        Result result = Result.newInstance(exp, algo.getName(), graph.getNumOfVertices(), !noCycles);
        result.setHasCycles(hasCycles);
        result.setExecTime((double)elapsedTime / MEGA);

        return result;
    }

    /**
     * Run the experiments: generate graphs with different sizes and
     * with/without cycles, and then run the various algorithms on the graph.
     *
     */
    public void runExperiments() {
        int size = initialGraphSize;
        int expNumber = 0;
        Result result;

        try {
            experimentResults.clear();
            for (int iteration = 0; iteration < maxIterations; iteration++, size *= multiplier) { // generate graph of different sizes;
                for (int cycle = 0; cycle < 2; cycle++) {  // generate graph with and without cycles
                    boolean noCycles = (cycle == 0); // arbitarily pick to have or not have cycles in the generated graph
                    graph = Graph.newInstance(size, noCycles);
                    for (Algorithm algo : algorithms) {
                        if (cancelExperiment) {
                            return;
                        }
                        result = runExperiment(expNumber++, graph, noCycles, algo);
                        if (result != null) {
                            experimentResults.add(result);
                        }
                    }
                }
            }
            // showMessage(FacesMessage.SEVERITY_INFO, "Exerpiemnts completed", "Check the results");
        } catch (Exception e) {
            showMessage(FacesMessage.SEVERITY_ERROR, "Something awful happened!", e.getMessage());
        } finally {
            progress = PROGRESS_MAX;
        }

    }

    public void onCancel() {
        cancelExperiment = true;
        progress = 0;
    }

    public void onComplete() {
        showMessage(FacesMessage.SEVERITY_INFO, "Exerpiemnts completed", "Check the results!");
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
    public List<Result> getExperimentResults() {
        return experimentResults;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getInitialGraphSize() {
        return initialGraphSize;
    }

    public void setInitialGraphSize(int initialGraphSize) {
        this.initialGraphSize = initialGraphSize;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getProgress() {
        return progress;
    }

    public List<Result> getFilteredResults() {
        return filteredResults;
    }

    public void setFilteredResults(List<Result> filteredResults) {
        this.filteredResults = filteredResults;
    }

}
