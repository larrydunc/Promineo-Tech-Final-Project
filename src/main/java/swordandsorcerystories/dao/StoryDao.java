package swordandsorcerystories.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import swordandsorcerystories.entity.Story;

public interface StoryDao extends JpaRepository<Story, Long> {

}
