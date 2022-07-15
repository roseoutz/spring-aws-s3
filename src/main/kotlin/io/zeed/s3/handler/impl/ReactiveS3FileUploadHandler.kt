package io.zeed.s3.handler.impl

import io.zeed.s3.config.props.AmazonS3Properties
import io.zeed.s3.dto.*
import io.zeed.s3.handler.FileUploadHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import software.amazon.awssdk.core.async.AsyncRequestBody
import software.amazon.awssdk.core.async.AsyncResponseTransformer
import software.amazon.awssdk.core.async.ResponsePublisher
import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.services.s3.model.*
import java.util.*

/**
 *packageName    : io.zeed.s3.handler.impl
 * fileName       : DefaultS3FileUploadHandler
 * author         : kimdonggyuuuuu
 * date           : 2022/07/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/14        kimdonggyuuuuu       최초 생성
 */
@Component
class ReactiveS3FileUploadHandler (
    private val amazonS3: S3AsyncClient,
    private val amazonS3Properties: AmazonS3Properties
): FileUploadHandler {
    private fun getOid(): String {
        return UUID.randomUUID().toString()
    }

    override fun upload(fileUploadRequest: FileUploadRequest): Mono<FileUploadResponse> {
        val oid = getOid()
        return uploadToS3(oid, fileUploadRequest)
            .map { FileUploadResponse(oid, it) }
    }

    private fun uploadToS3(oid: String, fileUploadRequest: FileUploadRequest): Mono<PutObjectResponse> {
        return Mono.fromFuture(amazonS3.putObject(
            PutObjectRequest.builder()
                .key(oid)
                .contentType(fileUploadRequest.contentType)
                .contentLength(fileUploadRequest.getFileSize())
                .bucket(amazonS3Properties.bucket)
                .build(),
            AsyncRequestBody
                .fromFile(fileUploadRequest.file)
        ))
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
                .bucket(amazonS3Properties.bucket)
                .build(),
            AsyncResponseTransformer.toPublisher()
        )

        return Mono.fromFuture(future)
    }

    override fun remove(fileRemoveRequest: FileRemoveRequest): Mono<FileRemoveResponse> {
        return removeFromS3(fileRemoveRequest)
            .map { FileRemoveResponse(fileRemoveRequest.oid, it) }

    }

    fun removeFromS3(fileRemoveRequest: FileRemoveRequest): Mono<DeleteObjectResponse> {
        return Mono.fromFuture(
            amazonS3.deleteObject(
                DeleteObjectRequest.builder()
                    .key(fileRemoveRequest.oid)
                    .bucket(amazonS3Properties.bucket)
                    .build()
            )
        )
    }
}