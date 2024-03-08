package com.cybersoft.uniclub.controller;

import com.cybersoft.uniclub.Service.Imp.FileServiceImp;
import com.cybersoft.uniclub.Service.Imp.ProductServiceImp;
import com.cybersoft.uniclub.payload.request.InsertProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;
    @PostMapping("")
    public ResponseEntity<?> insertProduct(InsertProductRequest productRequest){
        productServiceImp.insertProduct(productRequest);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
