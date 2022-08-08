package tr.edu.anadolu.productlistapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.anadolu.productlistapp.model.Product;
import tr.edu.anadolu.productlistapp.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/Product")
//@CrossOrigin(origins = {"http://10.27.34.164:3000"})  //(origins = {"http://ip:port"}) add cross
@Api(value = "Product API Documentation")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @GetMapping("/sortByNameAsc")
    @ApiOperation(value = "Sorts Products with their name ascending. A->Z")
    public ResponseEntity<List<Product>> sortByNameAsc(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size) {
        logger.debug("Products are listed with ascending order by name properties.");
        return productService.sortByNameAsc(page, size);
    }

    @GetMapping("/sortByNameDesc")
    @ApiOperation(value = "Sorts Products with their name descending. Z->A")
    public ResponseEntity<List<Product>> sortByNameDesc(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size) {
        logger.debug("Products are listed with descending order by name properties.");
        return productService.sortByNameDesc(page, size);
    }

    @GetMapping("/sortByPriceAsc")
    @ApiOperation(value = "Sorts Products from cheapest to most expensive")
    public ResponseEntity<List<Product>> sortByPriceAsc(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size) {
        logger.debug("Products are listed with ascending order by price properties.");
        return productService.sortByPriceAsc(page, size);
    }

    @GetMapping("/sortByPriceDesc")
    @ApiOperation(value = "Sorts Products from most expensive to cheapest.")
    public ResponseEntity<List<Product>> sortByPriceDesc(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size) {
        logger.debug("Products are listed with descending order by price properties.");
        return productService.sortByPriceDesc(page, size);
    }

    @GetMapping("/sortByType/{productType}")
    @ApiOperation(value = "Gets the desired product type from frontend and returns products accordingly.")
    public ResponseEntity<List<Product>> sortByType(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size, @PathVariable @ApiParam(value = "Type of the product") String productType) {
        logger.debug("Products are listed with Type property: "+productType);
        return productService.sortByType(page, size, productType);
    }

    @GetMapping("/sortByCategory/{productCategory}")
    @ApiOperation(value = "Gets the desired product category from frontend and returns products accordingly.")
    public ResponseEntity<List<Product>> sortByCategory(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size, @PathVariable @ApiParam(value = "Category of the product") String productCategory) {
        logger.debug("Products are listed with Category property: "+productCategory);
        return productService.sortByCategory(page, size, productCategory);
    }

    @GetMapping("/listProducts")
    @ApiOperation(value = "Listing every product inside the database.")
    public ResponseEntity<List<Product>> findAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        logger.debug("Product listing process completed.");
        return productService.findAll(page, size);
    }

    @PostMapping("/createProduct")
    @ApiOperation(value = "Creates a new product.")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        logger.debug("A new product has been created: " + product.getProductId());
        return productService.createNewProduct(product);
    }

    @GetMapping("/{productId}")
    @ApiOperation(value = "Getting a productId from user and returns if it is in database.")
    public Product getOneProduct(@PathVariable @ApiParam(value = "ID of the product") String productId) {
        logger.debug("The product with id : " + productId + " has been retrieved.");
        return productService.getProduct(productId);
    }

    @PutMapping("/updateProduct/{productId}")
    @ApiOperation(value = "Given it is productId, updates the product in the database.")
    public Product updateProduct(@PathVariable @ApiParam(value = "ID of the product") String productId, @RequestBody Product newProduct) {
        logger.debug("The information of the product with the id: " + productId + " has been updated.");
        return productService.updateProduct(productId, newProduct);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    @ApiOperation(value = "Given it is productId, deletes the product in the database.")
    public void deleteProduct(@PathVariable @ApiParam(value = "ID of the product") String productId) {
        logger.warn("Deleting product with id: " + productId);
        productService.deleteProduct(productId);
    }
    //@CrossOrigin(origins = "*")
    @PutMapping("/{productId}/availability")
    @ApiOperation(value = "Sets the product availability if it can be sold or not.", notes = "After calling, the availability of the product changes to true or false.")
    public Product setAvailability(@PathVariable @ApiParam(value = "ID of the product") String productId) {
        logger.debug("The stock status of the product with : " + productId + " id number has been updated: " + productService.getProduct(productId).isAvailability());
        return productService.setAvailability(productId);
    }

    @DeleteMapping("/deleteEveryProduct")
    @ApiOperation(value = "Deletes every product in the database.", notes = "There shouldn't be an option like this but it is there anyways. Can be removed.")
    public void deleteAll() {
        productService.deleteAll();
        logger.warn("All product records in the database have been deleted.");
    }

    @GetMapping("/listAvailableProducts")
    @ApiOperation(value = "Lists available products.")
    public ResponseEntity<List<Product>> findAvailableProducts(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        logger.debug("The products in supply are listed.");
        return productService.findAvailableProducts(page, size);
    }

    @GetMapping("/listNotAvailableProducts")
    @ApiOperation(value = "Lists not available products.")
    public ResponseEntity<List<Product>> findNotAvailableProducts(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "5") int size) {
        logger.debug("Unavailable products are listed.");
        return productService.findNotAvailableProducts(page, size);
    }

    @PostMapping("/postImage/{id}")
    @ApiOperation(value = "Saving the recieved Base64 format string to database")
    public void postImage(@PathVariable String id, @RequestBody String imgStr) throws IOException {
        productService.setImageString(id, imgStr);
    }

    @GetMapping("/getImage/{id}")
    @ApiOperation(value = "Return a string with Base64 format")
    public String getImage(@PathVariable String id ) throws IOException {
        return productService.getImageString(id);
    }
}
