package by.sergey.zhuravlev.shop.service

import by.sergey.zhuravlev.shop.exception.NotFoundException
import by.sergey.zhuravlev.shop.factory.AttributeModelFactory.buildAttributeModel
import by.sergey.zhuravlev.shop.factory.CatalogModelFactory.buildCatalogModel
import by.sergey.zhuravlev.shop.model.AttributeModel
import by.sergey.zhuravlev.shop.model.CatalogModel
import by.sergey.zhuravlev.shop.repository.CatalogAttributeRepository
import by.sergey.zhuravlev.shop.repository.CatalogRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CatalogService(
  private val catalogRepository: CatalogRepository,
  private val catalogAttributeRepository: CatalogAttributeRepository,
) {

  @Transactional(readOnly = true)
  fun getRootCatalog(): CatalogModel {
    return catalogRepository.findCatalogWithNullParent()
      ?.let { buildCatalogModel(it) }
      ?: throw NotFoundException()
  }

  @Transactional(readOnly = true)
  fun getCatalog(catalogId: Long): CatalogModel {
    return catalogRepository.findCatalogById(catalogId)
      ?.let { buildCatalogModel(it) }
      ?: throw NotFoundException()
  }

  @Transactional(readOnly = true)
  fun getCatalogAttributes(catalogId: Long): List<AttributeModel> {
    return catalogAttributeRepository.findAllByIdCatalogId(catalogId)
      .map { buildAttributeModel(it) }
  }

  @Transactional(readOnly = true)
  fun getCatalogChildren(catalogId: Long): List<CatalogModel> {
    return catalogRepository.findAllByParentId(catalogId)
      .map { buildCatalogModel(it) }
  }

}