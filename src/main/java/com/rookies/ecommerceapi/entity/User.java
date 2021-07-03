package com.rookies.ecommerceapi.entity;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@Data // lombok help generate constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    // @GeneratedValue (strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "user_sequence"),
            // @Parameter(name = "initial_value", value = "0"),
            @Parameter(name = "increment_size", value = "1") })
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    // @Column(name = "role_id")
    // @NotNull
    // private Short roleId;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role Role;

    @NotNull
    private Short status;
    // , cascade = CascadeType.ALL
    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer Customer;
    // , cascade = CascadeType.ALL
    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToMany(mappedBy = "user")
    private Collection<Order> orders;

    @OneToMany(mappedBy = "user")
    private Collection<Review> reviews;

}
