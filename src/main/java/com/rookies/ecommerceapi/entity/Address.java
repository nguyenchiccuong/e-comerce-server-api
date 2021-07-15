package com.rookies.ecommerceapi.entity;

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
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Addresss", indexes = { @Index(name = "ad_ui_index", columnList = "user_id"),
        @Index(name = "ad_oi_index", columnList = "organization_id"),
        @Index(name = "ad_cn_index", columnList = "contact_name"),
        @Index(name = "ad_cn_index2", columnList = "contact_number") })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "address_sequence"),
            @Parameter(name = "increment_size", value = "1") })
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization Organization;

    @Column(name = "street_and_number")
    @NotBlank
    private String streetAndNumber;

    @NotBlank
    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String ward;

    private String detail;

    @Column(name = "contact_name")
    @NotBlank
    private String contactName;

    @Column(name = "contact_number")
    @NotBlank
    private String contactNumber;

    @NotBlank
    private String tag;

    @Column(name = "default_address")
    private Boolean defaultAddress;

    @Column(name = "payment_address")
    private Boolean paymentAddress;

    @OneToMany(mappedBy = "Address")
    private Collection<Order> orders;

}
