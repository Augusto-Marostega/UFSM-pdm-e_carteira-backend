package com.br.ufsm.csi.pdm.backend.controller;

import com.br.ufsm.csi.pdm.backend.dto.LoginDTO;
import com.br.ufsm.csi.pdm.backend.model.Usuario;
import com.br.ufsm.csi.pdm.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Usuario usuario = usuarioService.autenticarUsuario(loginDTO.getMatricula(), loginDTO.getSenha());
        if (usuario != null) {
            System.out.println("LOGIN OK");
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
}
