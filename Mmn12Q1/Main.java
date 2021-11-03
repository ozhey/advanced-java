import java.util.Calendar;

public class Main {

    private final static int NUM_OF_EMPLOYEES = 10;
    public static void main(String[] args) {
        
        Employee[] employees = MockData.generateMockEmployeeData(NUM_OF_EMPLOYEES); // this will return an array of employees with a length of NUM_OF_EMPLOYEES
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

        for (int i = 0; i < employees.length; i++) {    
            System.out.println(employees[i]);
            if (employees[i].getBirthDateMonth() == currentMonth) {
                System.out.println("Salary: $" + (employees[i].earnings() + 200) 
                    + ". Earned extra 200$ because he/she has a birthday this month!");
            } else {
                System.out.println("Salary: $" + employees[i].earnings());
            }
            System.out.println();
        }
    }
}
