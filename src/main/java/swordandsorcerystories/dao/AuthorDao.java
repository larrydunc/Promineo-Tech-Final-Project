package swordandsorcerystories.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import swordandsorcerystories.entity.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {

}
