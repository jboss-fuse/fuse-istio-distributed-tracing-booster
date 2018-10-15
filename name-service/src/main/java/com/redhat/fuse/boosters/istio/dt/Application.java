package com.redhat.fuse.boosters.istio.dt;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.reporters.RemoteReporter;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.jaegertracing.spi.Reporter;
import io.jaegertracing.thrift.internal.senders.HttpSender;
import org.apache.camel.opentracing.starter.CamelOpenTracing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

@CamelOpenTracing
@SpringBootApplication
public class Application {

    @Value("${jaeger.reporter.endpoint}")
    private String jagerReporterEndpoint;

    @Bean
    public io.opentracing.Tracer tracer() {
        final JaegerTracer.Builder builder = new JaegerTracer.Builder("name-service");
        builder.withSampler(new ConstSampler(true));

        if(!StringUtils.isEmpty(jagerReporterEndpoint)){
            RemoteReporter.Builder rBuilder = new RemoteReporter.Builder();
            rBuilder.withSender(new HttpSender.Builder(jagerReporterEndpoint).build());
            builder.withReporter(rBuilder.build());
        }

        return builder.build();
    }

    /**
     * Main method to start the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}