package com.sbszc.edu.apache.beam;

import com.sbszc.edu.apache.beam.exception.UnsupportedScenarioException;
import com.sbszc.edu.apache.beam.scenario.*;
import com.sbszc.edu.apache.beam.scenario.ScenarioOptions.Scenarios;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

public class Main {
    public static void main(String[] args) {
        PipelineOptionsFactory.register(ScenarioOptions.class);
        PipelineOptionsFactory.register(TransactionsExample.Options.class);
        PipelineOptionsFactory.register(DataflowExample.Options.class);
        PipelineOptionsFactory.register(PubSubExample.Options.class);

        ScenarioOptions scenarioOptions = PipelineOptionsFactory
                .fromArgs(args)
                .withValidation()
                .as(ScenarioOptions.class);

        Pipeline pipeline;

        switch (scenarioOptions.getScenario()) {
            case Scenarios.ParDoExample:
                pipeline = Pipeline.create();

                ParDoExample.accept(pipeline);
                break;
            case Scenarios.TransactionsExample:
                TransactionsExample.Options transactionsExampleOptions = PipelineOptionsFactory
                        .fromArgs(args)
                        .withValidation()
                        .as(TransactionsExample.Options.class);

                pipeline = Pipeline.create(transactionsExampleOptions);

                TransactionsExample.accept(pipeline, transactionsExampleOptions);
                break;
            case Scenarios.DataflowExample:
                DataflowExample.Options dataflowExampleOptions = PipelineOptionsFactory
                        .fromArgs(args)
                        .withValidation()
                        .as(DataflowExample.Options.class);

                pipeline = Pipeline.create(dataflowExampleOptions);

                DataflowExample.accept(pipeline, dataflowExampleOptions);
                break;
            case Scenarios.PubSubExample:
                PubSubExample.Options pubSubExampleOptions = PipelineOptionsFactory
                        .fromArgs(args)
                        .withValidation()
                        .as(PubSubExample.Options.class);

                pipeline = Pipeline.create(pubSubExampleOptions);

                PubSubExample.accept(pipeline, pubSubExampleOptions);
                break;
            default:
                throw new UnsupportedScenarioException(
                        String.format("Scenario '%s' not supported. Supported scenarios: %s",
                                scenarioOptions.getScenario(),
                                Scenarios.listScenarios()));
        }

        pipeline.run().waitUntilFinish();
    }
}

