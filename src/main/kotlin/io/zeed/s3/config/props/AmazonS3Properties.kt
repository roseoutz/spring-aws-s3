package io.zeed.s3.config.props

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 *packageName    : io.zeed.s3.config.props
 * fileName       : AmazonS3Properties
 * author         : kimdonggyuuuuu
 * date           : 2022/07/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/15        kimdonggyuuuuu       최초 생성
 */

@ConstructorBinding
@ConfigurationProperties(prefix = "amazon.s3")
data class AmazonS3Properties(
    val bucket: String,
    val region: String,
    val accessKey: String,
    val secretKey: String
) {
}