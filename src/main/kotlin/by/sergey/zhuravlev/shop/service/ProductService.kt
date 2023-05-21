package by.sergey.zhuravlev.shop.service

import by.sergey.zhuravlev.shop.domain.predicate.ProductPredicateBuilder
import by.sergey.zhuravlev.shop.exception.ShopServiceException
import by.sergey.zhuravlev.shop.factory.CatalogModelFactory.buildCatalogModel
import by.sergey.zhuravlev.shop.factory.ImageModelFactory.buildImageModel
import by.sergey.zhuravlev.shop.factory.ProductAttributeModelFactory.buildProductAttributeModel
import by.sergey.zhuravlev.shop.factory.ProductModelFactory.buildProductModel
import by.sergey.zhuravlev.shop.model.*
import by.sergey.zhuravlev.shop.repository.*
import by.sergey.zhuravlev.shop.uilts.component1
import by.sergey.zhuravlev.shop.uilts.component2
import by.sergey.zhuravlev.shop.uilts.withNullable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
  private val productRepository: ProductRepository,
  private val catalogRepository: CatalogRepository,
  private val discountRepository: DiscountRepository,
  private val productImageRepository: ProductImageRepository,
  private val productAttributeValueRepository: ProductAttributeValueRepository
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
      ?: throw ShopServiceException(ErrorCode.NOT_FOUND)
  }

  @Transactional(readOnly = true)
  fun getAllProducts(sort: ProductSortEntry?, limit: Long?, offset: Long?): List<ProductModel> {
    val targetOffset = offset ?: DEFAULT_OFFSET
    val targetLimit = limit ?: DEFAULT_LIMIT
    val targetSort = sort ?: DEFAULT_SORT
    return productRepository.findAll(
      PageRequest.of(
        (targetOffset / targetLimit).toInt(),
        targetLimit.toInt(),
        targetSort.order, targetSort.field.columnName
      )
    )
      .map { product ->
        buildProductModel(product, null)
      }
      .toList()
  }

  @Transactional(readOnly = true)
  fun getAllProducts(entries: List<ProductFilterEntry>?, sort: ProductSortEntry?, limit: Long?, offset: Long?): List<ProductModel> {
    val targetOffset = offset ?: DEFAULT_OFFSET
    val targetLimit = limit ?: DEFAULT_LIMIT
    val targetSort = sort ?: DEFAULT_SORT
    val targetEntries = entries ?: emptyList()
    return productRepository.findAll(
      ProductPredicateBuilder()
        .withEntries(targetEntries)
        .build(),
      PageRequest.of(
        (targetOffset / targetLimit).toInt(),
        targetLimit.toInt(),
        targetSort.order, targetSort.field.columnName
      )
    )
      .map { product ->
        buildProductModel(product, null)
      }
      .toList()
  }

  @Transactional(readOnly = true)
  fun getProductAttributes(productId: Long): List<ProductAttributeModel> {
    return productAttributeValueRepository.findAllByIdProductId(productId)
      .map { buildProductAttributeModel(it) }
  }

  @Transactional(readOnly = true)
  fun getProductCatalog(productId: Long): CatalogModel {
    return catalogRepository.findProductCatalogByProductId(productId)
      ?.let { buildCatalogModel(it) }
      ?: throw ShopServiceException(ErrorCode.NOT_FOUND)
  }

  @Transactional(readOnly = true)
  fun getProductImages(productId: Long): List<ImageModel> {
    return productImageRepository.findAllByIdProductId(productId)
      .map { buildImageModel(it) }
  }

  companion object {
    val DEFAULT_SORT = ProductSortEntry(ProductSortField.ID, Sort.Direction.ASC)
    const val DEFAULT_LIMIT = 20L
    const val DEFAULT_OFFSET = 0L
  }
}