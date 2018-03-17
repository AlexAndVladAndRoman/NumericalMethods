
private fun solve(s: String, f: () -> Matrix) {
    try {
        print("$s: ")
        printResult(f())
    } catch (e: Exception) {
        println(s + e.toString())
    }
}

private fun solveAll(a: Matrix, b: Matrix, c: Matrix) {
    solve("G") { solveGaussian(a, b.getColumn(0)) }
//    solve("J") { JacobiSolver.getSolve(a, b, c) }
//    solve("S") { SeidelSolver.getSolve(a, b, c) }
    solve("C") { solveConjugateGradient(a, b, c, 1e-6) }
}

//private fun getMatrix(file: String): Matrix = MatrixReader.getDoubleMatrix(File("res/double/$file.txt"))

private fun printResult(matrix: Matrix) {
    for (i in 0 until matrix.size) {
        print(matrix.get(i, 0).toString() + " ")
    }
    println()
}

fun main(args: Array<String>) {
    val matrix = Matrix(
            arrayOf(
                    doubleArrayOf(1.0, 0.0, 0.0),
                    doubleArrayOf(1.0, 2.0, 0.0),
                    doubleArrayOf(1.0, 2.0, 3.0)
            )
    )
    val ans = doubleArrayOf(1.0, 2.0, 3.0)
    println(matrix.cond)
    println(solveGaussian(matrix, ans))
    val n = 3
    double(n)
}

fun double(n: Int) {
    val b = generateRandom(n, 1)
    val c = generateRandom(n, 1)
    println("B matrix of size $b")
    println("C matrix of size $c")
//    val sym = Generator.generateSymmetric(n)
//    println("Symmetric matrix of size $sym")
//    solveAll(sym, b, c)
//    (0 until n).forEach { sym.set(it, it, 1e-6) }
//    println("Bad symmetric matrix of size $sym")
//    solveAll(sym, b, c)
//    val diag = Generator.generateDiagonallyDominant(n)
//    println("Diagonally Dominant matrix of size $diag")
//    solveAll(diag, b, c)
//    val random = Generator.generateRandom(n, n)
//    println("Random matrix of size $random")
//    solveAll(random, b, c)
    val bad = generateBad(n)
    println("Bad matrix of size $bad")
    solveAll(bad, b, c)
}
