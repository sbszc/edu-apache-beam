package com.sbszc.edu.apache.beam.factory;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;

public interface ScenarioOptions extends PipelineOptions {
    @Description("Scenario to execute")
    @Validation.Required
    String getScenario();

    void setScenario(String scenario);
}
