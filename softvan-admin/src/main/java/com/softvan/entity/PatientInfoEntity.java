package com.softvan.entity;


import com.softvan.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="patietinfo")
public class PatientInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;

    @Column(name= "HAS_ALLERGY")
    private Boolean hasAllergy;

    @Column(name="HAS_BLOOD_PRESSURE")
    private Boolean hasBloodPressure;


}
