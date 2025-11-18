package org.iplacex.proyectos.discografia.artistas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/artistas")
@CrossOrigin(origins = "*")
public class ArtistaController {

    private final IArtistaRepository artistaRepository;

    public ArtistaController(IArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @GetMapping
    public List<Artista> getAllArtistas() {
        return artistaRepository.findAll();
    }

    @PostMapping
    public Artista createArtista(@RequestBody Artista artista) {
        return artistaRepository.save(artista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> getArtistaById(@PathVariable String id) {
        return artistaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> updateArtista(@PathVariable String id, @RequestBody Artista artistaActualizado) {
        return artistaRepository.findById(id)
                .map(artista -> {
                    artista.setNombre(artistaActualizado.getNombre());
                    artista.setPais(artistaActualizado.getPais());
                    artista.setAnioInicio(artistaActualizado.getAnioInicio());
                    artistaRepository.save(artista);
                    return ResponseEntity.ok(artista);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtista(@PathVariable String id) {
        return artistaRepository.findById(id)
                .map(artista -> {
                    artistaRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
