import java.util.Iterator;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
        Student[] students = newStudentsArr();
        String[] phoneNums = new String[]{"0502486325","0528642057","0505563227"};
        AssociationTable<Student, String> assoTable;
        try {
            assoTable = new AssociationTable<>(students,phoneNums);
            Student s4 = new Student("Alon","Oren","271236703","2001");
            assoTable.add(s4, "0528654793");
            Iterator<Map.Entry<Student,String>> iter = assoTable.keyIterator();
            while(iter.hasNext()) {
                Map.Entry<Student,String> next = iter.next();
                System.out.println("key - student: " + next.getKey());
                System.out.println("value - phone number: " + next.getValue() + "\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // arrays not the same size
        }
    }

    // initializes and returns an array of 3 students (constant values)
    private static Student[] newStudentsArr() {
        Student s1 = new Student("Yakir","Cohen","204325423","1998");
        Student s2 = new Student("Oz","Perez","112325423","1995");
        Student s3 = new Student("Shiri","Helvits","304532623","1993");
        return new Student[]{s1,s2,s3};
    }
}
