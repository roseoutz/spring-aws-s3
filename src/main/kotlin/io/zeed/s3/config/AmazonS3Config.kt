package io.zeed.s3.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

/**
 *packageName    : io.zeed.s3.config
 * fileName       : AmazonS3Config
 * author         : kimdonggyuuuuu
 * date           : 2022/07/08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/08        kimdonggyuuuuu       최초 생성
 */
@Configuration
class AmazonS3Config(
    @Value("\${cloud.aws.credentials.access-key}")
    val accessKey: String,

    @Value("\${cloud.aws.credentials.secret-key}")
    val secretKey: String,

    @Value("\${cloud.aws.region.static}")
    val regionStatic: String
) {

}