import java.util.Random;

// MockData generates an array of random employees of different types in order to test the program.
public final class MockData {

    private static final String[] firstNames = { "Luke", "Celina", "Barney", "Holly", "Ramon", "Wade", "Domenic",
            "Nate", "Marilyn", "Macy", "Rose", "Charlize", "Nathan", "Vera", "Marina", "John", "Kenzie", "Rachael",
            "Jenna", "Julian", "Peyton", "Wade", "Celia", "Livia", "Aleksandra", "Shelby", "Shannon", "Doug", "Nancy",
            "Cara", "Rihanna", "Benny", "Gil", "Candice", "Eve", "Davina", "Aiden", "Isla", "Eduardo", "Leroy",
            "Catherine", "Caleb", "Tyler", "Matt", "Erin", "Amelia", "Andie", "Carmella", "Julian", "Jacob", "Beatrice",
            "Jessica", "Anthony", "Nathan", "Johnathan", "Kirsten", "Mary", "Jackeline", "Makena", "Rosalee" };
    private static final String[] lastNames = { "Weston", "Utterson", "Vincent", "Fowler", "Holt", "Ross", "Mullins",
            "Cadman", "Yard", "Pickard", "Silva", "Mcleod", "Hopkins", "Nash", "Ebden", "Little", "Tate", "Cunningham",
            "Duvall", "Rodgers", "Jefferson", "Graves", "Broomfield", "Broomfield", "Sheldon", "Ellwood", "Eyres",
            "Rust", "Ogilvy", "Robinson", "Atkinson", "Morrow", "Phillips", "Avery", "Murray", "Jordan", "Roth", "Snow",
            "Wilson", "Ainsworth", "Oakley", "Jefferson", "Lynn", "Rose", "Speed", "Mcnally", "Mitchell", "Adler",
            "Addison", "Jeffery", "Clarke" };
    private static final String[] socialSecurityNumbers = { "625-26-4216", "375-10-7040", "646-11-8460", "117-10-8561",
            "274-25-6224", "040-02-1101", "751-66-0310", "727-54-3666", "447-87-7342", "007-60-8770", "050-47-4630",
            "538-63-4355", "276-22-2630", "112-77-5260", "505-22-4771", "236-22-3340", "262-76-3442", "764-33-6374",
            "885-24-4030", "277-74-3588", "772-17-6180", "588-07-4744", "302-76-3326", "382-16-4650", "261-71-4444",
            "128-30-8718", "680-87-4070", "641-32-2457", "357-62-2710", "688-33-5506", "827-66-2460", "278-68-4144",
            "085-60-5438", "030-56-2542", "868-52-5243", "100-41-8051", "021-11-3333", "860-51-4116", "535-04-5222",
            "851-14-1142", "064-42-7127", "247-52-0131", "486-58-7047", "261-84-5104", "201-68-1687", "333-10-1065",
            "447-54-6075", "530-23-1473", "868-47-0051", "415-08-6055", "665-36-1514", "618-15-2608", "585-74-7555",
            "844-22-6471", "648-78-6434", "125-32-7746", "071-67-0455", "038-25-6184", "860-73-1242", "051-54-7048",
            "806-73-3038" };

    private static enum EmployeeType {
        BASE_PLUS_COMMISSION,
        COMMISSION,
        HOURLY,
        PIECE_WORKER,
        SALARIED
    }

    private static final EmployeeType[] employeeTypes = EmployeeType.values();

    private static final Random rnd = new Random();

    // private constructor to avoid unnecessary intantiation of the class
    private MockData() {
    }

