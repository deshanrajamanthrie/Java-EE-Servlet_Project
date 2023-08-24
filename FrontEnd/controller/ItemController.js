import {Item} from "../model/Item.js";
import {itemsArray} from "../db/db.js";

export class ItemController {
    constructor() {
        $("#btnItemSave").click(this.itemHandle.bind(this));
        $("#btnItemSearch").click(this.itemSearch.bind(this));
        $("#btnItemUpdate").click(this.itemUpdateHandle.bind(this));
        /* this.isValid();*/
        this.textFieldOnAction();

        this.itemsarray2 = itemsArray;
        this.loadAllItem();

    }

    textFieldOnAction() {
        $("#txtItemid").keydown(function (event) {
            if (event.key === 'Enter') {
                $("#txtItemName").focus();
            }
        });
        $("#txtItemName").keydown(function (event) {
            if (event.key === 'Enter') {
                $("#txtItemqty").focus();
            }
        });
        $("#txtItemqty").keydown(function (event) {
            if (event.key === 'Enter') {
                $("#txtItemUnitPrize").focus();
            }
        });
        $("#txtItemUnitPrize").keydown(function (event) {
            if (event.key === 'Enter') {
                $("#btnItemSave").focus();
            }
        });
    }

    itemHandle() {
        for (let i in this.itemsarray2) {
            if (this.itemsarray2[i].code === $("#txtItemid").val()) {
                alert("Already Exists!")
                return;
            }
        }
        if (this.isValid()) {
            let code = $("#txtItemid").val();
            let name = $("#txtItemName").val();
            let qty = $("#txtItemqty").val();
            let unitPrize = $("#txtItemUnitPrize").val();
            let itemobj = new Item(code, name, qty, unitPrize);
            if ((itemobj.code && itemobj.name && itemobj.qty && itemobj.unitPrize) === "") {
                alert("Submit Failed! Please Input Your Detail");
            } else {
                this.saveItem(itemobj);
            }
        }
    }

    saveItem(itemobj) {
        let itemAdd = this.itemsarray2.push(itemobj);
        console.log(itemAdd);
        this.loadAllItem();
    }

    loadAllItem() {
        $("#item-Table").empty();
        for (let i in this.itemsarray2) {
            let printRow = `<tr><th>${this.itemsarray2[i].code}</th><th>${this.itemsarray2[i].name}</th><th>${this.itemsarray2[i].qty}</th><th>${this.itemsarray2[i].unitPriceitem}</th></tr>`;
            $("#item-Table").append(printRow);
        }
        this.clearTextField();
    }

    itemSearch() {
        this.itemsarray2.filter(function (e) {
            if (e._code === $("#searchItem").val()) {
                $("#txtItemid").val(e._code);
                $("#txtItemName").val(e._name);
                $("#txtItemqty").val(e._qty);
                $("#txtItemUnitPrize").val(e._unitPriceitem);
            }/*{
                if (e._code !== $("#searchItem").val()) {
                    $("#searchItem").css('border', '2px solid #d63031');
                    $("#not-Define-item").text("Have Not Define an Item").css('color', 'red');
                }
            }*/
        });


    }

    //================================================Update items
    itemUpdateHandle() {

        let upName = $("#txtItemName").val();
        let upqty = $("#txtItemqty").val();
        let upUnit = $("#txtItemUnitPrize").val();

        this.itemsarray2.forEach((e) => {           //get all items array's data after check item id and input id
            if (e._code === $("#txtItemid").val()) {
                e._name = upName;
                e._qty = upqty;
                e._unitPriceitem = upUnit;
            }
        });
        this.loadAllItem();
    }

    //===============clear the text Field
    clearTextField() {
        $("#txtItemid").val("");
        $("#txtItemName").val("");
        $("#txtItemqty").val("");
        $("#txtItemUnitPrize").val("")
    }

    //=======================Regex settle
    isValid() {
        let iscode = /^M([0-9]){3,3}$/;
        let isType = /^[A-Za-z]+-[0-9]{4}$|^[A-Za-z\s]+$/;
        let isQty = /^-?\d+(?:\.\d+)?$/;
        let isUnit = /-?\d+(?:\.\d+)?$/;

        if (!iscode.test($("#txtItemid").val())) {
            $("#txtItemid").css('border', '2px solid #d63031');
            $("#error-ItemId").text(" Follow This : M001").css('color', '#d63031');
            return false;                  //if not compatible the input that regex pattern ,there fore we must return false
        } else {
            $("#txtItemid").css('border', '2px solid #26de81');
            $("#error-ItemId").text("");
        }
        if (!isType.test($("#txtItemName").val())) {
            $("#txtItemName").css('border', '2px solid #d63031');
            $("#error-itemName").text(" Follow This : Laptop").css('color', '#d63031');
            return false;
        } else {
            $("#txtItemName").css('border', '2px solid #26de81');
            $("#error-itemName").text("");

        }
        if (!isQty.test($("#txtItemqty").val())) {
            $("#txtItemqty").css('border', '2px solid #d63031');
            $("#error-itemQty").text(" Follow This : 100").css('color', '#d63031');
            return false;
        } else {
            $("#txtItemqty").css('border', '2px solid #26de81');
            $("#error-itemQty").text("");

        }
        if (!isUnit.test($("#txtItemUnitPrize").val())) {
            $("#txtItemUnitPrize").css('border', '2px solid #d63031');
            $("#error-itemunitPrize").text(" Follow This : 1000.00").css('color', '#d63031');
            return false;
        } else {
            $("#txtItemUnitPrize").css('border', '2px solid #26de81');
            $("#error-itemunitPrize").text("");
        }
        return true;
    }


}

new ItemController();