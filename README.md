# Challenge 2 - Stocks API

This is a REST API version of the last challenge.

## Models

There are 3 Models in the project, 2 of which are mapped to tables. The third exists for presentation purposes.

-`StockData` which maps to the table `stocks`

-`Company` which maps to the table `companies`

- `AggregateData` which is used to hold compiled info from `companies` and `stocks`


## Routes

#### `POST /stocks/load`
This route will read the data from the remote URL stored in the `stocks.remote.url` of the `application.properties`

The `stocks` and `companies` tables will be filled with data from this request.

#### `GET /stocks`

This will return the full array of stock data with nested company object references.

#### `GET /stocks/{type}/{symbol}/{date}`

This route will generate data for a given stock symbol. The path variable `type` is used to determine what kind of data 
the user is trying to collect (`month` or `date`). 

> Notes about `date` 
> - In a `month` call the date should be a numeric representation of the 
month, either 2 or 1 digit(s) will work i.e. `06`
> - In a `date` call the date should be formatted as such
i.e. `2018-06-22`

The `symbol` path variable is used to identify the company in question
and should be 4 characters long. i.e. `GOOG`

##### Example

`GET /stocks/date/GOOG/2018-06-22`

##### Response

The following is an example response:

```json
{
    "highestPrice": 1130.99,
    "lowestPrice": 1120.01,
    "totalVolume": 724223,
    "closePrice": 1122.57,
    "type": "date",
    "symbol": "GOOG",
    "dateRequested": "2018-06-22"
}}
```

### Testing 

This project also includes a [set of Postman Tests](https://github.com/aturingmachine/stocks-api/blob/master/stocks-api.postman_collection.json)
to run against the API running on your localhost! They should be run in order
from bottom to top.
