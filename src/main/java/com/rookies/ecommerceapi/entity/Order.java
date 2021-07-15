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
import javax.persistence.Index;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders", indexes = { @Index(name = "or_ui_index", columnList = "user_id"),
        @Index(name = "or_mulitIndex1", columnList = "create_date, update_date") })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "order_sequence"),
            @Parameter(name = "increment_size", value = "1") })
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private Short status;

    @Column(name = "create_date")
    @NotNull
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address Address;

    @Column(name = "payment_method")
    @NotBlank
    private String paymentMethod;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payment_status")
    @NotNull
    private Short paymentStatus;

    @Transient
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private Integer total;

    @OneToMany(mappedBy = "order")
    private Collection<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "order")
    private Collection<Review> reviews;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order [createDate=" + createDate + ", id=" + id + ", paymentId=" + paymentId + ", paymentMethod="
                + paymentMethod + ", paymentStatus=" + paymentStatus + ", status=" + status + ", total=" + total
                + ", updateDate=" + updateDate + "]";
    }

}
