package fr.efrei.pokemon.services;

import fr.efrei.pokemon.models.Battle;
import fr.efrei.pokemon.repositories.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleService {

    private final BattleRepository battleRepository;

    @Autowired
    public BattleService(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    public List<Battle> findAll() {
        // SELECT * FROM battle;
        return battleRepository.findAll();
    }

    public Battle findById(String id) {
        // Optional : soit l'objet soit null
        // SELECT * FROM battle WHERE id = :id
        return battleRepository.findById(id).orElse(null);
    }

    public void save(Battle battle) {
        // INSERT INTO battle VALUES (:trainer1, :trainer2, :pokemon1, :pokemon2, :result);
        battleRepository.save(battle);
    }

    public void delete(String id) {
        // DELETE FROM battle WHERE id = :id
        battleRepository.deleteById(id);
    }

    public void update(String id, Battle battleBody) {
        Battle existingBattle = findById(id);
        if (existingBattle != null) {
            existingBattle.setTrainer1(battleBody.getTrainer1());
            existingBattle.setTrainer2(battleBody.getTrainer2());
            existingBattle.setPokemon1(battleBody.getPokemon1());
            existingBattle.setPokemon2(battleBody.getPokemon2());
            existingBattle.setResult(battleBody.getResult());
            battleRepository.save(existingBattle);
        }
    }

    public void partialUpdate(String id, Battle battleBody) {
        Battle existingBattle = findById(id);
        if (existingBattle != null) {
            if (battleBody.getTrainer1() != null) {
                existingBattle.setTrainer1(battleBody.getTrainer1());
            }
            if (battleBody.getTrainer2() != null) {
                existingBattle.setTrainer2(battleBody.getTrainer2());
            }
            if (battleBody.getPokemon1() != null) {
                existingBattle.setPokemon1(battleBody.getPokemon1());
            }
            if (battleBody.getPokemon2() != null) {
                existingBattle.setPokemon2(battleBody.getPokemon2());
            }
            if (battleBody.getResult() != null) {
                existingBattle.setResult(battleBody.getResult());
            }
            battleRepository.save(existingBattle);
        }
    }
}
