package com.example.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.StudentManagement.model.Student;
import com.example.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
    
    @Cacheable(value = "students", key = "#name")
    public Student getStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setClassName(studentDetails.getClassName());
        student.setPhoneNumber(studentDetails.getPhoneNumber());
        return studentRepository.save(student);
    }
    
    

}
