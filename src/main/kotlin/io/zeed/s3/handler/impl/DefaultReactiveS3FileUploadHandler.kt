package io.zeed.s3.handler.impl

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import io.zeed.s3.handler.FileUploadHandler
import io.zeed.s3.handler.ReactiveS3FileUploadHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.io.File

/**
 *packageName    : io.zeed.s3.handler
 * fileName       : S3FileUploadHandler
 * author         : kimdonggyuuuuu
 * date           : 2022/07/08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/08        kimdonggyuuuuu       최초 생성
 */
@Service
class DefaultReactiveS3FileUploadHandler(
    @Value("\${cloud.aws.s3.bucket}")
    val bucketName: String,
    val amazonS3Client: AmazonS3Client
): ReactiveS3FileUploadHandler {

    override fun upload(file: FilePart): Mono<String> {
        TODO("Not yet implemented")
    }

    private fun uploadToS3(file: File) {
        amazonS3Client.putObject(
            PutObjectRequest(bucketName, "", file)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )
    }

}