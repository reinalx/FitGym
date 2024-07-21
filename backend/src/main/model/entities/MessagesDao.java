package main.model.entities;

import org.springframework.data.repository.CrudRepository;

public interface MessagesDAO extends CrudRepository<Messages, Long> {

}