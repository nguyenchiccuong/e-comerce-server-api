package com.rookies.ecommerceapi.entity;

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
@Table(name = "order_details", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "order_id", "product_detail_id" }) })
public class OrderDetail {
        @Id
        // @GeneratedValue (strategy = GenerationType.IDENTITY)
        @GeneratedValue(generator = "sequence-generator")
        @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
                        @Parameter(name = "sequence_name", value = "order_detail_sequence"),
                        // @Parameter(name = "initial_value", value = "0"),
                        @Parameter(name = "increment_size", value = "1") })
        private Long id;

        // @Column(name = "order_id")
        // @NotNull
        // private Long orderId;
        @ManyToOne
        @JoinColumn(name = "order_id", nullable = false)
        private Order order;

        // @Column(name = "product_detail_id")
        // @NotNull
        // private Long productDetailId;
        @ManyToOne
        @JoinColumn(name = "product_detail_id", nullable = false)
        private ProductDetail productDetail;

        @NotNull
        private Integer quantity;

        @NotNull
        private Long price;

        public OrderDetail(Long id, Order order, ProductDetail productDetail, @NotNull Integer quantity,
                        @NotNull Long price) {
                this.id = id;
                this.order = order;
                this.productDetail = productDetail;
                this.quantity = quantity;
                this.price = price;
        }

        public OrderDetail() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Order getOrder() {
                return order;
        }

        public void setOrder(Order order) {
                this.order = order;
        }

        public ProductDetail getProductDetail() {
                return productDetail;
        }

        public void setProductDetail(ProductDetail productDetail) {
                this.productDetail = productDetail;
        }

        public Integer getQuantity() {
                return quantity;
        }

        public void setQuantity(Integer quantity) {
                this.quantity = quantity;
        }

        public Long getPrice() {
                return price;
        }

        public void setPrice(Long price) {
                this.price = price;
        }

        @Override
        public String toString() {
                return "OrderDetail [id=" + id + ", price=" + price + ", quantity=" + quantity + "]";
        }

}
