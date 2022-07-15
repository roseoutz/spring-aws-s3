package io.zeed.s3.config

import io.zeed.s3.config.props.AmazonS3Properties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.http.async.SdkAsyncHttpClient
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.services.s3.S3Configuration
import java.net.URI
import java.time.Duration

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
class AmazonS3Config {

    @Bean
    fun awsCredentialsProvider(amazonS3Properties: AmazonS3Properties): AwsCredentialsProvider {
        return AwsCredentialsProvider {
            val credential: AwsCredentials = AwsBasicCredentials.create(amazonS3Properties.accessKey, amazonS3Properties.secretKey)
            credential
        }
    }


    @Bean
    fun s3AsyncClient(amazonS3Properties: AmazonS3Properties, awsCredentialsProvider: AwsCredentialsProvider): S3AsyncClient {

        val httpClient: SdkAsyncHttpClient = NettyNioAsyncHttpClient.builder()
            .writeTimeout(Duration.ZERO)
            .maxConcurrency(64)
            .build()

        val serviceConfig: S3Configuration = S3Configuration.builder()
            .checksumValidationEnabled(false)
            .chunkedEncodingEnabled(true)
            .build()

        return S3AsyncClient.builder()
            .httpClient(httpClient)
            .region(Region.of(amazonS3Properties.region))
            .credentialsProvider(awsCredentialsProvider)
            .serviceConfiguration(serviceConfig)
            .build()
    }


}