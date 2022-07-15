package io.zeed.test

import io.zeed.s3.ReactiveS3FileUploadHandler
import io.zeed.s3.dto.FileDownloadRequest
import io.zeed.s3.dto.FileRemoveRequest
import io.zeed.s3.dto.FileUploadRequest
import org.junit.jupiter.api.DisplayName
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

    @DisplayName(value = "파일 업로드 후 다운로드 테스트")
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

    @DisplayName(value = "파일 업로드 후 제거 테스트")
    @Test
    fun file_upload_and_remove_test() {
        val file = FileMock()
        val fileUploadRequest = FileUploadRequest(file.filePath, file.getFile(), file.contentType)
        val fileResponse = handler.upload(fileUploadRequest)
            .flatMap {
                val fileRemoveRequest = FileRemoveRequest(it.oid)
                handler.remove(fileRemoveRequest)
            }


        StepVerifier.create(fileResponse)
            .expectNextMatches { it.deleteObjectResponse.sdkHttpResponse().isSuccessful }
            .expectComplete()
            .verify()
    }

    @DisplayName(value = "파일 업로드 후 제거 후 존재하지 않는 파일 다운로드 테스트")
    @Test
    fun file_upload_and_remove_and_download_test() {
        val file = FileMock()
        val fileUploadRequest = FileUploadRequest(file.filePath, file.getFile(), file.contentType)
        val fileResponse = handler.upload(fileUploadRequest)
            .flatMap {
                val fileRemoveRequest = FileRemoveRequest(it.oid)
                handler.remove(fileRemoveRequest)
            }
            .flatMap {
                val fileDownloadRequest = FileDownloadRequest(it.oid)
                handler.download(fileDownloadRequest)
            }

        StepVerifier.create(fileResponse)
            .expectError(NoSuchKeyException::class.java)
            .verify()
    }

    @DisplayName(value = "존재하지 않는 파일 다운로드 테스트")
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
