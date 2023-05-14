package by.sergey.zhuravlev.shop.domain.predicate

import by.sergey.zhuravlev.shop.domain.Availability
import by.sergey.zhuravlev.shop.domain.QProduct
import by.sergey.zhuravlev.shop.model.ProductFilterEntry
import by.sergey.zhuravlev.shop.model.ProductFilterType
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import java.math.BigDecimal

class ProductPredicateBuilder {

  private val mapper: ObjectMapper = ObjectMapper()

  private var idExpression: BooleanExpression? = null
  private var queryExpression: BooleanExpression? = null
  private var priceExpression: BooleanExpression? = null
  private var availabilityExpression: BooleanExpression? = null
  private var attributeExpression: BooleanExpression? = null

  fun withId(id: Long): ProductPredicateBuilder {
    idExpression = product.id.eq(id)
    return this
  }

  fun withIds(ids: List<Long>): ProductPredicateBuilder {
    idExpression = product.id.`in`(ids)
    return this
  }

  fun withCatalog(id: Long): ProductPredicateBuilder {
    idExpression = product.catalog.id.eq(id)
    return this
  }

  fun withCatalogs(ids: List<Long>): ProductPredicateBuilder {
    idExpression = product.catalog.id.`in`(ids)
    return this
  }

  fun withQuery(query: String): ProductPredicateBuilder {
    queryExpression = product.title.containsIgnoreCase(query)
    return this
  }

  fun withPriceFrom(price: BigDecimal): ProductPredicateBuilder {
    TODO("Not yet implemented")
  }

  fun withPriceTo(price: BigDecimal): ProductPredicateBuilder {
    TODO("Not yet implemented")
  }

  fun withAvailability(availability: Availability): ProductPredicateBuilder {
    availabilityExpression = product.availability.eq(availability)
    return this
  }

  private fun withAvailabilities(availabilities: List<Availability>): ProductPredicateBuilder {
    if (availabilities.isNotEmpty()) {
      availabilityExpression = product.availability.`in`(availabilities)
    }
    return this
  }

  fun withAttribute(attributeCode: String, value: List<String>): ProductPredicateBuilder {
    if (value.isNotEmpty()) {
      attributeExpression = product.attributeValues.any().id.catalogAttributeValue.id.attribute.id.code.eq(attributeCode)
        .and(product.attributeValues.any().id.catalogAttributeValue.id.value.`in`(value))
    }
    return this
  }

  fun withAttributes(attributesMap: Map<String, List<String>>): ProductPredicateBuilder {
    TODO("Not yet implemented")
  }

  fun withEntry(entry: ProductFilterEntry): ProductPredicateBuilder {
    when (entry.type) {
      ProductFilterType.ID -> entry.also { filter ->
        if (filter.value != null) {
          withId(filter.value.toLong())
        }
        if (!filter.values.isNullOrEmpty()) {
          withIds(filter.values.map { it.toLong() })
        }
      }

      ProductFilterType.CATALOG -> entry.also { filter ->
        if (filter.value != null) {
          withCatalog(filter.value.toLong())
        }
        if (!filter.values.isNullOrEmpty()) {
          withCatalogs(filter.values.map { it.toLong() })
        }
      }

      ProductFilterType.QUERY -> entry.also { filter ->
        if (filter.value != null) {
          withQuery(filter.value)
        }
        if (!filter.values.isNullOrEmpty()) {
          throw IllegalArgumentException("Multiple values not supported")
        }
      }

      ProductFilterType.PRICE_FROM -> entry.also { filter ->
        if (filter.value != null) {
          withPriceFrom(BigDecimal(filter.value))
        }
        if (!filter.values.isNullOrEmpty()) {
          throw IllegalArgumentException("Multiple values not supported")
        }
      }

      ProductFilterType.PRICE_TO -> entry.also { filter ->
        if (filter.value != null) {
          withPriceTo(BigDecimal(filter.value))
        }
        if (!filter.values.isNullOrEmpty()) {
          throw IllegalArgumentException("Multiple values not supported")
        }
      }
      ProductFilterType.AVAILABILITY -> entry.also { filter ->
        if (filter.value != null) {
          withAvailability(Availability.valueOf(filter.value))
        }
        if (!filter.values.isNullOrEmpty()) {
          withAvailabilities(filter.values.map { Availability.valueOf(it) })
        }
      }
      ProductFilterType.ATTRIBUTES -> entry.also { filter ->
        if (filter.value != null) {
          withAttributes(mapper.readValue(filter.value, object : TypeReference<Map<String, List<String>>>() {}))
        }
        if (!filter.values.isNullOrEmpty()) {
          throw IllegalArgumentException("Use Map for value field to select a multiple values")
        }
      }
    }
    return this
  }

  fun withEntries(entries: List<ProductFilterEntry>): ProductPredicateBuilder {
    for (entry in entries) {
      withEntry(entry)
    }
    return this
  }

  fun build(): Predicate {
    return ExpressionUtils.allOf(
      idExpression, queryExpression, priceExpression, availabilityExpression, queryExpression
    ) ?: Expressions.TRUE.isTrue
  }

  companion object {
    private val product: QProduct = QProduct("product")
  }
}