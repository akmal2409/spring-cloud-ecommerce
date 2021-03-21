# spring-cloud-ecommerce

## Architecture
![Architecture](https://github.com/akmal2409/spring-cloud-ecommerce/blob/main/Architecture.png)


## Technologies Used


## Setup Vault with secrets
[Download HashiCorp Vault on your machine](https://www.hashicorp.com/products/vault)
The secrets are sensitive pieces of data that are stored as encrtypted
data objects in vault. All database configurations as well as OAuth2
clientId and clientSecret are stored there.

#### Start the server in dev mode 
    vault server -dev

And copy the token

#### Load secrets 
###### Through bash script
    #!/bin/bash

    export VAULT_ADDR='http://127.0.0.1:8200'
    
    echo "Input vault token"
    
    read token
    
    export VAULT_TOKEN=$token
    
    vault kv put secret/order-service @/{your path}/spring-cloud-ecommerce/order-service/order-service.json
    vault kv put secret/product-service @/{your path}/spring-cloud-ecommerce/product-service/product-service.json
    vault kv put secret/api-gateway-service @/{your path}/spring-cloud-ecommerce/api-gateway-service/api-gateway-service.json
    echo "Secrets were added"

Start the script ./vault-init.sh and input your token.
You will have to do this everytime because dev server does not save
any data.

###### Through command line

    $ vault kv put secret/order-service @/{your path}/spring-cloud-ecommerce/order-service/order-service.json
    $ vault kv put secret/product-service @/{your path}/spring-cloud-ecommerce/product-service/product-service.json
    $ vault kv put secret/api-gateway-service @/{your path}/spring-cloud-ecommerce/api-gateway-service/api-gateway-service.json

#### Start services

    $ cd spring-cloud-eccomerce/Docker
    $ docker-compose up -d
