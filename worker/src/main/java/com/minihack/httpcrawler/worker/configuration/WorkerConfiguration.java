package com.minihack.httpcrawler.worker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Top configuration bean,
 * import other configuration beans here.
 */
@Configuration
@Import(value = {WorkerComponentConfiguration.class})
public class WorkerConfiguration {
}
