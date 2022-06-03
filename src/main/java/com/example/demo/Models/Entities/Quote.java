package com.example.demo.Models.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="quotes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quote {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String type;

    private String text;

    private String author;

}

