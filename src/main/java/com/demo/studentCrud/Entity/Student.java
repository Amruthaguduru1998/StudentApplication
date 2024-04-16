package com.demo.studentCrud.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String studentName;
    private int age;
    private  String email;
}
