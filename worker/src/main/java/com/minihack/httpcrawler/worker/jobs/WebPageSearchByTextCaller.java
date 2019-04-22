package com.minihack.httpcrawler.worker.jobs;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class WebPageSearchByTextCaller implements Runnable {

    String url;
    String outputFilePath;
    String text;
    FileWriter writer;


    public WebPageSearchByTextCaller(String text, String url,String outputFilePath)
    {
        this.url = url;
        this.outputFilePath = outputFilePath;
        this.text = text;

    }

    public void run() {

        try {
            // load the response
            // check if it contains the search text, if so, write it into the output file
            Document doc = Jsoup.connect(String.format("https://%s", url.replaceAll("^\"|\"$", ""))).get();

            if(doc.body().text().toLowerCase().contains(text.toLowerCase())){
                writer = new FileWriter(new File(outputFilePath), true);
                writer.write(url);
                writer.write(System.lineSeparator());
                writer.flush();
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

