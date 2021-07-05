package com.rookies.ecommerceapi.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "reviews", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "order_id", "product_detail_id", "user_id" }) })
public class Review {
        @Id
        // @GeneratedValue (strategy = GenerationType.IDENTITY)
        @GeneratedValue(generator = "sequence-generator")
        @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
                        @Parameter(name = "sequence_name", value = "review_sequence"),
                        // @Parameter(name = "initial_value", value = "0"),
                        @Parameter(name = "increment_size", value = "1") })
        private Long id;

        // @Column(name = "product_detail_id")
        // @NotNull
        // private Long productDetailId;
        @ManyToOne
        @JoinColumn(name = "product_detail_id", nullable = false)
        private ProductDetail productDetail;

        // @Column(name = "user_id")
        // @NotNull
        // private Long userId;
        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        // @Column(name = "order_id")
        // private Long orderId;
        @ManyToOne
        @JoinColumn(name = "order_id")
        private Order order;

        @Column(name = "num_of_star")
        @NotNull
        private Short numOfStar;

        private String description;

        private Short img;

        @Column(name = "create_date")
        @NotNull
        private LocalDateTime createDate;

        @Column(name = "update_date")
        private LocalDateTime updateDate;

        @NotNull
        private Short anonymous;

        @NotNull
        private Short status;

        public Review(Long id, ProductDetail productDetail, User user, Order order, @NotNull Short numOfStar,
                        String description, Short img, @NotNull LocalDateTime createDate, LocalDateTime updateDate,
                        @NotNull Short anonymous, @NotNull Short status) {
                this.id = id;
                this.productDetail = productDetail;
                this.user = user;
                this.order = order;
                this.numOfStar = numOfStar;
                this.description = description;
                this.img = img;
                this.createDate = createDate;
                this.updateDate = updateDate;
                this.anonymous = anonymous;
                this.status = status;
        }

        public Review() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public ProductDetail getProductDetail() {
                return productDetail;
        }

        public void setProductDetail(ProductDetail productDetail) {
                this.productDetail = productDetail;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public Order getOrder() {
                return order;
        }

        public void setOrder(Order order) {
                this.order = order;
        }

        public Short getNumOfStar() {
                return numOfStar;
        }

        public void setNumOfStar(Short numOfStar) {
                this.numOfStar = numOfStar;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Short getImg() {
                return img;
        }

        public void setImg(Short img) {
                this.img = img;
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

        public Short getAnonymous() {
                return anonymous;
        }

        public void setAnonymous(Short anonymous) {
                this.anonymous = anonymous;
        }

        public Short getStatus() {
                return status;
        }

        public void setStatus(Short status) {
                this.status = status;
        }

        @Override
        public String toString() {
                return "Review [anonymous=" + anonymous + ", createDate=" + createDate + ", description=" + description
                                + ", id=" + id + ", img=" + img + ", numOfStar=" + numOfStar + ", order=" + order
                                + ", productDetail=" + productDetail + ", status=" + status + ", updateDate="
                                + updateDate + ", user=" + user + "]";
        }

}
