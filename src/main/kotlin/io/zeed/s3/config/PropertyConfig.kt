package io.zeed.s3.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 *packageName    : io.zeed.s3.config
 * fileName       : PropertyConfig
 * author         : kimdonggyuuuuu
 * date           : 2022/07/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/15        kimdonggyuuuuu       최초 생성
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan
class PropertyConfig {
}