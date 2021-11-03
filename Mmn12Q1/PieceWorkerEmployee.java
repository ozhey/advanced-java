public class PieceWorkerEmployee extends Employee {
    private int piecesSold; // gross weekly sales
    private double salaryPerPiece; // commission percentage

    // constructor
    public PieceWorkerEmployee(String firstName, String lastName, String socialSecurityNumber, int piecesSold,
            double salaryPerPiece, int year, int month, int dayOfMonth) {
        super(firstName, lastName, socialSecurityNumber, year, month, dayOfMonth);

        if (piecesSold < 0) {
            throw new IllegalArgumentException("Number of pieces sold must be >= 0");
        }

        if (salaryPerPiece < 0) {
            throw new IllegalArgumentException("Salary for each piece must be >= 0");
        }

        this.piecesSold = piecesSold;
        this.salaryPerPiece = salaryPerPiece;
    }

    // set amount of pieces sold
    public void setPiecesSold(int piecesSold) {
        if (piecesSold < 0.0) { // validate
            throw new IllegalArgumentException("Number of pieces sold must be >= 0");
        }

        this.piecesSold = piecesSold;
    }

    // return amount of pieces sold
    public double getPiecesSold() {
        return piecesSold;
    }

    // set salary per piece
    public void setSalaryPerPiece(double salaryPerPiece) {
        if (salaryPerPiece < 0) {
            throw new IllegalArgumentException("Salary for each piece must be >= 0");
        }
        this.salaryPerPiece = salaryPerPiece;
    }

    // return salary per piece
    public double getSalaryPerPeice() {
        return salaryPerPiece;
    }

    // calculate earnings; override abstract method earnings in Employee
    @Override
    public double earnings() {
        return getPiecesSold() * getSalaryPerPeice();
    }

    // return String representation of CommissionEmployee object
    @Override
    public String toString() {
        return String.format("%s: %s%n%s: $%,.2f; %s: $%.2f", "piece worker employee", super.toString(), "pieces sold",
                getPiecesSold(), "salary per piece", getSalaryPerPeice());
    }
}
