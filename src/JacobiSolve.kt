
fun solveJacobi(a0: Matrix, b: Matrix, x0: Matrix, e: Double): Matrix {
    val n = a0.size
    val r = a0.copy()
    for (i in 0 until n) {
        r[i, i] = 0.0
    }
    val d = a0.subtract(r)
    val q = d.invert().multiply(r).norm
    var result = x0
    var nResult = Matrix(n, 1)
    if (q > 1) throw Exception()
    while (true) {
        for (i in 0 until n) {
            var s = 0.0
            for (j in 0 until n) {
                if (i != j)
                    s += a0[i, j] * result[j, 0]
            }
            nResult[i, 0] = (b[i, 0] - s) / a0[i, i]
        }
        val cond = result.subtract(nResult).norm
        if (cond / (1 - q) < e) break
        result = nResult
        nResult = Matrix(n, 1)
    }
    return nResult
}