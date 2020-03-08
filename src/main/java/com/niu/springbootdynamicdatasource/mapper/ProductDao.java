package com.niu.springbootdynamicdatasource.mapper;

import com.niu.springbootdynamicdatasource.model.Product;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Product mapper for operate data of products table
 *
 * @author HelloWood
 * @date 2017-07-11 10:54
 * @Email hellowoodes@gmail.com
 */
@Mapper
public interface ProductDao {

	Product select(@Param("id") long id);

	Integer update(Product product);

	Integer insert(Product product);

	Integer delete(long productId);

	List<Product> getAllProduct();
}
