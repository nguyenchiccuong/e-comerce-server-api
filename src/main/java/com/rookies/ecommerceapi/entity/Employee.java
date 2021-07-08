package com.rookies.ecommerceapi.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees", uniqueConstraints = { @UniqueConstraint(columnNames = { "phone_number" }),
        @UniqueConstraint(columnNames = { "email" }) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank // @NotNull
    private String name;

    @Column(name = "phone_number")
    @NotBlank // @NotNull
    private String phoneNumber;

    @NotNull
    private LocalDate dob;

    @Email
    @NotBlank // @NotNull
    private String email;

    @NotBlank // @NotNull
    private String address;

    @NotNull
    private Boolean sex;

    @Column(name = "create_date")
    @NotNull
    private LocalDateTime createDate;

    @Override
    public String toString() {
        return "Employee [address=" + address + ", createDate=" + createDate + ", dob=" + dob + ", email=" + email
                + ", name=" + name + ", phoneNumber=" + phoneNumber + ", sex=" + sex + ", userId=" + userId + "]";
    }

}
