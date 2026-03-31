package controller;

import entity.Dish;
import entity.DishIngredient;
import entity.Ingredient;
import exception.BadRequestException;
import service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getDishes() {
        return ResponseEntity.ok(dishService.findAllDishes());
    }

    @PutMapping("/{id}/ingredients")
    public ResponseEntity<Dish> updateDishIngredients(
            @PathVariable Integer id,
            @RequestBody(required = false) List<DishIngredient> ingredients) {
        if (ingredients == null) {
            throw new BadRequestException("Request body containing list of ingredients is mandatory");
        }
        return ResponseEntity.ok(dishService.updateDishIngredients(id, ingredients));
    }

    @GetMapping("/{id}/ingredients")
    public ResponseEntity<List<Ingredient>> getDishIngredientsFiltered(
            @PathVariable Integer id,
            @RequestParam(required = false) String ingredientName,
            @RequestParam(required = false) Double ingredientPriceAround) {
        return ResponseEntity.ok(dishService.getDishIngredientsFiltered(id, ingredientName, ingredientPriceAround));
    }

}
