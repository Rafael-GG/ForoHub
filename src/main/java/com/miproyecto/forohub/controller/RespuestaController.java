package com.miproyecto.forohub.controller;

import com.miproyecto.forohub.domain.respuesta.*;
import com.miproyecto.forohub.domain.topico.TopicoRepository;
import com.miproyecto.forohub.domain.usuario.UsuarioRepository;
import com.miproyecto.forohub.infra.errors.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/respuesta")
@SecurityRequirement(name="bearer-key")
public class RespuestaController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespuestaService respuestaService;
    @Autowired
    private RespuestaRepository repository;

    //Registrar nueva Respuesta
    @PostMapping
    @Transactional
    public ResponseEntity respuestaRegistrada (@RequestBody @Valid RespuestaDTO respuestaDTO) throws ValidacionDeIntegridad {
        var respuestaRegistrada = respuestaService.respuestaCreadaDTO(respuestaDTO);
        return ResponseEntity.ok(respuestaRegistrada);
    }

    //Obtener todas las Respuestas
    @GetMapping("/respuestas")
    public ResponseEntity<Page<ListarRespuestasDTO>>  listarRespuestas(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(repository.findByActiveTrue(paged).map(ListarRespuestasDTO::new));
    }

    //Actualizar una respuesta por id
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity respuestaActualizada(@RequestBody @Valid RespuestaActualizadaDTO respuestaActualizadaDTO){
        Respuesta respuesta=repository.getReferenceById(respuestaActualizadaDTO.id());
        respuesta.respuestaActualizada(respuestaActualizadaDTO);
        return ResponseEntity.ok(new RespuestaCreadaDTO(respuesta.getId(),respuesta.getSolution(),
                respuesta.getAuthor().getId(),
                respuesta.getTopico().getId(),
                respuesta.getCreationDate()));
    }

    //Eliminar una respuesta por id
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta = repository.getReferenceById(id);
        respuesta.diactivateResponse();
        return ResponseEntity.noContent().build();
    }

    //Obtener una Respuesta pasando el id
    @GetMapping("/{id}")
    public ResponseEntity <RespuestaCreadaDTO> respuestaCreada(@PathVariable Long id){
        Respuesta respuesta=repository.getReferenceById(id);
        var respuestaRegistrada = new RespuestaCreadaDTO(respuesta.getId(),
                respuesta.getSolution(),
                respuesta.getAuthor().getId(),
                respuesta.getTopico().getId(),
                respuesta.getCreationDate());
        return ResponseEntity.ok(respuestaRegistrada);
    }
}