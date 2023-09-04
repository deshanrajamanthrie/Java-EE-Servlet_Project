import {Customer} from "../model/Customer.js";
import {customerArray} from "../db/db.js";

export class CustomerController {
    constructor() {
        $("#btnSaveCustomer").click(this.customerSave.bind(this));
        $("#btnUpdatesCustomer").click(this.customerUpdate.bind(this));
        this.loadAllCustomer();
        $("#btnDeleteCustomer").click(this.customerDelete.bind(this))
        this.txtFieldKeyOnAction();

    }


    //===================================Save Customer
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
                    if (resp.code === 200) {
                        alert(resp.message);
                    } else if (resp.code === 400) {
                        alert(resp.message);
                    } else {
                        alert(resp.data)
                    }
                }
            }
        )

    }

    //======================================Get All Customer
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

                    $("#tbl-customer>:last-child").click(function () {
                        let id = $(this).children().eq(0).text();
                        let name = $(this).children().eq(1).text();
                        let address = $(this).children().eq(2).text();
                        let contact = $(this).children().eq(3).text();


                        console.log(id + "" + name + "" + address + "" + contact);
                        $("#txtcustomerid").val(id);
                        $("#txtcustomerName").val(name);
                        $("#txtcustomerAddres").val(address);
                        $("#txtcustomerContact").val(contact);

                    });
                }
            }
        });

    }

    // ===========================Bind Customer in to the text Fields

    /*  allBindCustomer(row) {
         $(row).click(function () {
             alert("Hello")
               let id = $(this).children().eq(0).id.text();
               let name = $(this).children().eq(1).text();
               let address = $(this).children().eq(2).text();
               let contact = $(this).children().eq(3).text();
               alert("Hello")

               console.log(id + "" + name + "" + address + "" + contact);
               $("#txtcustomerid").val(id);
               $("#txtcustomerName").val(name);
               $("#txtcustomerAddres").val(address);
               $("#txtcustomerContact").val(contact);

         });
     }*/

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
            data: JSON.stringify(customer),// String Data Convert to Json Type
            success: function (resp) {
                if (resp.code === 200) {
                    alert(resp.message);
                } else if (resp.code === 400) {
                    alert(resp.message);
                } else {
                    alert(resp.data);
                }

            }
        });
    }

    //===============================Delete Customer
    customerDelete() {
        $.ajax({
            url: "http://localhost:8080/Mapping/customer" + $("#txtcustomerid").val(),
            method: "DELETE",
            success: (resp) => {
                if (resp.code === 200) {
                    alert(resp.message)
                } else if (resp.code == 400) {
                    alert(resp.message)
                }else{
                    alert(resp.message)
                }
            }

        });
    }

    //=======================Mouse key On Action
    txtFieldKeyOnAction() {
        $("#txtcustomerid").on('keydown', function (event) {
            if (event.key === 'Enter') {
                $("#txtcustomerName").focus();
            }
        });
        $("#txtcustomerName").on('keydown', function (event) {
            if (event.key === 'Enter') {
                $("#txtcustomerAddres").focus();
            }

        });
        $("#txtcustomerAddres").on('keydown', function (event) {
            if (event.key === 'Enter') {
                $("#txtcustomerContact").focus();
            }
        });
    }

    IsValid() {
        //===============================================================================Regex Patterns
        let isId = /^C([0-9]){3,3}$/;
        let isName = /^([a-zA-Z]{2,}\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\s?([a-zA-Z]{1,})?)$/;
        let isAddress = /^([A-z]){2,}$/;
        let isContact = /^(?:0|94|\+94)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|912)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\d)\d{6}$/;


        if (!isId.test($("#txtcustomerid").val())) {
            $("#txtcustomerid").css('border', '2px solid #d63031');
            $("#error-id").text(" Follow This : C001").css('color', '#d63031');
            // $("#txtcustomerid").val("");

            return false;
        } else {
            $("#txtcustomerid").css('border', '2px solid #26de81');
            $("#error-id").text("");

        }
        if (!isName.test($("#txtcustomerName").val())) {
            $("#txtcustomerName").css('border', '2px solid #d63031');
            $("#error-name").text("Follow This: Saman Kumara").css('color', '#d63031');
            //$("#txtcustomerName").val("");
            return false;
        } else {
            $("#txtcustomerName").css('border', '2px solid #26de81');
            $("#error-name").text("");
        }
        if (!isAddress.test($("#txtcustomerAddres").val())) {
            $("#txtcustomerAddres").css('border', '2px solid #d63031');
            $("#error-address").text("Follow This:Colombo").css('color', '#d63031');
            // $("#txtcustomerAddres").val("");
            return false;
        } else {
            $("#txtcustomerAddres").css('border', '2px solid #26de81');
            $("#error-address").text("");
        }
        if (!isContact.test($("#txtcustomerContact").val())) {
            $("#txtcustomerContact").css('border', '2px solid #d63031');
            $("#error-contact").text("Follow This :0777777777 ").css('color', '#d63031');
            return false;
            // $("#txtcustomerContact").val("");
        } else {
            $("#txtcustomerContact").css('border', '2px solid #26de81');
            $("#error-contact").text("");
        }
        return true;
    }


}


let customerController = new CustomerController();











