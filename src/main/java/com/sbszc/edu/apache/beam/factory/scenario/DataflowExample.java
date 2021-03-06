package com.sbszc.edu.apache.beam.factory.scenario;

import com.sbszc.edu.apache.beam.factory.ScenarioOptions;
import com.sbszc.edu.apache.beam.model.ProdTypePrice;
import com.sbszc.edu.apache.beam.model.Transaction;
import com.sbszc.edu.apache.beam.util.LoggingTransform;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

import java.math.BigDecimal;

public interface DataflowExample {

    static void accept(Pipeline pipeline, Options options) {
        pipeline.apply("Read strings from input file", TextIO.read().from(options.getInputFile()))
                .apply(new LoggingTransform<>())
                .apply("Map string to Transaction", ParDo.of(new DoFn<String, Transaction>() {
                    @ProcessElement
                    public void processElement(@Element String str, OutputReceiver<Transaction> out) {
                        String[] arr = str.split(",");
                        Long id = Long.parseLong(arr[0]);
                        String customerType = arr[1];
                        String productType = arr[2];
                        BigDecimal price = BigDecimal.valueOf(Double.valueOf(arr[3]));
                        out.output(new Transaction(id, customerType, productType, price));
                    }
                }))
                .apply(new LoggingTransform<>())
                .apply("Map Transaction to ProdTypePrice", ParDo.of(new DoFn<Transaction, ProdTypePrice>() {
                    @ProcessElement
                    public void processElement(@Element Transaction transaction, OutputReceiver<ProdTypePrice> out) {
                        out.output(new ProdTypePrice(transaction.getProductType(), transaction.getPrice()));
                    }
                }))
                .apply(new LoggingTransform<>())
                .apply("Map ProdTypePrice to string", ParDo.of(new DoFn<ProdTypePrice, String>() {
                    @ProcessElement
                    public void processElement(@Element ProdTypePrice prodTypePrice, OutputReceiver<String> out) {
                        out.output(String.format("%s,%s", prodTypePrice.getProductType(), prodTypePrice.getPrice()));
                    }
                }))
                .apply(new LoggingTransform<>())
                .apply("Write strings to output file", TextIO.write()
                        .withoutSharding()
                        .to(options.getOutputFile())
                        .withSuffix(options.getOutputFileSuffix()));
    }

    interface Options extends ScenarioOptions {
        @Description("Input file to read from")
        @Validation.Required
        String getInputFile();

        void setInputFile(String inputFile);

        @Description("Output file to write to")
        @Validation.Required
        String getOutputFile();

        void setOutputFile(String outputFile);

        @Description("Output file suffix")
        @Validation.Required
        String getOutputFileSuffix();

        void setOutputFileSuffix(String outputFileSuffix);
    }
}
