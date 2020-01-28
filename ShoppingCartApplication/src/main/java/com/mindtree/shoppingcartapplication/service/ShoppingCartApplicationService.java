package com.mindtree.shoppingcartapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingcartapplication.dto.AddProductDTO;
import com.mindtree.shoppingcartapplication.dto.CartDisplayDTO;
import com.mindtree.shoppingcartapplication.entities.Apparel;
import com.mindtree.shoppingcartapplication.entities.Book;
import com.mindtree.shoppingcartapplication.entities.Cart;
import com.mindtree.shoppingcartapplication.entities.CartItems;
import com.mindtree.shoppingcartapplication.entities.Product;
import com.mindtree.shoppingcartapplication.entities.UserInfo;
import com.mindtree.shoppingcartapplication.exception.EmptyCartException;
import com.mindtree.shoppingcartapplication.exception.NoProductsAvailableException;
import com.mindtree.shoppingcartapplication.exception.NoSuchCategoryException;
import com.mindtree.shoppingcartapplication.exception.NoSuchProductException;
import com.mindtree.shoppingcartapplication.exception.NoSuchUserException;
import com.mindtree.shoppingcartapplication.exception.ProductNotInCartException;
import com.mindtree.shoppingcartapplication.exception.ShoppingCartApplicationException;
import com.mindtree.shoppingcartapplication.repository.ApparelRepo;
import com.mindtree.shoppingcartapplication.repository.BookRepo;
import com.mindtree.shoppingcartapplication.repository.CartItemRepo;
import com.mindtree.shoppingcartapplication.repository.CartRepo;
import com.mindtree.shoppingcartapplication.repository.ProductRepo;
import com.mindtree.shoppingcartapplication.repository.UserInfoRepo;

@Service
public class ShoppingCartApplicationService {

	@Autowired
	ProductRepo productRepo;

	@Autowired
	BookRepo bookrepo;

	@Autowired
	ApparelRepo apparelRepo;

	@Autowired
	CartItemRepo cartItemRepo;

	@Autowired
	UserInfoRepo userInfoRepo;

	@Autowired
	CartRepo cartRepo;

	public void insertBookValues() {

		Book book = new Book(1, "HP", 242, "fiction", "ABC HYJ", "oxford");
		bookrepo.save(book);

	}

	public void insertApparelValues() {

		Apparel apparel = new Apparel(2, "Indian-Wear", 2422, "Kurta-palazzo", "ABC HYJ", "2-piece");
		apparelRepo.save(apparel);

	}

	public void insertUser() {

		UserInfo user = new UserInfo();
		user.setUserId(1);
		user.setUserName("ABCC");
		userInfoRepo.save(user);
	}

	public List<Product> getAllProducts() throws NoProductsAvailableException {
		List<Product> allProds = (List<Product>) productRepo.findAll();

		if((allProds != null)||(!allProds.isEmpty())) {
			return allProds;
		}
		else {
			throw new NoProductsAvailableException("Error: No products are available to display.");
		}
	}

	public List<Product> getProductsByname(final String productName) throws NoSuchProductException {

		List<Product> productList = new ArrayList<>();
		productList = productRepo.fetchByName(productName);
		if((productList != null)&&(!productList.isEmpty())) {
			return productList;
		}
		else {
			throw new NoSuchProductException("Error: No such Product with this name Found");
		}
		
	}

	@SuppressWarnings("rawtypes")
	public List getProductsByCategory(final String productCategory) throws NoSuchCategoryException {
		if (productCategory.equalsIgnoreCase("Book") || (productCategory.equalsIgnoreCase("Books"))) {
			List<Book> bookList = (List<Book>) bookrepo.findAll();
			return bookList;
		} else if (productCategory.equalsIgnoreCase("Apparel") || (productCategory.equalsIgnoreCase("Apparels"))) {
			List<Apparel> apparelList = (List<Apparel>) apparelRepo.findAll();
			return apparelList;
		}
		else {
			throw new NoSuchCategoryException("Error: No such Category exists");
		}
	}

	public Product getProductById(int id) throws NoSuchProductException {

		Product product = productRepo.findById(id).get();
		if (product == null) {
			throw new NoSuchProductException("Error: No Product was found to display. Try later.");
		}
		else
			return product;
	}

