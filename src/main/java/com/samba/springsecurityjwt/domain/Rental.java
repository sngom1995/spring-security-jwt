package com.samba.springsecurityjwt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rental {
    @Id
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Car car;

    @ManyToOne
    private User user;
}
