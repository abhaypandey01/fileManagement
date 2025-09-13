import java.util.*;
import java.io.*;
class Employee implements Serializable{
    private static int conunter=1;
    private String name;
    private int salary;
    private int id;
    private static final long serialVersionUID=1l;

    
    public Employee(String name, int salary)
    {
        this.name=name;
        this.salary=salary;
        this.id=conunter++;
    }

    public int getId()
    {
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public void display()
    {
        System.out.println("name: "+this.name);
        System.out.println("salary: "+this.salary);
    }


}
class EmployeeManager{
    File file=new File("java");
    Scanner sc=new Scanner(System.in);
    ArrayList<Employee> el=new ArrayList<Employee>();

    public void start(){
        
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            el = (ArrayList<Employee>) ois.readObject();
            ois.close();
        }
        catch(Exception e)
        {

        }
        System.out.println("1:for crating new employee");
        System.out.println("2:for deleting a emplayee");
        System.out.println("3:for list the employee");
        System.out.println("4:for update the sudent ");
        System.out.println("5:for exit the applicatino ");
        int i=sc.nextInt();
        if(i==1){
            createNew();
        }
        else if(i==2){
            deleteEmployee();
        }
        else if(i==3){
            display();
        }
        else if(i==4){
            updateEmployee();
        }
        else if(i==5){
            System.exit(0);
        }
        else{
            System.out.println("you entered the no is not in the list plese enter again");
            start();
        };
    }

    private void deleteEmployee() {
        try{
            System.out.print("Enter employee ID to delete: ");
        int id = sc.nextInt(); sc.nextLine();

        boolean removed = el.removeIf(e -> e.getId() == id);

        if (removed) {
            System.out.println("Employee with ID " + id + " deleted.");
            save();
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    } catch (Exception e) {
        System.out.println("Delete operation failed. Please try again.");
    }

    start();
    }

    public void createNew()
        {
            System.out.println("enter the name of the employee");
            sc.nextLine();
            String fullname=sc.nextLine();
            
            System.out.println("enter the salary of the employee");
            int salary=sc.nextInt();
            sc.nextLine();
            el.add(new Employee(fullname,salary));
            try{
                save();
                start();

            }
            catch(Exception e)
            {
            System.out.println("Operation failed please try again.");
            }
            
        }

        public void display() 
        {
            for(Employee e: el)
            {
                // oos.writeObject(e);
                System.out.println("ID: " + e.getId());
                e.display();
            }
            start();
        }

        public void updateEmployee(){
            boolean flag = false;
            System.out.println("Please provide a valid id");
            int id = sc.nextInt();
            sc.nextLine();
            for(Employee e : el){
                int employeeId = e.getId();
                if(id == employeeId){
                    System.out.println("Enter corrected name:");
                    String name = sc.nextLine();
                    System.out.println("Enter updated salary:");
                    int salary = sc.nextInt();
                    sc.nextLine();
                    e.setName(name);
                    e.setSalary(salary);
                    flag = true;
                    break;
                }
            }
            if(flag){
                try{
                    System.out.println("Employee Updated Successfully.");
                save();
            }
            catch(Exception e)
            {
            System.out.println("Operation failed please try again.");
            }
            start();
            } else {
                System.out.println("Employee with " + id + " not found.");
                start();
            }
            
        }

        public void save() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(el);
        oos.close();
    }

    }


class Test{
    public static void main(String args[]) throws Exception
    {
        EmployeeManager e=new EmployeeManager();
        e.start();
    }
}