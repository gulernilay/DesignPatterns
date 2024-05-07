package Facade.Example3;

//Soru: Öğrenci Bilgi Sistemi
//
//Bir okul, öğrencilerin ders notlarını görüntülemesi ve akademik bilgilerini takip etmesi için bir sistem geliştirmek istiyor. Bu sistem, öğrencilerin ders notlarını sorgulamasını, ders programlarını görüntülemesini ve devamsızlık durumlarını kontrol etmesini sağlamalıdır. Facade tasarım desenini kullanarak bu işlemleri basitleştirecek bir sistem tasarlayın.
//
//Gereksinimler:
//
//Öğrenciler, ders notlarını sorgulayabilmelidir.
//Öğrenciler, ders programlarını görüntüleyebilmelidir.
//Öğrenciler, devamsızlık durumlarını kontrol edebilmelidir.
//Sistem, öğrencilerin güvenli bir şekilde akademik bilgilerini sorgulamasını sağlamalıdır.


class Student{
    protected int id;
    public Student(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
}
//Fascade System
class InformationSystem{
    private Grades gd;
    private Schedule sc;
    private Absenteeism ab;

    public InformationSystem(){
        gd=new Grades();
        sc=new Schedule();
        ab=new Absenteeism();
    }
    public void CheckStudentInfo(Student st){
        gd.DisplayGrade(st);
        sc.DisplaySchedule(st);
        ab.DisplayAbsenteeism(st);
    }

}
//Subsystem1
class Grades{
    public void DisplayGrade(Student st){
        System.out.println("The student with number " + "" +st+" grade is: " + 92);
  }
}
//Subsystem2
class Schedule{
    public void DisplaySchedule(Student st){
        System.out.println("The student with number " + "" +st +" schedule is displayed below. " );
    }

}
//Subsystem3
class Absenteeism{
    public void DisplayAbsenteeism(Student st){
        System.out.println("The student with number " + "" +st +" has 3 days absent. " );
    }

}


public class Example3 {
    public static void main(String[] args) {
        Student student1=new Student(55);
        InformationSystem it=new InformationSystem();
        it.CheckStudentInfo(student1);
    }
}
