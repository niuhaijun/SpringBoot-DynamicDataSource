package com.niu.springbootdynamicdatasource.service;

import com.niu.springbootdynamicdatasource.error.ServiceException;
import com.niu.springbootdynamicdatasource.mapper.ProductDao;
import com.niu.springbootdynamicdatasource.model.Product;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Product service for handler logic of product operation
 *
 * @author HelloWood
 * @date 2017-07-11 11:58
 * @Email hellowoodes@gmail.com
 */
@Service
public class ProductService {

	@Resource
	private ProductDao productDao;

	/**
	 * Get product by id
	 * If not found product will throw ServiceException
	 */
	public Product select(long productId) throws ServiceException {

		Product product = productDao.select(productId);
		if (product == null) {
			throw new ServiceException("Product:" + productId + " not found");
		}
		return product;
	}

	/**
	 * Update product by id
	 * If update failed will throw ServiceException
	 */
	@Transactional(rollbackFor = DataAccessException.class)
	public Product update(long productId, Product newProduct) throws ServiceException {

		if (productDao.update(newProduct) <= 0) {
			throw new ServiceException("Update product:" + productId + "failed");
		}
		return newProduct;
	}

	/**
	 * Add product to DB
	 */
	@Transactional(rollbackFor = DataAccessException.class)
	public boolean add(Product newProduct) throws ServiceException {

		Integer num = productDao.insert(newProduct);
		if (num <= 0) {
			throw new ServiceException("Add product failed");
		}
		return true;
	}

	/**
	 * Delete product from DB
	 */
	@Transactional(rollbackFor = DataAccessException.class)
	public boolean delete(long productId) throws ServiceException {

		Integer num = productDao.delete(productId);
		if (num <= 0) {
			throw new ServiceException("Delete product:" + productId + "failed");
		}
		return true;
	}

	/**
	 * Get all product
	 *
	 * @return
	 */
	public List<Product> getAllProduct() {

		return productDao.getAllProduct();
	}
}
