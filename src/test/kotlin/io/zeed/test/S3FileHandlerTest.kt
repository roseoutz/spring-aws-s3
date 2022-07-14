package io.zeed.test

import io.zeed.s3.ReactiveS3FileUploadHandler
import io.zeed.s3.dto.FileDownloadRequest
import io.zeed.s3.dto.FileUploadRequest
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier
import software.amazon.awssdk.services.s3.model.NoSuchKeyException
import java.util.*

/**
 *packageName    : io.zeed.test
 * fileName       : S3FileHandlerTest
 * author         : kimdonggyuuuuu
 * date           : 2022/07/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/14        kimdonggyuuuuu       최초 생성
 */
class S3FileHandlerTest : AbstractS3Test() {

    private val handler = ReactiveS3FileUploadHandler(amazonS3, s3Properties.bucket)

    @Test
    fun file_upload_and_download_test() {
        val file = FileMock()
        val fileUploadRequest = FileUploadRequest(file.filePath, file.getFile(), file.contentType)
        val fileResponse = handler.upload(fileUploadRequest)
            .flatMap {
                val fileDownloadRequest = FileDownloadRequest(it.oid)
                handler.download(fileDownloadRequest)
            }
            .map { it.response }


        StepVerifier.create(fileResponse)
            .expectNextMatches { it.response().sdkHttpResponse().isSuccessful }
            .expectComplete()
            .verify()
    }

    @Test
    fun file_not_exist_download_test() {
        val file = FileMock()
        val fileUploadRequest = FileUploadRequest(file.filePath, file.getFile(), file.contentType)
        val fileResponse = handler.upload(fileUploadRequest)
            .flatMap {
                val fileDownloadRequest = FileDownloadRequest(UUID.randomUUID().toString())
                handler.download(fileDownloadRequest)
            }
            .map { it.response }

        StepVerifier.create(fileResponse)
            .expectError(NoSuchKeyException::class.java)
            .verify()
    }
}
