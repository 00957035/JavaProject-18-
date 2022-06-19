package frame;


import Crawler.Crawler;
import Game.GameGUI;
import javazoom.jl.decoder.JavaLayerException;
import music.BGMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;


public class FrameStart extends JFrame{
    private String userName;
    private final JLabel labelTitle = new JLabel("~英文單字抱佛腳~");
    private final JLabel labelUserName = new JLabel("輸入User姓名：");
    private final JLabel labelTestYear = new JLabel("英文考古：");
    private final JTextField textUserName = new JTextField("Anna");
    private final Button btnNext = new Button("next");;
    private JComboBox<String> jComboBox;
    private  String fileName;
    private ImageIcon role;
    private int role_x = 0;
    private Timer timer;
    private Container contain;
    private ImagePanel ip;
    public FrameStart(){
        super("Test");
        init();
    }
    public void init(){ //開始視窗
        //背景設定
        ImageIcon img = new ImageIcon("img\\start.png"); //要設定的背景圖片
        JLabel imgLabel = new JLabel(img); //將背景圖放在標籤裡。
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE)); //將背景標籤新增到jfram的LayeredPane面板裡。
        imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); // 設定背景標籤的位置

        contain = this.getContentPane(); //呼叫getContentPane()方法將表單(JFrame)轉換為容器
        ((JPanel) contain).setOpaque(false); // 將內容面板設為透明。將LayeredPane面板中的背景顯示出來。
        contain.setLayout(null); //排版nono
        //contain.setLayout(new GridLayout(5,1,5,5)); //設定容器使用網路佈局管理器，設定7行3列的網格，行間距為5，列間距為5

        labelTitle.setForeground(Color.PINK.darker().darker());
        labelTitle.setBounds(430, 110, 500, 100);
        labelTitle.setFont(new Font("標楷體", Font.BOLD, 50)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        //labelTitle.setHorizontalAlignment(SwingConstants.CENTER); //使標籤上的文字居中
        //contain.setBackground(Color.GRAY);  //設定容器的背景顏色

        labelUserName.setBounds(270, 400, 500, 100);
        labelUserName.setForeground(Color.pink.darker().darker());
        labelUserName.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        labelUserName.setHorizontalAlignment(SwingConstants.CENTER); //使標籤上的文字居中

        labelTestYear.setBounds(250, 450, 500, 100);
        labelTestYear.setForeground(Color.pink.darker().darker());
        labelTestYear.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        labelTestYear.setHorizontalAlignment(SwingConstants.CENTER); //使標籤上的文字居中

        JLabel labelText = new JLabel();
        labelText.setBounds(840, 439, 230, 28);
        labelText.setForeground(Color.pink);
        labelText.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN,18));
        textUserName.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN,20));
        textUserName.setForeground(Color.PINK.darker());
        textUserName.setBounds(600, 439, 230, 28);
        textUserName.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent arg0){ // 正在輸入訊息時
                labelText.setText("Is typing ...");
            }
            @Override
            public void focusLost(FocusEvent e) {
                labelText.setText("");
            }
        });

        String[] optionsToChoose = {"109year", "108year", "107year", "106year", "105year", "104year", "103year", "102year", "101year", "100year", "99year", "98year", "97year", "96year"};
        jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN,20));
        jComboBox.setBackground(Color.WHITE);
        jComboBox.setForeground(Color.PINK.darker());
        jComboBox.setBounds(560, 489, 280, 28);

        role = new ImageIcon("img\\wait.png");
        ip = new ImagePanel();
        ip.setBounds(0, 600, 1250, 100);
        ip.setVisible(false);
        contain.add(ip);

        btnNext.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        btnNext.setBackground(Color.WHITE);
        btnNext.setForeground(Color.PINK.darker().darker());
        btnNext.setBounds(580, 550, 90, 28);
        btnNext.addActionListener(new ActionListener() {
            // 覆写actionPerformed方法
            @Override
            public void actionPerformed(ActionEvent e) {
                Icon icon;
                boolean check = true;
                btnNext.setBackground(Color.lightGray);
                userName = textUserName.getText();
                String year = jComboBox.getItemAt(jComboBox.getSelectedIndex());
                if(getUserName().equals("")){
                    icon = new ImageIcon("img\\v.png");
                    JOptionPane.showMessageDialog(FrameStart.this, String.format("User's name cannot be vacancy~"), "Error", JOptionPane.PLAIN_MESSAGE, icon);
                    return;
                }

                fileName = "user\\" + userName;
                File file = new File(fileName);
                icon = new ImageIcon("img\\w.png");
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(FrameStart.this, "Welcome " + userName + " to join for the first time", "Welcome", JOptionPane.PLAIN_MESSAGE, icon);
                    file.mkdirs();// mkdirs建立多級目錄
                }
                else JOptionPane.showMessageDialog(FrameStart.this, "Welcome " + userName + " back again！！！", "Welcome", JOptionPane.PLAIN_MESSAGE, icon);

                fileName = "user\\" + userName + "\\" + year + ".txt";
                //System.out.println(fileName);
                file = new File(fileName);
                icon = new ImageIcon("img\\r.png");
                if(file.exists() && !file.isDirectory()) JOptionPane.showMessageDialog(FrameStart.this, "Review again " + year, "Start", JOptionPane.PLAIN_MESSAGE, icon);
                else{
                    icon = new ImageIcon("img\\f.png");
                    check = false;
                    JOptionPane.showMessageDialog(FrameStart.this, "It's first time to read this unit.\nPlease wait for loading words！", "Wait", JOptionPane.PLAIN_MESSAGE, icon);
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(check) nextPage(year);
                else if(year.equals("106year")){
                    File start = new File("user\\106year.txt");
                    File end = new File(fileName);
                    try(BufferedInputStream bis=new BufferedInputStream(new FileInputStream(start));
                        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(end))) {
                        int len = 0;
                        byte[] flush = new byte[1024];
                        while((len=bis.read(flush)) != -1) {
                            bos.write(flush, 0, len);
                        }
                        bos.flush();
                    } catch(FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch(IOException ioException) {
                        ioException.printStackTrace();
                    }
                    nextPage(year);
                }
                else {
                    Crawler crawler = new Crawler();
                    crawler.setFileName(fileName);
                    crawler.setYear(jComboBox.getSelectedIndex());
                    boolean isFinish=false;

                    Thread thread = new Thread(new Runnable(){
                        @Override
                        public void run() {
                            try {
                                crawler.startCrawler();
                                nextPage(year);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    thread.start();
                    ip.setVisible(true);
                    revalidate();
                    timer = new Timer();
                    timer.schedule(new timerTask1(), 1000, 10);
                }
            }
        });

        //將需要將元件(label)新增至容器
        contain.add(labelTitle);
        contain.add(labelUserName);
        contain.add(textUserName);
        contain.add(labelTestYear);
        contain.add(jComboBox);
        contain.add(btnNext);
        contain.add(labelText);
    }
    public String getUserName(){ return userName;}
    public String getFileName(){ return fileName;}
    public void nextPage(String year){
        dispose(); //關閉此頁面
        FramePage1 framePage1 = new FramePage1(getUserName(), getFileName(), year);
        framePage1.setSize(1250, 800);   //設定寬，長
        framePage1.setLocation(140, 15);
        framePage1.setBackground(Color.pink.darker());
        framePage1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePage1.setVisible(true);
    }
    class ImagePanel extends JPanel{
        public ImagePanel(){
            this.setBackground(null);
            this.setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(role.getImage(), role_x, 0, null);
        }
    }
    class timerTask1 extends TimerTask {
        @Override
        public void run(){
            if(role_x < 800) role_x++;
            else{
                timer.cancel();
                timer = new Timer();
                timer.schedule(new timerTask2(), 1000, 10);
            }
            //System.out.println(role_x);
            repaint();
        }
    }
    class timerTask2 extends TimerTask {
        @Override
        public void run(){
            if(role_x >= 0) role_x--;
            else{
                timer.cancel();
                timer = new Timer();
                timer.schedule(new timerTask1(), 1000, 10);
            }
            repaint();
        }
    }
}

