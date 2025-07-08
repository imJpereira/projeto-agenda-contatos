package br.edu.notredame.agenda_de_contatos.DTOs;

public record ContactDTO(
        String nome,
        String telefone,
        String cidade,
        String estado,
        String email,
        String categoria
) {
}
