
fun scalarTimes(a : Matrix, matrix: Matrix): Double {
    return a.data
            .zip(matrix.data)
            .foldRight(0.0) { x, acc ->
                x.first.zip(x.second)
                        .foldRight(0.0) { y, acc2 ->
                            y.first * y.second + acc2
                        } + acc
            }
}

fun solveConjugateGradient(a0: Matrix, b0: Matrix, x0: Matrix, epsilon: Double): Matrix {
    if (a0.determinant() < 0 || a0 != a0.transpose()) throw Exception("Doesn't satisfy condition")

    var xkm = x0
    var rkm = b0.subtract(a0.multiply(x0))
    var pkm = rkm

    var k = 0
    while (true) {
        k++
        val ak = scalarTimes(rkm, rkm) / scalarTimes(a0.multiply(pkm), pkm)
        val xk = xkm.add(pkm.scalarMultiply(ak))
        val rk = rkm.subtract(a0.multiply(pkm.scalarMultiply(ak)))
        val bk = scalarTimes(rk, rk) / scalarTimes(rkm, rkm)
        val pk = rk.add(pkm.scalarMultiply(bk))

        xkm = xk
        rkm = rk
        pkm = pk

        val d = rk.norm / b0.norm
        if (d < epsilon)
            break
    }

    return xkm
}