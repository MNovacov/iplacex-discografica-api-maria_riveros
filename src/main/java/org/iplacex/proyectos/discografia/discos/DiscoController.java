package org.iplacex.proyectos.discografia.discos;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.*;
import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    private final IDiscoRepository discoRepository;
    private final IArtistaRepository artistaRepository;

    public DiscoController(IDiscoRepository discoRepository, IArtistaRepository artistaRepository) {
        this.discoRepository = discoRepository;
        this.artistaRepository = artistaRepository;
    }

    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> HandlePostDiscoRequest(@RequestBody Disco disco) {
        if (!artistaRepository.existsById(disco.idArtista)) {
            return new ResponseEntity<>("El artista especificado no existe", HttpStatus.NOT_FOUND);
        }
        Disco nuevo = discoRepository.save(disco);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        List<Disco> discos = discoRepository.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> HandleGetDiscoRequest(@PathVariable String id) {
        Optional<Disco> disco = discoRepository.findById(id);
        if (disco.isPresent()) {
            return new ResponseEntity<>(disco.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Disco no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable String id) {
        List<Disco> discos = discoRepository.findDiscosByIdArtista(id);
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }
}
