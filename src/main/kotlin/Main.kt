fun main() {
    // Массив символов русского алфавита и их соответствующие номера
    val alphabet = listOf(
        'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О',
        'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ', 'Э', 'Ю', 'Я'
    )
    val alphabetNumbers = listOf(
        21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5, 30,
        27, 8, 18, 10, 33, 31, 32, 19, 7, 17
    )

    // Создаем карту, связывающую символы алфавита с их номерами
    val alphabetMap = alphabet.zip(alphabetNumbers).toMap()
    val reverseAlphabetMap = alphabetNumbers.zip(alphabet).toMap()

    // Функция для получения индекса символа в алфавите
    fun getShiftedChar(char: Char, shift: Int, encode: Boolean = true): Char {
        val upperChar = char.uppercaseChar()
        val charNumber = alphabetMap[upperChar] ?: return char

        // Рассчитываем сдвиг (закольцовывание)
        val newNumber = if (encode) {
            (charNumber + shift - 1) % 33 + 1
        } else {
            (charNumber - shift - 1 + 33) % 33 + 1
        }

        return reverseAlphabetMap[newNumber] ?: char
    }

    // Запрашиваем режим: шифрование или дешифрование
    print("Выберите режим: зашифровать (1) или расшифровать (2): ")
    val mode = readLine()?.toIntOrNull()

    // Запрашиваем исходное сообщение и ключевое слово
    print("Введите сообщение: ")
    val inputText = readLine() ?: return

    print("Введите ключевое слово: ")
    val key = readLine()?.uppercase() ?: return

    // Шифрование или дешифрование
    val result = StringBuilder()
    for (i in inputText.indices) {
        val currentChar = inputText[i]

        // Если текущий символ не буква, просто добавляем его без изменений
        if (!currentChar.isLetter()) {
            result.append(currentChar)
            continue
        }

        // Вычисляем сдвиг на основе ключевого слова
        val keyChar = key[i % key.length]
        val shift = alphabetMap[keyChar] ?: 0

        // Выполняем шифрование или дешифрование
        val shiftedChar = if (mode == 1) {
            getShiftedChar(currentChar, shift, encode = true)
        } else {
            getShiftedChar(currentChar, shift, encode = false)
        }

        // Добавляем символ в результат
        result.append(if (currentChar.isLowerCase()) shiftedChar.lowercaseChar() else shiftedChar)
    }

    // Вывод результата
    println("Результат: $result")
}
