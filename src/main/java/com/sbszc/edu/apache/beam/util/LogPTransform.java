package com.sbszc.edu.apache.beam.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

@Slf4j
public class LogPTransform<T> extends PTransform<PCollection<T>, PCollection<T>> {
    @Override
    public PCollection<T> expand(PCollection<T> input) {
        return input.apply(ParDo.of(new DoFn<T, T>() {
            @ProcessElement
            public void processElement(@Element T element, OutputReceiver<T> out) {
                log.info(element.toString());
                out.output(element);
            }
        }));
    }
}
