package com.miproyecto.forohub.controller;

import com.miproyecto.forohub.domain.usuario.*;
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
@RequestMapping("/usuario")
@SecurityRequirement(name="bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Obtener todos los Usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<Page<ListarUsuariosDTO>> listarUsuarios(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(usuarioRepository.findByActiveTrue(paged).map(ListarUsuariosDTO::new));
    }

    //Actualizar un usuario por id
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizacionUsuario (@RequestBody @Valid ActualizacionUsuarioDTO actualizacionUsuarioDTO){
        Usuario usuario = usuarioRepository.getReferenceById(actualizacionUsuarioDTO.id());
        usuario.actualizacionUsuario(actualizacionUsuarioDTO);
        return ResponseEntity.ok(new ActualizacionUsuarioDTO(usuario.getId(),usuario.getName(), usuario.getEmail(), usuario.getPassword()));
    }

    //Eliminar un usuario por id
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.deactivateUser();
        return ResponseEntity.noContent().build();
    }

    //Obtener un Usuario pasando el id
    @GetMapping("/{id}")
    public ResponseEntity <RespuestaUsuarioDTO> registrarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var usuarioDetail = new RespuestaUsuarioDTO(usuario.getId(), usuario.getName());
        return ResponseEntity.ok(usuarioDetail);
    }
}