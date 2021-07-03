package com.rookies.ecommerceapi.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "orders")
@Data // lombok help generate constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    // @GeneratedValue (strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "order_sequence"),
            // @Parameter(name = "initial_value", value = "0"),
            @Parameter(name = "increment_size", value = "1") })
    private Long id;

    // @Column(name = "user_id")
    // @NotNull
    // private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private Short status;

    @Column(name = "create_date")
    @NotNull
    private LocalDateTime createDate;

    @Column(name = "update_date")
    @NotNull
    private LocalDateTime updateDate;

    @NotNull
    private String receiver;

    @NotNull
    private String address;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @Column(name = "payment_method")
    @NotNull
    private String paymentMethod;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payment_status")
    @NotNull
    private Short paymentStatus;

    @Transient
    private Integer total;

    @OneToMany(mappedBy = "order")
    private Collection<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "order")
    private Collection<Review> reviews;
}
