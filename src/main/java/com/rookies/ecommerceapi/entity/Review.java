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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "order_id", "product_detail_id", "user_id" }) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

        @Override
        public String toString() {
                return "Review [anonymous=" + anonymous + ", createDate=" + createDate + ", description=" + description
                                + ", id=" + id + ", img=" + img + ", numOfStar=" + numOfStar + ", status=" + status
                                + ", updateDate=" + updateDate + "]";
        }

}
