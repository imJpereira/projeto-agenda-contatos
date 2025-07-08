package br.edu.notredame.agenda_de_contatos.controllers;

import br.edu.notredame.agenda_de_contatos.DTOs.ContactDTO;
import br.edu.notredame.agenda_de_contatos.entities.ContactEntity;
import br.edu.notredame.agenda_de_contatos.services.ContactService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/contatos")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/novo")
    public ResponseEntity<ContactEntity> createContact(@RequestBody ContactDTO contactDTO) throws IllegalArgumentException {
        ContactEntity contactEntity = new ContactEntity();
        BeanUtils.copyProperties(contactDTO, contactEntity);

        ContactEntity novoContato = contactService.save(contactEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoContato);
    }

    @GetMapping()
    public ResponseEntity<List<ContactEntity>> getAllContacts() {
        return ResponseEntity.ok().body(contactService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactEntity> getContactById(@PathVariable UUID id) throws IllegalArgumentException {
        return ResponseEntity.ok().body(contactService.findById(id));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ContactEntity> updateContact(
            @PathVariable UUID id,
            @RequestBody ContactDTO contactDTO
    ) throws IllegalArgumentException {
        ContactEntity contatoParaEditar = contactService.findById(id);
        BeanUtils.copyProperties(contactDTO, contatoParaEditar);

        ContactEntity contatoEditado = contactService.save(contatoParaEditar);

        return ResponseEntity.ok().body(contatoEditado);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable UUID id) throws IllegalArgumentException {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<String> handlerMethod(Exception ex) {
        String msg = ex.getMessage().replaceAll("\r\n", "");
        return ResponseEntity.badRequest().body(msg);
    }
}
