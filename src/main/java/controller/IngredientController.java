package controller;

import entity.Ingredient;
import entity.StockValue;
import entity.Unit;
import service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getIngredients() {
        return ResponseEntity.ok(ingredientService.findAllIngredients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.findIngredientById(id));
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<StockValue> getIngredientStock(
            @PathVariable Integer id,
            @RequestParam("at") Instant temporal,
            @RequestParam("unit") Unit unit) {
        return ResponseEntity.ok(ingredientService.getIngredientStock(id, temporal, unit));
    }
}
