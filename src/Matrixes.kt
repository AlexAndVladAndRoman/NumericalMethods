import org.apache.commons.math3.linear.BlockRealMatrix
import org.apache.commons.math3.linear.MatrixUtils

typealias Matrix = BlockRealMatrix

operator fun Matrix.get(i: Int): DoubleArray = getRow(i)
operator fun Matrix.get(i: Int, j: Int) = getEntry(i, j)

operator fun Matrix.set(i: Int, a: DoubleArray) = setRow(i, a)
operator fun Matrix.set(i: Int, j: Int, a: Double) = setEntry(i, j, a)
val Matrix.size: Int get() = rowDimension
fun Matrix.invert(): Matrix = MatrixUtils.inverse(this) as Matrix
val Matrix.cond: Double get () = this.norm * this.invert().norm

fun Matrix.getSubMatrix(i: Int, j: Int): Matrix = getSubMatrix(
        (0 until size).filter { it != i }.toIntArray(),
        (0 until size).filter { it != j }.toIntArray()) as Matrix

fun Matrix.determinant(): Double {
    if (size == 1) return this[0, 0]
    return data.mapIndexed { i, row ->
        this.getSubMatrix(i, 0).determinant() * row[0] * (if (i % 2 == 0) 1 else -1)
    }.sum()
}

fun solveGaussian(a0: Matrix, b0: DoubleArray): Matrix {
    if (a0.determinant() == 0.0) throw Exception()
    val a = a0.copy()
    val b = b0.copyOf()
    val n = a.size
    val result = Matrix(n, 1)
    for (step in 0 until n - 1) {
        val t = a[step, step]
        for (i in step + 1 until n) {
            val tt = a[i, step] / t
            for (j in 0 until n) {
                a[i, j] = a[i, j] - tt * a[step, j]
            }
            b[i] = b[i] - tt * b[step]
        }
    }
    for (step in n - 1 downTo 0) {
        var sum = 0.0
        for (i in step + 1 until n) {
            sum -= a[step, i] * result[i, 0]
        }
        sum += b[step]
        result[step, 0] = sum / a[step, step]
    }
    return result
}
