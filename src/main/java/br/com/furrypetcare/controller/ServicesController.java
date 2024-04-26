package br.com.furrypetcare.controller;

import br.com.furrypetcare.domain.services.ServiceEntity;
import br.com.furrypetcare.domain.services.ServiceRepository;
import br.com.furrypetcare.domain.services.dao.DetailService;
import br.com.furrypetcare.domain.services.dao.SimpleServices;
import br.com.furrypetcare.domain.services.dto.RegisterService;
import br.com.furrypetcare.domain.services.dto.UpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/services")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Services data API")
public class ServicesController {

    @Autowired
    ServiceRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra serviços", description = "Método responsável por registrar os serviços.")
    public ResponseEntity<DetailService> addService(@RequestBody RegisterService registerService, UriComponentsBuilder componentsBuilder) {
        var service = new ServiceEntity(registerService);
        repository.save(service);
        var uri = componentsBuilder.path("service/{id}").buildAndExpand(service.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailService(service));
    }

    @GetMapping
    @Operation(summary = "Listar todos os serviços", description = "Método responsável por listar os serviços.")
    public ResponseEntity<Page<SimpleServices>> listServices(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = repository.findAll(pageable).map(SimpleServices::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar serviços", description = "Método responsável por consultar os serviços.")
    public ResponseEntity<DetailService> listServices(@PathVariable UUID id) {
        var optionalService = repository.findById(id);
        return optionalService.map(service ->
                ResponseEntity.ok(new DetailService(service))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar serviços", description = "Método responsável por alterar os serviços.")
    public ResponseEntity<DetailService> listServices(@RequestBody @Valid UpdateService updateService, @PathVariable UUID id) {
        var service = repository.getReferenceById(id);
        service.UpdateData(updateService);
        return ResponseEntity.ok(new DetailService(service));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exluir serviços", description = "Método responsável por exluir os serviços.")
    public ResponseEntity deleteService(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
