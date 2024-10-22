package fr.efrei.pokemon.services;

import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.repositories.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository repository;
    private final PokemonService pokemonService;

    @Autowired
    public TrainerService(TrainerRepository repository, PokemonService pokemonService) {
        this.repository = repository;
        this.pokemonService = pokemonService;
    }

    public List<Trainer> findAll() {
        return repository.findAll();
    }

    public Trainer findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Trainer save(CreateTrainer trainerBody) {
        Trainer trainer = new Trainer();
        trainer.setName(trainerBody.getName());
        
        // Récupération des Pokémon en fonction des IDs
        List<String> pokemonIds = trainerBody.getTeam();
        List<Pokemon> pokemonAAjouter = new ArrayList<>();
        
        for (String idPokemon : pokemonIds) {
            Pokemon pokemon = pokemonService.findById(idPokemon);
            if (pokemon != null) {
                pokemonAAjouter.add(pokemon);
            }
        }
        
        trainer.setTeam(pokemonAAjouter);
        return repository.save(trainer); // Retourne le Trainer créé
    }

    @Transactional
    public void update(String id, UpdateTrainer trainerBody) {
        Trainer trainer = findById(id);
        if (trainer == null) {
            return; // On pourrait également lever une exception
        }

        // Mise à jour des propriétés
        if (trainerBody.getName() != null) {
            trainer.setName(trainerBody.getName());
        }

        if (trainerBody.getTeam() != null && !trainerBody.getTeam().isEmpty()) {
            List<Pokemon> pokemonList = new ArrayList<>();
            List<String> pokemonIds = trainerBody.getTeam();

            for (String idPokemon : pokemonIds) {
                Pokemon pokemon = pokemonService.findById(idPokemon);
                if (pokemon != null) {
                    pokemonList.add(pokemon);
                }
            }
            
            trainer.setTeam(pokemonList); // Remplace l'ancienne équipe par la nouvelle
        }

        repository.save(trainer); // Sauvegarde des modifications
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
