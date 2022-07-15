package io.zeed.test

import com.amazonaws.services.s3.model.ObjectMetadata
import io.findify.s3mock.S3Mock
import io.zeed.test.config.S3Properties
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.http.async.SdkAsyncHttpClient
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.services.s3.S3Configuration
import java.io.File
import java.io.InputStream
import java.net.URI
import java.time.Duration

/**
 *packageName    : io.zeed.test
 * fileName       : AbstractS3Test
 * author         : kimdonggyuuuuu
 * date           : 2022/07/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/14        kimdonggyuuuuu       최초 생성
 */
abstract class AbstractS3Test {
    companion object {
        private val httpClient: SdkAsyncHttpClient = NettyNioAsyncHttpClient.builder()
            .writeTimeout(Duration.ZERO)
            .maxConcurrency(64)
            .build()

        private val serviceConfig: S3Configuration = S3Configuration.builder()
            .checksumValidationEnabled(false)
            .chunkedEncodingEnabled(true)
            .build()

        private val provider: AwsCredentialsProvider = AnonymousCredentialsProvider.create()

        val s3Properties = S3Properties("zeed-bucket-s3",
            "test-access-key",
            "test-secret-key",
            "ap-northeast-2"
        )

        val amazonS3: S3AsyncClient = S3AsyncClient.builder()
            .endpointOverride(URI("http://127.0.0.1:12345"))
            .httpClient(httpClient)
            .region(Region.of(s3Properties.regionStatic))
            .credentialsProvider(provider)
            .serviceConfiguration(serviceConfig)
            .build()

        private val s3Mock: S3Mock = S3Mock.Builder()
            .withPort(12345)
            .withInMemoryBackend()
            .build()


        @JvmStatic
        @BeforeAll
        fun setUp() {
            s3Mock.start()
            amazonS3.createBucket { it.bucket(s3Properties.bucket) }
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            s3Mock.stop()
        }
    }



    class FileMock {
        val filePath = "sample.txt"
        val contentType = "text/plain"

        fun getFileSize(): Long {
            val file = getFile()
            return file.length()
        }

        fun getFile(): File {
            val url = this.javaClass.classLoader.getResource(filePath) ?: throw IllegalArgumentException("File Not Found")

            return File(url.toURI())
        }

        fun getFileStream(): InputStream {
            return this.javaClass.classLoader.getResourceAsStream(filePath) ?: throw IllegalArgumentException("File Not Found")
        }

        fun getObjectMetadata(): ObjectMetadata {
            val objectMetaData = ObjectMetadata()
            objectMetaData.contentType = contentType
            return objectMetaData
        }

    }
}