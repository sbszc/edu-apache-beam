package com.sbszc.edu.apache.beam.factory;

import java.util.List;

public interface Scenarios {
    String ParDoExample = "ParDoExample";
    String TransactionsExample = "TransactionsExample";
    String DataflowExample = "DataflowExample";
    String PubSubExample = "PubSubExample";

    static String supportedScenarios() {
        return List.of(
                        ParDoExample,
                        TransactionsExample,
                        DataflowExample,
                        PubSubExample)
                .toString();
    }
}
