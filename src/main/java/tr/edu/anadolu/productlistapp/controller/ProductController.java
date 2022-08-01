package tr.edu.anadolu.productlistapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.anadolu.productlistapp.model.Product;
import tr.edu.anadolu.productlistapp.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/Product")
public class ProductController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/sortByNameAsc")
    public ResponseEntity<List<Product>> sortByNameAsc(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size) {
        logger.debug("Products are listed with ascending order by name properties.");
        return productService.sortByNameAsc(page, size);
    }

    @GetMapping("/sortByNameDesc")
    public ResponseEntity<List<Product>> sortByNameDesc(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size) {
        logger.debug("Products are listed with descending order by name properties.");
        return productService.sortByNameDesc(page, size);
    }

    @GetMapping("/sortByPriceAsc")
    public ResponseEntity<List<Product>> sortByPriceAsc(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size) {
        logger.debug("Products are listed with ascending order by price properties.");
        return productService.sortByPriceAsc(page, size);
    }

    @GetMapping("/sortByPriceDesc")
    public ResponseEntity<List<Product>> sortByPriceDesc(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size) {
        logger.debug("Products are listed with descending order by price properties.");
        return productService.sortByPriceDesc(page, size);
    }

    @GetMapping("/sortByType/{productType}")
    public ResponseEntity<List<Product>> sortByType(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size, @PathVariable String productType) {
        logger.debug("Products are listed which have this Type property: "+productType);
        return productService.sortByType(page, size, productType);
    }

    @GetMapping("/sortByCategory/{productCategory}")
    public ResponseEntity<List<Product>> sortByCategory(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size, @PathVariable String productCategory) {
        logger.debug("Products are listed which have this Category property: "+productCategory);
        return productService.sortByCategory(page, size, productCategory);
    }

    @GetMapping("/listProducts")
    public ResponseEntity<List<Product>> findAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        return productService.findAll(page, size);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return productService.createNewProduct(product);
    }

    @GetMapping("/{productId}")
    public Product getOneProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable String productId, @RequestBody Product newProduct) {
        return productService.updateProduct(productId, newProduct);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
    }

    // set true or false to availability of product
    @PutMapping("/{productId}/availability")
    public Product setAvailability(@PathVariable String productId) {
        return productService.setAvailability(productId);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @GetMapping("/listAvailableProducts")
    public ResponseEntity<List<Product>> findAvailableProducts(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        return productService.findAvailableProducts(page, size);
    }

    @GetMapping("/listNotAvailableProducts")
    public ResponseEntity<List<Product>> findNotAvailableProducts(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "5") int size) {
        return productService.findNotAvailableProducts(page, size);
    }

}
