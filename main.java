import java.io.*;
import java.util.*;

class FileManager
{
    private File f;

    public void start()
    {
        Scanner sc=new Scanner(System.in);
        


        System.out.println("if you want to go ahed press 0");
        int i=sc.nextInt();
        sc.nextLine();
        if(i!=0)
        {
            System.exit(0);

        }
        
        System.out.println("enter the file name or drictiory name");
        // Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        
        f=new File(str);
        if(f.exists())
        {
            if(f.isFile())
            {
                file();
            }
            else
            drictiory();
        
        

        }
        else{
            System.out.println("plese re-enter the file name file not found");
            start();
        }

        
        
        
        
    }
    public void file(){
        System.out.println("what do you want to do");
        System.out.println("1 for read file");
        System.out.println("2 for write in file");
        System.out.println("3 to exit");
        Scanner sc=new Scanner(System.in);
        int i=sc.nextInt();
        if(i==1)
        {
            readFile();
        }
        else if(i==2)
        {
            writeFile();
        }
        
        else if(i==3)
        {
            System.exit(0);
        }
        else
        {
            System.out.println("you enter no is not present in the list ");
            file();
        }
        

    };
    public void readFile()
    {
        Scanner sc=new Scanner(System.in);
        String emt = "";
        try{
            FileInputStream fi=new FileInputStream(f);
            while(true)
            {
                int i=fi.read();
                if(i==-1)
                {
                    break;
                }
                emt=emt+(char)i;
            }
            System.out.println(emt);
            System.out.println();
            System.out.println("is you want to continut enter 1");
            int en=sc.nextInt();
            if(en==1)
            {
                start();
            }
            else
            System.exit(0);

            

        }
        catch(IOException e)
        {
            System.out.println("exception in read file");
        }
        
        
    }
    public void writeFile(){
        try{
            FileOutputStream fo=new FileOutputStream(f);
            StringBuilder sb=new StringBuilder();
            System.out.println("enter twice to stop");
            Scanner sc=new Scanner(System.in);
            while(true)
            {
                String s=sc.nextLine();
                if(s.isEmpty())
                {
                    break;
                }
                sb.append(s);
                sb.append("\n");
            }
            String str=sb.toString();
            byte[] data=str.getBytes();
            fo.write(data);
            System.out.println("operation succsfull");
            file();
             
        }
        catch(IOException e)
        {
            start();
        }
    };
    public void drictiory(){
        Scanner sc=new Scanner(System.in);
        System.out.println("1 for list content");
        System.out.println("2 for create file");
        System.out.println("3 for delete file");
        System.out.println("4 for exit");
        int i=sc.nextInt();
        if(i==1)
        {
            listAll();
        }
        else if(i==2)
        {
            createFile();
        }
        else if(i==3)
        {
            deleteFile();
        }
        else if(i==4)
        {
            System.exit(0);

        }
        else 
        {
            System.out.println("invaild input ");
            drictiory();
        }
        

    };
    public void listAll()
    {
        File[] filelist=f.listFiles();
        System.out.println("enter 1 if you want to list all the items in the folder");
        System.out.println("enter 2 if you want to list all the files in the folder");
        System.out.println("enter 3 if you want to list all the diricotory in the folder");
        Scanner sc=new Scanner(System.in);
        int i=sc.nextInt();

        if(i==1)
        {
            for(File f: filelist)
            {
            System.out.println(f.getName());
        }
        start();

        }
        else if(i==2)
        {
            for(File f: filelist)
            {
                if(f.isFile())
                {
                    System.out.println(f.getName());
                }
            }
            start();
        }
        else if(i==3)
        {
            for(File f: filelist)
            {
                if(!f.isFile())
                {
                    System.out.println(f.getName());
                }
            }
            start();
        }
        else 
        {
            System.out.println("the no you entered is not present in the list ");
            drictiory();
        }
        
        

    
    }
    void createFile(){
        System.out.println("plese enter the name of the file");
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        try{
            String path=f.getAbsolutePath();
            String newfile=path+"\\"+str;

            File nf = new File(newfile);
            if(nf.exists()){
                System.out.println("Already exists");
            } else {
                nf.createNewFile();
                System.out.println("file created succesfully");
                start();
            }

        }
        catch(IOException e)
        {

        }
    };
    void deleteFile(){
        String path=f.getAbsolutePath();
        System.out.println("enter the name of the file you wnt to delete");
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        String deletePath=path+"\\"+str;
        File df=new File(deletePath);
        df.delete();
        start();

    };

}
class Test{
    public static void main(String args[])
    {
        FileManager fm=new FileManager();
        fm.start();
    }
}