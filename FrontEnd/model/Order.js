export class Order{

    get id() {
        return this._id;
    }

    set id(value) {
        this._id = value;
    }

    get date() {
        return this._date;
    }

    set date(value) {
        this._date = value;
    }

    get time() {
        return this._time;
    }

    set time(value) {
        this._time = value;
    }

    get customerId() {
        return this._customerId;
    }

    set customerId(value) {
        this._customerId = value;
    }

    get discount() {
        return this._discount;
    }

    set discount(value) {
        this._discount = value;
    }

    get totalPrice() {
        return this._totalPrice;
    }

    set totalPrice(value) {
        this._totalPrice = value;
    }

    constructor(id,date,time,customerId,discount,totalPrice) {
        this._id=id;
        this._date=date;
        this._time=time;
        this._customerId=customerId;
        this._discount=discount;
        this._totalPrice=totalPrice;
    }


}