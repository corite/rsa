import java.math.BigInteger

fun main() {
    val rsa = RSA()
    //using BigInteger in order to be able to use (almost) arbitrary length numbers
    val p = BigInteger("7")
    val q = BigInteger("11")
    val n = p*q
    val e = BigInteger("53")

    val d = rsa.calculateD( p, q, e)
    println("d= $d")
    val x = BigInteger("42")
    println("encrypting x= $x ...")
    val y = rsa.encrypt(x,e,n)
    println("encrypted: y= $y")
    println("decrypted: x= ${rsa.decrypt(y,d,n)}")

}