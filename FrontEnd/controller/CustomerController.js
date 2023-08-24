import {Customer} from "../model/Customer.js";
import {customerArray} from "../db/db.js";

export class CustomerController {
    constructor() {
        $("#btnSaveCustomer").click(this.customerSave.bind(this));
        $("#btnUpdatesCustomer").click(this.customerUpdate.bind(this));
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

            }
        );

    }

    //=======================Get All Customer
    loadAllCustomer() {
        $.ajax({
            url: "http://localhost:8080/Mapping/customer",
            method: "GET",
            success: function (resp) {
                $("#customerTable").empty();
                console.log(typeof resp);
                for (const customer of resp.data) {
                    let row = `<tr><td>${customer.id}</td><td>${customer.name}</td><td>${customer.address}</td><td>${customer.contact}</td></tr>`;
                    $("#tbl-customer").append(row);
                }
            }
        });
        this.allBindCustomer();

    }

    // ===========================Bind Customer in to the text Fields

    allBindCustomer() {
        $("#tbl-customer>tr").click(function () {
            alert("Hello")
            /*  let id = $(this).children().eq(0).id.text();
              let name = $(this).children().eq(1).text();
              let address = $(this).children().eq(2).text();
              let contact = $(this).children().eq(3).text();
              alert("Hello")

              console.log(id + "" + name + "" + address + "" + contact);
              $("#txtcustomerid").val(id);
              $("#txtcustomerName").val(name);
              $("#txtcustomerAddres").val(address);
              $("#txtcustomerContact").val(contact);*/

        });
    }

    //=============================Update Customer
    customerUpdate() {
        let cId = $("#txtcustomerid").val();
        let cName = $("#txtcustomerName").val()
        let cAddress = $("#txtcustomerAddres").val();
        let cContact = $("#txtcustomerContact").val();
        let customer = new Customer(cId, cName, cAddress, cContact);


        $.ajax({
            url: "http://localhost:8080/Mapping/customer",
            method: "PUT",
            data: JSON.stringify(customer),
            success: function (resp) {
                alert(resp);
                this.loadAllCustomer();
            }
        });
    }



}


let customerController = new CustomerController();











