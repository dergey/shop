package by.sergey.zhuravlev.shop.factory

import by.sergey.zhuravlev.shop.domain.Image
import by.sergey.zhuravlev.shop.domain.ProductImage
import by.sergey.zhuravlev.shop.model.ImageModel

object ImageModelFactory {

  fun buildImageModel(productImage: ProductImage): ImageModel {
    return buildImageModel(productImage.id.image)
  }

  fun buildImageModel(image: Image): ImageModel {
    return ImageModel(
      id = image.id!!,
      mimeType = image.mimeType,
      height = image.height,
      width = image.width,
      dataSize = image.dataSize,
      createAt = image.createAt,
    )
  }

}