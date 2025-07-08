package br.edu.notredame.agenda_de_contatos.services;

import br.edu.notredame.agenda_de_contatos.entities.ContactEntity;
import br.edu.notredame.agenda_de_contatos.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactEntity save(ContactEntity contact) throws IllegalArgumentException {
        validateContact(contact);
        return contactRepository.save(contact);
    }

    public List<ContactEntity> findAll() {
        return contactRepository.findAll();
    }

    public ContactEntity findById(UUID id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado"));
    }

    public void deleteById(UUID id) {
        contactRepository.deleteById(id);
    }

    private void validateContact(ContactEntity contact) throws IllegalArgumentException {
        String[] categorias = {"Aluno", "Responsável", "Professor", "Funcionário", "Gestor"};

        if (contact.getNome() == null) {
            throw new IllegalArgumentException("Nome inválido");
        }

        if (contact.getEmail() == null) {
            throw new IllegalArgumentException("Email inválido");
        }

        if (contact.getTelefone() == null) {
            throw new IllegalArgumentException("Telefone inválido");
        }

        if (contact.getCidade() == null) {
            throw new IllegalArgumentException("Cidade inválida");
        }

        if (contact.getEstado() == null) {
            throw new IllegalArgumentException("Estado inválido");
        }

        if (contact.getCategoria() == null || !Arrays.asList(categorias).contains(contact.getCategoria())) {
            throw new IllegalArgumentException("Categoria inválida. São permitidas somente as seguintes categorias:\n "+ Arrays.toString(categorias));
        }
    }


}
