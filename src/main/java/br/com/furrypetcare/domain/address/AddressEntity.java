package br.com.furrypetcare.domain.address;

import br.com.furrypetcare.domain.address.dao.RegisterAddress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "address")
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String street;
    private int number;
    private String country;
    private String city;
    private String state;
    private String zip;

    public AddressEntity(RegisterAddress registerAddress) {
        street = registerAddress.street();
        number = registerAddress.number();
        country = registerAddress.country();
        city = registerAddress.city();
        state = registerAddress.state();
        zip = registerAddress.zip();
    }

    public void updateData(RegisterAddress address) {
        if (address.street() != null) this.street = address.street();
        this.number = address.number();
        if (address.country() != null) this.country = address.country();
        if (address.city() != null) this.city = address.city();
        if (address.state() != null) this.state = address.state();
        this.zip = address.zip();

    }
}
