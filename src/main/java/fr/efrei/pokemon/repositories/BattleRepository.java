package fr.efrei.pokemon.repositories;

import fr.efrei.pokemon.models.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleRepository extends JpaRepository<Battle, String> {
}
