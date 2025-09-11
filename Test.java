import java.io.*;
class TestMain{
   public static void main(String args[]) throws IOException{
       File f = new File("java1");
       String path =  f.getAbsolutePath();
       System.out.println(path);
   }
}