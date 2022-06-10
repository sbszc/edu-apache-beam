package com.sbszc.edu.apache.beam.factory.scenario;

import com.sbszc.edu.apache.beam.factory.ScenarioOptions;
import com.sbszc.edu.apache.beam.util.LoggingTransform;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;

public interface PubSubExample {

    static void accept(Pipeline pipeline, Options options) {
        pipeline.apply("Read from subscription", PubsubIO.readStrings().fromSubscription(options.getSubscription()))
                .apply(new LoggingTransform<>());
    }

    interface Options extends ScenarioOptions {
        @Description("PubSub subscription")
        @Validation.Required
        String getSubscription();

        void setSubscription(String subscription);
    }
}
