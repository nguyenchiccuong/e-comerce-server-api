package com.rookies.ecommerceapi.entity;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Organizations", uniqueConstraints = { @UniqueConstraint(columnNames = { "organization_name" }) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Organization {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "organization_sequence"),
            @Parameter(name = "increment_size", value = "1") })
    private Integer id;

    @Column(name = "organization_name")
    @NotBlank
    private String organizationName;

    private Short img;

    @OneToMany(mappedBy = "Organization")
    private Collection<Brand> Brands;

    @OneToMany(mappedBy = "Organization")
    private Collection<Address> Addresss;

}
