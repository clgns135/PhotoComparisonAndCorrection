package ch02.MyTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Test03 {
    InputStream fis=null;
    OutputStream fos=null;

    Test03(){
        try{
            fis = new FileInputStream("C:\\Users\\COMPUTER\\Documents\\test\\cat.png");
    
            fos = new FileOutputStream("C:\\Users\\COMPUTER\\Documents\\testcase\\cat.png");

            byte[] arr = new byte[100];
            int result;
        while((result = fis.read(arr))!=-1){
            fos.write(arr,0,result);
        }
        System.out.println("파일 복사가 정상 처리 되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                fis.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        
    }
    
    

}
