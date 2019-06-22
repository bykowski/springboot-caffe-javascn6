package pl.bykowski.springbootcaffe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CoffeeApi {

//    1 BEGIN - wstrzykiwanie do pola
//    @Autowired
//    private CoffeeRepo coffeeRepo;
//    1 END - wstrzykiwanie do pola


    //    2 BEGIN - wstrzykiwanie do konstruktora
    private CoffeeRepo coffeeRepo;

    @Autowired
    public CoffeeApi(CoffeeRepo coffeeRepo) {
        this.coffeeRepo = coffeeRepo;
    }
    //    2 END - wstrzykiwanie do konstruktora

    //    3 BEGIN - wstrzykiwanie do metody
//    private CoffeeRepo coffeeRepo;
//
//    @Autowired
//    public void setCoffeeRepo(CoffeeRepo coffeeRepo) {
//        this.coffeeRepo = coffeeRepo;
//    }
    //    3 END - wstrzykiwanie do metody


    private List<Coffee> coffeList;

    public CoffeeApi() {
        coffeList = new ArrayList<>();
    }

    @GetMapping
    public List<Coffee> getCoffes() {
        return coffeList;
    }

    @PostMapping
    public void addCoffee(@RequestBody Coffee coffee) {
        coffeList.add(coffee);
    }

    @DeleteMapping
    public boolean removeCoffe(@RequestParam Long id) {
        return coffeList.removeIf(element -> element.getId().equals(id));

    }

    @PutMapping
    public void putCoffee(@RequestBody Coffee coffee) {
//        Optional<Coffee> first = coffeList.stream()
//                .filter(element -> element.getId().equals(coffee.getId()))
//                .findFirst();
//        if (first.isPresent()) {
//            coffeList.set(coffeList.indexOf(first.get()), coffee);
//        }

        Coffee foundElement = null;
        for (Coffee coffee1 : coffeList) {
            if (coffee1.getId().equals(coffee.getId())) {
                foundElement = coffee1;
            }
        }
        coffeList.set(coffeList.indexOf(foundElement), coffee);
    }


    @GetMapping("/hello/{lang}")
    public String get(@PathVariable String lang) {
        if (lang.equals("PL")) {
            return "Cześć";
        }
        if (lang.equals("EN")) {
            return "Hello";
        }
        return "Niepojemaju";
    }


    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        Coffee coffee1 = new Coffee("Prima", "Black");
        Coffee coffee2 = new Coffee("Czibo", "Black");
        Coffee coffee3 = new Coffee("Jacobs", "Black");
        coffeeRepo.save(coffee1);
        coffeeRepo.save(coffee2);
        coffeeRepo.save(coffee3);
    }
}
