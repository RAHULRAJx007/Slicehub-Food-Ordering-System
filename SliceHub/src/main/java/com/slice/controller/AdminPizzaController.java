package com.slice.controller;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.slice.model.Pizza;
import com.slice.service.PizzaService;

@Controller
@RequestMapping("/admin/pizzas")
public class AdminPizzaController {

    @Autowired
    private PizzaService pizzaService;

    // ✅ LIST PIZZAS (ADMIN DASHBOARD)
    @GetMapping
    public String listPizzas(Model model) {

        List<Pizza> pizzas = pizzaService.getAllPizzas();

        long availableCount = pizzas.stream()
                                    .filter(Pizza::isAvailable)
                                    .count();

        long unavailableCount = pizzas.size() - availableCount;

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("availableCount", availableCount);
        model.addAttribute("unavailableCount", unavailableCount);

        // 🔥 MUST MATCH templates/admin/pizzas.html
        return "admin/pizzas";
    }

    // ✅ SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "admin/addingpizza";
    }

    // ✅ SAVE / UPDATE PIZZA
    @PostMapping("/save")
    public String savePizza(
            @ModelAttribute("pizza") Pizza pizza,
            @RequestParam("image") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(file.getInputStream(),
                    uploadPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            pizza.setImageName(fileName);
        } else if (pizza.getId() != null) {
            // 🔥 KEEP OLD IMAGE DURING EDIT
            Pizza existing = pizzaService.getPizzaById(pizza.getId());
            pizza.setImageName(existing.getImageName());
        }

        pizzaService.savePizza(pizza);
        return "redirect:/admin/pizzas";
    }


    // ✅ EDIT FORM
    @GetMapping("/edit/{id}")
    public String editPizza(@PathVariable Long id, Model model) {
        Pizza pizza = pizzaService.getPizzaById(id);
        model.addAttribute("pizza", pizza);
        return "admin/addingpizza"; // 🔥 SAME PAGE
    }


    // ✅ DELETE
    @GetMapping("/delete/{id}")
    public String deletePizza(@PathVariable Long id) {
        pizzaService.deletePizza(id);
        return "redirect:/admin/pizzas";
    }
}
