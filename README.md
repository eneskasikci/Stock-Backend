# Introduction

This is the backend of canteen application for Anadolu University Computer Research and Application Center.

This project made with a team of 6 interns.

Using Rest API, Spring Boot, MongoDB, Docker, RabbitMQ. 

# Docker Setup

Start the terminal in the project directory. Run "docker-compose build" and then start the containers by "docker-compose up".

In Postman connect to "localhost:8081/api/Product/{ WRITE_MAPPINGS_HERE }" to test the functions.

## Mappings
### POST Mappings
#### createProduct: Adds a product to the database in JSON format

- localhost:8081/api/Product/createProduct

        {
            "productName" : "Espresso,
            "productPrice" : 5
        }

Other parameters gets set by null if it is not given.

### GET Mappings
#### listProducts: List every product that is on the database.
- localhost:8081/api/Product/listProducts

#### Listing products by their availability.
When products are added, they are available by default and will be listed to purchase.
- Returns products that are available.
  - localhost:8081/api/Product/listAvailableProducts

- This returns products that are not available.
  - localhost:8081/api/Product/listNotAvailableProducts


#### Sorting products given their name or prices

- Sorting product prices, names ascending or descending
  - localhost:8081/api/Product/sortByPriceAsc
  - localhost:8081/api/Product/sortByPriceDesc
  - localhost:8081/api/Product/sortByNameAsc
  - localhost:8081/api/Product/sortByNameDesc

#### Listing a product If it is exist with the ID
- localhost:8081/api/Product/URUN_{replaceThisWithANumber}

      Ex. localhost:8081/api/Product/URUN_1
     
        {
            "productId": "URUN_1",
            "productName": "Espresso",
            "productPrice": 3.0,
            "productType": "ICECEK",
            "productImage": null,
            "availability": true
        }         

#### Listing the products with their type
- localhost:8081/api/Product/sortByType/{productType}

### Delete Mappings
- localhost:8081/api/Product/{productId}

Deletes the product from the database with DELETE mapping.

### Put Mappings
If the product not available it shouldn't be purchasable. So the admin should change the availability of the product.
- localhost:8081/api/Product/{productId}/availability

This changes the state of the availability of the product. If the product is available it makes it not available and vice-versa.