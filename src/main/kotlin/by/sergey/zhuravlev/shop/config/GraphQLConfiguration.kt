package by.sergey.zhuravlev.shop.config

import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLScalarType
import graphql.schema.idl.RuntimeWiring
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer


@Configuration
class GraphQLConfiguration {

  @Bean
  fun runtimeWiringConfigurer(
    graphQlDateScalar: GraphQLScalarType,
    graphQlLocalDateScalar: GraphQLScalarType,
    graphQlLocalDateTimeScalar: GraphQLScalarType,
    graphQlLocalTimeScalar: GraphQLScalarType,
    graphQlOffsetDateTimeScalar: GraphQLScalarType,
    graphQlYearMonthScalar: GraphQLScalarType,
    graphQlDurationScalar: GraphQLScalarType
  ): RuntimeWiringConfigurer {
    return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
      wiringBuilder
        .scalar(ExtendedScalars.GraphQLLong)
        .scalar(ExtendedScalars.GraphQLBigDecimal)
        .scalar(ExtendedScalars.Object)
        .scalar(graphQlDateScalar)
        .scalar(graphQlLocalDateScalar)
        .scalar(graphQlLocalDateTimeScalar)
        .scalar(graphQlLocalTimeScalar)
        .scalar(graphQlOffsetDateTimeScalar)
        .scalar(graphQlYearMonthScalar)
        .scalar(graphQlDurationScalar)
    }
  }

}