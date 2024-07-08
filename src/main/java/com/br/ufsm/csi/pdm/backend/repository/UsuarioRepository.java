package com.br.ufsm.csi.pdm.backend.repository;

import com.br.ufsm.csi.pdm.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByMatricula(String matricula);
    Usuario findByMatriculaAndSenha(String matricula, String senha);
}
