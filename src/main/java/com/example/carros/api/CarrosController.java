package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping()
    public ResponseEntity<List<Carro>> getCarros() {
        List<Carro> carros = service.getCarros();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarroById(@PathVariable("id") Long id) {
        Optional<Carro> carro = service.getCarroById(id);
        if (carro.isPresent()) {
            return ResponseEntity.ok(carro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Carro>> getCarroByTipo(@PathVariable("tipo") String tipo) {
        List<Carro> carros = service.getCarroByTipo(tipo);
        if (carros.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(carros);
        }
    }

    @PostMapping()
    public ResponseEntity postCarro(@RequestBody Carro carro) {
        if (carro.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        if (carro.getNome() == null || carro.getNome().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        if (carro.getTipo() == null || carro.getTipo().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Carro> c = Optional.of(carro);
        Carro carroSalvo = service.save(carro);
        return ResponseEntity.created(URI.create("localhost:8080/api/v1/carros/" + carroSalvo.getId())).body(carroSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity putCarro(@PathVariable("id") Long id, @RequestBody Carro carro) {
        Assert.isNull(carro.getId(), "Não pode haver o campo id");
        Optional<Carro> c = service.getCarroById(id);
        if (c.isPresent()) {
            Carro carroAtualizado = service.update(id, carro);
            return ResponseEntity.ok(carroAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarro(@PathVariable("id") Long id) {
        Optional<Carro> c = service.getCarroById(id);
        if (c.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

