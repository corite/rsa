import java.math.BigInteger
import java.nio.charset.Charset
import java.security.MessageDigest

fun main() {
    showRsaPart1()
    showRsaPart2()
    showDiffOfSquares()
    showOaep()

}
fun showRsaPart1() {
    println(":::AES PART I:::")
    //using BigInteger in order to be able to use (almost) arbitrary length numbers
    val p = BigInteger("6791")
    val q = BigInteger("15679")
    val e = BigInteger("53")

    showEncAndDec(p,q,e)

}

fun showRsaPart2() {
    println("\n:::AES PART II:::")
    val rsa = RivestShamirAdleman()
    //using BigInteger in order to be able to use (almost) arbitrary length numbers
    val p = rsa.getPrime(2000)
    val q = rsa.getPrime(2000)
    val e = BigInteger("53")
    showEncAndDec(p,q,e)

}

fun showDiffOfSquares() {
    println("\n:::Difference of Squares:::")

    val p = BigInteger.valueOf(20353)
    val q = BigInteger.valueOf(41851)
    println("chose p=$p and q=$q. Now trying to factorize N=p*q=${p*q}...")
    val dos = DifferenceOfSquares()
    val pAndQ = dos.factorize(p*q)
    println("p= ${pAndQ.first}")
    println("p= ${pAndQ.second}")

}

fun showOaep() {
    println("\n:::OAEP:::")

    val oaep = OptimalAsymmetricEncryptionPadding()
    val originalMessage = "Hello World!"
    println("original message= '$originalMessage'")
    val byteMessage = originalMessage.toByteArray(Charset.defaultCharset())
    val rsa = RivestShamirAdleman()
    val p = rsa.getPrime(1000)
    val q = rsa.getPrime(1000)
    //val e = BigInteger.valueOf(53)
    //val d = rsa.calculateD(p,q,e)
    val n = p*q
    val hashFunction = MessageDigest.getInstance("SHA-256")
    val transformed = oaep.transform(n,byteMessage, hashFunction)
    println("transformed message= '${transformed.toString(Charset.defaultCharset())}'")
    //val encrypted = rsa.encrypt(BigInteger(transformed), e, n)
    //val decryptedBytes = rsa.decrypt(encrypted,d,n).toByteArray()
    val m = oaep.reverseTransform(transformed, hashFunction)
    println("un-transformed message= '${m.toString(Charset.defaultCharset())}'")

}

fun showEncAndDec(p:BigInteger, q:BigInteger, e:BigInteger) {
    val rsa = RivestShamirAdleman()
    val n = p*q
    val d = rsa.calculateD( p, q, e)
    println("p= $p")
    println("q= $q")
    println("d= $d")
    val x = BigInteger("42")
    println("encrypting x= $x")
    val y = rsa.encrypt(x,e,n)
    println("encrypted: y= $y")
    println("decrypted: x= ${rsa.decrypt(y,d,n)}")
}