package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping()
    public Iterable<Carro> get() {
        return service.getCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> getCarroById(@PathVariable("id") Long id) {
        return service.getCarroById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> getCarroByTipo(@PathVariable("tipo") String tipo) {
        return service.getCarroByTipo(tipo);
    }

    @PostMapping()
    public String postCarro(@RequestBody Carro carro) {
        Carro c = service.save(carro);
        return "Carro salvo com sucesso ID: " + c.getId();
    }

    @PutMapping("/{id}")
    public String putCarro(@PathVariable("id") Long id, @RequestBody Carro carro) {

        service.update(id, carro);
        Optional<Carro> c = getCarroById(id);

        return "Carro atualizado com sucesso\n\n" + c.get();
    }

    @DeleteMapping("/{id}")
    public String deleteCarro(@PathVariable("id") Long id){
        Optional<Carro> c = getCarroById(id);
        service.delete(id);
        return "Carro apagado do banco de dados \n\n"+c.get();
    }
}
