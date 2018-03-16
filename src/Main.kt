fun main(args: Array<String>) {
    val matrix = Matrix(
            arrayOf(
                    doubleArrayOf(1.0, 0.0, 0.0),
                    doubleArrayOf(1.0, 2.0, 0.0),
                    doubleArrayOf(1.0, 2.0, 3.0)
            )
    )
    println(matrix.cond)
}