package com.example.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    @Autowired
    private CarroRepository repository;

    public Iterable<Carro> getCarros() {
        return repository.findAll();
    }

    public Optional<Carro> getCarroById(Long id) {
        return repository.findById(id);
    }

    public Iterable<Carro> getCarroByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    public Carro save(Carro carro) {
        return repository.save(carro);
    }

    public void update(Long id, Carro carro) {
        Assert.notNull(id, "Não encontrado carro por este id: " + id);
        Optional<Carro> carro1 = getCarroById(id);
        if (carro1.isPresent()) {
            Carro carroUpdate = carro1.get();
            if (carro.getNome() != null) {
                carroUpdate.setNome(carro.getNome());
            }
            if (carro.getTipo() != null) {
                carroUpdate.setTipo(carro.getTipo());
            }
            repository.save(carroUpdate);
        } else {
            throw new RuntimeException("Não encontrado carro por este id: " + id);
        }
    }

    public void delete(Long id) {
        Optional<Carro> carro = getCarroById(id);
        if (carro.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Carro não existe");
        }
    }

    public List<Carro> getCarrosFake() {

        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro(1L, "Corolla"));
        carros.add(new Carro(2L, "Ram"));
        carros.add(new Carro(3L, "Camaro"));
        carros.add(new Carro(4L, "Civic"));
        carros.add(new Carro(5L, "Onix"));

        return carros;
    }
}
