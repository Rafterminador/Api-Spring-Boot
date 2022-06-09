package com.api.api.controller;
import com.api.api.exception.ResourceNotFoundException;
import com.api.api.model.Localidad;
import com.api.api.repository.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/localidad")
public class LocalidadController {
    @Autowired
    private LocalidadRepository localidadRepository;
    // obtener todas las localidades
    @GetMapping
    public List<Localidad> getAllLocalidades(){
        return localidadRepository.findAll();
    }

    // crear localidades
    @PostMapping("crear")
    public Localidad createLocalidad(@RequestBody Localidad localidad) {
        return localidadRepository.save(localidad);
    }

    // obtener localidades por id
    @GetMapping("obtener/{id}")
    public ResponseEntity<Localidad> getLocalidadById(@PathVariable  long id){
        Localidad localidad = localidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe ninguna localidad con ese:" + id));
        return ResponseEntity.ok(localidad);
    }

    // actualizar nombres de localidades
    @PutMapping("actualizar/{id}")
    public ResponseEntity<Localidad> updateLocalidad(@PathVariable long id,@RequestBody Localidad localidadDetails) {
        Localidad updateLocalidad = localidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ninguna localidad se tiene con ese: " + id));

        updateLocalidad.setNombre(localidadDetails.getNombre());
        localidadRepository.save(updateLocalidad);

        return ResponseEntity.ok(updateLocalidad);
    }

    // eliminar localidades
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<HttpStatus> deleteLocalidad(@PathVariable long id){

        Localidad localidad = localidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe ninguna localidad con ese: " + id));

        localidadRepository.delete(localidad);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
