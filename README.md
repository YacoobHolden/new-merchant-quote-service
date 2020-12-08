# New Merchant Quote Service
A service for calculating the cost for a new merchant. Receives CSV files containing costing data, and provides and API to
query cost for a given industry, transaction count and transaction volume

## Architecture
TBD

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

## Deploying
TBD

## Decisions, Assumptions & Known Issues
### Decisions
1. Avoid rounding during execution if possible (Round to reasonable large value)
1. Round price half down

### Asssumptions
1. If lower than lowest value - use lowest known value for that industry
1. If higher than greatest value - use greatest known value for that industry
1. Don't need to handle updates of uploaded values

### Known Issues
1. Currently validate CSV or input parameters
1. No authentication on APIs