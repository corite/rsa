import java.math.BigInteger

fun main() {
    //part1()
    part2()
}
fun part1() {
    println(":::PART I:::")
    //using BigInteger in order to be able to use (almost) arbitrary length numbers
    val p = BigInteger("7")
    val q = BigInteger("11")
    val e = BigInteger("53")

    showEncAndDec(p,q,e)

}

fun part2() {
    println(":::PART II:::")
    val rsa = RivestShamirAdleman()
    //using BigInteger in order to be able to use (almost) arbitrary length numbers
    val p = rsa.getPrime(2000)
    val q = rsa.getPrime(2000)
    val e = BigInteger("53")
    showEncAndDec(p,q,e)

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