    // Returns an array of random employees with the length of numOfEmployees
    public static Employee[] generateMockEmployeeData(int numOfEmployees) {
        Employee[] employees = new Employee[numOfEmployees];
        for (int i = 0; i < employees.length; i++) {
            EmployeeType employeeType = employeeTypes[rnd.nextInt(employeeTypes.length)];
            String firstName = firstNames[rnd.nextInt(firstNames.length)];
            String lastName = lastNames[rnd.nextInt(lastNames.length)];
            String socialSecurityNumber = socialSecurityNumbers[rnd.nextInt(socialSecurityNumbers.length)];
            int yearOfBirth = rnd.nextInt(65) + 1940; // 1940 to 2004
            int monthOfBirth = rnd.nextInt(12);
            int dayOfBirth = rnd.nextInt(29);
            switch (employeeType) {
            case COMMISSION:
                employees[i] = getMockCommissionEmployee(firstName, lastName, socialSecurityNumber, yearOfBirth,  monthOfBirth, dayOfBirth);
                break;
            case BASE_PLUS_COMMISSION:
                employees[i] = getMockBasePlusCommissionEmployee(firstName, lastName, socialSecurityNumber, yearOfBirth,  monthOfBirth, dayOfBirth);
                break;
            case HOURLY:
                employees[i] = getMockHourlyEmployee(firstName, lastName, socialSecurityNumber, yearOfBirth,  monthOfBirth, dayOfBirth);
                break;
            case SALARIED:
                employees[i] = getMockSalariedEmployee(firstName, lastName, socialSecurityNumber, yearOfBirth,  monthOfBirth, dayOfBirth);
                break;
            case PIECE_WORKER:
                employees[i] = getMockPieceWorkerEmployee(firstName, lastName, socialSecurityNumber, yearOfBirth,  monthOfBirth, dayOfBirth);
                break;
            default:
                break;
            }
        }
        return employees;
    }

    // Returns a random commission employee
    private static CommissionEmployee getMockCommissionEmployee(String firstName, String lastName, String socialSecurityNumber, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        double grossSales = rnd.nextDouble() * 55000 + 5000; // 5000 to 60000
        double commissionRate = rnd.nextDouble() / 10; // 0 to 0.1
        return new CommissionEmployee(firstName, lastName, socialSecurityNumber, grossSales, commissionRate,
                yearOfBirth, monthOfBirth, dayOfBirth);
    }

    // Returns a random piece worker employee
    private static PieceWorkerEmployee getMockPieceWorkerEmployee(String firstName, String lastName, String socialSecurityNumber, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        int piecesSold = rnd.nextInt(3000);
        double salaryPerPiece = rnd.nextDouble() * 10;
        return new PieceWorkerEmployee(firstName, lastName, socialSecurityNumber, piecesSold, salaryPerPiece,
                yearOfBirth, monthOfBirth, dayOfBirth);
    }

    // Returns a random hourly employee
    private static HourlyEmployee getMockHourlyEmployee(String firstName, String lastName, String socialSecurityNumber, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        double wage = rnd.nextDouble() * 30 + 10; // 10 to 40 $ per hour
        double hours = rnd.nextDouble() * 168; // 0.0 to 168.0 hours
        return new HourlyEmployee(firstName, lastName, socialSecurityNumber, wage, hours, yearOfBirth, monthOfBirth,
                dayOfBirth);
    }

    // Returns a random base plus commission employee
    private static BasePlusCommissionEmployee getMockBasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        double grossSales = rnd.nextDouble() * 55000 + 5000; // 5000.0 to 60000.0
        double commissionRate = rnd.nextDouble() / 10; // 0.0 to 0.1
        double baseSalary = rnd.nextDouble() * 5000; // 0.0 to 5000.0
        return new BasePlusCommissionEmployee(firstName, lastName, socialSecurityNumber, grossSales, commissionRate,
                baseSalary, yearOfBirth, monthOfBirth, dayOfBirth);
    }

    // Returns a random salaried employee
    private static SalariedEmployee getMockSalariedEmployee(String firstName, String lastName, String socialSecurityNumber, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        double weeklySalary = rnd.nextDouble() * 3000 + 300; // 300.0 to 3300.0
        return new SalariedEmployee(firstName, lastName, socialSecurityNumber, weeklySalary, yearOfBirth, monthOfBirth,
                dayOfBirth);
    }

}
