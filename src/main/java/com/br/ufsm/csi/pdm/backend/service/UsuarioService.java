package com.br.ufsm.csi.pdm.backend.service;

import com.br.ufsm.csi.pdm.backend.model.Usuario;
import com.br.ufsm.csi.pdm.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticarUsuario(String matricula, String senha) {
        return usuarioRepository.findByMatriculaAndSenha(matricula, senha);
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.orElse(null);
    }

    public Usuario findByMatricula(String matricula){
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioRepository.findByMatricula(matricula));
        return usuarioOptional.orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }


}
