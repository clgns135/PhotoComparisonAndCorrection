package ch02.MyTest;

import javax.swing.*;
import java.awt.*;

public class Test02 extends JFrame {
    GridBagLayout gbl;
    JPanel gbjp;

    public Test02(){
        super("J프레임 테스트");    // 프레임의 타이틀
        setSize(600, 600);  // 컨테이너 크기 지정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 기본 레이아웃을 FlowLayout으로 지정  좌우 간격 20px, 상하 간격 50px
        gbjp.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 50));
        
    }
}
