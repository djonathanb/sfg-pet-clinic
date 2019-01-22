package guru.springframework.sfgpetclinic.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "pet_types")
public class PetType extends BaseEntity {

    private String name;

}
