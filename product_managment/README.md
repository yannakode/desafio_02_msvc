# Products Managment
Product Management is a client microservice that consumes data from the Product Catalog microservice, offering operations with the GET, POST, PUT, and DELETE methods. It provides functionalities to list all products, retrieve a specific product, edit, delete, and filter products by price, as well as inventory control, displaying the quantity available.
# Technologies used
<img align="center" alt="java" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
<img align="center" alt="spring boot" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />

### Endpoints
* Create product: POST /productInventory/productManagement/v1
* Update product: PUT /productInventory/productManagement/v1/{id}
* List products: GET /productInventory/productManagement/v1
* Get product by id: GET /productInventory/productManagement/v1/{id}
* Filter products by price (min and max values): GET /productInventory/productManagement/v1/search?min={min_value}&max={max_value}
* Delete product by id: DELETE /productInventory/productManagement/v1/{id}

### Example response
```json
{
        "product": {
            "name": "Livro \"O Alquimista\" de Paulo Coelho",
            "description": "Clássico da literatura brasileira",
            "price": 24.99
        },
        "quantity": 4
    }

