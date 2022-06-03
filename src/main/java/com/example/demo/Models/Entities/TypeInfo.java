package com.example.demo.Models.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "info")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String type_label;

    private String image_url;

}
