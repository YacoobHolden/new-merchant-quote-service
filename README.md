# New Merchant Quote Service
A service for calculating the cost for a new merchant. Receives CSV files containing costing data, and provides and API to
query cost for a given industry, transaction count and transaction volume

## Architecture
TBD

## API
* **POST /api/csv**
    * **Accepts**: Multipart file
    * **Returns**: Empty response
* **GET /api/quote?industry=X&transactionVolume=Y&transactionCount=Z**
    * **Accepts**: Query parameters as above
    * **Returns**: Quote price in $

Further details on API can be found [here](TBD) or by following 'Running locally'

## Building

### Building source code
Use this to build the source code, including running tests

Requires: 
* mvn

Instructions:
```bash
$ mvn clean install
```

### Running locally
Use this to play with the service without deploying

Requires:
* docker
* docker-compose

Instructions:
```bash
# Start local docker image
$ cd localdev
$ ./run-all.sh
```

Can then view and use API [here](http://172.19.2.20:8080/api/swagger-ui.html)

## Deploying
TBD

## Decisions, Assumptions & Known Issues
### Decisions
1. Avoid rounding during execution if possible (Round to reasonable large value)
1. Round price half down

### Asssumptions
1. If lower than lowest value - use lowest known value for that industry
1. If higher than greatest value - use greatest known value for that industry

### Known Issues
1. Does not currently validate CSV or input parameters
1. Doesn't elegantly handle updates of uploaded values
1. No authentication on APIs