package com.softvan.entity;

import com.softvan.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="patient")
@Getter
@Setter
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;

    @Override
    public String toString() {
        return "PatientEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", status=" + status +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", patientInfoEntity=" + patientInfoEntity +
                '}';
    }

    @Column(name="First_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="EMAIL")
    private String email;

    @Column(name="DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "ACTIVE_STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;


    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;

    @Column(name = "UPDATED_DATE_TIME")
    private LocalDateTime updatedDateTime;

    @OneToOne( fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name="PATIENT_INFO_ID")
    private PatientInfoEntity patientInfoEntity;

    @PrePersist
    public void beforePersist() {
        this.createdDateTime = LocalDateTime.now();
        this.updatedDateTime = LocalDateTime.now();
        this.status = StatusEnum.ACTIVE;
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedDateTime = LocalDateTime.now();
    }


}
