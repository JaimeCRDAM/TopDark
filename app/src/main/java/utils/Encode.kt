package utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets.UTF_8
import java.util.Base64
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

@RequiresApi(Build.VERSION_CODES.O)
fun gzip(content: String): ByteArray {
    val bos = ByteArrayOutputStream()
    GZIPOutputStream(bos).bufferedWriter(UTF_8).use { it.write(content) }
    return bos.toByteArray()
}

fun ungzip(content: ByteArray): String =
    GZIPInputStream(content.inputStream()).bufferedReader(UTF_8).use { it.readText() }