package com.minihack.httpcrawler.worker.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


/**
 * Create singleton beans here.
 * Access to application properties using environment class.
 */
@ComponentScan(
basePackages = {
})
@Configuration
public class WorkerComponentConfiguration {

    @Autowired
    Environment environment;

}
