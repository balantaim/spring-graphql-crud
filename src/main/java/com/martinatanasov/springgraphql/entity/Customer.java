package com.martinatanasov.springgraphql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(unique = true, length = 50)
    private String email;

    @Column
    private Boolean enabled;

    @Column
    private String gender;

    @Column
    private String address;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.EAGER)
    private Set<Payment> payments = new HashSet<>();


}
