package com.example.demoprometheus;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BatchJobMetric implements CommandLineRunner {

    private static int metric = 0;
    @Autowired
    private MetricsManager metricsManager;

    @Autowired private SampleStore sampleStore;

    @Override
    public void run(String... args) throws Exception {
        while (true){
            sampleStore.set(Timer.start(Clock.SYSTEM));
            int inc = new Random().nextInt(10);
            metricsManager.trackCounterMetrics("metric.count", inc, "sampleAttr", "Attr1");
            metricsManager.trackTimerMetrics("metric.transaction", "sampleAttr", "Attr2");
            Thread.sleep(1000);
        }

    }
}
