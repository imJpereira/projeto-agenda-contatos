package br.edu.notredame.agenda_de_contatos.repositories;

import br.edu.notredame.agenda_de_contatos.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, UUID> {


}
