package Crawler;

import Reader_Writer.Writer;
import Word.Word;
import javazoom.jl.decoder.JavaLayerException;
import music.BGMusic;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Crawler {
    private String[] url ={
            "https://docs.google.com/forms/d/e/1FAIpQLSdnM9AsiDOMw0aW0fbg96sh-PzX_nPCiGXMTgVuyR56cRvD_A/viewform", //109
            "https://docs.google.com/forms/d/e/1FAIpQLScwnMFMWb8330tAurZj5bYUIq9ZWEJ89-WXnrwww0E9Q4k8ZA/viewform", //108
            "https://docs.google.com/forms/d/e/1FAIpQLScx5J-3KxMURlmYqsdZpwBlF3eU9ylN45kYCGhO3y-lrGkMzw/viewform", //107
            "https://docs.google.com/forms/d/e/1FAIpQLScqsEBob2oa90F3p3y85q75cBM6ttXgTNp1IgRJEjpaTRe6jg/viewform", //106
            "https://docs.google.com/forms/d/e/1FAIpQLSfyExLHWh6C2coSX14k487bUsalyyRKpShuJUruB1OkGfgOMQ/viewform", //105
            "https://docs.google.com/forms/d/e/1FAIpQLSdLkeBPW60M7k4IZoqGAD03b8exk5yGIaHeZ-QH5K9sz3SaBQ/viewform", //104
            "https://docs.google.com/forms/d/e/1FAIpQLSfY62d3LdhQrwCO7BK3YWTYm6UouXKkyRUlmpodwY35RlibIw/viewform", //103
            "https://docs.google.com/forms/d/e/1FAIpQLSeauMED2qmcAb5kwmKAS79Bo5IbfAplUc5Wf8RcFNOguIYWTQ/viewform", //102
            "https://docs.google.com/forms/d/e/1FAIpQLSdVjfNentkGixPqf2ocJ01Rpe1pIeWvkXde-kC8wWJA0v31Ow/viewform", //101
            "https://docs.google.com/forms/d/e/1FAIpQLSep6to_k9mzqrqqhfiU_EQHtiq-Wa2eK4Vgp825vCWKPMWSow/viewform", //100
            "https://docs.google.com/forms/d/e/1FAIpQLSc_W2A2i2w_DfV2iL9gzZoNhRmV5wueFz18kR4LBgIgt04fjA/viewform", //99
            "https://docs.google.com/forms/d/e/1FAIpQLSct96sIe5fHX4S-v5WNl36NpQoTZ069scgT3z1g1gt-Nu9BUw/viewform", //98
            "https://docs.google.com/forms/d/e/1FAIpQLSeaf2riVxadTM1NYIOOni-st8dL9WnLnkmAPX-p4t-KW-K00A/viewform", //97
            "https://docs.google.com/forms/d/e/1FAIpQLSfDnAz6CiBNNqULqfJeIX502dR1wrJz4AxZUEdv9OORaFPVoQ/viewform"  //96
    };
    private Word w;
    private Writer writer;
    private String fileName;
    private int year;
    private BGMusic music;
    public Crawler() {   }
    public void startCrawler(String word) throws IOException {
        writer = new Writer(getFileName());
        try{
            Document doc = Jsoup.connect("http://dict.cn/big5/"+word).get();
            Elements items = doc.getElementsByClass("layout sort");
            Element titleEle = items.select("li").get(0);
            String question = titleEle.text(); //取德例句
            question = question.substring(0, question.indexOf(".")); //刪除中文例句
            question = question.replace(word, "____"); //把關鍵字取代
            question += "\n0";

            Random random = new Random(); //產生Random物件
            List<Integer> randomList = new ArrayList<>();
            items = doc.getElementsByClass("layout nwd"); //抓相似單字
            while(randomList.size()<=3){ //總共4組
                int n = random.nextInt(4); //產生0~3數字
                if(randomList.contains(n)) continue;     //重複的不加入
                else{
                    randomList.add(n);
                    if(n==3) question += ("\n" + word);
                    else question += ("\n" + items.select("a").get(n).text());
                }
            }
            question += ("\n" + word);
            System.out.println(question);
            writer.addQuestions(question);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException){
            System.err.println("no find QA！！");
        }

    }

    public void startCrawler() throws IOException{
        try {
            music = new BGMusic("music\\startMusic.mp3");
            music.circularPlay();
        } catch (FileNotFoundException | JavaLayerException exception) {
            exception.printStackTrace();
        }
        writer = new Writer(getFileName());
        Document doc = Jsoup.connect(url[getYear()]).get();
        Elements items = doc.getElementsByClass("YEVVod");
        for (Element item : items) {
            String word = item.getElementsByClass("ulDsOb").get(0).getElementsByTag("span").get(0).text();
            w = new Word(word);
            addToText(w);
            //w.addToText();
            System.out.println(w.toString());
            //System.out.print(w.toString());
        }
        System.out.println("enddddddddd");
        music.close();
    }
    public void setYear(int year){ this.year = year; }
    public int getYear(){ return year;}
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getFileName(){ return fileName; }
    public void addToText(Word w){ writer.addWord(w.getWordEn(), w.getWordCn(), 0); }
}
