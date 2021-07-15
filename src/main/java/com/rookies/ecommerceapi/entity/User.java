package com.rookies.ecommerceapi.entity;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) }, indexes = {
        @Index(name = "us_un_index", columnList = "username", unique = true) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "user_sequence"),
            @Parameter(name = "increment_size", value = "1") })
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role Role;

    @NotNull
    private Short status;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer Customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToMany(mappedBy = "user")
    private Collection<Order> orders;

    @OneToMany(mappedBy = "user")
    private Collection<Review> reviews;

    public User(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", password=" + password + ", status=" + status + ", username=" + username + "]";
    }

}
