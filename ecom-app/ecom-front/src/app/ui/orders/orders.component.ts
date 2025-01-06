import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit{
  constructor(private http:HttpClient, private router:Router) {
  }
  public orders:any;

  ngOnInit(): void {
    this.http.get("http://localhost:8088/api/orders").subscribe({
      next:value => {
        this.orders = value;
      },
      error:err => console.log(err)
    })
  }

  getOrderDetails(orderId:string) {
    console.log(orderId)
    this.router.navigateByUrl("/orders-details/"+orderId)
  }
}
