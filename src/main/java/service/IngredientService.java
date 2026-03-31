package service;

import entity.Ingredient;
import entity.StockValue;
import entity.Unit;
import exception.ResourceNotFoundException;
import repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAllIngredients();
    }

    public Ingredient findIngredientById(Integer id) {
        try {
            return ingredientRepository.findIngredientById(id);
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("Ingredient not found")) {
                throw new ResourceNotFoundException("Ingredient.id=" + id + " is not found");
            }
            throw e;
        }
    }

    public StockValue getIngredientStock(Integer id, Instant temporal, Unit unit) {
        Ingredient ingredient = findIngredientById(id);
        StockValue stockValue = ingredient.getStockValueAt(temporal);
        return stockValue;
    }
}
