# catalog_product
Catalog Products is a microservice that provides functionality for managing products, including creating, updating, retrieving, filtering and deleting products.
# Technologies used
<img align="center" alt="java" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
<img align="center" alt="spring boot" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />
<img align="center" alt="h2" src="https://img.shields.io/badge/H2-003545?style=for-the-badge&logo=h2&logoColor=white" />

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
  "name": "computador",
  "description": "Latest model smartphone with 128GB storage",
  "price": 850.0
}

