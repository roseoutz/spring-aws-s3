package io.zeed.s3.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

/**
 *packageName    : io.zeed.s3.config
 * fileName       : DataConfig
 * author         : kimdonggyuuuuu
 * date           : 2022/07/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/13        kimdonggyuuuuu       최초 생성
 */
@Configuration
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories
class DataConfig(
    val mongoMappingContext: MongoMappingContext
) {

    @Bean
    fun reactiveMappingMongoConverter() : MappingMongoConverter {
        val converter = MappingMongoConverter(ReactiveMongoTemplate.NO_OP_REF_RESOLVER, mongoMappingContext)

        converter.setTypeMapper(DefaultMongoTypeMapper(null))

        return converter
    }
}