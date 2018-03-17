
fun solveSeidel(a0: Matrix, b0: Matrix, x0 : Matrix, eps : Double): Matrix {
    val n = a0.size
    val l = a0.copy()
    val e = Matrix(n, n)
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            l[i, j] = 0.0
        }
        e[i, i] = 1.0
    }
    val r = a0.subtract(l)
    val q = e.subtract(l).invert().multiply(r).norm
    var result = x0
    var nResult = Matrix(n, 1)
    if (q > 1) throw Exception()
    while (true) {
        for (i in 0 until n) {
            var s = 0.0
            for (j in 0 until i) {
                s += a0[i, j] * nResult[j, 0]
            }
            for (j in i + 1 until n) {
                s += a0[i, j] * result[j, 0]
            }
            nResult[i, 0] = (b0[i, 0] - s) / a0[i, i]
        }
        val cond = result.subtract(nResult).norm
        if (cond < eps) break
        result = nResult
        nResult = Matrix(n, 1)
    }
    return nResult
}