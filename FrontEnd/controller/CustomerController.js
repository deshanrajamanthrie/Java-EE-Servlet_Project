import {Customer} from "../model/Customer.js";
import {customerArray} from "../db/db.js";

export class CustomerController {
    constructor() {
        $("#btnSaveCustomer").click(this.customerSave.bind(this));
        this.loadAllCustomer();

    }
    //============Save Customer
    customerSave() {
        let cId = $("#txtcustomerid").val();
        let cName = $("#txtcustomerName").val()
        let cAddress = $("#txtcustomerAddres").val();
        let cContact = $("#txtcustomerContact").val();
        let customer = new Customer(cId, cName, cAddress, cContact);

        $.ajax({
            url: "http://localhost:8080/Mapping/customer",
            method: "POST",
            contentType: "application/json",  //Request Type
            data: JSON.stringify(customer), //Java Script Object convert to the Jason Object1
            success: function (resp) {


            }

        });
    }


}


let customerController = new CustomerController();









