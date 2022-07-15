package io.zeed.s3.config

import io.findify.s3mock.S3Mock
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener

/**
 *packageName    : io.zeed.s3.config
 * fileName       : S3MockConfig
 * author         : kimdonggyuuuuu
 * date           : 2022/07/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/15        kimdonggyuuuuu       최초 생성
 */
@Profile("test")
@Configuration
class S3MockConfig {

    lateinit var s3Mock: S3Mock

    @EventListener(ContextRefreshedEvent::class)
    fun start() {
        s3Mock = S3Mock.Builder()
            .withPort(12345)
            .withInMemoryBackend()
            .build()

        s3Mock.start()
    }

    @EventListener(ContextClosedEvent::class)
    fun stop() {
        s3Mock.stop()
    }
}