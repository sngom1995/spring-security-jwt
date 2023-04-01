package com.samba.springsecurityjwt.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
    @Id
    private Integer id;
    private String name;
    private String model;
    private String color;
    private String year;
    private String price;
    private String description;
    private String image;
}
