package org.springframework.samples.petclinic.feeding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "feeding_types")

public class FeedingType extends BaseEntity{

    
    @NotEmpty
    @Size(min = 5, max = 50)
    @Column(unique = true)
    private String name;

    @NotEmpty
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name= "pet_type_id", referencedColumnName = "id")
    private PetType petType;
}
