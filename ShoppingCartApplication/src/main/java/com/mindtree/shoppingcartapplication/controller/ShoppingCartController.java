package com.mindtree.shoppingcartapplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcartapplication.dto.AddProductDTO;
import com.mindtree.shoppingcartapplication.dto.ResponseDTO;
import com.mindtree.shoppingcartapplication.entities.Product;
import com.mindtree.shoppingcartapplication.exception.EmptyCartException;
import com.mindtree.shoppingcartapplication.exception.NoProductsAvailableException;
import com.mindtree.shoppingcartapplication.exception.NoSuchCategoryException;
import com.mindtree.shoppingcartapplication.exception.NoSuchProductException;
import com.mindtree.shoppingcartapplication.exception.NoSuchUserException;
import com.mindtree.shoppingcartapplication.exception.ProductNotInCartException;
import com.mindtree.shoppingcartapplication.service.ShoppingCartApplicationService;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	ShoppingCartApplicationService service;
	
	ResponseDTO responseDTO;

	@GetMapping(value = "/getProducts",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getAllValues() {
		service.insertBookValues();
		service.insertApparelValues();
		service.insertUser();
		try {
			responseDTO = new ResponseDTO(); 
			responseDTO.setProductList(service.getAllProducts());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (NoProductsAvailableException e) {
			responseDTO = new ResponseDTO(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getProductByName/{productName}")
	public ResponseEntity<ResponseDTO> getProductsByName(@PathVariable final String productName) {

		try {
			responseDTO = new ResponseDTO(service.getProductsByname(productName));
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (NoSuchProductException e) {
			responseDTO = new ResponseDTO(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		}
	}

	@GetMapping("/getProductsById/{id}")
	public ResponseEntity<ResponseDTO> getProductsById(@PathVariable final int id) {

		try {
			List<Product> productList = new ArrayList<>();
			productList.add(service.getProductById(id));
			responseDTO = new ResponseDTO(productList);
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (NoSuchProductException e) {
			responseDTO = new ResponseDTO(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		}

	}

	@GetMapping("/getProductsByCategory/{category}")
	public ResponseEntity<ResponseDTO> getProductsByCategory(@PathVariable final String category) {

		try {
			responseDTO = new ResponseDTO(service.getProductsByCategory(category));
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (NoSuchCategoryException e) {
			responseDTO = new ResponseDTO(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		}

	}

	@PostMapping("/addProductToCart")
	public ResponseEntity<ResponseDTO> addProductToCart(@RequestBody AddProductDTO addProdDTO) {
		try {
			service.addProductToCart(addProdDTO);
			responseDTO = new ResponseDTO("Products added to cart Successfully");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (NoSuchProductException | NoSuchUserException e) {
			responseDTO = new ResponseDTO(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		}

	}

	@GetMapping("/removeAllProductsFromCart/{userId}")
	public ResponseEntity<ResponseDTO> removeAllProductsFromCart(@PathVariable final Integer userId) {
		try {
			service.removeAllProductsFromCart(userId);
			responseDTO = new ResponseDTO("Products Removed Successfully");
			return new ResponseEntity<ResponseDTO>(HttpStatus.OK);
		} catch (EmptyCartException | NoSuchUserException e) {
			responseDTO = new ResponseDTO(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		}
	}

	@PostMapping("/removeProductInCart")
	public ResponseEntity<ResponseDTO> removeProductInCart(@RequestBody AddProductDTO addProdDTO) {
		try {
			service.removeProductInCart(addProdDTO);
			responseDTO = new ResponseDTO("Product Removed Successfully");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (NoSuchProductException e) {
			responseDTO = new ResponseDTO(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (ProductNotInCartException e) {
			responseDTO = new ResponseDTO(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		}
	}
	
	@GetMapping("displayCart/{userId}")
	public ResponseEntity<ResponseDTO> displayCart(@PathVariable final Integer userId) {
		try {
			responseDTO = new ResponseDTO(service.displayCart(userId));
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (NoSuchUserException | EmptyCartException e) {
			responseDTO = new ResponseDTO(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		}
	}
}
