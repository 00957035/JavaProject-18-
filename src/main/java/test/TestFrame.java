package test;

import frame.FrameStart;

import javax.swing.JFrame;

public class TestFrame {
    public static void main(String[] args) {
        FrameStart frameStart = new FrameStart(); ////建立一個表單物件
        frameStart.setSize(1250, 800); //設定寬，長
        frameStart.setLocation(140, 15);
        frameStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //設定預設的關閉視窗
        //frameStart.init();
        frameStart.setVisible(true); //視窗預設是不可見的
    }
}