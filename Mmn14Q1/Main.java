import java.util.Iterator;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
        Student s1 = new Student("Yakir","Cohen","204325423","1998");
        Student s2 = new Student("Oz","Perez","112325423","1995");
        Student s3 = new Student("Shiri","Helvits","304532623","1993");
        Student s4 = new Student("Alon","Oren","271236703","2001");
        Student[] students = new Student[]{s1,s2,s3};
        String[] phoneNums = new String[]{"0502486325","0528642057","0505563227"};
        AssociationTable<Student, String> at;
        try {
            at = new AssociationTable<>(students,phoneNums);
            at.add(s4, "0528654793");
            Iterator<Map.Entry<Student,String>> it = at.keyIterator();
            while(it.hasNext()) {
                Map.Entry<Student,String> next = it.next();
                System.out.println("key - student: " + next.getKey());
                System.out.println("value - phone number: " + next.getValue() + "\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
