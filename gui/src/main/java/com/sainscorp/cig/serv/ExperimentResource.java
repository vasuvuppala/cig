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
package com.sainscorp.cig.serv;

import com.sainscorp.cig.alg.Algorithm;
import com.sainscorp.cig.alg.Graph;
import com.sainscorp.cig.gui.Result;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author  <a href="mailto:vuppala@gmail.com">Vasu Vuppala</a>
 */
@Path("/exp")
@RequestScoped
public class ExperimentResource {

    /**
     * Creates a new instance of ExperimentResource
     */
    public ExperimentResource() {
    }

    /**
     * Perform an experiment and return the details.
     *
     * ToDo: Share code with Result Viewer bean
     *
     * @param size - number of vertices in the generated graph
     * @param cycles - should the generated graph have cycles in it?
     * @return list of experiment results
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Result> getExperiment(@DefaultValue("1000") @QueryParam("size") Integer size,
            @DefaultValue("false") @QueryParam("cycles") Boolean cycles) {

        List<Algorithm> algorithms = Algorithm.currentAlgorithms();
        List<Result> experimentResults = new ArrayList<>();

        int expNumber = 0;
        long startTime, endTime, elapsedTime;
        boolean hasCycles; // result from the experiment
        final double MEGA = 1000000.0;

        Result result;
        Graph graph = Graph.newInstance(size, !cycles);
        for (Algorithm algo : algorithms) {
            startTime = System.nanoTime();
            hasCycles = graph.hasCycle(algo.getAlgorithm());
            endTime = System.nanoTime();

            elapsedTime = (endTime - startTime);
            result = Result.newInstance(expNumber, algo.getName(), graph.getNumOfVertices(), cycles);
            result.setHasCycles(hasCycles);
            result.setExecTime((double) elapsedTime / MEGA);
            experimentResults.add(result);
        }

        return experimentResults;
    }
}
