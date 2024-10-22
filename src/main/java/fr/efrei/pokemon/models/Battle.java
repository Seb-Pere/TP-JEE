package fr.efrei.pokemon.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Trainer trainer1;

    @ManyToOne
    private Trainer trainer2;

    @OneToMany
    private List<Pokemon> pokemon1; // Pokémon du premier dresseur

    @OneToMany
    private List<Pokemon> pokemon2; // Pokémon du deuxième dresseur

    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Trainer getTrainer1() {
        return trainer1;
    }

    public void setTrainer1(Trainer trainer1) {
        this.trainer1 = trainer1;
    }

    public Trainer getTrainer2() {
        return trainer2;
    }

    public void setTrainer2(Trainer trainer2) {
        this.trainer2 = trainer2;
    }

    public List<Pokemon> getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(List<Pokemon> pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public List<Pokemon> getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(List<Pokemon> pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
