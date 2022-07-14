package io.zeed.s3.dto

import java.io.File
import java.io.InputStream

/**
 *packageName    : io.zeed.s3.dto
 * fileName       : FileUploadRequestDTO
 * author         : kimdonggyuuuuu
 * date           : 2022/07/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/14        kimdonggyuuuuu       최초 생성
 */
data class FileUploadRequest(
    val fileName : String,
    val file : File,
    val contentType : String
) {

    fun getFileStream(): InputStream {
        return file.inputStream()
    }

    fun getFileSize(): Long {
        return file.length()
    }

}