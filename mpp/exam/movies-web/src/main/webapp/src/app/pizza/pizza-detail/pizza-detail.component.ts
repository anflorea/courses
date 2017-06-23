import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';
import {PizzaService} from "../shared/pizza.service";
import {Pizza} from "../shared/pizza.model";
import {Ingredient} from "../shared/ingredient.model";

@Component({
  selector: 'ubb-pizza-detail',
  templateUrl: './pizza-detail.component.html',
  styleUrls: ['./pizza-detail.component.css']
})
export class PizzaDetailComponent implements OnInit {

  @Input()
  pizza: Pizza;
  ingredients: Ingredient[];

  constructor(private route: ActivatedRoute, private pizzaService: PizzaService, private location: Location) {}

  ngOnInit() {
    console.log("asdf ");
    this.route.params.switchMap((params: Params) => this.pizzaService.getPizza(+params['id'])).subscribe(pizza => this.ingredients = pizza.ingredients );
  }

  goBack(): void {
    console.log("asdf: " + this.pizza);
    this.location.back();
  }

}
