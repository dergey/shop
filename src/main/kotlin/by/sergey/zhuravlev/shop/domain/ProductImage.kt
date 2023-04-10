package by.sergey.zhuravlev.shop.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "product_images")
class ProductImage(

  @EmbeddedId
  var id: ProductImageId

) {

  @Embeddable
  data class ProductImageId(

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product,

    @ManyToOne
    @JoinColumn(name = "image_id")
    var image: Image

  ) : Serializable

}
