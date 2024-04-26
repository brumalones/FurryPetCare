package br.com.furrypetcare.domain.guardian;

import br.com.furrypetcare.domain.address.AddressEntity;
import br.com.furrypetcare.domain.guardian.dto.RegisterGuardian;
import br.com.furrypetcare.domain.guardian.dto.UpdateGuardian;
import br.com.furrypetcare.domain.pet.PetEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "guardian")
@Table(name = "guardian")
public class GuardianEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    @OneToMany(mappedBy = "guardian")
    private List<PetEntity> pets;

    public GuardianEntity(RegisterGuardian registerGuardian) {
        firstName = registerGuardian.firstName();
        lastName = registerGuardian.lastName();
        email = registerGuardian.email();
        phone = registerGuardian.phone();
        address = new AddressEntity(registerGuardian.address());
    }

    public void updateData(UpdateGuardian updateGuardian) {
        if (updateGuardian.email() != null)
            this.email = updateGuardian.email();
        if (updateGuardian.phone() != null)
            this.phone = updateGuardian.phone();
        if (updateGuardian.address() != null)
            this.address.updateData(updateGuardian.address());
    }
}
