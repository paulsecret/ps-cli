package com.ps.api.blueprint

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.slf4j.MDC
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.ComponentScan

@Slf4j
@CompileStatic
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration)
@ComponentScan
class Application {

    static void main(final String[] args) {
        setSlice()
        new SpringApplicationBuilder(Application.class).run(args);
    }

    /**
     * Sets bluegreen slice base on JVM property
     */
    private static void setSlice() {
        String slice = System.getProperty("bluegreen.slice")
        if (slice != null) {
            MDC.put("bluegreen.slice", slice)
        }
    }
}
