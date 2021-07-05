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

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
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

    public User(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public User(Long id, @NotNull String username, @NotNull String password, com.rookies.ecommerceapi.entity.Role role,
            @NotNull Short status, com.rookies.ecommerceapi.entity.Customer customer, Employee employee,
            Collection<Order> orders, Collection<Review> reviews) {
        this.id = id;
        this.username = username;
        this.password = password;
        Role = role;
        this.status = status;
        Customer = customer;
        this.employee = employee;
        this.orders = orders;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return Role;
    }

    public void setRole(Role role) {
        Role = role;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer customer) {
        Customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", password=" + password + ", status=" + status + ", username=" + username + "]";
    }

}
