package com.pearsystem.service;


import com.pearsystem.model.Category;
import com.pearsystem.payload.CategoryDto;
import com.pearsystem.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDto create(CategoryDto dto){ //todo Conversation CategoryDto to Category

       Category cat= this.modelMapper.map(dto, Category.class);
       Category save= this.categoryRepository.save(cat);
       //todo Category to CategoryDto;
        return this.modelMapper.map(save, CategoryDto.class);
    }
    public CategoryDto update(CategoryDto new_cate,int category_id){
       Category old_cat= this.categoryRepository.findById(category_id).orElseThrow(()-> new RuntimeException("Category not found"));

       old_cat.setTitle(new_cate.getTitle()) ;
     Category save=  this.categoryRepository.save(old_cat);
       return this.modelMapper.map(save, CategoryDto.class);
    }
    public void delete( int category_id){
        Category cat= this.categoryRepository.findById(category_id).orElseThrow(()-> new RuntimeException("Category not found"));
         this.categoryRepository.delete(cat);

    }
    public CategoryDto getById(int category_id){
        Category get_by_id= this.categoryRepository.findById(category_id).orElseThrow(()-> new RuntimeException("Category not found"));

        return this.modelMapper.map(get_by_id, CategoryDto.class);
    }
    public List<CategoryDto> getAll(){
        List<Category> findAll= this.categoryRepository.findAll();
        List<CategoryDto> allDto=  findAll.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());

        return allDto;
    }
}
