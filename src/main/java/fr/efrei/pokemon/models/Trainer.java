package fr.efrei.pokemon.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items; // Liste d'objets possédés par le dresseur

    @OneToMany(mappedBy = "trainer1", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Battle> battlesAsTrainer1; // Liste des batailles en tant que trainer1

    @OneToMany(mappedBy = "trainer2", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Battle> battlesAsTrainer2; // Liste des batailles en tant que trainer2

    @OneToMany
    private List<Pokemon> team;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Battle> getBattlesAsTrainer1() {
        return battlesAsTrainer1;
    }

    public void setBattlesAsTrainer1(List<Battle> battlesAsTrainer1) {
        this.battlesAsTrainer1 = battlesAsTrainer1;
    }

    public List<Battle> getBattlesAsTrainer2() {
        return battlesAsTrainer2;
    }

    public void setBattlesAsTrainer2(List<Battle> battlesAsTrainer2) {
        this.battlesAsTrainer2 = battlesAsTrainer2;
    }
}
