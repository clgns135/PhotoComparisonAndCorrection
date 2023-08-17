package ch02.MyTest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Test04 {
    byte[] imageInByte;

    Test04() {

        try {
            BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\COMPUTER\\Desktop\\test.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);
            baos.flush();

            imageInByte = baos.toByteArray();
            System.out.println(Arrays.toString(imageInByte));

            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
