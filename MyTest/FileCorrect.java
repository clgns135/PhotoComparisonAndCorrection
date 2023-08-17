package ch02.MyTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileCorrect extends JFrame implements ActionListener {
    static BufferedImage src, tempsrc;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    byte[] tempbyte;
    static int[][] pixels;
    static int[] temppixels;
    static private JPanel page1 = new JPanel();
    static private JPanel page2 = new JPanel();

    static private JButton btnSave = new JButton("저장");
    static private JButton btngray240 = new JButton("그레이 스케일 Rec.240");
    static private JButton btngray601 = new JButton("그레이 스케일 Rec.601");
    static private JButton btngray709 = new JButton("그레이 스케일 Rec.709");
    static private JButton btngrayhistogram = new JButton("히스토그램 회색");
    static private JLabel labelOpen1 = new JLabel();

    public FileCorrect(String s) {
        try {
            src = ImageIO.read(new File(s));
            /* ImageIO.write(src, "png", baos);
            baos.flush();
            tempbyte = baos.toByteArray();
            // System.out.println(Arrays.toString(tempbyte)); //test 확인용
            baos.close(); */

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.initHome();
        this.initpage();
        this.start();
    }

    public void initHome() { // 처음 화면을 위한 초기 할당
        // JFrame j = new JFrame();
        this.setTitle("보정 화면");// 타이틀
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
        page2.setLayout(new GridLayout(5, 0, 0, 50));
        page2.add(btnSave); // btnOpen1이 눌린 후에 활성화 됨
        page2.add(btngray240);
        page2.add(btngray601);
        page2.add(btngray709);
        page2.add(btngrayhistogram);

        page1.add(page2);
        add(page1, BorderLayout.EAST);
        labelOpen1.setIcon(new ImageIcon(src));
        add(labelOpen1, BorderLayout.CENTER);

        // page2.setLayout(new GridLayout(1, 1));
        // add(page2);
        // page2.add(btnOpen1);
        // page2.add(btnOpen2).setVisible(false); // btnOpen1이 눌린 후에 활성화 됨

        /*
         * page3.setBounds(0, 900, 500, 100);
         * page3.setLayout(new FlowLayout());
         * page3.add(labelOpen1);
         * page3.add(labelOpen2);
         * page3.setBounds(0, 0, 500, 100);
         */

        // 처음 프레임에 가운데쪽에 패널 생성

        /*
         * // page3.setBounds(0, 100, 500, 100);
         * this.getContentPane().add(page3);
         * this.getContentPane().add(page2);
         * this.getContentPane().add(page1);
         */

    }

    public void start() { // 버튼 반응, 파일 선택 담당
        btnSave.addActionListener(this); // btnSave이 눌렸는지 addActionListener로 확인
        btngray240.addActionListener(this); // btngray601이 눌렸는지 addActionListener로 확인
        btngray601.addActionListener(this); // btngray601이 눌렸는지 addActionListener로 확인
        btngray709.addActionListener(this); // btngray이 눌렸는지 addActionListener로 확인
        btngrayhistogram.addActionListener(this);
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == btnSave) { // btnsave가 눌렸을 때
            new FileChooser().ReturnFileBuffer(tempsrc);
            this.setVisible(false);
            // new FileChooser().setVisible(true);
        } else if (arg0.getSource() == btngray240) { // btntemp가 눌렸을 때
            grayScale(arg0.getSource());
        } else if (arg0.getSource() == btngray601) { // btntemp가 눌렸을 때
            grayScale(arg0.getSource());
        } else if (arg0.getSource() == btngray709) { // btntemp가 눌렸을 때
            grayScale(arg0.getSource());
        } else if (arg0.getSource() == btngrayhistogram) { // btntemp가 눌렸을 때
            histogram(arg0.getSource());
        }
    }

    public void grayScale(Object argsrc) {
        tempsrc = src;

        if (argsrc == btngray240) {
            System.out.println("240실행");
            for (int y = 0; y < tempsrc.getHeight(); y++) {
                for (int x = 0; x < tempsrc.getWidth(); x++) {
                    Color color = new Color(tempsrc.getRGB(x, y));
                    int Y = (int) (0.212 * color.getRed() + 0.701 * color.getGreen() + 0.087 * color.getBlue());
                    tempsrc.setRGB(x, y, new Color(Y, Y, Y).getRGB());
                }
            }
            ImageIcon icon = new ImageIcon(tempsrc);
            labelOpen1.setIcon(icon);
        } else if (argsrc == btngray601) {
            System.out.println("601실행");
            for (int y = 0; y < tempsrc.getHeight(); y++) {
                for (int x = 0; x < tempsrc.getWidth(); x++) {
                    Color color = new Color(tempsrc.getRGB(x, y));
                    int Y = (int) (0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());
                    tempsrc.setRGB(x, y, new Color(Y, Y, Y).getRGB());
                }
            }
            ImageIcon icon = new ImageIcon(tempsrc);
            labelOpen1.setIcon(icon);
        } else if (argsrc == btngray709) {
            System.out.println("709실행");
            for (int y = 0; y < tempsrc.getHeight(); y++) {
                for (int x = 0; x < tempsrc.getWidth(); x++) {
                    Color color = new Color(tempsrc.getRGB(x, y));
                    int Y = (int) (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue());
                    tempsrc.setRGB(x, y, new Color(Y, Y, Y).getRGB());
                }
            }
            ImageIcon icon = new ImageIcon(tempsrc);
            labelOpen1.setIcon(icon);
        }
    }

    public void histogram(Object argsrc) {
        tempsrc = src;
        if (argsrc == btngrayhistogram) {
            System.out.println("히스토그램 회색 실행");
            int width = tempsrc.getWidth();
            int height = tempsrc.getHeight();
            temppixels = new int[width * height];
            pixels = new int[width][height];
            PixelGrabber grab = new PixelGrabber(tempsrc, 0, 0, width, height, temppixels, 0, width);
            int valueofimage = 0, formula = 0;

            try {
                grab.grabPixels();
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        pixels[i][j] = temppixels[(i * 10) + (j * 1)];
                        System.out.println(temppixels[(i * 10) + (j * 1)]);
                        //System.out.println(pixels[i][j]);
                    }
                }
                // int[][] picture = new int[width][height];
                int low;
                int high;
                int pixel;
                low = pixels[0][0];
                high = pixels[0][0];

                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        pixel = pixels[i][j];
                        //System.out.println(pixel);
                        if (pixel < low) {
                            low = pixel;
                        } else if (pixel > high) {
                            high = pixel;
                        }
                    }
                }
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        valueofimage = pixels[i][j];
                        formula = (valueofimage - low / (high - low * 255));
                        pixels[i][j] = formula;
                    }
                }
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        temppixels[(i * 10) + (j * 1)] = pixels[i][j];
                    }
                }

                tempsrc = getImageFromArray(width, height);
                ImageIcon icon = new ImageIcon(tempsrc);
                labelOpen1.setIcon(icon);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    public static BufferedImage getImageFromArray(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0, 0, width, height, temppixels);
        return image;
    }

    public void handlesinglepixel(int x, int y, int pixel){
        //int alpha 
    }
}
