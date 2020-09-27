package signature

import java.io.File
import java.util.*

val romanSize = 10
val mediumSize = 3

fun main() {
    val scanner = Scanner(System.`in`)
    print("Enter name and surname: ")
    val name = scanner.nextLine()
    print("Enter person's status: ")
    val status = scanner.nextLine()
    println(nameTag(name, status))


}

fun find(chars: Char, sc: Scanner, size: Int): Array<String> {

    var glyph: Array<String> = Array(size) {""}
    while(sc.hasNext()) {
        var line = sc.nextLine()
        if (line[0] == chars && line.length > 1) {
            //sc.nextLine()
            if (line[1] == ' ') {
                for (i in 0..glyph.lastIndex) {
                    glyph[i] = sc.nextLine()
                }
            }
        }
    }
    sc.close()
    return glyph
}


fun readRoman (chars: Char): Array<String> {
    val romanSc = Scanner(File("C:/fonts/roman.txt").readText())
    return find(chars, romanSc, romanSize)
}

fun readMedium (chars: Char): Array<String> {
    val mediumSc = Scanner(File("C:/fonts/medium.txt").readText())
    return find(chars, mediumSc, mediumSize)
}

fun readL (chars: Char): Int {
    val romanSc = Scanner(File("C:/fonts/roman.txt").readText())
    return findLen(chars, romanSc)
}

fun readLM (chars: Char): Int {
    val mediumSc = Scanner(File("C:/fonts/medium.txt").readText())
    return findLen(chars, mediumSc)
}

fun findLen(chars: Char, sc: Scanner): Int {
    var result = 0
    while(sc.hasNext()) {
        var line = sc.nextLine()
        if (line[0] == chars && line.length > 1) {
            //sc.nextLine()
            if (line[1] == ' ') {
                result = line.split(" ")[1].toInt()
            }
        }
    }
    sc.close()
    return result
}

class nameTag(val name: String, val position: String){
    var nameTag = ""
    var size = 0
    var shiftNameL = 2
    var shiftNameR = 2
    var shiftPosL = 2
    var shiftPosR = 2
    init {
        this.size = calcSize(name, position)
        makeTop(this.size)
        makeName(this.name)
        makePosition(this.position)
        makeBottom(this.size)
    }
    fun makeTop(size: Int) {
        this.nameTag += "${"8".repeat(size)}\n"
    }

    fun makeName(name: String) {
        var order = 0
        repeat(romanSize) {
            this.nameTag += "88${" ".repeat(shiftNameL)}"
            for (i in 0..name.length - 1) {
                this.nameTag += ABC.findByChar(name[i], order, "roman")
                //this.nameTag += " "
            }
            order++
            this.nameTag += "${" ".repeat(shiftNameR)}88\n"
        }
    }

    fun makePosition(position: String) {
        var order = 0
        repeat(mediumSize) {
            this.nameTag += "88${" ".repeat(shiftPosL)}"
            for (i in 0..position.length - 1) {
                this.nameTag += ABC.findByChar(position[i], order, "medium")
                //this.nameTag += " "
            }
            order++
            this.nameTag += "${" ".repeat(shiftPosR)}88\n"
        }
    }

    fun makeBottom(size: Int) {
        nameTag += "${"8".repeat(size)}\n"
    }

    override fun toString(): String {
        return this.nameTag
    }

    fun calcSize(name: String, position: String): Int {
        var sizeRoman = 0
        for (i in 0..name.length - 1) {
            sizeRoman += ABC.findLength(name[i], "roman")
        }

        var sizePosition = 0
        for (i in 0..position.length - 1) {
            sizePosition += ABC.findLength(position[i], "medium")
        }

        if (sizeRoman >= sizePosition) {
            if ((sizeRoman - sizePosition) % 2 == 0) {
                shiftPosL += (sizeRoman - sizePosition) / 2
                shiftPosR += (sizeRoman - sizePosition) / 2
            } else {
                shiftPosL += (sizeRoman - sizePosition) / 2
                shiftPosR += (sizeRoman - sizePosition) / 2 + 1
            }
            return sizeRoman + 8
        }

        if (sizeRoman < sizePosition) {
            if ((sizePosition - sizeRoman) % 2 == 0) {
                shiftNameL += (sizePosition - sizeRoman) / 2
                shiftNameR += (sizePosition - sizeRoman) / 2
            } else {
                shiftNameL += (sizePosition - sizeRoman) / 2
                shiftNameR += (sizePosition - sizeRoman) / 2 + 1
            }
            return sizePosition + 8
        }

        return 0
    }
}

