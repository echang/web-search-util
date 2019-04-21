package com.minihack.httpcrawler.worker;

import com.minihack.httpcrawler.worker.configuration.WorkerConfiguration;
import com.minihack.httpcrawler.worker.configuration.WorkerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(value = {WorkerConfiguration.class})
@SpringBootApplication
public class WorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkerApplication.class, args);
    }

}
