package io.zeed.s3.dto

import software.amazon.awssdk.services.s3.model.DeleteObjectResponse

/**
 *packageName    : io.zeed.s3.dto
 * fileName       : FileDownloadRequest
 * author         : kimdonggyuuuuu
 * date           : 2022/07/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/14        kimdonggyuuuuu       최초 생성
 */
data class FileRemoveResponse(
    val oid: String,
    val deleteObjectResponse: DeleteObjectResponse
)
