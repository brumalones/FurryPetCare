package br.com.furrypetcare.controller;

import br.com.furrypetcare.domain.cart.CartEntity;
import br.com.furrypetcare.domain.cart.CartRepository;
import br.com.furrypetcare.domain.cart.ComponentCart;
import br.com.furrypetcare.domain.cart.dao.DetailCart;
import br.com.furrypetcare.domain.cart.dto.RegisterCart;
import br.com.furrypetcare.domain.cart.dto.UpdateCart;
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
@RequestMapping("/cart")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Cart of Services API")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ComponentCart componentCart;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra Carinho", description = "Método responsável exibir registar o carinho.")
    public ResponseEntity<DetailCart> registerCart(@RequestBody @Valid RegisterCart registerCart, UriComponentsBuilder componentsBuilder) {
        CartEntity cartEntity = componentCart.registerCart(registerCart);
        cartRepository.save(cartEntity);
        var uri = componentsBuilder.path("cart/{id}").buildAndExpand(cartEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailCart(cartEntity));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar carinho", description = "Método responsável exibir detalhes do carinho.")
    public ResponseEntity<DetailCart> detailCats(@PathVariable UUID id) {
        var optionalCartEntity = cartRepository.findById(id);
        return optionalCartEntity.map(cartEntity ->
                ResponseEntity.ok(new DetailCart(cartEntity))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Consultar Carinhos", description = "Método responsável exibir todos os carinho.")
    public ResponseEntity<Page<DetailCart>> listCats(@PageableDefault(size = 10) Pageable pageable) {
        var page = cartRepository.findAll(pageable).map(DetailCart::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar Carinho", description = "Método responsável alterar os dados do carinho.")
    public ResponseEntity<DetailCart> updateCart(@PathVariable UUID id, @RequestBody @Valid UpdateCart updateCart) {
        var cartEntity = cartRepository.getReferenceById(id);
        cartEntity.update(updateCart);
        return ResponseEntity.ok(new DetailCart(cartEntity));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir Carinho", description = "Método responsável excluir carinho.")
    public ResponseEntity deleteCart(@PathVariable UUID id) {
        cartRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
