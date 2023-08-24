export class Item {

    constructor(code, name, qty, unitPriceitem) {
        this._code = code;
        this._name = name;
        this._qty = qty;
        this._unitPriceitem = unitPriceitem;
    }


    get code() {
        return this._code;
    }

    set code(value) {
        this._code = value;
    }

    get name() {
        return this._name;
    }

    set name(value) {
        this._name = value;
    }

    get qty() {
        return this._qty;
    }

    set qty(value) {
        this._qty = value;
    }

    get unitPriceitem() {
        return this._unitPriceitem;
    }

    set unitPriceitem(value) {
        this._unitPriceitem = value;
    }

}