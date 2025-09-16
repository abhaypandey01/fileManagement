import java.util.*;
import java.io.*;
import java.time.*;

class Transaction implements Serializable{
    private LocalDateTime timestamp;
    private int id;
    private static int count = 1;

    public Transaction(){
        // this.id = UUID.randomUUID().toString();
        this.id = count++;
        this.timestamp = LocalDateTime.now();
        // count++;
    }

    public int getID(){return this.id;}

    public LocalDateTime getTimestamp(){return this.timestamp;}

    public String toString(){return this.id+" : "+this.timestamp;}
}

class Manager implements Serializable{
    File file = new File("file.dat");
    private ArrayList<Transaction> tl = new ArrayList<Transaction>();

    public void save(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(tl);
            oos.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addTransaction(){
        tl.add(new Transaction());
        try{
            save();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void display(){
        for(Transaction t : tl){System.out.println(t.toString());}
    }

    public void start(){
        try{
            if(file.exists()) addTransaction();
            else file.createNewFile();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

class Test{
    public static void main(String args[]){
        Manager mg = new Manager();
        mg.start();
        mg.start();
        mg.display();
    }
}