	public void addProductToCart(AddProductDTO addProdDto) throws NoSuchProductException, NoSuchUserException {

		Product product = productRepo.findById(addProdDto.getProdId()).get();
		UserInfo userInfo = userInfoRepo.findById(addProdDto.getUserId()).get();
	
		if((product != null)&&(userInfo != null)) {
			Cart cart = new Cart();
			if (userInfo.getCart() != null) {
				cart = userInfo.getCart();
			}
			CartItems cartItem = new CartItems();
			if ((product.getCart() != null) && (product.getCart().getCartId() != null)) {
				List<CartItems> cartItemList = cartItemRepo.fetchbyProdId(product);
				if ((cartItemList != null) && (!cartItemList.isEmpty())) {
					cartItem = cartItemList.get(0);
				}
			}
			if (cart.getCartTotal() != 0.0) {
				cart.setCartTotal(cart.getCartTotal() + product.getProdPrice());
			} else {
				cart.setCartTotal(product.getProdPrice());
			}
			userInfo.setCart(cart);
			product.setCart(cart);
			cartItem.setProduct(product);
			if (cartItem.getProductQty() == null) {
				cartItem.setProductQty(1);
			} else {
				cartItem.setProductQty(cartItem.getProductQty() + 1);
			}
			cartItem.setCart(cart);
			cartItemRepo.save(cartItem);
		} else if (product == null) {
			throw new NoSuchProductException("Eror: No such Product was found");
		} else if (userInfo == null) {
			throw new NoSuchUserException("Error: No such user exists.");
		}
	}

	public void removeAllProductsFromCart(Integer userId) throws EmptyCartException, NoSuchUserException {

		UserInfo userInfo = userInfoRepo.findById(userId).get();
		if(userInfo != null) {
			Cart cart = userInfo.getCart();
			List<CartItems> cartItemList = cart.getCartItemList();
			if((cartItemList != null)||(!cartItemList.isEmpty())){
				for (CartItems cartItems : cartItemList) {
					Product product = cartItems.getProduct();
					product.setCart(null);
					productRepo.save(product);
				}
				cartItemRepo.deleteAll(cartItemList);
				cartItemList = cartItemRepo.fetchbyCartId(cart);
				cart.setCartItemList(null);
				cart.setCartTotal(0.0);
				cartRepo.save(cart);
			}
			else {
				throw new EmptyCartException("No products exist in Cart");
			}
		}
		else {
			throw new NoSuchUserException("Error: No such user exists.");
		}
	}

	public void removeProductInCart(AddProductDTO addProdDto) throws NoSuchProductException, ProductNotInCartException {
			Product product = productRepo.findById(addProdDto.getProdId()).get();

			if ((product != null) && (product.getCart() != null)) {
				Cart cart = product.getCart();
				List<CartItems> cartItemsList = cart.getCartItemList();

				for (CartItems cartItems : cartItemsList) {
					if ((cartItems.getProduct() != null)
							&& (cartItems.getProduct().getProdId() == addProdDto.getProdId())) {
						if (cartItems.getProductQty() > 1) {
							cartItems.setProductQty(cartItems.getProductQty() - 1);
							cartItems.getCart()
									.setCartTotal(cartItems.getCart().getCartTotal() - product.getProdPrice());
							cartItemRepo.save(cartItems);
						} else {
							cartItems.getCart()
									.setCartTotal(cartItems.getCart().getCartTotal() - product.getProdPrice());
							product.setCart(null);
							productRepo.save(product);
							cartRepo.delete(cartItems.getCart());
							cartItemRepo.delete(cartItems);
						}

					}

				}
			} else if (product == null) {
				throw new NoSuchProductException("Eror: No such Product was found");
			} else if (product.getCart() == null) {
				throw new ProductNotInCartException("Error:No such Product exists in cart");
			}
			
	}
	
	public CartDisplayDTO displayCart(Integer userId) throws NoSuchUserException, EmptyCartException {
		UserInfo userInfo = userInfoRepo.findById(userId).get();
		if((userInfo != null)&&(userInfo.getCart() != null)){
			CartDisplayDTO cartDisplayDTO = new CartDisplayDTO();
			List<CartItems> cartItemsList = userInfo.getCart().getCartItemList();
			cartDisplayDTO.setProductList(cartItemsList.stream().map(e -> e.getProduct()).collect(Collectors.toList()));
			cartDisplayDTO.setCartTotal(userInfo.getCart().getCartTotal());
			return cartDisplayDTO;
		}else if(userInfo == null) {
			throw new NoSuchUserException("Error: No user found.");
		} else {
			throw new EmptyCartException("Cart for said User is Empty. Please add items before viewing");
		}
	}
}
