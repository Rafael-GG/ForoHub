package com.miproyecto.forohub.domain.usuario.validaciones;

import com.miproyecto.forohub.domain.usuario.RegistroUsuarioDTO;

public interface ValidadorDeUsuario {

    public void validate(RegistroUsuarioDTO RegistroUsuarioDTO);
}