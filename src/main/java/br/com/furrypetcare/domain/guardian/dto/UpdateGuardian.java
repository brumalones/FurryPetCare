package br.com.furrypetcare.domain.guardian.dto;
import br.com.furrypetcare.domain.address.dao.RegisterAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UpdateGuardian (
        @Email
        String email,
        @Pattern(regexp = "^\\d{11}$")
        String phone,
        @Valid
        RegisterAddress address
){
}
