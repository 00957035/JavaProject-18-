package Game;

import Crawler.Crawler;

import java.io.IOException;

public class AddNewQA {
    public AddNewQA(String word, String fileName) throws IOException {
        Crawler crawler = new Crawler();
        crawler.setFileName(fileName);
        //System.out.println(fileName);
        crawler.startCrawler(word);
    }
}
