@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import lesson5.task1.containsIn
import ru.spbstu.wheels.currentPlatform
import kotlin.math.*
import java.io.File
import java.lang.NullPointerException
import kotlin.IllegalArgumentException
import kotlin.system.exitProcess


fun myFun(inputName: String): Int {
    val list = File(inputName).readLines()

    // Индексы символа и строки Камеры
    var charC = 0
    var lineC = 0

    // Проверка списка строк на правильность
    val lineSize: Int
    if (list.isEmpty()) throw IllegalArgumentException()
    else lineSize = list[0].length
    var checkC = 0
    for ((indexLine, line) in list.withIndex()) {
        if (line.length != lineSize) throw IllegalArgumentException()
        for ((indexChar, char) in line.withIndex()) {
            if (!(char == '.' || char == '#' || char == 'C')) {
                throw IllegalArgumentException()
            }
            if (char == 'C') {
                ++checkC
                if (checkC > 1) throw IllegalArgumentException()
                charC = indexChar
                lineC = indexLine
            }
        }
    }
    if (checkC == 0) throw IllegalArgumentException()

    // Подсчёт шагов и свободных клеток
    val maxLine = list.size - 1
    val maxChar = list[0].length - 1
    var countEmpty = 0
    val pairs =
        listOf((-1 to 0), (1 to 0), (0 to -1), (0 to 1), (-1 to -1), (-1 to 1), (1 to -1), (1 to 1))
    var curLine: Int
    var curChar: Int

    // Поиск
    for ((first, second) in pairs) {
        var index = 0
        while (true) {
            ++index

            curLine = lineC + first * index
            if (curLine < 0 || curLine > maxLine) break
            curChar = charC + second * index
            if (curChar < 0 || curChar > maxChar) break

            if (list[curLine][curChar] == '.') ++countEmpty
            else break
        }
    }
    return countEmpty
}





fun main() {
    println(myFun("D://testsMyFun/text0.txt")) // 0
    println(myFun("D://testsMyFun/text1.txt")) // 16
    println(myFun("D://testsMyFun/text2.txt")) // 5
    println(myFun("D://testsMyFun/text3.txt")) // 4
    println(myFun("D://testsMyFun/text4.txt")) // 4
    println(myFun("D://testsMyFun/text5.txt")) // 6
    println(myFun("D://testsMyFun/text6.txt")) // 17
    try {
        println(myFun("D://testsMyFun/text7.txt"))
    } catch (e: IllegalArgumentException) { println("Asserted throw") }
    try {
        println(myFun("D://testsMyFun/text8.txt"))
    } catch (e: IllegalArgumentException) { println("Asserted throw") }
    try {
        println(myFun("D://testsMyFun/text9.txt"))
    } catch (e: IllegalArgumentException) { println("Asserted throw") }
    try {
        println(myFun("D://testsMyFun/text10.txt"))
    } catch (e: IllegalArgumentException) { println("Asserted throw") }
    try {
        println(myFun("D://testsMyFun/text11.txt"))
    } catch (e: IllegalArgumentException) { println("Asserted throw") }
    try {
        println(myFun("D://testsMyFun/text12.txt"))
    } catch (e: IllegalArgumentException) { println("Asserted throw") }

}

