package cn.com.hellowood.dynamicdatasource.controller;

import cn.com.hellowood.dynamicdatasource.apiutil.annotation.ApiResponseBody;
import cn.com.hellowood.dynamicdatasource.error.ServiceException;
import cn.com.hellowood.dynamicdatasource.model.Product;
import cn.com.hellowood.dynamicdatasource.service.ProductService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Product controller
 *
 * @author HelloWood
 * @date 2017-07-11 11:38
 * @Email hellowoodes@gmail.com
 */

@RestController
@RequestMapping("/product")
public class ProductController {

	@Resource
	private ProductService productService;

	/**
	 * Get product by id
	 */
	@GetMapping("/{id}")
	@ApiResponseBody
	public Product getProduct(@PathVariable("id") Long productId) throws ServiceException {

		return productService.select(productId);
	}

	/**
	 * Get all product
	 */
	@GetMapping("getAllProduct")
	@ApiResponseBody
	public List<Product> getAllProduct() {

		return productService.getAllProduct();
	}

	/**
	 * Update product by id
	 *
	 * @param productId
	 * @param newProduct
	 * @return
	 * @throws ServiceException
	 */

	@PutMapping("/{id}")
	@ApiResponseBody
	public Product updateProduct(@PathVariable("id") Long productId,
		@RequestBody Product newProduct) throws ServiceException {

		return productService.update(productId, newProduct);
	}

	/**
	 * Delete product by id
	 *
	 * @param productId
	 * @return
	 * @throws ServiceException
	 */
	@DeleteMapping("/{id}")
	@ApiResponseBody
	public boolean deleteProduct(@PathVariable("id") long productId) throws ServiceException {

		return productService.delete(productId);
	}

	/**
	 * Save product
	 */
	@PostMapping
	@ApiResponseBody
	public boolean addProduct(@RequestBody Product newProduct) throws ServiceException {

		return productService.add(newProduct);
	}
}
