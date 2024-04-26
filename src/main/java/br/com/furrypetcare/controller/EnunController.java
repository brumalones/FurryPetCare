package br.com.furrypetcare.controller;

import br.com.furrypetcare.domain.pet.PetBehavior;
import br.com.furrypetcare.domain.pet.PetCategory;
import br.com.furrypetcare.domain.pet.PetGender;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enuns")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Type of Enuns data API")
public class EnunController {

    @GetMapping("/behavior")
    @Operation(summary = "Listar Comportamentos", description = "Método responsável listar os tipos de comportamentos dos pets.")
    public ResponseEntity listBehavior() {
        var listPetBehavior = Arrays.stream(PetBehavior.values()).map(PetBehavior::getDisplayName).collect(Collectors.toList());
        return ResponseEntity.ok(listPetBehavior);
    }

    @GetMapping("/category")
    @Operation(summary = "Listar Categorias", description = "Método responsável listar as categorias dos pets.")
    public ResponseEntity listCategory() {
        var listPetCategory = Arrays.stream(PetCategory.values()).map(PetCategory::getDisplayName).collect(Collectors.toList());
        return ResponseEntity.ok(listPetCategory);
    }

    @GetMapping("/gender")
    @Operation(summary = "Listar genero", description = "Método responsável listar os tipos de generos dos pets.")
    public ResponseEntity listGender() {
        var listPetGender = Arrays.stream(PetGender.values()).map(PetGender::getDisplayName).collect(Collectors.toList());
        return ResponseEntity.ok(listPetGender);
    }
}