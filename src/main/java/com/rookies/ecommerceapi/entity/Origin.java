package com.rookies.ecommerceapi.entity;

import java.util.Collection;
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
@Table(name = "origins", uniqueConstraints = { @UniqueConstraint(columnNames = { "country" }) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Origin {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "origin_sequence"),
            @Parameter(name = "increment_size", value = "1") })
    private Integer id;

    @NotBlank 
    private String country;

    @OneToMany(mappedBy = "origin")
    private Collection<Product> products;

    @Override
    public String toString() {
        return "Origin [country=" + country + ", id=" + id + "]";
    }

}
