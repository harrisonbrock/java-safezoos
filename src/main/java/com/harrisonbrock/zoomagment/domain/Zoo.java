package com.harrisonbrock.zoomagment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "zoos")
public class Zoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "zooId")
    private long id;

    @Column(name = "zooName")
    @NotBlank
    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "zoo_phone_number", joinColumns = @JoinColumn(name ="zooid"))
    @AttributeOverrides({
            @AttributeOverride(name = "phoneType", column = @Column(name = "phone_type")),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "phone_number"))
    })
    private Set<TelephoneNumber> telephoneNumbers = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "zoo_animals", joinColumns = @JoinColumn(name = "zooid"))
    private Set<Animal> animals = new HashSet<>();

}