enum class ABC(val chars: Char, val lrU: Int, val romU: Array<String>,
               val lrL: Int, val romL: Array<String>,
               val lmU: Int, val medU: Array<String>,
               val lmL: Int, val medL: Array<String>) {
    A('A', readL('A'), readRoman('A'), readL('a'), readRoman('a'),
            readLM('A'), readMedium('A'), readLM('a'), readMedium('a'),),
    B('B', readL('B'), readRoman('B'), readL('b'), readRoman('b'),
            readLM('B'), readMedium('B'), readLM('b'), readMedium('b'),),
    C('C', readL('C'), readRoman('C'), readL('c'), readRoman('c'),
            readLM('C'), readMedium('C'), readLM('c'), readMedium('c'),),
    D('D', readL('D'), readRoman('D'), readL('d'), readRoman('d'),
            readLM('D'), readMedium('D'), readLM('d'), readMedium('d'),),
    E('E', readL('E'), readRoman('E'), readL('e'), readRoman('e'),
            readLM('E'), readMedium('E'), readLM('e'), readMedium('e'),),
    F('F', readL('F'), readRoman('F'), readL('f'), readRoman('f'),
            readLM('F'), readMedium('F'), readLM('f'), readMedium('f'),),
    G('G', readL('G'), readRoman('G'), readL('g'), readRoman('g'),
            readLM('G'), readMedium('G'), readLM('g'), readMedium('g'),),
    H('H', readL('H'), readRoman('H'), readL('h'), readRoman('h'),
            readLM('H'), readMedium('H'), readLM('h'), readMedium('h'),),
    I('I', readL('I'), readRoman('I'), readL('i'), readRoman('i'),
            readLM('I'), readMedium('I'), readLM('i'), readMedium('i'),),
    J('J', readL('J'), readRoman('J'), readL('j'), readRoman('j'),
            readLM('J'), readMedium('J'), readLM('j'), readMedium('j'),),
    K('K', readL('K'), readRoman('K'), readL('k'), readRoman('k'),
            readLM('K'), readMedium('K'), readLM('k'), readMedium('k'),),
    L('L', readL('L'), readRoman('L'), readL('l'), readRoman('l'),
            readLM('L'), readMedium('L'), readLM('l'), readMedium('l'),),
    M('M', readL('M'), readRoman('M'), readL('m'), readRoman('m'),
            readLM('M'), readMedium('M'), readLM('m'), readMedium('m'),),
    N('N', readL('N'), readRoman('N'), readL('n'), readRoman('n'),
            readLM('N'), readMedium('N'), readLM('n'), readMedium('n'),),
    O('O', readL('O'), readRoman('O'), readL('o'), readRoman('o'),
            readLM('O'), readMedium('O'), readLM('o'), readMedium('o'),),
    P('P', readL('P'), readRoman('P'), readL('p'), readRoman('p'),
            readLM('P'), readMedium('P'), readLM('p'), readMedium('p'),),
    Q('Q', readL('Q'), readRoman('Q'), readL('q'), readRoman('q'),
            readLM('Q'), readMedium('Q'), readLM('q'), readMedium('q'),),
    R('R', readL('R'), readRoman('R'), readL('r'), readRoman('r'),
            readLM('R'), readMedium('R'), readLM('r'), readMedium('r'),),
    S('S', readL('S'), readRoman('S'), readL('s'), readRoman('s'),
            readLM('S'), readMedium('S'), readLM('s'), readMedium('s'),),
    T('T', readL('T'), readRoman('T'), readL('t'), readRoman('t'),
            readLM('T'), readMedium('T'), readLM('t'), readMedium('t'),),
    U('U', readL('U'), readRoman('U'), readL('u'), readRoman('u'),
            readLM('U'), readMedium('U'), readLM('u'), readMedium('u'),),
    V('V', readL('V'), readRoman('V'), readL('v'), readRoman('v'),
            readLM('V'), readMedium('V'), readLM('v'), readMedium('v'),),
    W('W', readL('W'), readRoman('W'), readL('w'), readRoman('w'),
            readLM('W'), readMedium('W'), readLM('w'), readMedium('w'),),
    X('X', readL('X'), readRoman('X'), readL('x'), readRoman('x'),
            readLM('X'), readMedium('X'), readLM('x'), readMedium('x'),),
    Y('Y', readL('Y'), readRoman('Y'), readL('y'), readRoman('y'),
            readLM('Y'), readMedium('Y'), readLM('y'), readMedium('y'),),
    Z('Z', readL('Z'), readRoman('Z'), readL('z'), readRoman('z'),
            readLM('Z'), readMedium('Z'), readLM('z'), readMedium('z'),),
    SPACE(' ', 10, arrayOf("          "), 10, arrayOf("          "),
            5, arrayOf("     "), 5, arrayOf("     "));

    companion object {
        fun findByChar(chars: Char, order: Int, type: String): String {
            when (type) {
                "roman" -> {
                    when (chars) {
                        in 'a'..'z' -> {
                            for (enum in ABC.values()) {
                                if (chars.toUpperCase() == enum.chars) return enum.romL[order]
                            }
                        }
                        in 'A'..'Z' -> {
                            for (enum in ABC.values()) {
                                if (chars == enum.chars) return enum.romU[order]
                            }
                        }
                        ' ' -> return ABC.SPACE.romU[0]
                    }
                }
                "medium" -> {
                    when (chars) {
                        in 'a'..'z' -> {
                            for (enum in ABC.values()) {
                                if (chars.toUpperCase() == enum.chars) return enum.medL[order]
                            }
                        }
                        in 'A'..'Z' -> {
                            for (enum in ABC.values()) {
                                if (chars == enum.chars) return enum.medU[order]
                            }
                        }
                        ' ' -> return ABC.SPACE.medU[0]
                    }
                }
            }
            return "Not Find"
        }

        fun findLength(chars: Char, type: String): Int {
            when (type) {
                "roman" -> {
                    when (chars) {
                        in 'a'..'z' -> {
                            for (enum in ABC.values()) {
                                if (chars.toUpperCase() == enum.chars) return enum.lrL
                            }
                        }
                        in 'A'..'Z' -> {
                            for (enum in ABC.values()) {
                                if (chars == enum.chars) return enum.lrU
                            }
                        }
                        ' ' -> return 10
                    }
                }
                "medium" -> {
                    when (chars) {
                        in 'a'..'z' -> {
                            for (enum in ABC.values()) {
                                if (chars.toUpperCase() == enum.chars) return enum.lmL
                            }
                        }
                        in 'A'..'Z' -> {
                            for (enum in ABC.values()) {
                                if (chars == enum.chars) return enum.lmU
                            }
                        }
                        ' ' -> return 5
                    }
                }
            }
            return 0
        }
    }

}