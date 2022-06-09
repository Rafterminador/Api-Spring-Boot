package com.api.api.controller;
import com.api.api.exception.ResourceNotFoundException;
import com.api.api.model.AsignarLocalidad;
import com.api.api.model.Evento;
import com.api.api.model.Localidad;
import com.api.api.repository.EventoRepository;
import com.api.api.repository.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private LocalidadRepository localidadRepository;
    // obtener todos los eventos
    @GetMapping("lista")
    public List<Evento> getAllEventos(){
        return eventoRepository.findAll();
    }

    // crear eventos
    @PostMapping("crear")
    public Evento createEventos(@RequestBody Evento evento) {
        return eventoRepository.save(evento);
    }

    // obtener eventos por id
    @GetMapping("obtener/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable  long id){
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe ningun evento con ese:" + id));
        return ResponseEntity.ok(evento);
    }

    // actualizar eventos
    @PutMapping("asignar")
    public ResponseEntity<String> updateEvento(@RequestBody AsignarLocalidad asignarLocalidad) {
        Evento updateEvento = eventoRepository.findById(Long.parseLong(asignarLocalidad.getEvento()))
                .orElseThrow(() -> new ResourceNotFoundException("Ningun evento se tiene con ese: " + asignarLocalidad.getEvento()));
        Localidad localidad = localidadRepository.findById(Long.parseLong(asignarLocalidad.getLocalidad()))
                .orElseThrow(() -> new ResourceNotFoundException("No existe ninguna localidad con ese: " + asignarLocalidad.getLocalidad()));
        updateEvento.setLocalidad(localidad.getNombre());
        eventoRepository.save(updateEvento);

        return ResponseEntity.ok("ko");
    }



    // eliminar eventos
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<HttpStatus> deleteEvento(@PathVariable long id){

        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe ningun evento con ese: " + id));

        eventoRepository.delete(evento);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
