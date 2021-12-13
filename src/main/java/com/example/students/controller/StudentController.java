package com.example.students.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.students.entity.Student;
import com.example.students.repository.StudentRepository;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {

    // Injeta um dependencia!!!!
    @Autowired
    private StudentRepository repo;

    // End Point 1
    // (GET) http://localhost:8080/students
    @GetMapping
    public List<Student> getStudents() {
        List<Student> list = repo.findAll();
        return list;
    }

    // End Point 2
    // (GET) http://localhost:8080/students/{id}
    @GetMapping("{id}")
    public Student getStudent(@PathVariable int id) {
        Optional<Student> op =  repo.findById(id);
        Student student = op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return student;
    }

    // EndPoint 3
    // Save
    @PostMapping
    public Student save(@RequestBody Student student) {
        student = repo.save(student);
        return student;
    }

     //EndPoint 4
    // Delete
    @DeleteMapping("{id}")
    public void remove(@PathVariable Integer id){
        Optional<Student> op =  repo.findById(id);
        Student student = op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repo.delete(student);
    }

       //EndPoint 5
    // Put
    @PutMapping("{id}")
    public Student update(@RequestBody Student updateStudent, @PathVariable Integer id){
        Optional<Student> op =  repo.findById(id);
        Student student = op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        student.setName(updateStudent.getName());
        student.setSex(updateStudent.getSex());
        student.setCity(updateStudent.getCity());
        student.setEmail(updateStudent.getEmail());
        student.setCourse(updateStudent.getCourse());
        repo.save(student);
        return student;
    }



}
