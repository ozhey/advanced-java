// Represents an employee in the factory. 
public abstract class Employee {

   private final String firstName;
   private final String lastName;
   private final String socialSecurityNumber;
   private final BirthDate birthDate;

   // constructor
   public Employee(String firstName, String lastName, String socialSecurityNumber, int yearOfBirth, int monthOfBirth,
         int dayOfBirth) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.socialSecurityNumber = socialSecurityNumber;
      this.birthDate = new BirthDate(yearOfBirth, monthOfBirth, dayOfBirth);
   }

   // return first name
   public String getFirstName() {
      return firstName;
   }

   // return last name
   public String getLastName() {
      return lastName;
   }

   // return social security number
   public String getSocialSecurityNumber() {
      return socialSecurityNumber;
   }

   public BirthDate getBirthDate() {
      return birthDate;
   }

   public int getBirthDateMonth() {
      return birthDate.getMonth();
   }

   // return String representation of Employee object
   @Override
   public String toString() {
      return String.format("%s %s%nsocial security number: %s%n%s", getFirstName(), getLastName(),
            getSocialSecurityNumber(), getBirthDate().toString());
   }

   // abstract method must be overridden by concrete subclasses
   public abstract double earnings(); // no implementation here
}
