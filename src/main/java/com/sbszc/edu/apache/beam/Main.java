package com.sbszc.edu.apache.beam;

import com.sbszc.edu.apache.beam.factory.PipelineFactory;

public class Main {
    public static void main(String[] args) {
        PipelineFactory.getPipeline(args)
                .run()
                .waitUntilFinish();
    }
}
