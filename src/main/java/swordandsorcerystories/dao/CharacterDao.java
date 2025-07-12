package swordandsorcerystories.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import swordandsorcerystories.entity.Character;

public interface CharacterDao extends JpaRepository<Character, Long> {

}
