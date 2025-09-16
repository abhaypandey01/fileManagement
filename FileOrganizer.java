import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

class FileOrganizer{
    private File directory;
    private Map<String, String> categories = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public FileOrganizer(){
        categories.put("jpg", "Images");
        categories.put("jpeg", "Images");
        categories.put("png", "Images");
        categories.put("docx", "Documents");
        categories.put("pdf", "Documents");
    }

    public void getFiles(){
        System.out.println("Enter directory name: ");
        String dirName = sc.nextLine();
        directory = new File(dirName);

        if(!directory.isDirectory()){
            System.out.println("Invalid directory name.");
        }
        File[] files = directory.listFiles();
            for(File file: files){
                String name = file.getName();
                System.out.println(name);
            }
    }

    public void organizeFiles(){
        for(File file: directory.listFiles()){
            if(file.isFile()){
                String ext = getExtension(file);
                if(categories.containsKey(ext)){
                File targetDir = new File(directory, categories.get(ext));
                targetDir.mkdir();
                file.renameTo(new File(targetDir, file.getName()));
                }
            }
        }
    }

    public String getExtension(File file){
        String fileName = file.getName();
        int index = fileName.lastIndexOf('.');
        if (index > 0 && index < fileName.length() - 1) {
            return fileName.substring(index + 1);
        }
        return "";
    }

}

class Test{
    public static void main(String args[]){
        FileOrganizer fo = new FileOrganizer();
        fo.getFiles();
        fo.organizeFiles();
    }
}