package com.rookies.ecommerceapi.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "order_id", "product_detail_id" }) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail {
        @Id
        @GeneratedValue(generator = "sequence-generator")
        @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
                        @Parameter(name = "sequence_name", value = "order_detail_sequence"),
                        @Parameter(name = "increment_size", value = "1") })
        private Long id;

        @ManyToOne
        @JoinColumn(name = "order_id", nullable = false)
        private Order order;

        @ManyToOne
        @JoinColumn(name = "product_detail_id", nullable = false)
        private ProductDetail productDetail;

        @NotNull
        private Integer quantity;

        @NotNull
        private Long price;

        @Override
        public String toString() {
                return "OrderDetail [id=" + id + ", price=" + price + ", quantity=" + quantity + "]";
        }

}
