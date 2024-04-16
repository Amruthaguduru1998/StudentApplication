package com.demo.studentCrud.controller;

import com.demo.studentCrud.Entity.Student;
import com.demo.studentCrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/add")
    public ResponseEntity<Student> addStudentDetails(@RequestBody Student student){
        studentRepository.save(student);
        return  new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("get")
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentRepository.findAll(),HttpStatus.OK);

    }

    @GetMapping("get/{id}")
    public ResponseEntity<Student> getStudentsById(@PathVariable long id){

        Optional<Student> student=studentRepository.findById(id);
        if(student.isPresent())
        return new ResponseEntity<>(student.get(),HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("get/{id}")
    public ResponseEntity<Student> updateStudentsById(@PathVariable long id,@RequestBody Student student){
        Optional<Student> stud=studentRepository.findById(id);
        if(stud.isPresent())
        {
            stud.get().setStudentName(student.getStudentName());
            stud.get().setAge(student.getAge());
            stud.get().setEmail(student.getEmail());
            return new ResponseEntity<>(studentRepository.save(stud.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id){
        Optional<Student> student=studentRepository.findById(id);
        if (student.isPresent()){
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
