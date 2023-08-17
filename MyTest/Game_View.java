package ch02.MyTest;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Game_View extends JFrame {
    // 이미지 파일 불러오는 툴킷.
    Toolkit imageTool = Toolkit.getDefaultToolkit();
    Image flight = imageTool.getImage("C:\\Users\\COMPUTER\\Desktop\\cursor.jpg");
    Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

    // 이미지 버퍼
    Image buffImg;
    Graphics buffG;

    // 플레이어 비행기의 위치값.
    int xpos = 100;
    int ypos = 100;

    public Game_View() {
        init();
    }

    public void init() {
        // JFrame j = new JFrame();
        this.setTitle("사진 보정 및 비교 프로그램");// 타이틀
        this.setSize(1280, 1024);// 프레임의 크기
        this.setResizable(false);// 창의 크기를 변경하지 못하게
        this.setLocationRelativeTo(null);// 창이 가운데 나오게
        this.setLayout(null);// 레이아웃 설정
        this.setVisible(true);// 창이 보이게
        this.getContentPane().setBackground(Color.black); // 프레임 색 변경
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame이 정상적으로 종료되게 함
        
        /* // 프레임의 대한 설정.
        setTitle("JFrame 테스트"); // 프레임 제목 설정.
        // setSize(res.width, res.height); // 프레임의 크기 설정.
        setSize(500, 500); // 테스트
        setResizable(false); // 프레임의 크기 변경 못하게 설정.
        setLocationRelativeTo(null);// 창이 가운데 나오게
        setLayout(null);
        setVisible(true); // 프레임 보이기;

        //customcursor(); // 커서 숨기기
        this.getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 프로세스 종료.
    } */
    }
    public void garbage1() {
        // 마우스 감지
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xpos = e.getX() - 10;
                ypos = e.getY() - 10;
                // System.out.println(xpos + ":" + ypos + " GridLayout 영역 좌표: ");
            }
        });

        addMouseListener(new MouseListener() {
            // 마우스가 해당 컴포넌트를 클릭
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            // 마우스가 해당 컴포넌트 영역 안으로 들어올때 발생
            @Override
            public void mouseEntered(MouseEvent e) {
            }

            // 마우스가 해당 컴포넌트 영역 밖으로 나갈때 발생
            @Override
            public void mouseExited(MouseEvent e) {
            }

            // 마우스 버튼을 누를때 발생
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("마우스 눌림 " + e.getX() + ":" + e.getY());
            }

            // 눌러진 마우스를 놓을때 발생
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });

        // 키보드 감지
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        System.out.println("w 눌림");
                        break;
                    case KeyEvent.VK_S:
                        System.out.println("s 눌림");
                        break;
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        buffImg = createImage(getWidth(), getHeight()); // 버퍼링용 이미지 ( 도화지 )
        buffG = buffImg.getGraphics(); // 버퍼링용 이미지에 그래픽 객체를 얻어야 그릴 수 있다고 한다. ( 붓? )
        update(g);
    }

    @Override
    public void update(Graphics g) {
        buffG.clearRect(0, 0, 854, 480); // 백지화
        buffG.drawImage(flight, xpos, ypos, this); // 유저 비행기 그리기.
        g.drawImage(buffImg, 0, 0, this); // 화면g애 버퍼(buffG)에 그려진 이미지(buffImg)옮김. ( 도화지에 이미지를 출력 )
        repaint();
    }

    // 커서 숨기기
    public void customcursor() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image cursorimage = tk.getImage("");
        Point point = new Point(20, 20);
        Cursor cursor = tk.createCustomCursor(cursorimage, point, "cursor");
        setCursor(cursor);
    }
}

/* class Main { // 실행
    public static void main(String[] args) {
        try {
            new Game_View();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
} */