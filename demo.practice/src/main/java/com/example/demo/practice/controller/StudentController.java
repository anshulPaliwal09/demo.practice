package com.example.demo.practice.controller;

import com.example.demo.practice.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "Anshul", "Paliwal");
        return ResponseEntity.ok().header("customHeader","headerValue").body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"1fn","1ln"));
        students.add(new Student(2,"2fn","2ln"));
        students.add(new Student(3,"3fn","3ln"));
        students.add(new Student(4,"4fn","4ln"));

        return ResponseEntity.ok(students);
    }

    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> getStudentsPathVariable(@PathVariable("id") int studentId,
                                           @PathVariable("first-name") String fName,
                                           @PathVariable("last-name") String lName
                                           ) {
        Student student = new Student(studentId,fName,lName);
        return ResponseEntity.ok(student);
    }

    @GetMapping("query")
    public ResponseEntity<Student> getStudentRequestVariable(@RequestParam int id,
                                             @RequestParam String fN,
                                             @RequestParam String lN) {
        Student student = new Student(id, fN, lN);
        return ResponseEntity.ok(student);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,
                                 @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return ResponseEntity.ok("Deleted Successfully.");
    }
}
