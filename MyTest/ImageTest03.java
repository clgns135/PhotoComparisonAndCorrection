package ch02.MyTest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageTest03 {
    public static void main(String[] args) throws Exception {
        try {
            FileChooserTest fileChooser = new FileChooserTest();
        } catch (Exception e) {
            System.out.println("----------------");
            e.printStackTrace();
        }
        
    }

    static class FileChooserTest extends JFrame implements ActionListener {
        private JFileChooser fileComponent = new JFileChooser();
        private JButton btnOpen = new JButton("열기");
        private JButton btnSave = new JButton("저장");
        private JLabel labelOpen = new JLabel(" ");
        private JLabel labelSave = new JLabel(" ");

        public FileChooserTest() {
            this.init();
            this.start();
            this.setSize(500, 300);
            this.setVisible(true);
        }

        public void init() {
            getContentPane().setLayout(new FlowLayout());
            add(btnOpen);
            add(btnSave);
            add(labelOpen);
            add(labelSave);
        }

        public void start() {
            btnOpen.addActionListener(this);
            btnSave.addActionListener(this);
            //"*.jpg", "*.gif", "*.png"
            fileComponent.setFileFilter(new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "gif")); // 확장자 
            fileComponent.setMultiSelectionEnabled(false); // 다중 선택 불가 설정
        }

        public void actionPerformed(ActionEvent arg0) {
            if (arg0.getSource() == btnOpen) {
                if (fileComponent.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    labelOpen.setText("열기 파일 경로 : " + fileComponent.getSelectedFile().toString());
                }
            } else if (arg0.getSource() == btnSave) {
                if (fileComponent.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    // 파일 저장을 원하면 아래에 파일 저장 로직을 구현하면 됨.
                    labelSave.setText("저장 파일 경로 : " + fileComponent.getSelectedFile().toString());
                }
            }
        }
    }
}
