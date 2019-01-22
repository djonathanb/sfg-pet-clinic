package guru.springframework.sfgpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = {"pet"})
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

  private LocalDate date;
  private String description;

  @ManyToOne
  private Pet pet;

}
