package Word;

import translate.Translate;

import java.io.IOException;

public class Word {
    private String wordEn;
    private String wordCn;
    private int error;
    private Translate t;
    public Word(String word) throws IOException {
        t = new Translate();
        //writer = new Writer("C:\\JavaProject\\src\\main\\java\\text.txt");
        this.wordEn = word;
        this.wordCn = t.translate("en", "zh_tw", word);
        this.error = 0;
    }
    public Word(String wordEn, String wordCn, int error){
        this.wordEn = wordEn;
        this.wordCn = wordCn;
        this.error = error;
    }

    public Word() {
        this("", "", 0);
    }

    public void setWordEn(String wordEn){this.wordEn = wordEn;}
    public void setWordCn(String wordCn){this.wordCn = wordCn;}
    public void setError(int error){this.error = error;}
    public String getWordEn(){return this.wordEn;}
    public String getWordCn(){return this.wordCn;}
    public int getError(){return this.error;}

    @Override
    public String toString() {
        return "word = " + wordEn + " " + wordCn + " " + error;
    }

}
