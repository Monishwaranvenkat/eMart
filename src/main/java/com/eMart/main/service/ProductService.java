package com.eMart.main.service;

import com.eMart.main.entity.Category;
import com.eMart.main.entity.InvoiceSummary;
import com.eMart.main.entity.Product;
import com.eMart.main.entity.ProductDetails;
import com.eMart.main.repository.CategoryRepositry;
import com.eMart.main.repository.ProductRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    ProductRepositry productRepositry;

    public boolean addProduct()
    {
        return  true;
    }
    @Transactional
    public boolean verifyProducts(Set<InvoiceSummary> products) {
        for (InvoiceSummary item:products
             ) {
                Product product;
                if(productRepositry.isProductExist(item.getProductDescription()))
                {
                    product=productRepositry.findByDescription(item.getProductDescription()).get();
                    Boolean isBatchExist=false;
                    for (ProductDetails productDetails:product.getProductDetails()
                         ) {

                        if (productDetails.getExpiryDate().compareTo(item.getExpiryDate()) == 0) {
                            System.out.println(productDetails.getCount() + item.getCount());
                            productDetails.setCount(productDetails.getCount() + item.getCount());
                            isBatchExist = true;
                            break;
                        }
                    }
                        if(!isBatchExist)
                        {
                            ProductDetails newProductDetails=new ProductDetails();
                            newProductDetails.setCost(item.getCost());
                            newProductDetails.setCount(item.getCount());
                            newProductDetails.setCurrency(item.getCurrency());
                            newProductDetails.setSupplierCode(item.getVendorCode());
                            newProductDetails.setLocation("inventory");
                            newProductDetails.setExpiryDate(item.getExpiryDate());
                            newProductDetails.setLotNumber(123L);
                            newProductDetails.setProduct(product);
                            product.getProductDetails().add(newProductDetails);
                        }
                }
                else{
                    product = new Product();
                    product.setDescription(item.getProductDescription());
                    product.setCategory(getCategory(item.getProductCategory()));
                    Set<ProductDetails> productDetailsSet=new HashSet<>();
                    ProductDetails productDetails=new ProductDetails();
                    productDetails.setCost(item.getCost());
                    productDetails.setCount(item.getCount());
                    productDetails.setCurrency(item.getCurrency());
                    productDetails.setSupplierCode(item.getVendorCode());
                    productDetails.setLocation("inventory");
                    productDetails.setExpiryDate(item.getExpiryDate());
                    productDetails.setLotNumber(123L);
                    productDetails.setProduct(product);
                    productDetailsSet.add(productDetails);
                    product.setProductDetails(productDetailsSet);
                }
            productRepositry.save(product);
        }

        return true;
    }

    @Autowired
    CategoryRepositry categoryRepositry;
    public Category getCategory(String category) {
        try{

            return categoryRepositry.findByCategoryName(category).get();
        }catch(Exception e){

            Category newCategory=new Category(category);
            categoryRepositry.save(newCategory);
            return categoryRepositry.findByCategoryName(category).get();
        }
    }
}
