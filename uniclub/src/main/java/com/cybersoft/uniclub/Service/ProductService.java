package com.cybersoft.uniclub.Service;

import com.cybersoft.uniclub.Service.Imp.FileServiceImp;
import com.cybersoft.uniclub.Service.Imp.ProductServiceImp;
import com.cybersoft.uniclub.entity.ProductDetailEntity;
import com.cybersoft.uniclub.entity.ProductEntity;
import com.cybersoft.uniclub.entity.key.ProductDetailId;
import com.cybersoft.uniclub.exception.InsertException;
import com.cybersoft.uniclub.payload.request.InsertProductRequest;
import com.cybersoft.uniclub.repository.ProductDetailRepository;
import com.cybersoft.uniclub.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Transactional
    @Override
    public boolean insertProduct(InsertProductRequest productRequest) {
        boolean isSuccess = false;
        fileServiceImp.saveFile(productRequest.getFile());
        try {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductName(productRequest.getProductName());
            productEntity.setPrice(productRequest.getPrice());
            productEntity.setImage(productRequest.getFile().getOriginalFilename());

             productRepository.save(productEntity);

            //Thêm tiếp dữ liệu vào bảng product detail
            ProductDetailEntity productDetailEntity = new ProductDetailEntity();
            ProductDetailId productDetailId = new ProductDetailId();
            productDetailId.setIdProduct(productEntity.getIdProduct());
            productDetailId.setIdTag(productRequest.getIdTag());
            productDetailId.setIdCategory(productRequest.getIdCategory());
            productDetailId.setIdColor(productRequest.getIdColor());
            productDetailId.setIdSize(productRequest.getIdSize());

            productDetailEntity.setId(productDetailId);

            productDetailRepository.save(productDetailEntity);

            isSuccess = true;
        }catch(Exception e){
            throw new InsertException("Lỗi " + e.getMessage());

            //  @Transactional chỉ roll back khi bắt ở tầng RuntimeException
        }
        return isSuccess;
    }
}
