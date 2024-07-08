package com.br.ufsm.csi.pdm.backend.dto;
import lombok.Data;
import java.util.Date;


@Data
public class UsuarioDTO {
    private Long id;
    private String matricula;
    private String nome;
    private String senha;
    private String curso;
    private String validade;
    private Date dataInscricao;
    private byte[] fotoPerfil;
}
