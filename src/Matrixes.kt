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

