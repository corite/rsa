import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom
import kotlin.random.Random

class OptimalAsymmetricEncryptionPadding {
    private fun maskGeneratingFunction(seed:ByteArray, length:Int, hashFunction:MessageDigest):ByteArray {
        var t = byteArrayOf()
        var counter = 0

        do {
            counter++
            val c = getBytes(counter)
            t += hashFunction.digest(seed + c)

        } while (t.size <= length)
        return t.copyOfRange(0,length)
    }


    private fun getBytes(word: Int):ByteArray {
        val mask:UInt = "FF000000".toUInt(16)
        val bytes = ByteArray(4)
        for (i in 0..3) {
            bytes[i] = ((word.toUInt() and (mask shr (8*i))) shr ((3-i)*8)).toByte()
        }
        return bytes
    }

    fun transform(n:BigInteger, m:ByteArray, hashFunction:MessageDigest):ByteArray {
        if (m.size > n.toByteArray().size - 2*hashFunction.digestLength - 2) {
            throw IllegalArgumentException("message is too long")
        }
        val l = byteArrayOf()
        val lHash = hashFunction.digest(l)
        val random = SecureRandom()
        val seed = random.generateSeed(42)
        //len(n) = len(m) + len(PS) − 2 · len(hash) − 2
        val psLength = n.toByteArray().size - m.size + 2*hashFunction.digestLength + 2
        val firstMgf = maskGeneratingFunction(seed,42,hashFunction)
        // what is 'h'?
        // what should 'length' from MGF be?

        return byteArrayOf()
    }
}