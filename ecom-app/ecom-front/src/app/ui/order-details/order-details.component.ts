import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent implements OnInit{
  public orderId!:string;
  orderDetails:any;
  constructor(private route:ActivatedRoute, private http:HttpClient) {
    this.orderId = route.snapshot.params["id"];
  }
  ngOnInit(): void {
    this.http.get("http://localhost:8088/api/orders/"+this.orderId).subscribe(
        {
            error:err=>console.log(err),
            next:details=>{
              this.orderDetails = details;
            }
        }
    )
  }

    getTotalOrder(orderDetails: any) {
        let total:number = 0;
        orderDetails.productItems.forEach((pi: { price: number; quantity: number }) => {
            total += pi.price * pi.quantity;
        });
        return total
    }
}
