package io.zeed.s3.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

/**
 *packageName    : io.zeed.s3.document
 * fileName       : FileInfoDocument
 * author         : kimdonggyuuuuu
 * date           : 2022/07/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/15        kimdonggyuuuuu       최초 생성
 */
@Document("file_info")
data class FileInfoDocument(
    @Id
    val id: ObjectId,
    val originName: String,
    val contentType: String,
    val fileSize: Long,
    @CreatedDate
    val insertTime: Date,
    @LastModifiedDate
    val updateTime: Date
)
