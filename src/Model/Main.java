package Model;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String name;
        int id;
        EmployeeDaoIntrf dao=new EmployeeDaoImpl();
        System.out.println("Welcome to Employee management application");

        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("1. Add Employee\n" +
                    "2. Show All Employee\n" +
                    "3. Show Employee based on id \n" +
                    "4. Update the employee\n" +
                    "5. Delete the employee\n");

            System.out.println("Enter Choice: ");
            int ch=sc.nextInt();
            switch (ch) {
                case 1:
                    Employee emp = new Employee();
                    System.out.println("Enter  ID : ");
                    id = sc.nextInt();
                    System.out.println("Enter name ");
                    name = sc.next();
                    System.out.println("Enter role ");
                    String role = sc.next();
                    System.out.println("Enter AGE : ");
                    int age = sc.nextInt();
                    System.out.println("Enter phone : ");
                    int phone = sc.nextInt();
                    System.out.println("Enter address ");
                    String address = sc.next();
                    System.out.println("Enter familySituation ");
                    String familySituation = sc.next();
                    System.out.println("Enter shift ");
                    String shift = sc.next();
                    System.out.println("Enter email ");
                    String email = sc.next();
                    System.out.println("Enter Salary ");
                    float salary = sc.nextFloat();
                    emp.setId(id);
                    emp.setName(name);
                    emp.setRole(role);
                    emp.setAge(age);
                    emp.setPhone(phone);
                    emp.setAddress(address);
                    emp.setFamilySituation(familySituation);
                    emp.setShift(shift);
                    emp.setEmail(email);
                    emp.setSalary(salary);
                    dao.createEmployee(emp);
                    break;
                case 6:
                    System.out.println("Thank you for using our Application !!!");
                    System.exit(0);
                default:
                    System.out.println("Enter valid choice !");
                    break;
            }
        }while(true);
    }
}