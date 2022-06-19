package Game;

import Reader_Writer.Writer;
import javazoom.jl.decoder.JavaLayerException;
import music.BGMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GameStart extends JFrame {
    private Container contain;
    private JLabel labelTitle;
    private JTextArea content;
    private JButton buttonStart, buttonReturn;
    private String userName, year;
    private BGMusic music;
    public GameStart(String userName, String year){
        super("Game Time");
        try {
            music = new BGMusic("music\\happyMusic.mp3");
            music.circularPlay();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
        this.userName = userName;
        this.year = year;
        init();
    }
    public void init() { //開始視窗

        //背景設定
        ImageIcon img = new ImageIcon("img\\backGround.png"); //要設定的背景圖片
        JLabel imgLabel = new JLabel(img); //將背景圖放在標籤裡。
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE)); //將背景標籤新增到jfram的LayeredPane面板裡。
        imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); // 設定背景標籤的位置

        contain = this.getContentPane(); //呼叫getContentPane()方法將表單(JFrame)轉換為容器
        ((JPanel) contain).setOpaque(false); // 將內容面板設為透明。將LayeredPane面板中的背景顯示出來。
        contain.setLayout(null); //排版nono

        labelTitle = new JLabel("Game Tips");
        labelTitle.setForeground(Color.PINK.darker().darker());
        labelTitle.setBounds(500, 80, 500, 100);
        labelTitle.setFont(new Font("KAWAIITEGAKIMOJI", Font.BOLD, 50)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)

        content = new JTextArea("\n\nSelect the correct answer to the question and press attack, \nif the answer is correct, \nyou can attack the opponent！", 100, 500);
        content.setBounds(350, 200, 600, 300);
        content.setLineWrap(true);
        content.setEditable(false);
        content.setForeground(Color.PINK.darker());
        content.setFont(new Font("KAWAIITEGAKIMOJI", Font.BOLD, 30)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)

        buttonStart = new JButton("START");
        buttonStart.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        buttonStart.setBackground(Color.WHITE);
        buttonStart.setForeground(Color.PINK.darker().darker());
        buttonStart.setBounds(480, 550, 150, 50);
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.close();
                dispose();
                String fileName = "game\\" + userName;
                File file = new File(fileName);
                if (!file.exists()) file.mkdirs();// mkdirs建立多級目錄
                fileName = "game\\" + userName + "\\" + year + ".txt";
                file = new File(fileName);
                if(file.exists() && !file.isDirectory()){ //檔案存在
                    Scanner input = null;
                    ArrayList<Questions> questions = new ArrayList<Questions>();
                    try { //讀黨
                        input = new Scanner(Paths.get(fileName));
                    } catch (IOException ioException) {
                        System.err.println("Error opening file. Terminating.");
                        System.exit(1);
                    }
                    while(input.hasNext())
                        questions.add(new Questions(input.nextLine(),  Integer.parseInt(input.nextLine()), input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine()));

                    Collections.sort(questions, new QuestionSort()); //用error次數排序
                    try { //刪除原檔案文件
                        FileWriter fileWriter = new FileWriter(fileName);
                        fileWriter.write("");
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Writer writer = new Writer(fileName);
                    for (int i=0; i<questions.size(); i++) { //從新寫入txt
                        writer.addQuestions(questions.get(i).question);
                        writer.addQuestions(String.valueOf(questions.get(i).error));
                        writer.addQuestions(questions.get(i).option1);
                        writer.addQuestions(questions.get(i).option2);
                        writer.addQuestions(questions.get(i).option3);
                        writer.addQuestions(questions.get(i).option4);
                        writer.addQuestions(questions.get(i).ans);
                    }
                }
                else{ //檔案不存在 複製error都為0的原QA給user
                    copyFile("game\\"+year+".txt", fileName);
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                SecureRandom random = new SecureRandom();

                Role role1 = new Role(userName, 500 + random.nextInt(500), 100 + random.nextInt(156), 20 + random.nextInt(80));
                Role role2 = new Role("English devil", 500 + random.nextInt(500), 100 + random.nextInt(156), 20 + random.nextInt(80));

                GameGUI gui = new GameGUI(role1, role2, fileName);
                gui.setSize(1250, 800); //設定寬，長
                gui.setLocation(140, 15);
                gui.setBackground(Color.pink.darker());
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setVisible(true);
            }
        });

        buttonReturn = new JButton("RETURN");
        buttonReturn.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        buttonReturn.setBackground(Color.WHITE);
        buttonReturn.setForeground(Color.PINK.darker().darker());
        buttonReturn.setBounds(650, 550, 150, 50);
        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.close();
                dispose();
            }
        });

        contain.add(labelTitle);
        contain.add(content);
        contain.add(buttonStart);
        contain.add(buttonReturn);
    }
    public static void copyFile(String sourcePath, String newPath) {
        File start = new File(sourcePath);
        File end = new File(newPath);
        try(BufferedInputStream bis=new BufferedInputStream(new FileInputStream(start));
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(end))) {
            int len = 0;
            byte[] flush = new byte[1024];
            while((len=bis.read(flush)) != -1) {
                bos.write(flush, 0, len);
            }
            bos.flush();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}