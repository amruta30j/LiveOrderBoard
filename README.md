**

## LiveOrderBoard

**

This is a 'Live Order Board' library. It allows users to create, delete and view summary of live orders. Summary is returned in a market depth format. Following are the instructions for UI team or client to use this library.
1. All the classes to use are inside the api package.
2. Create an object of LiveOrderBoard using appropriate factory. For ex. to use in-memory-data store please create factory object of LiveOrderBoardInMemoryDBFactory.
here is an example, 		

```
LiveOrderBoardFactory liveOrderBoardFactory = new LiveOrderBoardInMemoryDBFactory();
LiveOrderBoard liveOrderBoard = liveOrderBoardFactory.create();
```
		
3. Create order: Order can be created using  createOrder() function inside LiveOrderBoard class.
Following are the required parameters to create an order: 
```
•	User Id (a String value)
•	Order Quantity (a double value eg. 3.4)
•	Order Type (a String value . Allowed values : "buy","sell")
•	Order price(an integer value)
```
Upon creating an order user will be given a response object called as `OrderInformation` which contains an orderId and order status for the order created. Following are the valid order statuses 
`EXECUTED,FAILED,PENDING,REGISTERED,CANCELLED`
If the order creation is successful, the orderStatus will be set to REGISTERED. It will be set to FAILED in case of any failure.
4. Cancel order: Order can be cancelled using `cancelOrder` method inside LiveOrderBoard class. To cancel an order user must have the orderId (the one which was returned as response to register order).
Upon cancelling an order, the user will receive a response in the form of `OrderInformation` object. If the order has been cancelled successfully, the orderStatus will be set to CANCELLED. Please note that we are not deleting the order from our database but simply marking it as CANCELLED for future references.
5. Get summary information of live orders: Live orders (in this case REGISTERED orders) are returned via getLiveOrdersSummary() functionality. The output of this function is LiveOrdersSummary. to see the output on the console, please use

```
LiveOrdersSummary liveOrdersSummary = liveOrderBoard.getLiveOrdersSummary();
```

liveOrdersSummary.print() function inside the LiveOrdersSummary class. 

It produces an output as below,
```
SELL: 3.8 kg for £300
SELL: 7.0 kg for £305
SELL: 3.5 kg for £310
SELL: 6.4 kg for £400
SELL: 1.4 kg for £500
BUY: 4.4 kg for £500
BUY: 7.2 kg for £400
BUY: 3.5 kg for £310
```
