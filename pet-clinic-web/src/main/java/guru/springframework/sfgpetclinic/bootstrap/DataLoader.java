package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialityService specialityService;
  private final VisitService visitService;

  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                    SpecialityService specialityService, VisitService visitService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialityService = specialityService;
    this.visitService = visitService;
  }

  @Override
  public void run(String... args) throws Exception {
    int count = petTypeService.findAll().size();
    if (count == 0) {
      loadData();
    }
  }

  private void loadData() {
    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    cat.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);

    Owner owner1 = new Owner();
    owner1.setFirstName("Michael");
    owner1.setLastName("Weston");
    owner1.setAddress("123 Main Street");
    owner1.setCity("Miami");
    owner1.setCity("123456789");

    Pet mikesPet = new Pet();
    mikesPet.setPetType(savedDogPetType);
    mikesPet.setOwner(owner1);
    mikesPet.setName("Rosco");
    mikesPet.setBirthDate(LocalDate.now());
    owner1.getPets().add(mikesPet);

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Fiona");
    owner2.setLastName("Glenanne");
    owner2.setAddress("123 Main Street");
    owner2.setCity("Miami");
    owner2.setCity("123456789");

    Pet fionasPet = new Pet();
    fionasPet.setPetType(savedCatPetType);
    fionasPet.setOwner(owner2);
    fionasPet.setName("Mimi");
    fionasPet.setBirthDate(LocalDate.now());
    owner2.getPets().add(fionasPet);
    ownerService.save(owner2);

    System.out.println("Loaded owners...");

    Visit catVisit = new Visit();
    catVisit.setPet(fionasPet);
    catVisit.setDate(LocalDate.now());
    catVisit.setDescription("Sneezy Kitty");
    visitService.save(catVisit);

    Specialty radiology = new Specialty();
    radiology.setDescription("Radiology");
    Specialty savedRadiology = specialityService.save(radiology);

    Specialty surgery = new Specialty();
    surgery.setDescription("Surgery");
    Specialty savedSurgery = specialityService.save(surgery);

    Specialty dentistry = new Specialty();
    dentistry.setDescription("Dentistry");
    Specialty savedDentistry = specialityService.save(dentistry);

    Vet vet1 = new Vet();
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");
    vet1.getSpecialties().add(savedRadiology);
    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Jessie");
    vet2.setLastName("Porter");
    vet2.getSpecialties().add(savedSurgery);
    vetService.save(vet2);

    System.out.println("Loaded vets...");
  }
}
