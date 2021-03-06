package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.io.Console;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "fisiere/Studenti.xml";
    String filenameTema = "fisiere/Teme.xml";
    String filenameNota = "fisiere/Note.xml";

    //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
    //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
    //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
    //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);

    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void AddStudent()
    {
        service.deleteStudent("100");
        Student s= new Student("100","test",1, "test@gmail.com");
        service.addStudent(s);
        assert ( service.findStudent("100")!=null);
    }

    @Test
    public void AddStudent2(){
        service.deleteStudent("100");
        Student s= new Student("100","test",1, "test@gmail.com");
        service.addStudent(s);
        Student s2= service.findStudent("100");
        assert(s2.getID()=="100");
    }

    @Test
    public void AddStudent3(){
        service.deleteStudent("100");
        Student s= new Student("100","test",1, "test@gmail.com");
        service.addStudent(s);
        Student s2= service.findStudent("100");
        assert(s2.getNume()=="test");
    }

    @Test
    public void AddStudent4(){
        service.deleteStudent("100");
        Student s= new Student("100","test",1, "test@gmail.com");
        service.addStudent(s);
        Student s2= service.findStudent("100");
        assert(s2.getEmail()=="test@gmail.com");
    }

    @Test
    public void AddStudent5(){
        service.deleteStudent("100");
        Student s= new Student("100","test",1, "test@gmail.com");
        service.addStudent(s);
        Student s2= service.findStudent("100");
        assert(s2.getGrupa()==1);
    }

    @Test
    public void AddStudent6(){
        service.deleteStudent("100");
        boolean thrown = false;
        Student s= new Student("","test",1, "test@gmail.com");
        try {
            service.addStudent(s);
        }catch (ValidationException v)
        {
            thrown=true;
        }
        assert(thrown);
    }

    @Test
    public void AddStudent7(){
        service.deleteStudent("100");
        boolean thrown = false;
        Student s= new Student("","test",1, "test@gmail.com");
        try {
            service.addStudent(s);
        }catch (ValidationException v)
        {
            if(v.getMessage()=="Id incorect!")
                thrown=true;
        }
        assert(thrown);
    }

    @Test
    public void AddStudent8(){
        service.deleteStudent("100");
        boolean thrown = false;
        Student s= new Student("100","",1, "test@gmail.com");
        try {
            service.addStudent(s);
        }catch (ValidationException v)
        {
            if(v.getMessage()=="Nume incorect!")
                thrown=true;
        }
        assert(thrown);
    }

    @Test
    public void AddStudent9(){
        service.deleteStudent("100");
        boolean thrown = false;
        Student s= new Student("100","test",1, "");
        try {
            service.addStudent(s);
        }catch (ValidationException v)
        {
            if(v.getMessage()=="Email incorect!")
                thrown=true;
        }
        assert(thrown);
    }

    @Test
    public void AddStudent10(){
        service.deleteStudent("100");
        boolean thrown = false;
        Student s= new Student("100","test",-1, "test@gmail.com");
        try {
            service.addStudent(s);
        }catch (ValidationException v)
        {
            if(v.getMessage()=="Grupa incorecta!")
                thrown=true;
        }
        assert(thrown);
    }



    //Lab2
    @Test
    public void AddNewTema()
    {
        service.deleteTema("1234");
        Tema t=new Tema("1234","test",1,1);
        service.addTema(t);
        assert(service.findTema("1234")!=null);
    }

    @Test
    public void AddNewTema2()
    {
        Tema t2=new Tema("1234","test",1,1);
        service.addTema(t2);
        Tema t=new Tema("1234","test",1,1);
        t=service.addTema(t);

        assertEquals(t,null);

    }

    @Test
    public void AddNewTema3()
    {
        boolean thrown = false;
        Tema t=new Tema("","test",1,1);

        try {
            service.addTema(t);
        }catch (ValidationException v)
        {
            if(v.getMessage()=="Numar tema invalid!")
                thrown=true;
        }
        assert(thrown);
    }

    @Test
    public void AddNewTema4()
    {
        boolean thrown = false;
        Tema t=new Tema("1234","",1,1);

        try {
            service.addTema(t);
        }catch (ValidationException v)
        {
            if(v.getMessage()=="Descriere invalida!")
                thrown=true;
        }
        assert(thrown);
    }

    @Test
    public void AddNewTema5()
    {
        boolean thrown = false;
        Tema t=new Tema("1234","test",-1,1);

        try {
            service.addTema(t);
        }catch (ValidationException v)
        {
            if(v.getMessage()=="Deadlineul trebuie sa fie intre 1-14.")
                thrown=true;
        }
        assert(thrown);
    }

    @Test
    public void AddNewTema6()
    {
        boolean thrown = false;
        Tema t=new Tema("1234","test",1,-1);

        try {
            service.addTema(t);
        }catch (ValidationException v)
        {
            if(v.getMessage()=="Saptamana primirii trebuie sa fie intre 1-14.")
                thrown=true;
        }
        assert(thrown);
    }


    //lab3

    @Test
    public void AddStudentT()
    {
        service.deleteStudent("100");
        Student s= new Student("100","test",1, "test@gmail.com");
        service.addStudent(s);
        assert ( service.findStudent("100")!=null);
    }

    @Test
    public void AddAssigmentT()
    {
        service.deleteTema("1234");
        Tema t=new Tema("1234","test",1,1);
        service.addTema(t);
        assert(service.findTema("1234")!=null);
    }

    @Test
    public void AddGradeT()
    {
        LocalDate dataPredare = LocalDate.of(2018, 10,20);
        Nota n=new Nota("1234","1","2",10, dataPredare);
        service.addNota(n,"bun");
        assert(service.findNota("1234")!=null);
    }

    @Test
    public void AddT()
    {
        service.deleteStudent("100");
        Student s= new Student("100","test",1, "test@gmail.com");
        service.addStudent(s);

        service.deleteTema("1234");
        Tema t=new Tema("1234","test",3,2);
        service.addTema(t);


        LocalDate dataPredare = LocalDate.of(2018, 10,20);
        Nota n=new Nota("1234","100","1234",10, dataPredare);
        service.addNota(n,"bun");
        assert(service.findNota("1234")!=null);
    }

    //lab4
    @Test
    public void AddStudentlab4()
    {
        service.deleteStudent("100");
        Student s= new Student("100","test",1, "test@gmail.com");
        service.addStudent(s);
        assert ( service.findStudent("100")!=null);
    }

    @Test
    public void AddStudentandAssigmentlab4()
    {
        service.deleteStudent("100");
        Student s= new Student("100","test",1, "test@gmail.com");
        service.addStudent(s);
        assert ( service.findStudent("100")!=null);
        service.deleteTema("1234");
        Tema t=new Tema("1234","test",1,1);
        service.addTema(t);
        assert(service.findTema("1234")!=null);
    }

    @Test
    public void AddGradelab4()
    {
        service.deleteStudent("100");
        Student s= new Student("100","test",1, "test@gmail.com");
        service.addStudent(s);

        service.deleteTema("1234");
        Tema t=new Tema("1234","test",3,2);
        service.addTema(t);


        LocalDate dataPredare = LocalDate.of(2018, 10,20);
        Nota n=new Nota("1234","100","1234",10, dataPredare);
        service.addNota(n,"bun");
        assert(service.findNota("1234")!=null);
    }


}
