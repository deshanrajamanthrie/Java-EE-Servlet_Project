import {Item} from "../model/Item.js";
import {itemsArray} from "../db/db.js";

export class ItemController {
    constructor() {
        $("#btnItemSave").click(this.itemSave.bind(this));
        $("#btnItemDelete").click(this.itemDelete.bind(this));
        $("#btnItemUpdate").click(this.itemUpdate.bind(this));
        this.textFieldOnAction();
        this.loadAllItem();
    }


    itemSave() {
        if (this.isValid()) {
            let code = $("#txtItemid").val();
            let itemName = $("#txtItemName").val();
            let itemQty = $("#txtItemqty").val()
            let itemUnitPrice = $("#txtItemUnitPrize").val();
            let items = new Item(code, itemName, Number.parseInt(itemQty), Number.parseInt(itemUnitPrice));

            $.ajax({
                url: "http://localhost:8080/Mapping/item",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(items),
                success: function (resp) {
                    if (resp.code === 200) {
                        alert(resp.message);
                    }
                    if (resp.code === 400) {
                        alert(resp.message);
                    } else {
                        alert(resp.data);
                    }

                }
            });
            this.clearTextField();
        } else {

        }
        this.loadAllItem();
    }

    itemUpdate() {


    }


    //=================================================Item GetAll
    loadAllItem() {
        $.ajax({
            url: "http://localhost:8080/Mapping/item",
            method: "GET",
            success: function (resp) {
                $("#item-Table").empty();
                for (const item of resp.data) {
                    let row = `<tr><td>${item.code}</td><td>${item.itemName}</td><td>${item.qty}</td><td>${item.unitPrice}</td></tr>`;
                    $("#item-Table").append(row);

                    $("#item-Table>:last-child").click(function () {
                        let code = $(this).children().eq(0).text();
                        let itemName = $(this).children().eq(1).text();
                        let qty = $(this).children().eq(2).text();
                        let unitPrice = $(this).children().eq(3).text();

                        $("#txtItemid").val(code);
                        $("#txtItemName").val(itemName);
                        $("#txtItemqty").val(qty);
                        $("#txtItemUnitPrize").val(unitPrice);

                    });
                }

            }
        });
    }


    textFieldOnAction() {
        $("#txtItemid").on('keydown', function (event) {
            if (event.key === 'Enter') {
                $("#txtItemName").focus();
            }
        });
        $("#txtItemName").on('keydown', function (event) {
            if (event.key === 'Enter') {
                $("#txtItemqty").focus();
            }
        });
        $("#txtItemqty").on('keydown', function (event) {
            if (event.key === 'Enter') {
                $("#txtItemUnitPrize").focus();
            }
        });
    }

    itemDelete() {
        let code = $("#txtItemid").val();
        $.ajax({
            url: "http://localhost:8080/Mapping/item?code=" + code,
            method: "DELETE",
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
        this.loadAllItem();
        this.clearTextField();
    }


    //============================clear the text Field
    clearTextField() {
        $("#txtItemid").val("");
        $("#txtItemName").val("");
        $("#txtItemqty").val("");
        $("#txtItemUnitPrize").val("")
    }

    //===========================Regex settle
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