package com.rookies.ecommerceapi.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Index;
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
                @UniqueConstraint(columnNames = { "order_id", "product_detail_id", "user_id" }) }, indexes = {
                                @Index(name = "rv_pdi_index", columnList = "product_detail_id"),
                                @Index(name = "rv_mulitIndex1", columnList = "create_date, update_date"),
                                @Index(name = "rv_ui_index", columnList = "user_id"),
                                @Index(name = "rv_oi_index", columnList = "order_id") })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {
        @Id
        @GeneratedValue(generator = "sequence-generator")
        @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
                        @Parameter(name = "sequence_name", value = "review_sequence"),
                        @Parameter(name = "increment_size", value = "1") })
        private Long id;

        @ManyToOne
        @JoinColumn(name = "product_detail_id", nullable = false)
        private ProductDetail productDetail;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

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
