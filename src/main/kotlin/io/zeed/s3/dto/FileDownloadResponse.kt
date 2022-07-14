package io.zeed.s3.dto

import software.amazon.awssdk.core.async.ResponsePublisher
import software.amazon.awssdk.services.s3.model.GetObjectResponse
import java.io.InputStream

/**
 *packageName    : io.zeed.s3.dto
 * fileName       : FileDownloadResponse
 * author         : kimdonggyuuuuu
 * date           : 2022/07/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/14        kimdonggyuuuuu       최초 생성
 */
data class FileDownloadResponse(
    val oid: String,
    val response: ResponsePublisher<GetObjectResponse>
)
