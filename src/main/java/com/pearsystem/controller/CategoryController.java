package com.pearsystem.controller;

import com.pearsystem.payload.ApiResponse;
import com.pearsystem.payload.CategoryDto;
import com.pearsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
//todo CREATE CATEGORY
    @PostMapping("/create")
    public ResponseEntity<CategoryDto>  create(@RequestBody CategoryDto catDto){
        CategoryDto create=this.categoryService.create(catDto);
        return new ResponseEntity<>(create, HttpStatus.ACCEPTED);
    }

    //TODO  UPDATE CATEGORY
    @PostMapping("/update/{id}")
    public  ResponseEntity<CategoryDto> update(@RequestBody CategoryDto catDto,@PathVariable int id){
        System.out.println(id);
       CategoryDto update= this.categoryService.update(catDto,id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
    //todo DELETE CATEGORY
    @DeleteMapping("/delete/{catId}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable int catId){
        this.categoryService.delete(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully ",true), HttpStatus.OK);
    }
    //todo  get Category_by_id
    @GetMapping("/get_by_id/{catId}")
    public ResponseEntity<CategoryDto> getById(@PathVariable int catId){
        CategoryDto getById=this.categoryService.getById(catId);
        return new ResponseEntity<>(getById, HttpStatus.OK);
    }

    // todo get all category
    @GetMapping("/get_all")
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> getAll=this.categoryService.getAll();
        return  new ResponseEntity<>(getAll, HttpStatus.OK);

    }
























}
