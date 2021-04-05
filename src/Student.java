import java.io.*;

class Student implements Serializable{
    private static final long serialVersionUID = 1L;
    String name; int note;
    Student(String name, int note){this.name = name;this.note = note;}
    public String toString(){
        return name+"   "+note;
    }
}