package com.pearsystem.service;

import com.pearsystem.exception.ResourceNotFoundException;
import com.pearsystem.model.Category;
import com.pearsystem.model.Product;
import com.pearsystem.payload.CategoryDto;
import com.pearsystem.payload.ProductDto;
import com.pearsystem.payload.ProductResponse;
import com.pearsystem.repository.CategoryRepository;
import com.pearsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDto createProduct(ProductDto productDto,int catid){
        //Fetch category is available or not
        Category cat= this.categoryRepository.findById(catid).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        // todo  -ProductDto to Product
       Product product= toEntity(productDto);
       product.setCategory(cat);
       Product save=this.productRepository.save(product);

//      Product saved=  productRepository.save(entity);
      // todo  --Product to ProductDto
        ProductDto dto=toDto(save);
        return dto;
    }

    public ProductResponse getAllProducts(int pageNumber, int pageSize, String sortBy, String shortDir){

        Sort sort=null;

        if(shortDir.trim().toLowerCase().equals("asc")){
          sort=Sort.by(sortBy).ascending();
            System.out.println(sort);
        }else {
            sort=Sort.by(sortBy).descending();
            System.out.println(sort);

        }
      Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page= this.productRepository.findAll(pageable);
        List<Product> pageProduct= page.getContent();

        List<ProductDto> productDto= pageProduct.stream().map(p->this.toDto(p)).collect(Collectors.toList());

        ProductResponse response=new ProductResponse();
        response.setContent(productDto);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        //TODO ProductDto to Product
       // List<Product> findAll=productRepository.findAll();
        //todo  Product to ProductDto
      // List<ProductDto> findAllDto= findAll.stream().map(product -> this.toDto(product)).collect(Collectors.toList());
       return response;

    }
    public ProductDto getProductById(int pid){
        Product viewById= productRepository.findById(pid).orElseThrow(() -> new RuntimeException(pid+"   Product not found   "));
       ProductDto dto =this.toDto(viewById);
        return dto;

    }
    public void deleteProduct(int pid){
        Product byId=productRepository.findById(pid).orElseThrow(() -> new RuntimeException(pid+"   Product not found   "));
        productRepository.delete(byId);

    }
    public ProductDto updateProduct(int pid,ProductDto newProduct){
        Product oldProduct= productRepository.findById(pid).orElseThrow(()-> new RuntimeException(pid+"  Product not found  "));

        oldProduct.setProduct_name(newProduct.getProduct_name());
        oldProduct.setProduct_description(newProduct.getProduct_description());
        oldProduct.setProduct_price(newProduct.getProduct_price());
        oldProduct.setProduct_quantity(newProduct.getProduct_quantity());
        oldProduct.setStock(newProduct.isStock());
        oldProduct.setLive(newProduct.isLive());
        oldProduct.setProduct_imageName(newProduct.getProduct_imageName());
       Product save= productRepository.save(oldProduct);
       ProductDto dto=toDto(save);
        return dto;
        




    }
    // todo  ProductDto to Product
    public Product toEntity(ProductDto productDto){
        Product p= new Product();
        p.setProduct_id(productDto.getProduct_id());
        p.setProduct_name(productDto.getProduct_name());
        p.setProduct_description(productDto.getProduct_description());
        p.setProduct_price(productDto.getProduct_price());
        p.setProduct_quantity(productDto.getProduct_quantity());
        p.setProduct_imageName(productDto.getProduct_imageName());
        p.setStock(productDto.isStock());
        p.setLive(productDto.isLive());

        return p;
    }
    //todo  -- Product to ProductDto
    public ProductDto toDto(Product product){
        ProductDto pDto= new ProductDto();
        pDto.setProduct_id(product.getProduct_id());
        pDto.setProduct_name(product.getProduct_name());
        pDto.setProduct_description(product.getProduct_description());
        pDto.setProduct_price(product.getProduct_price());
        pDto.setProduct_quantity(product.getProduct_quantity());
        pDto.setProduct_imageName(product.getProduct_imageName());
        pDto.setLive(product.isLive());
        pDto.setStock(product.isStock());

        //Change Category to CategoryDto
        CategoryDto catDto= new CategoryDto();
        catDto.setCategoryId(product.getCategory().getCategoryId());
        catDto.setTitle(product.getCategory().getTitle());

        // Then Set category Dto in Product Dto
        pDto.setCategory(catDto);
        return pDto;




    }












}
