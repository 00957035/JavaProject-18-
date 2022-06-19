package Game;

import Work.ChangeTxt;
import javazoom.jl.decoder.JavaLayerException;
import music.BGMusic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class GameGUI extends JFrame {

    private Role player1, player2;
    private Battle battle;
    private JPanel player1Zone, player2Zone, panelQA;
    private JTextField player1HpField, player2HpField;
    private JLabel player1Image, player2Image, question;
    private JButton player1Attack, player2Attack, playReturn;
    private ButtonGroup buttonGroup;
    private Scanner input;
    private String ans, fileName;
    JRadioButton[] radioButton;
    private int role_x=0, role_y=0, error;
    private Timer timer;
    private ImagePanel ip;
    private BGMusic music;
    public GameGUI(Role player1, Role player2, String fileName) {
        super("Simple Battle");
        try {
            music = new BGMusic("music\\slowMusic.mp3");
            music.circularPlay();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
        // setLayout(new GridLayout(1, 2));
        this.fileName = fileName;
        setLayout(new BorderLayout());

        this.player1 = player1;
        this.player2 = player2;

        JPanel panel = new JPanel(new GridLayout(5, 1));
        openFile();
        question = new JLabel(input.nextLine(), SwingConstants.CENTER);
        question.setForeground(Color.PINK.darker().darker());
        question.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)

        error = Integer.parseInt(input.nextLine());

        panelQA = new JPanel(new FlowLayout());
        buttonGroup = new ButtonGroup();
        radioButton = new JRadioButton[4];
        for(int i=0; i<4; i++){
            radioButton[i] = new JRadioButton(input.nextLine(),false);
            radioButton[i].setForeground(Color.PINK.darker());
            radioButton[i].setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20));
            panelQA.add(radioButton[i]);
            buttonGroup.add(radioButton[i]);
        }
        ans = input.nextLine();
        System.out.println("ans = " + ans);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 200, 10));
        player1Attack = new JButton("User Attack!");
        player1Attack.addActionListener(new MyEventListner());
        setButton(player1Attack);
        player2Attack = new JButton("Devil Attack!");
        player2Attack.addActionListener(new MyEventListner());
        setButton(player2Attack);
        playReturn = new JButton("finish");
        playReturn.addActionListener(new MyEventListner());

        setButton(playReturn);
        buttonPanel.add(player1Attack);
        buttonPanel.add(playReturn);
        buttonPanel.add(player2Attack);
        panel.add(new JLabel(""));
        panel.add(question);
        panel.add(panelQA);
        panel.add(new JLabel(""));
        panel.add(buttonPanel);

        add(panel, BorderLayout.NORTH);

        player1Zone = new JPanel();
        player1Zone.setLayout(new BorderLayout());

        JPanel player1Status = new JPanel();

        JLabel labelUserHP = new JLabel(player1.getName() + "'s HP：");
        labelUserHP.setForeground(Color.PINK.darker().darker());
        labelUserHP.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 25)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        player1Status.add(labelUserHP);

        player1HpField = new JTextField("" + player1.getHp());
        player1HpField.setForeground(Color.PINK.darker().darker());
        player1HpField.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 25)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        player1HpField.setEditable(false);
        player1Status.add(player1HpField);

        player1Zone.add(player1Status, BorderLayout.NORTH);

        Icon player1Icon = new ImageIcon("img\\user.png");
        player1Image = new JLabel(player1Icon);

        player1Zone.add(player1Image, BorderLayout.CENTER);

        add(player1Zone, BorderLayout.WEST);

        player2Zone = new JPanel();
        player2Zone.setLayout(new BorderLayout());

        JPanel player2Status = new JPanel();
        JLabel labelDevilHP = new JLabel("English Devil's HP：");
        labelDevilHP.setForeground(Color.PINK.darker().darker());
        labelDevilHP.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 25)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        player2Status.add(labelDevilHP);

        player2HpField = new JTextField("" + player2.getHp());
        player2HpField.setEditable(false);
        player2HpField.setForeground(Color.PINK.darker().darker());
        player2HpField.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 25)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        player2Status.add(player2HpField);
        player2Zone.add(player2Status, BorderLayout.NORTH);

        Icon player2Icon = new ImageIcon("img\\demon.png");
        player2Image = new JLabel(player2Icon);

        player2Zone.add(player2Image, BorderLayout.CENTER);

        add(player2Zone, BorderLayout.EAST);

        ip = new ImagePanel(new ImageIcon("img\\right.png"));
        ip.setBounds(0, 600, 1250, 100);
        ip.setVisible(false);
        add(ip);

        battle = new Battle(player1, player2, this);
    }


    class ImagePanel extends JPanel {
        ImageIcon role;
        public ImagePanel(ImageIcon role){
            this.role = role;
            this.setBackground(null);
            this.setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(role.getImage(), role_x, role_y, null);
        }
    }
    private void setButton(JButton button){
        button.setFont(new Font("KAWAIITEGAKIMOJI", Font.PLAIN, 20)); //Font.PLAIN(細), Font.BOLD(粗), Font.ITALIC(斜)
        button.setBackground(Color.WHITE);
        button.setForeground(Color.PINK.darker().darker());
    }
    public JTextField getPlayer1HpField() {
        return player1HpField;
    }

    public JTextField getPlayer2HpField() {
        return player2HpField;
    }
    public void openFile(){
        try {
            input = new Scanner(Paths.get(fileName));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }
    public void changeImagePanel(ImageIcon role){
        remove(ip);
        ip = new ImagePanel(role);
        add(ip);
        ip.setVisible(true);
        revalidate();
    }
    class timerTask1 extends TimerTask {
        @Override
        public void run(){
            if(role_x < 650) role_x++;
            else timer.cancel();
            //System.out.println(role_x);
            repaint();
        }
    }
    class timerTask2 extends TimerTask {
        @Override
        public void run(){
            if(role_x > -250) role_x--;
            else timer.cancel();
            repaint();
        }
    }
    private class MyEventListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == player1Attack){
                SelectButtonGroup s1 = new SelectButtonGroup(buttonGroup);
                if(s1.getSelect()==null){
                    Icon icon = new ImageIcon("img\\n.png");
                    JOptionPane.showMessageDialog(GameGUI.this, "No answer selected"+ans, "no input", JOptionPane.PLAIN_MESSAGE, icon);
                    return;
                }
                player1Attack.setEnabled(false);
                player2Attack.setEnabled(true);
                //System.out.println(s1.getSelect());
                if(s1.getSelect().equals(ans)){
                    role_x = 0;
                    role_y = 250;
                    changeImagePanel(new ImageIcon("img\\right.png"));
                    timer = new Timer();
                    timer.schedule(new timerTask1(), 0, 1);
                    if(battle.play(true) == 2){
                        timer.cancel();
                        role_y = 50;
                        role_x = 150;
                        changeImagePanel(new ImageIcon("img\\win_big.png"));
                        Icon icon = new ImageIcon("img\\win.png");
                        JOptionPane.showMessageDialog(GameGUI.this, String.format("%s 打倒了 %s!\n", player1.getName(), player2.getName()), "Win", JOptionPane.PLAIN_MESSAGE, icon);
                        player2Attack.setEnabled(false);
                        player2Image.setIcon(new ImageIcon("img\\demon_2.png"));
                        player2Zone.add(player2Image, BorderLayout.CENTER);
                    }
                }
                else{
                    error = error + 1;
                    ChangeTxt change = new ChangeTxt(fileName);
                    try {
                        change.readLineVarFileString(question.getText(), ""+error);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    role_y = 0;
                    role_x = 80;
                    changeImagePanel(new ImageIcon("img\\wrong.png"));
                    Icon icon = new ImageIcon("img\\q.png");
                    JOptionPane.showMessageDialog(GameGUI.this, "the answer is = "+ans, "Answer wrong", JOptionPane.PLAIN_MESSAGE, icon);
                }
            }
            else if(event.getSource() == player2Attack){
                role_x = 650;
                role_y = 250;
                changeImagePanel(new ImageIcon("img\\left.png"));
                timer = new Timer();
                timer.schedule(new timerTask2(), 0, 1);

                player2Attack.setEnabled(false);
                player1Attack.setEnabled(true);
                if(battle.play(false) == 2 || !input.hasNext()){
                    timer.cancel();
                    role_y = 30;
                    role_x = 120;
                    changeImagePanel(new ImageIcon("img\\lose_big.png"));
                    Icon icon = new ImageIcon("img\\lose.png");
                    JOptionPane.showMessageDialog(GameGUI.this, String.format("%s 打倒了 %s!\n", player2.getName(), player1.getName()), "Lose", JOptionPane.PLAIN_MESSAGE, icon);
                    player1Attack.setEnabled(false);
                    player1Image.setIcon(new ImageIcon("img\\user_2.png"));
                    player1Zone.add(player1Image, BorderLayout.CENTER);
                }
                if(input.hasNext()){
                    question.setText(input.nextLine());
                    error = Integer.parseInt(input.nextLine());
                    for(int i=0; i<4; i++) radioButton[i].setText(input.nextLine());
                    ans = input.nextLine();
                    System.out.println("ans = " + ans);
                }
            }
            else if(event.getSource() == playReturn) {
                music.close();
                dispose();
            }
            revalidate();

        }

    }
}