/*
fun myFun(inputName: String): Int {
    val list = File(inputName).readLines().toMutableList()


    // Индексы символа и строки Камеры
    var charC = 0
    var lineC = 0


    // Проверка списка строк на правильность
    val lineSize: Int
    if (list.isEmpty()) throw IllegalArgumentException()
    else lineSize = list[0].length
    var checkC = 0
    for ((indexLine, line) in list.withIndex()) {
        if (line.length != lineSize) throw IllegalArgumentException()
        for ((indexChar, char) in line.withIndex()) {
            if (!(char == '.' || char == '#' || char == 'C')) {
                throw IllegalArgumentException()
            }
            if (char == 'C') {
                ++checkC
                if (checkC > 1) throw IllegalArgumentException()
                charC = indexChar
                lineC = indexLine
            }
        }
    }
    if (checkC == 0) throw IllegalArgumentException()


    // Логические переменные для контроля границ строк в восьми направлениях
    var countBooleans = 8
    var cUp = true
    var cDown = true
    var cLeft = true
    var cRight = true
    var cUpLeft = true
    var cUpRight = true
    var cDownLeft = true
    var cDownRight = true


    // Подсчёт шагов и свободных клеток
    val maxListIndex = list.size - 1
    val maxLineIndex = list[0].length - 1
    var countEmpty = 0
    var index = 0


    // Поиск
    while (countBooleans > 0) {
        ++index

        if (cUp) {
            if (lineC != 0) {
                if (list[lineC - index][charC] == '.') ++countEmpty
                else {
                    cUp = false
                    --countBooleans
                }
                if (lineC - index == 0 && cUp) {
                    cUp = false
                    --countBooleans
                }
            } else {
                cUp = false
                --countBooleans
            }
        }

        if (cDown) {
            if (lineC != maxListIndex) {
                if (list[lineC + index][charC] == '.') ++countEmpty
                else {
                    cDown = false
                    --countBooleans
                }
                if (lineC + index == maxListIndex && cDown) {
                    cDown = false
                    --countBooleans
                }
            } else {
                cDown = false
                --countBooleans
            }
        }

        if (cLeft) {
            if (charC != 0) {
                if (list[lineC][charC - index] == '.') ++countEmpty
                else {
                    cLeft = false
                    --countBooleans
                }
                if (charC - index == 0 && cLeft) {
                    cLeft = false
                    --countBooleans
                }
            } else {
                cLeft = false
                --countBooleans
            }
        }

        if (cRight) {
            if (charC != maxLineIndex) {
                if (list[lineC][charC + index] == '.') ++countEmpty
                else {
                    cRight = false
                    --countBooleans
                }
                if (charC + index == maxLineIndex && cRight) {
                    cRight = false
                    --countBooleans
                }
            } else {
                cRight = false
                --countBooleans
            }
        }

        if (cUpLeft) {
            if (lineC != 0 && charC != 0) {
                if (list[lineC - index][charC - index] == '.') ++countEmpty
                else {
                    cUpLeft = false
                    --countBooleans
                }
                if (cUpLeft && (lineC - index == 0 || charC - index == 0)) {
                    cUpLeft = false
                    --countBooleans
                }
            } else {
                cUpLeft = false
                --countBooleans
            }
        }

        if (cUpRight) {
            if (lineC != 0 && charC != maxLineIndex) {
                if (list[lineC - index][charC + index] == '.') ++countEmpty
                else {
                    cUpRight = false
                    --countBooleans
                }
                if (cUpRight && (lineC - index == 0 || charC + index == maxLineIndex)) {
                    cUpRight = false
                    --countBooleans
                }
            } else {
                cUpRight = false
                --countBooleans
            }
        }

        if (cDownLeft) {
            if (lineC != maxListIndex && charC != 0) {
                if (list[lineC + index][charC - index] == '.') ++countEmpty
                else {
                    cDownLeft = false
                    --countBooleans
                }
                if (cDownLeft && (lineC + index == maxListIndex || charC - index == 0)) {
                    cDownLeft = false
                    --countBooleans
                }
            } else {
                cDownLeft = false
                --countBooleans
            }
        }

        if (cDownRight) {
            if (lineC != maxListIndex && charC != maxLineIndex) {
                if (list[lineC + index][charC + index] == '.') ++countEmpty
                else {
                    cDownRight = false
                    --countBooleans
                }
                if (cDownRight && (lineC + index == maxListIndex || charC + index == maxLineIndex)) {
                    cDownRight = false
                    --countBooleans
                }
            } else {
                cDownRight = false
                --countBooleans
            }
        }
    }

    return countEmpty
}

*/










// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    for (line in File(inputName).readLines()) {
        if (line.startsWith("_")) continue
        writer.write(line)
        writer.newLine()
    }
    writer.close()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val listSubToSet = substrings.toSet()
    val map = mutableMapOf<String, Int>()
    for (line in listSubToSet) {
        map[line] = 0
    }

    val reader = File(inputName).readLines()
    for (line in reader) {
        for (phrase in listSubToSet) {
            val str = line.lowercase()
            val phrase2 = phrase.lowercase()

            var index = 0
            var num = str.indexOf(phrase2, index)

            while (num != -1) {
                map[phrase] = map[phrase]!! + 1
                index = num + 1
                num = str.indexOf(phrase2, index)
            }
        }
    }

    return map
}

/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    val list = (File(inputName).readLines()).toMutableList()
    if (list.size == 1 && list[0] == "") {
        writer.write("")
        writer.close()
        return
    }

    val regex = Regex(
        """[а-я]*[жчшщ][ыяю][а-я]*|жю(?!ри)|(?<!бро)шю(?!р)|(?<!пара)шю(?!т)""",
        RegexOption.IGNORE_CASE
    )
    val reg1 = Regex("""(?<=[жчшщ])ы""", RegexOption.IGNORE_CASE)   //   и
    val reg2 = Regex("""(?<=[жчшщ])я""", RegexOption.IGNORE_CASE)   //   а
    val reg3 = Regex("""(?<=[жчшщ])ю""", RegexOption.IGNORE_CASE)   //   у

    for (str in list) {
        val line = Regex(""" """).split(str).toMutableList()
        for ((index, word) in line.withIndex()) {
            if (regex.containsMatchIn(word)) {
                when {
                    reg1.containsMatchIn(word) -> reg1.replace(word, "и")
                    reg2.containsMatchIn(word) -> reg2.replace(word, "а")
                    reg3.containsMatchIn(word) -> reg3.replace(word, "у")
                }
                line[index] = word
            }
        }
        writer.write(line.joinToString(separator = " "))
        writer.newLine()
    }
    writer.close()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()

    var maximum = 0
    for (str in File(inputName).readLines()) {
        if (str.trim().length > maximum)
            maximum = str.trim().length
    }

    for (str in File(inputName).readLines()) {
        var space = (maximum - str.trim().length) / 2
        while (space > 0) {
            writer.write(" ")
            space -= 1
        }
        writer.write(str.trim())
        writer.newLine()
    }

    writer.close()
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    try {
        val listDivided = mutableListOf<MutableList<String>>()
        val listIn = (File(inputName).readLines()).toMutableList()

        for (i in 0 until listIn.size) {
            if (listIn[i] != listIn[i].trim()) break
            if (i == listIn.size - 1) {
                for (j in 0 until listIn.size) {
                    writer.write(listIn[j])
                    if (j == listIn.size - 1) {
                        writer.close()
                        return
                    }
                    writer.newLine()
                }
            }
        }

        val listOfLengths = mutableListOf<Int>()
        var maxLength = 0
        var indexOfMaxString = 0
        var strNoSpace: Int

        for (str in listIn) {
            strNoSpace = str.replace(Regex("""\s+"""), "").length
            if (strNoSpace > maxLength) {
                maxLength = strNoSpace
                indexOfMaxString = listDivided.size
            }
            if (strNoSpace == 0) listDivided.add(mutableListOf(""))
            else listDivided.add(str.split(Regex("""\s+""")).toMutableList())
            listOfLengths.add(strNoSpace)
        }

        for (list in listDivided) {
            list.removeAt(0)
        }

        maxLength += listDivided[indexOfMaxString].size - 1
        var index = -1

        for (list in listDivided) {
            ++index
            if (listOfLengths[index] == 0) {
                writer.newLine()
                continue
            }
            if (list.size == 1) {
                writer.write(list[0])
                writer.newLine()
                continue
            }

            val spaceSize = (maxLength - listOfLengths[index]) / (list.size - 1)
            var remainderOfSpace = (maxLength - listOfLengths[index]) % (list.size - 1)
            var countSpaces = 0

            for (word in list) {
                writer.write(word)
                ++countSpaces
                if (countSpaces == list.size) break
                for (time in 1..spaceSize)
                    writer.write(" ")
                if (remainderOfSpace > 0) {
                    writer.write(" ")
                    --remainderOfSpace
                }
            }
            writer.newLine()
        }
    } catch (e: java.lang.IndexOutOfBoundsException) {
        writer.write("")
    } catch (e: NullPointerException) {
        writer.write("")
    } finally {
        writer.close()
    }
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {

    // * * * * * * *
    // Функции 1) поиска текущей спускающейся цифры и 2) количества цифр в числе

    val ten = 10   // Десять. Для функций

    fun findDigit(n: Int, amount: Int): Int = if (amount > 0)
        n % ten.toDouble().pow(amount).toInt() / ten.toDouble().pow(amount - 1).toInt() else 0

    fun countDigits(n: Int): Int {
        var numOfDigits = 0
        var num = n
        while (num > 0) {
            num /= 10
            ++numOfDigits
        }
        return if (n != 0) numOfDigits else 1
    }


    // * * * * * * *
    // Переменные и константы

    val writer = File(outputName).bufferedWriter()

    var space = 1                           // Пространство (начальное)
    var checkDigit = 0                      // Текущая спускающаяся цифра
    var dent = lhv                          // Текущее делимое
    var amount = 0                          // Количество цифр, оставшееся для прибавления

    val quotient = lhv / rhv                // Частное
    val remainder = lhv % rhv               // Конечный остаток от деления
    val numOfDigits = countDigits(dent)     // Количество цифр в изначальном делимом


    // * * * * * * *
    // Определяем начальные dent, amount & checkDigit

    for (i in 1..numOfDigits) {
        if (lhv / (ten.toDouble().pow(numOfDigits - i).toInt()) / rhv > 0) {
            dent = lhv / (ten.toDouble().pow(numOfDigits - i).toInt())
            amount = numOfDigits - i
            checkDigit = findDigit(lhv, amount)
            break
        }
    }

    if (amount < 0) {
        println("Amount < 0")
        writer.close()
        return
    }


    // * * * * * * *
    // Вывод первых трёх строк

    writer.write(" $lhv | $rhv")

    writer.newLine()
    writer.write("-${dent / rhv * rhv}")
    for (i in 1..amount) writer.write(" ")
    writer.write("   $quotient")

    writer.newLine()
    for (i in 1 until space) writer.write(" ")
    for (i in 1..countDigits(dent) + 1) writer.write("-")

    if (amount == 0) {
        writer.newLine()
        for (i in 1..space + countDigits(dent) - countDigits(remainder)) writer.write(" ")
        writer.write(remainder.toString())
        writer.close()
        return
    }


    // * * * * * * *
    // Вывод остальных разностей

    while (true) {

        writer.newLine()
        for (i in 1..space + countDigits(dent) - countDigits(dent % rhv)) writer.write(" ")
        writer.write((dent % rhv).toString())
        if (amount == 0) break
        writer.write(checkDigit.toString())

        val dentPrevious = dent
        dent = dent % rhv * ten + checkDigit
        --amount
        if (amount != 0) checkDigit = findDigit(lhv, amount)



        writer.newLine()

        var spaceTwo = space + countDigits(dentPrevious) - countDigits(dentPrevious % rhv) +
                countDigits(dent) - countDigits(dent / rhv * rhv) - 1
        if (dentPrevious % rhv == 0) ++spaceTwo

        for (i in 1..spaceTwo) writer.write(" ")
        writer.write("-")
        writer.write((dent / rhv * rhv).toString())



        writer.newLine()

        val spaceThree = if (countDigits(dent) > countDigits(dent / rhv * rhv) + 1)
            space + countDigits(dentPrevious) - countDigits(dentPrevious % rhv)
        else space + countDigits(dentPrevious) - countDigits(dent / rhv * rhv)

        for (i in 1..spaceThree) writer.write(" ")

        val line = space + countDigits(dentPrevious) + 1 - spaceThree
        for (i in 1..line) writer.write("-")

        space += countDigits(dentPrevious) - countDigits(dentPrevious % rhv)
        if (dentPrevious % rhv == 0) ++space
    }

    writer.close()
}