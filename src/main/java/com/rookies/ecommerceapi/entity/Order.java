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

@Entity
@Table(name = "orders")
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

    public Order(Long id, User user, @NotNull Short status, @NotNull LocalDateTime createDate,
            @NotNull LocalDateTime updateDate, @NotNull String receiver, @NotNull String address,
            @NotNull String phoneNumber, @NotNull String paymentMethod, String paymentId, @NotNull Short paymentStatus,
            Integer total, Collection<OrderDetail> orderDetails, Collection<Review> reviews) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.receiver = receiver;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.paymentMethod = paymentMethod;
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.total = total;
        this.orderDetails = orderDetails;
        this.reviews = reviews;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Short getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Short paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Collection<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Order [address=" + address + ", createDate=" + createDate + ", id=" + id + ", orderDetails="
                + orderDetails + ", paymentId=" + paymentId + ", paymentMethod=" + paymentMethod + ", paymentStatus="
                + paymentStatus + ", phoneNumber=" + phoneNumber + ", receiver=" + receiver + ", reviews=" + reviews
                + ", status=" + status + ", total=" + total + ", updateDate=" + updateDate + ", user=" + user + "]";
    }

}
