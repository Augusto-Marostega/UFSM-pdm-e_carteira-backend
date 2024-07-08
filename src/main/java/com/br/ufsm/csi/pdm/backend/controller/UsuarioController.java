package com.br.ufsm.csi.pdm.backend.controller;

import com.br.ufsm.csi.pdm.backend.dto.UsuarioDTO;
import com.br.ufsm.csi.pdm.backend.model.Usuario;
import com.br.ufsm.csi.pdm.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Base64;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Value("${upload.dir}") // Injeta o caminho configurado no application.properties
    private String UPLOAD_DIR;

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para popular os dados de um novo usuário
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarDadosUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<?> getCarteiraUsuario(@PathVariable String matricula) {
        Usuario usuario = usuarioService.findByMatricula(matricula);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setMatricula(usuario.getMatricula());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setCurso(usuario.getCurso());
        usuarioDTO.setValidade(usuario.getValidade());
        usuarioDTO.setDataInscricao(usuario.getDataInscricao());
        usuarioDTO.setFotoPerfil(usuario.getFotoPerfil());
        return ResponseEntity.ok(usuarioDTO);
    }

    @PostMapping("/carteira/atualizarfoto")
    public ResponseEntity<String> atualizarFotoUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioAtualizado = usuarioService.findByMatricula(usuario.getMatricula());
            usuarioAtualizado.setFotoPerfil(usuario.getFotoPerfil());
            usuarioService.save(usuarioAtualizado);
            System.out.println("Atualizar foto OK.");
            return ResponseEntity.status(HttpStatus.CREATED).body("Foto do usuário atualizada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar foto do usuário: " + e.getMessage());
        }
    }

}
