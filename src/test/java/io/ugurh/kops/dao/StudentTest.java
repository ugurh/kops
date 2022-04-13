package io.ugurh.kops.dao;

import io.ugurh.kops.KopsApplication;
import io.ugurh.kops.entity.Student;
import io.ugurh.kops.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KopsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
//@Transactional
public class StudentTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testCRUD() {
        Student student1 = new Student();
        student1.setName("Alex");
        Student mentor1 = new Student();
        mentor1.setName("Fred");
        student1.setMentor(mentor1);
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("Michel");
        Student mentor2 = new Student();
        mentor2.setName("Mac");
        student2.setMentor(mentor2);
        studentRepository.save(student2);

        List<Student> students = studentRepository.findAll();

        assertEquals(4L, students.size());

        Student tempStudent = students.get(1);
        tempStudent.setName("Alex James");
        Student temp = tempStudent.getMentor();
        temp.setName("Fred James");
        studentRepository.save(tempStudent);
        students = studentRepository.findAll();

        assertEquals(4L, students.size());

        tempStudent = students.get(3);
        studentRepository.delete(tempStudent);
        students = studentRepository.findAll();

        assertEquals(2L, students.size());

    }
}
