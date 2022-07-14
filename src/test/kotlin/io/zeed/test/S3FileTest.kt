package io.zeed.test

import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import org.apache.http.entity.ContentType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.util.FileCopyUtils
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


    /*
    @Test
    fun uploadObjectTest() {
        // given
        val file = FileMock()
        val fileOid = UUID.randomUUID().toString()
        val files = file.getFile()

        amazonS3.putObject(PutObjectRequest(s3Properties.bucket, fileOid, files).withCannedAcl(CannedAccessControlList.PublicRead))

        // when
        val s3Object = amazonS3.getObject(s3Properties.bucket, fileOid)

        // then
        Assertions.assertThat(s3Object.objectMetadata.contentType).isEqualTo(file.contentType)
        Assertions.assertThat(String(FileCopyUtils.copyToByteArray(s3Object.objectContent))).isEqualTo("this is sample file")
    }

    @Test
    fun emptyFileUploadTest() {

        val file = FileMock()
        val fileOid = UUID.randomUUID().toString()

        Assertions.assertThatThrownBy { amazonS3.putObject(s3Properties.bucket, fileOid, null, file.getObjectMetadata()) }
            .isInstanceOf(NullPointerException::class.java)
    }

    @Test
    fun emptyMetadataTest() {
        val file = FileMock()
        val fileOid = UUID.randomUUID().toString()
        val fileStream = file.getFileStream()

        amazonS3.putObject(s3Properties.bucket, fileOid, fileStream, null)

        val s3Object = amazonS3.getObject(s3Properties.bucket, fileOid)

        // then
        Assertions.assertThat(s3Object.objectMetadata.contentType).isEqualTo(ContentType.APPLICATION_OCTET_STREAM.mimeType)
        Assertions.assertThat(String(FileCopyUtils.copyToByteArray(s3Object.objectContent))).isEqualTo("this is sample file")
    }

    @Test
    fun removeObjectTest() {
        // given
        val file = FileMock()
        val fileOid = UUID.randomUUID().toString()
        val fileStream = file.getFileStream()

        amazonS3.putObject(s3Properties.bucket, fileOid, fileStream, file.getObjectMetadata())

        //when
        amazonS3.deleteObject(s3Properties.bucket, fileOid)

        Assertions.assertThatThrownBy { amazonS3.getObject(s3Properties.bucket, fileOid) }
            .isInstanceOf(AmazonS3Exception::class.java)
    }

     */

}