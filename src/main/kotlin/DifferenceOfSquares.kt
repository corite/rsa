import java.math.BigInteger

class DifferenceOfSquares {


    fun factorise(N:BigInteger):Pair<BigInteger, BigInteger> {
        var u = N.sqrt()
        while (!isSquare(u.pow(2)-N)) {
           u++
        }
        val w = (u.pow(2)-N).sqrt()
        return Pair(u+w,u-w)
    }
    private fun isSquare(x:BigInteger):Boolean {
        return x.sqrt().pow(2) == x
    }
}