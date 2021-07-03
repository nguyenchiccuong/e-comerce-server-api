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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "order_id", "product_detail_id" }) })
@Data // lombok help generate constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
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
}
