package com.minihack.httpcrawler.worker.web;


import com.minihack.httpcrawler.worker.jobs.WebPageSearchByTextCaller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;


@Api(description = "Api endpoint for searching against all urls")
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Process error, error message should be sent in the response message.")
})
@Controller
@RequestMapping("/search/testing")
public class WebCrawlerTestingController {

    public WebCrawlerTestingController() throws Exception
    {
    }

    @ApiOperation(value = "search the urls by input text",
            nickname = "searchUrlsByText")
    @RequestMapping(path = "/searchByText", method = RequestMethod.GET, params = {"numThreads","text"})
    public ResponseEntity<Void> searchUrlsByText(@RequestParam(required = true, value = "numThreads",defaultValue = "20") String numThreads,@RequestParam(required = true, value = "text") String text) {

        try {

            String inputFilePath = null;
            String outputFilePath = null;


            Properties mainProperties = new Properties();
            FileInputStream file;

            //the base folder is ./, the root of the application.properties file
            String path = "./application.properties";

            //load the file handle for application.properties
            file = new FileInputStream(path);
            //load all the properties from this file
            mainProperties.load(file);
            file.close();

            inputFilePath = mainProperties.getProperty("crawler.input.local.path");
            outputFilePath = mainProperties.getProperty("crawler.output.local.path");

            
            List<String> lines = FileUtils.readLines(new File(inputFilePath), "utf-8");
            //Need to take split with ",", take the second items after line 1
            lines.remove(0);
            Queue<String> queue = new LinkedList<>(lines);

            while (queue.size() > 0){
                List<Thread> ts = new ArrayList<Thread>();

                for(int i = 0 ; i < Integer.parseInt(numThreads) ; i++) {
                    String url = queue.poll().split(",")[1];
                    Thread t = new Thread(new WebPageSearchByTextCaller(text,url,outputFilePath));
                    t.start();
                    ts.add(t);
                    if(queue.size() ==0)
                        break;
                }

                for(int i = 0; i < ts.size(); i++)
                    ts.get(i).join();
            }

            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
