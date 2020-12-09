# New Merchant Quote Service
A service for calculating the cost for a new merchant. Receives CSV files containing costing data, and provides and API to
query cost for a given industry, transaction count and transaction volume

## Architecture
TBD

## API
* **POST /api/csv**
    * **Accepts**: Multipart file
    * **Returns**: Empty response
    * **Requires**: Authenticated with role USER
* **GET /api/quote?industry=X&transactionVolume=Y&transactionCount=Z**
    * **Accepts**: Query parameters as above
    * **Returns**: Quote price in $
    * **Requires**: Authenticated with role USER
* **GET /home**
    * **Returns**: Home page for interacting with app
    * **Requires**: Authenticated with role USER
* **GET /health**
    * **Returns**: Empty body
    * **Requires**: No authentication
* **GET /swagger-ui.html**
    * **Returns**: SwaggerUI page for testing and exploring application
    * **Requires**: No authentication


Further details on API can be found [here](http://new-m-publi-1eqhl0nteohsn-1135320769.ap-southeast-2.elb.amazonaws.com/swagger-ui.html) or by following 'Running locally'

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
Requires:
* jq
* docker
* aws cli (V1)

Instructions:

1 - Run the deployment scripts
```bash
$ ./deploy.sh
```

2 - Identify load balancer URL in AWS Console
* Sign in to AWS Console
* Go to EC2/Load Balancers
* Copy DNS Name value

3 - Upload CSV
```bash
$  curl -F file=@/home/YOUR.USER/Downloads/example.csv YOUR_LB_DNS/api/csv --user 'admin:admin'
```

4 - Run queries via cURL
```bash
$  curl YOUR_LB_DNS/api/csv?industry=INDUSTRY&transactionCount=TRANSACTION_COUNT&transactionVolume=TRANSACTION_VOLUME --user 'user:password'
```

5 - Run queries from web
* Go to YOUR_LB_DNS/swagger-ui.html and use credentials 'user:password' when prompted

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