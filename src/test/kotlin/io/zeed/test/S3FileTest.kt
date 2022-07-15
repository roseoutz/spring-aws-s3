package io.zeed.test

import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import reactor.test.StepVerifier
import software.amazon.awssdk.core.async.AsyncRequestBody
import software.amazon.awssdk.core.async.AsyncResponseTransformer
import software.amazon.awssdk.core.async.ResponsePublisher
import software.amazon.awssdk.services.s3.model.*
import java.io.File
import java.nio.ByteBuffer
import java.util.*

/**
 *packageName    : io.zeed.aws
 * fileName       : AwsS3Test
 * author         : kimdonggyuuuuu
 * date           : 2022/07/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/13        kimdonggyuuuuu       최초 생성
 */
class S3FileTest : AbstractS3Test() {
    private fun uploadFile(oid: String, file: File?, contentType: String?, fileSize: Long?) : Mono<PutObjectResponse> {
        return Mono.fromFuture(
            amazonS3.putObject(
                PutObjectRequest.builder()
                    .key(oid)
                    .contentType(contentType)
                    .contentLength(fileSize)
                    .bucket(s3Properties.bucket)
                    .build(),
                AsyncRequestBody
                    .fromFile(file)
            ))
    }

    private fun uploadAfterGetFile(oid: String, file: File?, contentType: String?, fileSize: Long?): Mono<ByteBuffer> {
        return  uploadFile(oid, file, contentType, fileSize)
            .flatMap { getFile(oid) }
            .flatMap { it.toMono().flatMap { stream -> stream.toMono() } }
    }

    private fun getFile(oid:String) : Mono<ResponsePublisher<GetObjectResponse>> {
        return Mono.fromFuture(
            amazonS3.getObject(
                GetObjectRequest.builder()
                    .key(oid)
                    .bucket(s3Properties.bucket)
                    .build(),
                AsyncResponseTransformer.toPublisher()
            )
        )
    }

    private fun removeFile(oid: String): Mono<DeleteObjectResponse> {
        return Mono.fromFuture(
            amazonS3.deleteObject(
                DeleteObjectRequest.builder()
                    .key(oid)
                    .bucket(s3Properties.bucket)
                    .build()
            )
        )
    }

    @Test
    fun uploadObjectTest() {
        // given
        val file = FileMock()
        val fileOid = UUID.randomUUID().toString()

        val responseMono = uploadAfterGetFile(fileOid, file.getFile(), file.contentType, file.getFileSize())

        StepVerifier.create(responseMono)
            .expectNextMatches { String(it.array()) == "this is sample file" }
            .expectComplete()
            .verify()

    }

    @Test
    fun emptyFileUploadTest() {

        val file = FileMock()
        val fileOid = UUID.randomUUID().toString()

        val responseMono = uploadFile(fileOid, null, file.contentType, file.getFileSize())

        StepVerifier.create(responseMono)
            .expectError(NullPointerException::class.java)
            .verify()
    }

    @Test
    fun emptyMetadataTest() {
        val file = FileMock()
        val fileOid = UUID.randomUUID().toString()

        val responseMono = uploadAfterGetFile(fileOid, file.getFile(), null, null)

        StepVerifier.create(responseMono)
            .expectNextMatches { String(it.array()) == "this is sample file" }
            .expectComplete()
            .verify()
    }


    @Test
    fun removeObjectTest() {
        // given
        val file = FileMock()
        val fileOid = UUID.randomUUID().toString()

        val responseMono = uploadFile(fileOid, file.getFile(), file.contentType, file.getFileSize())
            .flatMap { removeFile(fileOid) }
            .flatMap { getFile(fileOid) }

        //whe

        StepVerifier.create(responseMono)
            .expectError(NoSuchKeyException::class.java)
            .verify()
    }



}