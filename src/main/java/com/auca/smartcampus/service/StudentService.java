package com.auca.smartcampus.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auca.smartcampus.domain.Student;
import com.auca.smartcampus.repository.StudentRepository;

@Service
public class StudentService 
{
    @Autowired
    private StudentRepository studentRepo;

    public String saveStudent(Student student) {
        studentRepo.save(student);
        return "Student saved successfully";
    }

    public List<Student> getAllStudents() {
         return studentRepo.findAll();
      }

    public Optional<Student> getStudentById(UUID id) {
        return studentRepo.findById(id);
      }

    public String updateStudent(UUID id, Student updated) {
        Optional<Student> existing = studentRepo.findById(id);
        if (existing.isEmpty()) {
            return "The student with that id does not exist";
        }
        Student student = existing.get();
        student.setFirstName(updated.getFirstName());
        student.setLastName(updated.getLastName());
        student.setRegNumber(updated.getRegNumber());
        student.setFaculty(updated.getFaculty());
        studentRepo.save(student);
        return "Student updated successfully";
    }

    public String deleteStudent(UUID id) {
        if (studentRepo.findById(id).isEmpty()) {
            return "The student with that id does not exist";
        }
        studentRepo.deleteById(id);
        return "Student deleted successfully";
    }
}
