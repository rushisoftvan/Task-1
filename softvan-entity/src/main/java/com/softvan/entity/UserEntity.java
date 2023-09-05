package com.softvan.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="SOFTVAN_USER")
@DynamicUpdate
public class UserEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name="EMAIL")
    private String email;

    @Column(name="IS_LOCKED")
    private boolean isLocked;

    @Column(name="LOCK_TIME")
    private LocalDateTime lockTime;

    @Column(name="FAILED_ATTEMPT")
    private int failedAttempt;

    @Column(name="LOGIN_COUNT")
    private long loginCount;

    @Column(name="FAILED_ATTEMPT_WINDOW_TIME")
    private LocalDateTime failedAttemptWindowTime;


    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "REGISTRATION_DATE_TIME")
    private LocalDateTime registrationDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private RoleEntity role;

    @PrePersist
    public void beforeInsert(){
        this.registrationDateTime = LocalDateTime.now();
    }

}
