
import org.apache.commons.math3.FieldElement
import org.apache.commons.math3.linear.Array2DRowRealMatrix
import org.apache.commons.math3.linear.MatrixUtils

operator fun <T> FieldElement<T>.plus(other: T): T = this.add(other)

operator fun <T> FieldElement<T>.minus(other: T): T = this.subtract(other)

operator fun <T> FieldElement<T>.times(other: T): T = this.multiply(other)

operator fun <T> FieldElement<T>.div(other: T): T = this.divide(other)

operator fun <T> FieldElement<T>.unaryMinus(): T = this.negate()

interface ComparableElement<T> : FieldElement<T>, Comparable<T>

class Matrix(init: Array<DoubleArray>) : Array2DRowRealMatrix(init) {
    val size: Int = rowDimension

    operator fun get(i: Int) = getRow(i)
    operator fun get(i: Int, j: Int) = getEntry(i, j)

    operator fun set(i: Int, a: DoubleArray) = setRow(i, a)
    operator fun set(i: Int, j: Int, a: Double) = setEntry(i, j, a)

//    val norm: Double
//        get() = data.map { row -> row.fold(0.0) { a, b -> a + b } }.reduce(::max)

    fun invert(): Matrix = Matrix(MatrixUtils.inverse(this).data)

//    override fun getSubMatrix(startRow: Int, endRow: Int, startColumn: Int, endColumn: Int): Matrix {
//        return super.getSubMatrix(startRow, endRow, startColumn, endColumn) as Matrix
//    }

//    fun determinant(): Double {
//        if (size == 1) return 0.0
//        var result = 0.0
//        for (i in 0 until size) {
//            val d = this.getSubMatrix(i, 0, i + size - 1, size - 1).determinant()
//            result +=
//                    if (i % 2 == 0) data[i][0] * d
//                    else -data[i][0] * d
//        }
//        return result
//    }

    val cond: Double
        get () = this.norm * this.invert().norm
}
