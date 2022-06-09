package com.sbszc.edu.apache.beam.scenario;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;

import java.util.List;

public interface ScenarioOptions extends PipelineOptions {
    @Description("Scenario to execute")
    @Validation.Required
    String getScenario();

    void setScenario(String scenario);

    interface Scenarios {
        String ParDoExample = "ParDoExample";
        String TransactionsExample = "TransactionsExample";
        String DataflowExample = "DataflowExample";

        static String listScenarios() {
            return List.of(
                            ParDoExample,
                            TransactionsExample,
                            DataflowExample)
                    .toString();
        }
    }
}
