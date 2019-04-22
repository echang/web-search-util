package com.minihack.httpcrawler.worker.web;

import com.minihack.httpcrawler.worker.jobs.WebPageSearchByTextCaller;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;


@RunWith(MockitoJUnitRunner.class)
public class WebCrawlerTestingControllerTest {

   @InjectMocks
    WebCrawlerTestingController webCrawlerTestingController= new WebCrawlerTestingController();


    @Test
    public void testSearchUrlsByText_Ok() throws Exception {

        URL res = getClass().getClassLoader().getResource("urls.txt");
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath_urls = file.getAbsolutePath();

        res = getClass().getClassLoader().getResource("results.txt");
        file = Paths.get(res.toURI()).toFile();
        String absolutePath_results = file.getAbsolutePath();

        WebPageSearchByTextCaller caller=new WebPageSearchByTextCaller("twitter","twitter.com",absolutePath_results);

        whenNew(WebPageSearchByTextCaller.class).withParameterTypes(String.class,String.class,String.class).withArguments("twitter","twitter.com",absolutePath_results).thenReturn(caller);


        ResponseEntity<Void> response=webCrawlerTestingController.searchUrlsByText("20","twitter",absolutePath_urls,absolutePath_results);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }


    @Test
    public void testSearchUrlsByText_notOk() throws Exception {

        ResponseEntity<Void> response=webCrawlerTestingController.searchUrlsByText("20","twitter","","");

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());

    }
}
