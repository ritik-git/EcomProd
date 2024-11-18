package com.telusko.SpringEcom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.telusko.SpringEcom.model.Product;
import com.telusko.SpringEcom.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity <List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.ACCEPTED);
    }
    
   @GetMapping("/product/{id}")
public ResponseEntity<Product> getProductById(@PathVariable int id) {
    Optional<Product> product = productService.getProductById(id);
    if (product.isPresent()) {
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@PostMapping("/product")
public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
    
   Product saveProduct;
try {
    System.out.println("Received Product: " + product);
    System.out.println("Received File Name: " + imageFile.getOriginalFilename());
    System.out.println("Received File Size: " + imageFile.getSize());
    saveProduct = productService.addProduct(product,imageFile);
    return new ResponseEntity<>(saveProduct,HttpStatus.CREATED);
} catch (IOException e) {
   
    return new ResponseEntity<>(e.getMessage(),HttpStatus.CREATED);
}
   
}

    
    

}
