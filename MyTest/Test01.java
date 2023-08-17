// https://ming9mon.tistory.com/47
// JFrame Test

package ch02.MyTest;

import javax.swing.JFrame;

public class Test01 extends JFrame {
    public Test01() {
        super("J프레임 테스트");    // 프레임의 타이틀 지정
        setSize(500, 500);  // 컨테이너 크기 지정
        setVisible(true); // 창 가시화
    }

    public static void main(String[] args) {
        Test01 t = new Test01();
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);  // 종료 이벤트
    }
}

