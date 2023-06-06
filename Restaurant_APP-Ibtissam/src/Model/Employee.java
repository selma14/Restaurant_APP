package Model;

public class Employee {
    private
        int id,age,phone;
        float salary;
        String name,role,address,shift,familySituation,email;
    public Employee(int id, int age, int phone, float salary, String name, String role, String address, String shift, String familySituation, String email) {
        this.id = id;
        this.age = age;
        this.phone = phone;
        this.salary = salary;
        this.name = name;
        this.role = role;
        this.address = address;
        this.shift = shift;
        this.familySituation = familySituation;
        this.email = email;
    }

    public Employee() {

    }

    public int getId() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public int getPhone() {
        return phone;
    }
    public float getSalary() {
        return salary;
    }
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public String getAddress() {
        return address;
    }
    public String getShift() {
        return shift;
    }
    public String getFamilySituation() {
        return familySituation;
    }
    public String getEmail() {
        return email;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }
    public void setFamilySituation(String familySituation) {
        this.familySituation = familySituation;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", age=" + age +
                ", phone=" + phone +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", address='" + address + '\'' +
                ", shift='" + shift + '\'' +
                ", familySituation='" + familySituation + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
