package org.example;

import static org.junit.Assert.assertTrue;

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

import java.io.Console;
import java.time.LocalDate;

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
    public void RemoveStudent()
    {
        Student s= new Student("1234","test",1, "test@gmail.com");
        service.addStudent(s);
        assert ( service.findStudent("1234")!=null);
        service.deleteStudent("1234");
        assert ( service.findStudent("1234")==null);
    }

    @Test
    public void UpdateStudent()
    {
        Student s= new Student("1234","test",1, "test@gmail.com");
        service.addStudent(s);
        Student s2= new Student("1234","testupdated",1, "test@gmail.com");
        service.updateStudent(s2);
        Student check=service.findStudent("1234");
        assert (check.getNume().equals("testupdated"));
    }

    @Test
    public void AddTema()
    {
        service.deleteTema("1234");
        Tema t=new Tema("1234","test",1,1);
        service.addTema(t);
        assert(service.findTema("1234")!=null);
    }

    @Test
    public void RemoveTema()
    {
        Tema t=new Tema("1234","test",1,1);
        service.addTema(t);
        assert(service.findTema("1234")!=null);
        service.deleteTema("1234");
        assert(service.findTema("1234")==null);
    }

    @Test
    public void UpdateTema()
    {
        Tema t=new Tema("1234","test",1,1);
        service.addTema(t);
        Tema t2=new Tema("1234","testupdated",1,1);
        service.updateTema(t2);
        Tema t3=service.findTema("1234");
        assert(t3.getDescriere().equals("testupdated"));
    }

    @Test
    public void AddNota()
    {
        LocalDate dataPredare = LocalDate.of(2018, 10,20);
        Nota n=new Nota("1234","1","2",10, dataPredare);
        service.addNota(n,"bun");
        assert(service.findNota("1234")!=null);
    }

    @Test
    public void RemoveNota()
    {

        LocalDate dataPredare = LocalDate.of(2018, 10,20);
        Nota n=new Nota("1234","1","2",10, dataPredare);
        service.addNota(n,"bun");
        assert(service.findNota("1234")!=null);
        service.deleteNota("1234");
        assert(service.findNota("1234")==null);
    }

    @Test
    public void TestGeturiNota()
    {
        LocalDate dataPredare = LocalDate.of(2018, 10,20);
        Nota n=new Nota("1234","1","2",10, dataPredare);
        service.addNota(n,"bun");
        assert (service.findNota("1234").getNota()==10);
        assert (service.findNota("1234").getID()=="1234");
        assert (service.findNota("1234").getIdStudent()=="1");
        assert (service.findNota("1234").getIdTema()=="2");
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
        service.deleteTema("1234");
        Tema t=new Tema("1234","test",1,1);
        service.addTema(t);
        assert(service.findTema("1234")!=null);
    }


}
