import {itemsArray, customerArray, orderArray, orderDetailArray} from "../db/db.js";
import {Order} from "../model/Order.js";
import {OrderDetail} from "../model/OrderDetail.js";


export class OrderController {
    constructor() {
        this.initialize();
        this.customerArray2 = customerArray;
        this.orderArray2 = orderArray;
        this.itemArray2 = itemsArray;

        this.orderDetailArray2 = orderDetailArray;


    }


    initialize() {
        //console.log("Kill")
        let id = orderArray.length + " ";  //PadStart is only compatible String
        $("#lblOrderID").text("D" + id.padStart(4, "0"));
        $("#custIdOrder").html(" ");

        customerArray.forEach(e => {
            console.log(e.id);
            $("#dropCustomer").append(
                `<li><a class="dropdown-item" href="#">${e.id}</a></li>`
            );

            $("#dropCustomer > li:last-child").click(() => {
                $("#orderCustomerName").val(e.name);
                $("#orderCustomerAddress").val(e.address);
                $("#orderCustomerContact").val(e.contact);

            });

        });

    }

}

let orderController = new OrderController();