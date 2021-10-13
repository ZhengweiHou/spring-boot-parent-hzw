import java.io.*;

public class FileEncodeTest {
    public static void main(String[] args) throws Exception {
        System.out.println("file.encoding : "+System.getProperty("file.encoding"));
        System.out.println("sun.jnu.encoding : "+System.getProperty("sun.jnu.encoding"));

        System.setProperty("file.encoding", "UTF-8");
        System.out.println("file.encoding : "+System.getProperty("file.encoding"));

        FileReader fileReader = new FileReader( "/home/houzw/document/git-rep/spring-boot-parent-hzw/spring-boot-jvm/src/main/java/file_encoding/utf-8txt.txt");
        System.out.println("FileReader Encode : " + fileReader.getEncoding());
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        while((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();

        FileReader fileReader2 = new FileReader( "/home/houzw/document/git-rep/spring-boot-parent-hzw/spring-boot-jvm/src/main/java/file_encoding/gbktxt.txt");
        System.out.println("FileReader2 Encode : " + fileReader2.getEncoding());
        BufferedReader br2 = new BufferedReader(fileReader2);
        while((line = br2.readLine()) != null) {
            System.out.println(line);
        }
        br2.close();
    }
}
