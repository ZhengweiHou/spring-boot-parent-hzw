
import java.io.*;
import java.nio.charset.Charset;

public class FileEncodeTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Charset.defaultCharset :" + Charset.defaultCharset());
        System.out.println("file.encoding : "+System.getProperty("file.encoding"));

        System.out.println("================================");
        System.out.println(">[utf-8txt.txt] read by default:");
        FileReader fr1 = new FileReader( "./utf-8txt.txt");
        System.out.println("[utf-8txt.txt] fileReader.Encoding : " + fr1.getEncoding());
        System.out.println(new BufferedReader(fr1).readLine());
        fr1.close();

        System.out.println(">[utf-8txt.txt] read by utf-8:");
        BufferedReader br1 = new BufferedReader(new InputStreamReader(
                new FileInputStream("./utf-8txt.txt"), "utf-8"));
        System.out.println(br1.readLine());
        br1.close();

        System.out.println("================================");
        System.out.println(">[  gbktxt.txt] read by default:");
        FileReader fr2 = new FileReader( "./gbktxt.txt");
        System.out.println("[  gbktxt.txt] FileReader Encode : " + fr2.getEncoding());
        System.out.println(new BufferedReader(fr2).readLine());
        fr2.close();

        System.out.println(">[  gbktxt.txt] read by GBK:");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(
                new FileInputStream("./gbktxt.txt"), "GBK"));
        System.out.println(br2.readLine());
        br2.close();
        
    }
}


//        System.out.println("================================");
//                String str="我是代码中直接输出的中文字符";
//                System.out.println(str);
//                System.out.println(str.getBytes(Charset.defaultCharset()).length);
//                String str1 = new String(str.getBytes("utf-8"),"GBK");
//                System.out.println(str1);





