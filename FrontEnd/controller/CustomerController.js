import {Customer} from "../model/Customer.js";
import {customerArray} from "../db/db.js";


export class CustomerController {
    constructor() {
        $("#btnSaveCustomer").click(this.customerHandle.bind(this));
        this.txtFieldOnAction();
        $("#customerTable>tr").click(this.tableHandle.bind(this));
        $("#searchCustomer").click(this.customerSearch.bind(this));
        $("#btnUpdatesCustomer").click(this.updateCustomer.bind(this));
        $("#btnDeleteCustomer").click(this.deleteCustomer.bind(this));
        this.customerArray2 = customerArray;
        this.loadAllCustomer();
        console.log(this.customerArray2);
    }


    tableHandle() {
        let id = $(this).children(":nth-child(1)").text();
        let name = $(this).children(":nth-child(2)").text();
        let address = $(this).children(":nth-child(3)").text();
        let contact = $(this).children(":nth-child(4)").text();
        console.log(id, name, address, contact);

        $("#txtcustomerid").val(id);
        $("#txtcustomerName").val(name);
        $("#txtcustomerAddres").val(address);
        $("#txtcustomerContact").val(contact);
    }

    /*  updateCustomer(){
          alert("Hello Update");
      }*/


    customerHandle() {
        for (let i of customerArray) {
            if (i.id === $("#txtcustomerid").val().trim()) { //Check the customer Id before include it
                alert("Already Customer has been Exists!");
                return;                                       //=======================================================not run in the code
            }

        }

        if (this.IsValid() || this.txtFieldOnAction()) {

            let id = $("#txtcustomerid").val().trim();
            let name = $("#txtcustomerName").val().trim();
            let address = $("#txtcustomerAddres").val().trim();
            let contact = $("#txtcustomerContact").val().trim();
            let customerObj = new Customer(id, name, address, contact);
            if (customerObj.id === "") {
                alert("Submit Failed! Please Input Your Detail.");
                return;
            } else {
                this.saveCustomer(customerObj);
            }
        }
        this.tableHandle();
    }

    saveCustomer(cutomerObj) {                           //===================================================only customer Save

        customerArray.push(cutomerObj);
        this.loadAllCustomer();


    }

    loadAllCustomer() {
        $("#customerTable").empty();                                 //this use statement for do empty table
        for (let i of this.customerArray2) {
            let printRow = `<tr><th>${i.id}</th><th>${i.name}</th><th>${i.address}</th><th>${i.contact}</th></tr>`;
            console.log(printRow);
            $("#customerTable").append(printRow);

        }
        this.cleartextFields();
    }




    //===================================================================key down Function "Enter"
    txtFieldOnAction() {

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

        $("#txtcustomerContact").keydown(function (event) {
            if (event.key === 'Enter') {
                $("#btnSaveCustomer").focus();
            }
        });
        $("#btnSaveCustomer").keydown(function (event) {
            if (event.key === 'Enter') {
                $("#txtcustomerid").focus();
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

    /*downKeyOnAction();*/

    customerSearch() {
        let custId = $("#txtsearchCustomer").val();
        let responced = this.searchId(custId);
        if (responced) {
            $("#txtcustomerid").val(responced.id);
            $("#txtcustomerName").val(responced.name);
            $("#txtcustomerAddres").val(responced.address);
            $("#txtcustomerContact").val(responced.contact);
        } else {

        }
    }

    updateCustomer() {
        if (this.IsValid()) {
            let id = $("#txtcustomerid").val();
            let name = $("#txtcustomerName").val();
            let address = $("#txtcustomerAddres").val();
            let contact = $("#txtcustomerContact").val();

            this.customerArray2.forEach((e) => {
                    if (e.id === id) {
                        e.name = name;
                        e.address = address;
                        e.contact = contact;
                    }
                }
            );
            this.loadAllCustomer();
        }
    }

    deleteCustomer() {
        this.customerArray2 = this.customerArray2.filter(e => {
            return e._id !== $("#txtcustomerid").val();
        });
        this.customerArray2.forEach(e => {
            console.log(e)
        });

        this.loadAllCustomer();

    }
    searchId(custId) {
        for (let i = 0; i < customerArray.length; i++) {
            if (customerArray[i].id === custId) {
                return customerArray[i];
            }
        }
    }


    cleartextFields() {
        $("#txtcustomerid").val("");
        $("#txtcustomerName").val("");
        $("#txtcustomerAddres").val("");
        $("#txtcustomerContact").val("");
    }

}


let customerController = new CustomerController();





