import { Component, OnInit } from '@angular/core';
import {PizzaService} from "./shared/pizza.service";
import {Pizza} from "./shared/pizza.model";
import {Router} from "@angular/router";

@Component({
  selector: 'ubb-pizza',
  templateUrl: './pizza.component.html',
  styleUrls: ['./pizza.component.css']
})
export class PizzaComponent implements OnInit {
  pizzas: Pizza[];
  errorMessage: string;
  selectedPizza: Pizza;

  constructor(private pizzaService: PizzaService, private router: Router) { }

  ngOnInit() {
    this.getPizzas();
  }

  onSelect(pizza: Pizza): void {
    this.selectedPizza = pizza;
  }

  gotoDetail(selectedPizza: Pizza): void{
    this.router.navigate(['/pizzashop2010/pizza/', selectedPizza.id]);
  }

  updateOne(pizza: Pizza) {
    console.log("update one pizza: ");
    console.log(pizza);
    this.pizzaService.updatePizza(pizza)
        .subscribe(_ => {
          console.log("pizza updated successfuly");
        });

  }

  updateAll(newPrice: number) {
    console.log("Increment all the prices with " + newPrice);
    this.pizzaService.updateAllPizzas(newPrice)
        .subscribe(pizzas => {
          this.pizzas = pizzas;
        });
  }

  getPizzas() {
    this.pizzaService.getPizzas()
        .subscribe(
            pizzas => this.pizzas = pizzas,
            error => this.errorMessage = <any>error
        );
  }

  save(name: string, description: string, price: number){
    if (!name || !description || !price){
      alert("The fields cannot be empty!");
      return;
    }
    this.pizzaService.create(name,description,price).subscribe(response => {
          alert(JSON.stringify({ pizza: response}, null, 4));
        }

    );

  }

}
