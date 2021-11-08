public class Main {

    private final static int NUM_OF_EMPLOYEES = 10;
    public static void main(String[] args) {
        
        Employee[] employees = MockData.generateMockEmployeeData(NUM_OF_EMPLOYEES); // this will return an array of employees with a length of NUM_OF_EMPLOYEES
        for (int i = 0; i < employees.length; i++) {    
            System.out.println(employees[i] + "\n");
        }
    }
}
