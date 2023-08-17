package ch02.MyTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFrame implements ActionListener {

    private JFileChooser fileComponent = new JFileChooser(); // 윈도우 탐색기에서 파일 선택을 위한 변수
    static private File tempFile1, tempFile2; // 파일 선택 후 임시로 저장될 파일 변수
    static private String tempString;
    static private JButton btnOpen1 = new JButton("열기"); // 열기 버튼
    static private JButton btnOpen2 = new JButton("열기"); // 열기 버튼
    static private JButton btnSave = new JButton("저장"); // 저장 버튼
    static private JButton btnCorrect = new JButton("보정"); // 보정 버튼
    static private JButton btnComparison = new JButton("비교"); // 비교 버튼
    static private JLabel labelOpen1 = new JLabel(" "); // 파일 선택 후 경로를 알려주는 라벨 변수
    static private JLabel labelOpen2 = new JLabel(" "); // 파일 선택 후 경로를 알려주는 라벨 변수
    static private JLabel labelSave = new JLabel(" "); // 파일 저장 후 경로를 알려주는 라벨 변수
    static private JLabel labelCorrect = new JLabel(" "); // 임시로 변수 선언, 사용안하면 삭제
    static private JLabel labelComparison = new JLabel(" "); // 임시로 변수 선언, 사용안하면 삭제
    static private JPanel page1 = new JPanel(); // 최하단에 위치한 패널, 라벨(Open, Save, Correct, Comparison), 버튼(Save, Correct,
                                                // Comparison) 할당
    static private JPanel page2 = new JPanel(); // 버튼(Open1, Open2) 할당
    static private JPanel page3 = new JPanel(); // 새로운 프레임에 할당
    static private JFrame frame1 = new JFrame(); // btnCorrect를 누르면 보정 할 수 있는 새로운 프레임 창을 띄우기 위한 변수
    FileInputStream fis1 = null; // 파일을 입력 받고 읽기 위한 변수
    static BufferedImage src; // 저장을 위한 변수

    byte buffer1[] = new byte[1024 * 1024]; // 1kb
    byte buffer2[] = new byte[100];

    public FileChooser() { // 메인에서 불러오는 생성자 함수
        this.initHome(); // 처음 화면을 위한 초기 할당
        this.initpage(); // 첫 패널 할당, 레이아웃, 버튼, 라벨

        this.start(); // 버튼 반응, 파일 선택 담당
    }

    public void initHome() { // 처음 화면을 위한 초기 할당
        // JFrame j = new JFrame();
        this.setTitle("사진 보정 및 비교 프로그램");// 타이틀
        this.setSize(1280, 1024);// 프레임의 크기
        this.setResizable(false);// 창의 크기를 변경하지 못하게
        this.setLocationRelativeTo(null);// 창이 가운데 나오게
        this.setLayout(null);// 레이아웃 설정
        this.setVisible(true);// 창이 보이게
        // this.getContentPane().setBackground(Color.black); // 프레임 색 변경
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame이 정상적으로 종료되게 함
    }

    public void initpage() { // 첫 패널 할당, 레이아웃, 버튼, 라벨

        this.getContentPane().setLayout(new BorderLayout()); // BorderLayout 형식으로 패널 생성

        // 패널이나 프레임에 추가
        // 처음 프레임에 최하단쪽에 패널 생성
        page1.add(labelOpen1);
        page1.add(btnSave); // btnOpen1이 눌린 후에 활성화 됨
        page1.add(btnCorrect).setEnabled(false); // btnOpen1이 눌린 후에 활성화 됨 && btnOpen2가 눌린 후에 비활성화 됨
        page1.add(btnComparison).setEnabled(false); // btnOpen2가 눌린 후에 활성화 됨
        page1.add(labelSave);
        page1.add(labelCorrect);
        page1.add(labelComparison);
        page1.add(labelOpen2);
        add(page1, BorderLayout.SOUTH);

        /*
         * page3.setBounds(0, 900, 500, 100);
         * page3.setLayout(new FlowLayout());
         * page3.add(labelOpen1);
         * page3.add(labelOpen2);
         * page3.setBounds(0, 0, 500, 100);
         */

        // 처음 프레임에 가운데쪽에 패널 생성
        page2.setLayout(new GridLayout(1, 2));
        add(page2);
        page2.add(btnOpen1);
        page2.add(btnOpen2).setVisible(false); // btnOpen1이 눌린 후에 활성화 됨

        /*
         * // page3.setBounds(0, 100, 500, 100);
         * this.getContentPane().add(page3);
         * this.getContentPane().add(page2);
         * this.getContentPane().add(page1);
         */

    }

    public void start() { // 버튼 반응, 파일 선택 담당
        btnOpen1.addActionListener(this); // btnOpen1이 눌렸는지 addActionListener로 확인
        btnOpen2.addActionListener(this); // btnOpen2이 눌렸는지 addActionListener로 확인
        btnSave.addActionListener(this); // btnSave이 눌렸는지 addActionListener로 확인
        btnCorrect.addActionListener(this); // btnCorrect이 눌렸는지 addActionListener로 확인
        btnComparison.addActionListener(this); // btnComparison이 눌렸는지 addActionListener로 확인
    }

    public void actionPerformed(ActionEvent arg0) { // arg0에 각 버튼이랑 이벤트 비교 후 이벤트 실행
        if (arg0.getSource() == btnOpen1) { // btnOpen1이 눌렸을 때
            fileComponent.setFileFilter(new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png")); // 확장자를 jpg,
                                                                                                        // png로 제한
            fileComponent.setMultiSelectionEnabled(false); // 다중 선택 불가 설정
            if (fileComponent.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { // FileChooser로 컴퓨터 내 파일 불러오기
                labelOpen1.setText("열기 파일 경로 : " + fileComponent.getSelectedFile().toString()); // 파일 경로를 labelOpen1에 부여
                tempFile1 = fileComponent.getSelectedFile(); // tempFile1에 불러온 파일을 임시저장
                tempString = fileComponent.getSelectedFile().toString();

                ImageIcon icon = new ImageIcon(fileComponent.getSelectedFile().toString()); // 컴퓨터에서 불러온 경로에 따라 아이콘 이미지
                                                                                            // 생성
                btnOpen1.setText(""); // btnOpen1 텍스트 초기화
                btnOpen1.setIcon(icon); // btnOpen1 이미지 부여

                System.out.println(fileComponent.getSelectedFile().toString()); // 데이터 테스트 확인용
            }
            btnOpen2.setEnabled(true); // btnOpen2 활성화
            btnOpen2.setVisible(true); // btnOpen2 활성화
            btnSave.setEnabled(true); // btnSave 활성화
            btnCorrect.setEnabled(true); // btnCorrect 활성화
        } else if (arg0.getSource() == btnOpen2) { // btnOpen2가 눌렸을 때
            fileComponent.setFileFilter(new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png")); // 확장자를 jpg,
                                                                                                        // png로 제한
            fileComponent.setMultiSelectionEnabled(false); // 다중 선택 불가 설정
            if (fileComponent.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { // FileChooser로 컴퓨터 내 파일 불러오기
                labelOpen2.setText("열기 파일 경로 : " + fileComponent.getSelectedFile().toString()); // 파일 경로를 labelOpen2에 부여
                tempFile2 = fileComponent.getSelectedFile(); // tempFile2에 불러온 파일을 임시저장

                ImageIcon icon = new ImageIcon(fileComponent.getSelectedFile().toString()); // 컴퓨터에서 불러온 경로에 따라 아이콘 이미지
                                                                                            // 생성
                btnOpen2.setText(""); // btnOpen2 텍스트 초기화
                btnOpen2.setIcon(icon); // btnOpen2 이미지 부여

                System.out.println(fileComponent.getSelectedFile().toString());
            }
            btnCorrect.setEnabled(false); // btnCorrect 비활성화
            btnComparison.setEnabled(true); // btnComparison 활성화
        } else if (arg0.getSource() == btnSave) {
            fileComponent.setFileFilter(new FileNameExtensionFilter("JPG Images", "jpg, jpeg, png"));
            if (fileComponent.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                labelSave.setText("저장 파일 경로 : " + fileComponent.getSelectedFile().toString());

                try {
                    ImageIO.write(src, "jpg", new File(fileComponent.getSelectedFile().toString()));
                } catch (Exception e) {
                    e.getStackTrace();
                } finally {
                    System.out.println("저장 파일 경로 : " + fileComponent.getSelectedFile().toString());
                }

            }
        } else if (arg0.getSource() == btnCorrect) {
            FileCorrect fileCorrect = new FileCorrect(tempString);
            this.setVisible(false);
            System.out.println("보정 버튼 눌림");
        } else if (arg0.getSource() == btnComparison) {
            FileComparison fileComparison = new FileComparison(tempFile1, tempFile2);
            float tempvalue = fileComparison.bufferedImagesEqual();
            System.out.println(tempvalue);
            if (tempvalue != 0) {
                JOptionPane.showMessageDialog(this, "비교 결과 " + tempvalue + "%" + " 일치합니다.");
            }
            System.out.println("비교 버튼 눌림");
        }
    }

    public void ReturnFileBuffer(BufferedImage buffsrc) {
        src = buffsrc;
        btnOpen1.setIcon(new ImageIcon(src));
        this.setVisible(true);
    }

    public void FileBuffer(String s) {
        try {
            if (s != null) {
                fis1 = new FileInputStream(s);

                int readcount;

                while ((readcount = fis1.read(buffer2)) != -1) {
                    break;
                }
            }
            if (tempFile2 != null) {
                FileInputStream in2 = new FileInputStream(tempFile2);
                BufferedInputStream bis2 = new BufferedInputStream(in2);

                int readcount;
                byte buffer[] = new byte[1024 * 1024]; // 1kb

                while ((readcount = bis2.read(buffer, 0, buffer.length)) != -1) {
                    bis2.read(buffer, 0, readcount);
                }
            }
        } catch (Exception e) {
            System.out.println("FileChooser_FileBuffer 함수에서 오류 발생");
            e.printStackTrace();
        } finally {
            try {
                fis1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}