package io.zeed.s3

import com.amazonaws.AmazonClientException
import io.zeed.s3.dto.FileDownloadRequest
import io.zeed.s3.dto.FileDownloadResponse
import io.zeed.s3.dto.FileUploadRequest
import io.zeed.s3.dto.FileUploadResponse
import io.zeed.s3.handler.FileUploadHandler
import reactor.core.publisher.Mono
import software.amazon.awssdk.core.async.AsyncRequestBody
import software.amazon.awssdk.core.async.AsyncResponseTransformer
import software.amazon.awssdk.core.async.ResponsePublisher
import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.GetObjectResponse
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectResponse
import java.util.*

/**
 *packageName    : io.zeed.s3
 * fileName       : DefaultS3FileUploadHanlder
 * author         : kimdonggyuuuuu
 * date           : 2022/07/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/14        kimdonggyuuuuu       최초 생성
 */
class ReactiveS3FileUploadHandler(
    private val amazonS3: S3AsyncClient,
    private val bucket: String
): FileUploadHandler {

    override fun upload(fileUploadRequest: FileUploadRequest): Mono<FileUploadResponse> {
        val oid = getOid()
        return uploadToS3(oid, fileUploadRequest)
            .map { FileUploadResponse(oid, it) }
    }

    private fun uploadToS3(oid: String, fileUploadRequest: FileUploadRequest): Mono<PutObjectResponse> {
        try {
            return Mono.fromFuture(amazonS3.putObject(
                PutObjectRequest.builder()
                    .key(oid)
                    .contentType(fileUploadRequest.contentType)
                    .contentLength(fileUploadRequest.getFileSize())
                    .bucket(bucket)
                    .build(),
                AsyncRequestBody
                    .fromFile(fileUploadRequest.file)
            ))
        } catch (e: AmazonClientException) {
            throw IllegalStateException(e)
        }
    }

    override fun download(fileDownloadRequest: FileDownloadRequest): Mono<FileDownloadResponse> {
        //FileDownloadResponse(fileDownloadRequest.oid, s3Object.objectContent)
        return downloadFromS3(fileDownloadRequest)
            .map { FileDownloadResponse(fileDownloadRequest.oid, it) }
    }

    private fun downloadFromS3(fileDownloadRequest: FileDownloadRequest): Mono<ResponsePublisher<GetObjectResponse>> {
        val future = amazonS3.getObject(
            GetObjectRequest.builder()
                .key(fileDownloadRequest.oid)
                .bucket(bucket)
                .build(),
            AsyncResponseTransformer.toPublisher()
        )

        return Mono.fromFuture(future)
    }

    private fun getOid(): String {
        return UUID.randomUUID().toString()
    }
}