package by.sergey.zhuravlev.shop.service

import by.sergey.zhuravlev.shop.domain.Image
import by.sergey.zhuravlev.shop.repository.ImageRepository
import by.sergey.zhuravlev.shop.service.storage.StorageService
import by.sergey.zhuravlev.shop.uilts.toByteArray
import by.sergey.zhuravlev.shop.uilts.toImageReader
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.util.unit.DataSize
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.time.LocalDateTime
import javax.imageio.ImageReader

@Service
class ImageService(
  private val imageRepository: ImageRepository,
  private val storageService: StorageService
) {

  fun fetchImageResource(image: Image): Resource {
    return storageService.loadAsResource(image.storageId)
  }

  fun createImage(multipartFile: MultipartFile): Image {
    val imageReader: ImageReader = multipartFile.inputStream.toImageReader()
    val bufferedImage: BufferedImage = imageReader.read(0)
    val mimeType = imageReader.originatingProvider.mimeTypes[0]
    val imageData: ByteArray = bufferedImage.toByteArray()
    return imageRepository.save(
      Image(
        null,
        mimeType,
        bufferedImage.height,
        bufferedImage.width,
        DataSize.ofBytes(imageData.size.toLong()),
        "",//storageService.store(imageData),
        LocalDateTime.now()
      )
    )
  }

}