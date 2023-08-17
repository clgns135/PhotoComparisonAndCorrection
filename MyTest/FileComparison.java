package ch02.MyTest;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileComparison extends JFrame {
    FileInputStream fis1, fis2;
    static BufferedImage src1, src2;
   
    FileComparison(File tempfile1, File tempfile2){
        System.out.println("s1 파일 경로 : "+tempfile1);
        System.out.println("s2 파일 경로 : "+tempfile2);
        try {
            fis1 = new FileInputStream(tempfile1);
            fis2 = new FileInputStream(tempfile2);
            src1 = ImageIO.read(fis1);
            src2 = ImageIO.read(fis2);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public float bufferedImagesEqual() {
        System.out.println("bufferedImagesEqual 함수 시작");
        if (src1.getWidth() == src2.getWidth() && src1.getHeight() == src2.getHeight()) {
            System.out.println("첫 if 돌입");
            int width =src1.getWidth();
            int height = src1.getHeight();
            int calc = 0;
            for (int x = 0; x < src1.getWidth(); x++) {
                for (int y = 0; y < src1.getHeight(); y++) {
                    if (src1.getRGB(x, y) == src2.getRGB(x, y))
                        calc+=1;
                }
            }
            return ((float)calc/(width*height)*100);
        } else {
            JOptionPane.showMessageDialog(null, "파일 해상도가 다릅니다.");
            return 0;
        }
    }
}
