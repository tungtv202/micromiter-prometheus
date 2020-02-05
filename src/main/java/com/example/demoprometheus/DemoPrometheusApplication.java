package com.example.demoprometheus;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoPrometheusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoPrometheusApplication.class, args);
    }

    @Bean
    public MeterFilter meterFilter() {
        return new MeterFilter() {
            @Override
            public MeterFilterReply accept(Meter.Id id) {
                /*if(id.getName().startsWith("tomcat.")) {
                    return MeterFilterReply.DENY;
                }
                if(id.getName().startsWith("jvm.")) {
                    return MeterFilterReply.DENY;
                }
                if(id.getName().startsWith("logback.")) {
                    return MeterFilterReply.DENY;
                }
                if(id.getName().startsWith("system.")) {
                    return MeterFilterReply.DENY;
                }
                if(id.getName().startsWith("http.")) {
                    return MeterFilterReply.DENY;
                }*/

                if (!id.getName().startsWith("metric.") && !id.getName().startsWith("process.")) {
                    return MeterFilterReply.DENY;
                }
                return MeterFilterReply.NEUTRAL;
            }
        };
    }

}
