import {Customer} from "../model/Customer.js";
import {Item} from "../model/Item.js";
import {OrderDetail} from "../model/OrderDetail.js";
import {Order} from "../model/Order.js";

var customerArray = new Array(); //customerArray
customerArray.push(new Customer("C001", "Kamal Weerasingha", "Colombo", "0762277559"));
customerArray.push(new Customer("C002", "Nimal Santha", "Colombo", "0752177592"));
customerArray.push(new Customer("C003", "Herath Bandara", "Colombo", "0750277596"));


var itemsArray = new Array();//Item Array
itemsArray.push(new Item("M001", "Laptop", 6, 85000.00));
itemsArray.push(new Item("M002", "Mouse", 6, 1000.00));
itemsArray.push(new Item("M003", "Speaker Set", 6, 3000.00));

var orderArray = new Array(); //Order Array
orderArray.push(new Order("D005",new Date().toISOString().split("T")[0],new Date().toLocaleTimeString(),"C001","0","1000.00"));
var orderDetailArray = new Array();//


export {customerArray, itemsArray, orderArray, orderDetailArray}


