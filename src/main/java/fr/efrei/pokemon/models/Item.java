package fr.efrei.pokemon.models;

import jakarta.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name; // Nom de l'objet
    private String description; // Description de l'objet
    private int price; // Prix de l'objet

    @ManyToOne
    private Trainer owner; // Dresseur qui possède l'objet

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Trainer getOwner() {
        return owner;
    }

    public void setOwner(Trainer owner) {
        this.owner = owner;
    }
}
