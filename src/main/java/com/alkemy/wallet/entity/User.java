package com.alkemy.wallet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL" , nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;

    @Column(name = "SOFT_DELETE")
    private Boolean softDelete;

    @OneToOne
    @JoinColumn(name="ROLE_ID", referencedColumnName = "ID")
    private Role role;

    @OneToMany(mappedBy = "userId")
    private List<Account> accounts;

}
