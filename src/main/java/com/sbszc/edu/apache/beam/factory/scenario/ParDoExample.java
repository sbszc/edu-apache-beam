package com.sbszc.edu.apache.beam.factory.scenario;

import com.sbszc.edu.apache.beam.model.BankAccount;
import com.sbszc.edu.apache.beam.util.LoggingTransform;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

import java.math.BigDecimal;
import java.util.Arrays;

public interface ParDoExample {

    static void accept(Pipeline pipeline) {
        pipeline.apply("Create BankAccounts from in-memory data", Create.of(Arrays.asList(
                        new BankAccount(1, "owner 1", BigDecimal.valueOf(100.0)),
                        new BankAccount(2, "owner 1", BigDecimal.valueOf(200.0)),
                        new BankAccount(3, "owner 2", BigDecimal.valueOf(300.0)),
                        new BankAccount(4, "owner 3", BigDecimal.valueOf(400.0))))
                )
                .apply(new LoggingTransform<>())
                .apply("Map BankAccount to string", ParDo.of(new DoFn<BankAccount, String>() {
                    @ProcessElement
                    public void processElement(@Element BankAccount account, OutputReceiver<String> out) {
                        out.output(account.toString());
                    }
                }))
                .apply(new LoggingTransform<>());
    }
}
