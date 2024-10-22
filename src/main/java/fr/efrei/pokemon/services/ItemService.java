package fr.efrei.pokemon.services;

import fr.efrei.pokemon.models.Item;
import fr.efrei.pokemon.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        // SELECT * FROM item;
        return itemRepository.findAll();
    }

    public Item findById(String id) {
        // Optional : soit l'objet soit null
        // SELECT * FROM item WHERE id = :id
        return itemRepository.findById(id).orElse(null);
    }

    public void save(Item item) {
        // INSERT INTO item VALUES (:name, :description, :price);
        itemRepository.save(item);
    }

    public void delete(String id) {
        // DELETE FROM item WHERE id = :id
        itemRepository.deleteById(id);
    }

    public void update(String id, Item itemBody) {
        Item existingItem = findById(id);
        if (existingItem != null) {
            existingItem.setName(itemBody.getName());
            existingItem.setDescription(itemBody.getDescription());
            existingItem.setPrice(itemBody.getPrice());
            existingItem.setOwner(itemBody.getOwner());
            itemRepository.save(existingItem);
        }
    }

    public void partialUpdate(String id, Item itemBody) {
        Item existingItem = findById(id);
        if (existingItem != null) {
            if (itemBody.getName() != null) {
                existingItem.setName(itemBody.getName());
            }
            if (itemBody.getDescription() != null) {
                existingItem.setDescription(itemBody.getDescription());
            }
            if (itemBody.getPrice() != 0) {
                existingItem.setPrice(itemBody.getPrice());
            }
            if (itemBody.getOwner() != null) {
                existingItem.setOwner(itemBody.getOwner());
            }
            itemRepository.save(existingItem);
        }
    }
}
