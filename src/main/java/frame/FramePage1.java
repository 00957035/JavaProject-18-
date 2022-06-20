package frame;

import Game.AddNewQA;
import Game.GameGUI;
import Game.GameStart;
import Game.Role;
import Reader_Writer.Reader;
import Reader_Writer.Writer;
import Work.ChangeTxt;
import Work.InputTranslateWord;
import Work.Shuffle;
import Work.VisitWebsite;
import translate.Translate;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.SecureRandom;

public class FramePage1 extends JFrame {
    private static String fileName;
    private String userName, year;
    private JLabel labelUser;
    private Container contain;
    private final JButton btnTranslate=new JButton("Translate"), btnHideEn=new JButton("Hide EN"),
            btnShowEn=new JButton("Show EN"), btnCheck=new JButton("Test & Check"), btnShuffle = new JButton("Shuffle"),
            btnReturn=new JButton("Return"), btnArchaeology=new JButton("Archaeology"), btnGame=new JButton("Game");
    private JScrollPane scrollWordTable;
    private JTable wordTable;
    private String[] title;
    private int isHide = 0;
    private String [][] wordData1, wordData2, wordData3;
    private Writer writer;
    private Reader reader;
    public FramePage1(String userName, String fileName, String year){
        super("單字內頁");
        writer = new Writer(fileName);
        reader = new Reader(fileName);
        this.userName = userName;
        this.year = year;
        this.fileName = fileName;
        setPage();
    }
    public void setPage(){
        //背景設定
        ImageIcon img = new ImageIcon("img\\page01.png"); //要設定的背景圖片
        JLabel imgLabel = new JLabel(img); //將背景圖放在標籤裡。
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE)); //將背景標籤新增到jfram的LayeredPane面板裡。
        imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); // 設定背景標籤的位置

        contain = this.getContentPane(); //呼叫getContentPane()方法將表單(JFrame)轉換為容器
        ((JPanel) contain).setOpaque(false); // 將內容面板設為透明。將LayeredPane面板中的背景顯示出來。
        contain.setLayout(null); //排版nono

        labelUser = new JLabel("Hello " + userName);
        labelUser.setForeground(Color.PINK.darker().darker());
        labelUser.setBounds(75, 95, 200, 100);
        labelUser.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 30)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)

        myEventListner header = new myEventListner();

        btnGame.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        btnGame.setBackground(Color.WHITE);
        btnGame.setForeground(Color.PINK.darker());
        btnGame.setBounds(60, 420, 200, 28);
        btnGame.addActionListener(header);

        btnArchaeology.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        btnArchaeology.setBackground(Color.WHITE);
        btnArchaeology.setForeground(Color.PINK.darker());
        btnArchaeology.setBounds(60, 460, 200, 28);
        btnArchaeology.addActionListener(header);

        btnTranslate.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        btnTranslate.setBackground(Color.WHITE);
        btnTranslate.setForeground(Color.PINK.darker());
        btnTranslate.setBounds(60, 500, 200, 28);
        btnTranslate.addActionListener(header);

        btnHideEn.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        btnHideEn.setBackground(Color.WHITE);
        btnHideEn.setForeground(Color.PINK.darker());
        btnHideEn.setBounds(60, 540, 200, 28);
        btnHideEn.addActionListener(header);

        btnCheck.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        btnCheck.setBackground(Color.WHITE);
        btnCheck.setForeground(Color.PINK.darker());
        btnCheck.setBounds(60, 580, 200, 28);
        btnCheck.addActionListener(header);

        btnShowEn.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        btnShowEn.setBackground(Color.WHITE);
        btnShowEn.setForeground(Color.PINK.darker());
        btnShowEn.setBounds(60, 620, 200, 28);
        btnShowEn.addActionListener(header);

        btnShuffle.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        btnShuffle.setBackground(Color.WHITE);
        btnShuffle.setForeground(Color.PINK.darker());
        btnShuffle.setBounds(60, 660, 200, 28);
        btnShuffle.addActionListener(header);

        btnReturn.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        btnReturn.setBackground(Color.WHITE);
        btnReturn.setForeground(Color.PINK.darker());
        btnReturn.setBounds(60, 700, 200, 28);
        btnReturn.addActionListener(header);

        contain.add(labelUser);
        contain.add(btnGame);
        contain.add(btnArchaeology);
        contain.add(btnTranslate);
        contain.add(btnReturn);
        contain.add(btnHideEn);
        contain.add(btnCheck);
        contain.add(btnShowEn);
        contain.add(btnShuffle);

        fillData();
        setTable();
    }
    public void setTable(){
        TableModel model;
        if(isHide == 1){ //isHide == true 隱藏英文
            model = new DefaultTableModel(wordData2, title){
                public Class getColumnClass(int column){
                    Class returnValue;
                    if ((column >= 0) && (column < getColumnCount())) returnValue = getValueAt(0, column).getClass();
                    else returnValue = Object.class;
                    return returnValue;
                }
            };
        }
        else if(isHide == 0){ //isHide == false 顯示英文
            model = new DefaultTableModel(wordData1, title){
                public Class getColumnClass(int column){
                    Class returnValue;
                    if ((column >= 0) && (column < getColumnCount())) returnValue = getValueAt(0, column).getClass();
                    else returnValue = Object.class;
                    return returnValue;
                }
            };
        }
        else{
            model = new DefaultTableModel(wordData3, title){
                public Class getColumnClass(int column){
                    Class returnValue;
                    if ((column >= 0) && (column < getColumnCount())) returnValue = getValueAt(0, column).getClass();
                    else returnValue = Object.class;
                    return returnValue;
                }
            };
        }
        wordTable = new JTable(model); //表格
        wordTable.setAutoCreateRowSorter(true); //設定列表可排序
        JTableHeader head = wordTable.getTableHeader(); // 創建表格標題對象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 設置表頭大小
        head.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20));// 設置表格字體
        head.setBackground(new Color(232, 232, 232));
        head.setForeground(Color.pink.darker().darker());
        wordTable.setFont(new Font("標楷體", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        wordTable.setForeground(Color.pink.darker().darker()); //設定內容顏色
        wordTable.setGridColor(Color.pink.darker());
        wordTable.setSelectionBackground(new Color(248, 222, 141, 255)); // 設定被選中的行背景

        //wordTable.setShowHorizontalLines(false); //不顯示列橫線
        wordTable.setShowVerticalLines(false); //不顯示列直線
        wordTable.setRowHeight(40);
        scrollWordTable = new JScrollPane(wordTable);
        wordTable.setFillsViewportHeight(true);
        scrollWordTable.setBounds(319, 0, 931, 770);
        setTableColor();
        //this.contain.add(scrollWordTable);
        add(scrollWordTable);
    }

    public void fillData() {
        writer = new Writer(fileName);
        reader = new Reader(fileName);
        wordData1 = reader.readAllWords(0);
        wordData2 = reader.readAllWords(1);
        wordData3 = reader.readAllWords(2);
        if(isHide==0) title = new String[]{"English", "Chinese", "Error"};
        else title = new String[]{"English", "test", "Chinese", "Error"};
    }
    public void updateTable() {
        remove(scrollWordTable);
        fillData();
        setTable();
        SwingUtilities.updateComponentTreeUI(this);
    }
    public void setIsHide(int check){
        this.isHide = check;
    }
    public void checkTable() throws IOException {
        ChangeTxt changeTxt =new ChangeTxt(fileName);
        String testWord, word, str;
        int errorTime = 0;
        for(int i = 0; i < wordTable.getRowCount(); i++) wordData3[i][1] = wordTable.getValueAt(i, 1).toString();
        isHide = 2;
        remove(scrollWordTable);
        setTable();
        SwingUtilities.updateComponentTreeUI(this);
        for(int i = 0; i < wordTable.getRowCount(); i++) {
            word = wordData1[i][0];
            testWord = wordTable.getValueAt(i, 1).toString();
            //System.out.println("check = " + word + " " + testWord);
            if(!word.equals(testWord)) {
                //str = (String)wordTable.getValueAt(i, 3);
                errorTime = Integer.parseInt(wordData1[i][2]) + 1;
                //wordTable.setValueAt(errorTime+"", i, 3);
                //changeErrorTime(findWordAddress(word), ""+errorTime); //更新txt黨
                changeTxt.readLineVarFileWord(word, ""+errorTime); //更新txt黨
                wordTable.setValueAt("false", i, 3);
            }
        }
        changeColor();
    }
    public void setTableColor(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if(row%2 == 0) setBackground(new Color(255,250,250)); //基數行顏色255 250 250
                else if(row%2 == 1) setBackground(new Color(242, 240, 248, 255));  //偶數行顏色238 224 229
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        for (String s : title) wordTable.getColumn(s).setCellRenderer(tcr);
    }
    private void changeColor() {//改false的顏色
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                //boolean selected = get
                if(table.getValueAt(row, column).equals("false") && row%2==0){
                    setBackground(new Color(255,250,250));
                    setForeground(Color.RED);
                }
                else if(table.getValueAt(row, column).equals("false") && row%2==1){
                    setBackground(new Color(242, 240, 248, 255));
                    setForeground(Color.RED);
                }
                else if(row%2 == 0){
                    setForeground(Color.pink.darker().darker());
                    setBackground(new Color(255,250,250)); //基數行顏色255 250 250
                }
                else if(row%2 == 1){
                    setForeground(Color.pink.darker().darker());
                    setBackground(new Color(242, 240, 248, 255));  //偶數行顏色238 224 229
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        for (String s : title) wordTable.getColumn(s).setCellRenderer(tcr);
    }
    class myEventListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { // 按鈕被點選後
            String actionCommand = e.getActionCommand();// 獲取按鈕上的字
            Icon icon;
            switch (actionCommand) {
                case "Game":
                    SecureRandom random = new SecureRandom();

                    GameStart gui = new GameStart(userName, year);
                    gui.setSize(1250, 800);   //設定寬，長
                    gui.setLocation(140, 15);
                    gui.setBackground(Color.pink.darker());
                    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    gui.setVisible(true);
                    break;
                case "Archaeology":
                    new VisitWebsite();
                    break;
                case "Return":
                    dispose(); //關閉此頁面
                    FrameStart frameStart = new FrameStart(); ////建立一個表單物件
                    frameStart.setSize(1250, 800); //設定寬，長
                    frameStart.setLocation(140, 15);
                    frameStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //設定預設的關閉視窗
                    //frameStart.init();
                    frameStart.setVisible(true); //視窗預設是不可見的
                    break;
                case "Hide EN":
                    setIsHide(1);
                    updateTable();
                    break;
                case "Test & Check":
                    try{
                        checkTable();
                    } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        icon = new ImageIcon("img\\h.png");
                        JOptionPane.showMessageDialog(FramePage1.this, "Hide English before taking the test", "Error",1, icon);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "Show EN":
                    setIsHide(0);
                    updateTable();
                    break;
                case "Shuffle":
                    if(isHide == 1) {
                        icon = new ImageIcon("img\\s.png");
                        JOptionPane.showMessageDialog(FramePage1.this, "Plaese show English before shuffle the table！", "Error", 1, icon);
                        return;
                    }
                    try {
                        new Shuffle(wordData1.length, fileName);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    updateTable();
                    break;
                case "Translate":
                    try {
                        InputTranslateWord input = new InputTranslateWord();
                        if(input.addOrNot()){
                            writer.addWord(input.getWord().getWordEn(), input.getWord().getWordCn(), 0);
                            new AddNewQA(input.getWord().getWordEn(), "game\\"+userName+"\\"+year+".txt");
                        }
                        updateTable();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
            }
        }
    }
}
