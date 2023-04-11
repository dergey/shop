package by.sergey.zhuravlev.shop.config

import graphql.scalars.ExtendedScalars
import graphql.schema.idl.RuntimeWiring
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer


@Configuration
class GraphQLConfiguration {

  @Bean
  fun runtimeWiringConfigurer(): RuntimeWiringConfigurer {
    return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
      wiringBuilder
        .scalar(ExtendedScalars.GraphQLLong)
        .scalar(ExtendedScalars.GraphQLBigDecimal)
        .scalar(ExtendedScalars.Object)
        .scalar(ExtendedScalars.Date)
        .scalar(ExtendedScalars.DateTime)
    }
  }

}