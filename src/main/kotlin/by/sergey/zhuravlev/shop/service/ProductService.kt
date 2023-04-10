package by.sergey.zhuravlev.shop.service

import by.sergey.zhuravlev.shop.factory.CatalogModelFactory.buildCatalogModel
import by.sergey.zhuravlev.shop.factory.ImageModelFactory.buildImageModel
import by.sergey.zhuravlev.shop.factory.ProductAttributeModelFactory.buildProductAttributeModel
import by.sergey.zhuravlev.shop.factory.ProductModelFactory.buildProductModel
import by.sergey.zhuravlev.shop.model.CatalogModel
import by.sergey.zhuravlev.shop.model.ImageModel
import by.sergey.zhuravlev.shop.model.ProductAttributeModel
import by.sergey.zhuravlev.shop.model.ProductModel
import by.sergey.zhuravlev.shop.repository.*
import by.sergey.zhuravlev.shop.uilts.component1
import by.sergey.zhuravlev.shop.uilts.component2
import by.sergey.zhuravlev.shop.uilts.withNullable
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
  private val productRepository: ProductRepository,
  private val catalogRepository: CatalogRepository,
  private val discountRepository: DiscountRepository,
  private val productImageRepository: ProductImageRepository,
  private val productAttributeRepository: ProductAttributeRepository
) {

  @Transactional(readOnly = true)
  fun getProductById(productId: Long): ProductModel {
    return productRepository.findProductById(productId)
      ?.withNullable {
        discountRepository.findByProduct(it)
      }
      ?.let { (product, discount) ->
        buildProductModel(product, discount)
      }
      ?: throw NotFoundException()
  }

  @Transactional(readOnly = true)
  fun getProductAttributes(productId: Long): List<ProductAttributeModel> {
    return productAttributeRepository.findAllByIdProductId(productId)
      .map { buildProductAttributeModel(it) }
  }

  @Transactional(readOnly = true)
  fun getProductCatalog(productId: Long): CatalogModel {
    return catalogRepository.findProductCatalogByProductId(productId)
      ?.let { buildCatalogModel(it) }
      ?: throw NotFoundException()
  }

  @Transactional(readOnly = true)
  fun getProductImages(productId: Long): List<ImageModel> {
    return productImageRepository.findAllByIdProductId(productId)
      .map { buildImageModel(it) }
  }
}