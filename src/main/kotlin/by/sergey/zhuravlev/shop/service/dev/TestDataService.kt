package by.sergey.zhuravlev.shop.service.dev

import by.sergey.zhuravlev.shop.domain.*
import by.sergey.zhuravlev.shop.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.unit.DataSize
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.annotation.PostConstruct

@Service
class TestDataService(
  private val internalService: InternalTestDataService
) {

  @Service
  class InternalTestDataService(
    private val catalogRepository: CatalogRepository,
    private val catalogAttributeRepository: CatalogAttributeRepository,
    private val catalogAttributeValueRepository: CatalogAttributeValueRepository,
    private val productRepository: ProductRepository,
    private val productImageRepository: ProductImageRepository,
    private val productAttributeValueRepository: ProductAttributeValueRepository,
    private val imageRepository: ImageRepository,

    ) {

    @Transactional
    fun loadTestData() {
      if (catalogRepository.findCatalogWithNullParent() != null) {
        return
      }

      // Loading catalogs:
      var parent = catalogRepository.save(Catalog(null, "Главная", null, emptyList()))
      parent = catalogRepository.save(Catalog(null, "Одежда", parent, emptyList()))
      parent = catalogRepository.save(Catalog(null, "Мужская одежда", parent, emptyList()))
      val catalog = catalogRepository.save(Catalog(null, "Мужские футболки", parent, emptyList()))

      // Loading catalogs attributes:
      val catalogAttributeSize = catalogAttributeRepository.save(
        CatalogAttribute(
          CatalogAttribute.CatalogAttributeId("size", catalog),
          "Размер",
          emptyList()
        )
      )
      val catalogAttributeColor = catalogAttributeRepository.save(
        CatalogAttribute(
          CatalogAttribute.CatalogAttributeId("color", catalog),
          "Цвет",
          emptyList()
        )
      )

      // Loading catalogs attributes
      val catalogAttributeSizeValues = catalogAttributeValueRepository.saveAll(
        listOf(
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeSize, "S")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeSize, "M")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeSize, "L")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeSize, "XL")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeSize, "XXL")
          )
        )
      )

      val catalogAttributeColorValues = catalogAttributeValueRepository.saveAll(
        listOf(
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "бежевый")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "белый")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "голубой")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "желтый")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "зеленый")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "коричневый")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "коричневый")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "красный")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "оранжевый")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "полоска")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "разноцветный")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "розовый")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "серый")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "синий")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "фиолетовый")
          ),
          CatalogAttributeValue(
            CatalogAttributeValue.CatalogAttributeValueId(catalogAttributeColor, "черный")
          )
        )
      )

      // Loading products
      var product = productRepository.save(
        Product(
          null,
          catalog,
          "Джемпер мужской",
          "Черный мужской джемпер отлично подойдет для самый требовательных мужчин," +
              " которые хорошо относятся к своему внешнему виду",
          BigDecimal("49.99"),
          "BYN",
          Availability.IN_STOCK,
          null,
          LocalDateTime.now(),
          LocalDateTime.now(),
          emptyList(),
          emptyList()
        )
      )
      productImageRepository.saveAll(
        listOf(
          ProductImage(
            ProductImage.ProductImageId(
              product,
              imageRepository.save(
                Image(
                  null,
                  "image/jpeg",
                  512,
                  512,
                  DataSize.ofKilobytes(6927L),
                  "5e2906f3-10b6-4fc9-864e-507ec8ec9431",
                  LocalDateTime.now()
                )
              )
            )
          ),
          ProductImage(
            ProductImage.ProductImageId(
              product,
              imageRepository.save(
                Image(
                  null,
                  "image/jpeg",
                  512,
                  1024,
                  DataSize.ofKilobytes(2684L),
                  "d50b14b6-b860-4d50-b042-1ba426580acf",
                  LocalDateTime.now()
                )
              )
            )
          ),
          ProductImage(
            ProductImage.ProductImageId(
              product,
              imageRepository.save(
                Image(
                  null,
                  "image/jpeg",
                  512,
                  1024,
                  DataSize.ofKilobytes(1687L),
                  "4e473bf8-8725-4d0d-b967-9ea89de979bb",
                  LocalDateTime.now()
                )
              )
            )
          )
        )
      )

      productAttributeValueRepository.saveAll(
        listOf(
          ProductAttributeValue(
            ProductAttributeValue.ProductAttributeValueId(
              product,
              catalogAttributeSizeValues.random()
            )
          ),
          ProductAttributeValue(
            ProductAttributeValue.ProductAttributeValueId(
              product,
              catalogAttributeColorValues.random()
            )
          )
        )
      )

      product = productRepository.save(
        Product(
          null,
          catalog,
          "Футболка мужская",
          "Черная мужская футболка для самых заряженных дедов, которые любят стильную одежду, и носочки в " +
              "горошек.",
          BigDecimal("22.99"),
          "BYN",
          Availability.IN_STOCK,
          null,
          LocalDateTime.now(),
          LocalDateTime.now(),
          emptyList(),
          emptyList()
        )
      )
      productImageRepository.saveAll(
        listOf(
          ProductImage(
            ProductImage.ProductImageId(
              product,
              imageRepository.save(
                Image(
                  null,
                  "image/jpeg",
                  512,
                  512,
                  DataSize.ofKilobytes(5828L),
                  "5e2906f3-10b6-4fc9-864e-507ec8ec9431",
                  LocalDateTime.now()
                )
              )
            )
          ),
          ProductImage(
            ProductImage.ProductImageId(
              product,
              imageRepository.save(
                Image(
                  null,
                  "image/jpeg",
                  512,
                  1024,
                  DataSize.ofKilobytes(4526L),
                  "d50b14b6-b860-4d50-b042-1ba426580acf",
                  LocalDateTime.now()
                )
              )
            )
          ),
          ProductImage(
            ProductImage.ProductImageId(
              product,
              imageRepository.save(
                Image(
                  null,
                  "image/jpeg",
                  512,
                  1024,
                  DataSize.ofKilobytes(4956L),
                  "4e473bf8-8725-4d0d-b967-9ea89de979bb",
                  LocalDateTime.now()
                )
              )
            )
          )
        )
      )

      productAttributeValueRepository.saveAll(
        listOf(
          ProductAttributeValue(
            ProductAttributeValue.ProductAttributeValueId(
              product,
              catalogAttributeSizeValues.random()
            )
          ),
          ProductAttributeValue(
            ProductAttributeValue.ProductAttributeValueId(
              product,
              catalogAttributeColorValues.random()
            )
          )
        )
      )
    }
  }

  @PostConstruct
  fun loadTestData() {
    internalService.loadTestData()
  }

}