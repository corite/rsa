import java.math.BigInteger

class RSA {

    private fun squareMultiply(xNum:BigInteger, m:BigInteger, n:BigInteger):BigInteger {
        // BigInteger.modPow() would probably do the trick too (though I don't know how exactly it is implemented),
        // but since it is part of the exercise this is the implementation "from scratch"
        var x = xNum
        var y = BigInteger.ONE
        val r = m.bitLength()
        for (i in 0..r) {
            if (m.testBit(i)) {
                y = y*x % n
            }
            x = x.pow(2) % n
        }
        if (!y.equals(xNum.modPow(m,n))) {
            println("squareMultiply panic")
        }
        return y
    }

    private fun eeA(a:BigInteger, b:BigInteger):Array<BigInteger> {
        // this could also be done by using the built-in BigInteger.modInverse() function
        var r0 = a; var r1 = b
        var (k,s0,s1,t0,t1) = listOf( BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE )

        do {
            k++
            val qk = r0 / (r1)
            val rkTmp = r1
            r1 = r0 - qk*rkTmp
            val skTmp = s1
            s1 = s0 - qk*skTmp
            val tkTmp = t1
            t1 = t0 - qk*tkTmp

            r0 = rkTmp
            s0 = skTmp
            t0 = tkTmp

        } while (r1 != BigInteger.ZERO)
        if (r0 != BigInteger.ONE || BigInteger.ONE != s0 * a % b) {
            println("eeA panic")
        }
        return arrayOf(r0,s0,t0)
    }

    fun encrypt(x:BigInteger, e:BigInteger, n:BigInteger):BigInteger {
        return squareMultiply(x,e,n)
    }

    fun decrypt(y:BigInteger, d:BigInteger, n:BigInteger):BigInteger {
        return squareMultiply(y,d,n)
    }

    fun calculateD(p:BigInteger, q:BigInteger, e:BigInteger):BigInteger {
        val phiOfN = (p - BigInteger.ONE) * (q - BigInteger.ONE)
        return eeA(e,phiOfN)[1]+phiOfN
    }
}