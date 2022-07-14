package io.zeed.s3.dto

import software.amazon.awssdk.services.s3.model.PutObjectResponse

/**
 *packageName    : io.zeed.s3.dto
 * fileName       : S3ResponseDTO
 * author         : kimdonggyuuuuu
 * date           : 2022/07/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/14        kimdonggyuuuuu       최초 생성
 */
class FileUploadResponse(
    val oid: String,
    val putObjectResponse: PutObjectResponse
)
