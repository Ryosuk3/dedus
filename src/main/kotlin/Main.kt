
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.Beige500
import ui.Black200
import ui.LightBlue
import ui.Navy200
import kotlin.reflect.KClass

@Composable
@Preview
fun WordsToNums(onBack: () -> Unit) {
    var text by remember { mutableStateOf("Перевести в численный вид") }
    var resultText by  remember { mutableStateOf("Здесь будет полученное число\nВводите числа в формате: триста тридцать три") }
    val message = remember { mutableStateOf("") }
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(color = Navy200),
            contentAlignment = Alignment.Center

        ) {
            Button(
                onClick = onBack, modifier = Modifier.align(Alignment.TopEnd).size(45.dp),
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Black200
                )
            ) {
                Text(
                    "x",
                    modifier = Modifier.fillMaxSize(),
                    color = LightBlue,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        resultText,
                        color = LightBlue,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(25.dp)
                    )
                }
                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Beige500),
                        value = message.value,
                        textStyle = TextStyle(fontSize = 25.sp),
                        singleLine = true,

                        onValueChange = { newText ->
                            message.value = newText
                        })
                }
                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {

                            resultText=wordsToNumber(message.value).toString()
                        },
                        modifier = Modifier.padding(top = 20.dp),

                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Black200
                        ),

                        ) {
                        Text(
                            text,
                            color = LightBlue,
                        )
                    }
                }

            }


        }

    }
}

@Composable
@Preview
fun ReplaceWords(onBack: () -> Unit) {
    var text by remember { mutableStateOf("Поместить слова из диапазона в конец") }
    var resultText by remember { mutableStateOf("Здесь будет полученное предложение") }
    val dstart = remember { mutableStateOf("") }
    val dend = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(color = Navy200),
            contentAlignment = Alignment.Center
        ){
            Button(
                onClick = onBack, modifier = Modifier.align(Alignment.TopEnd).size(45.dp),
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Black200
                )
            ) {
                Text(
                    "x",
                    color = LightBlue,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        resultText,
                        color = LightBlue,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(25.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Beige500),
                        value = message.value,
                        textStyle = TextStyle(fontSize = 25.sp),
                        singleLine = true,

                        onValueChange = { newText ->
                            message.value = newText
                        })
                }
                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.size(width = 100.dp, height = 50.dp),
                        value = dstart.value,
                        textStyle = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center),
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Beige500),
                        singleLine = true,
                        onValueChange = { newText ->
                            dstart.value = newText
                        })
                    TextField(
                        modifier = Modifier.padding(start=30.dp).size(width = 100.dp, height = 50.dp),
                        value = dend.value,
                        textStyle = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center),
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Beige500),
                        singleLine = true,
                        onValueChange = { newText ->
                            dend.value = newText
                        })
                }
                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            if (dstart.value!="" && dend.value!=""){
                                resultText=moveWordsToEnd(message.value,dstart.value.toInt(),dend.value.toInt())
                            }
                            else{
                                resultText="Ошибка: диапазон не введен или введен не полностью"
                            }
                        },
                        modifier = Modifier.padding(top = 20.dp),

                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Black200
                        ),

                        ) {
                        Text(
                            text,
                            color = LightBlue,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf("App") } // Управление экраном

    when (currentScreen) {
        "App" -> MainScreen(
            onWordsToNumsClick = { currentScreen = "WordsToNums" },
            onReplaceWordsClick = { currentScreen = "ReplaceWords" }
        )
        "WordsToNums" -> WordsToNums(onBack = { currentScreen = "App" })
        "ReplaceWords" -> ReplaceWords(onBack = { currentScreen = "App" })
    }
}

@Composable
fun MainScreen(onWordsToNumsClick: () -> Unit, onReplaceWordsClick: () -> Unit) {
    var wordsToNumsButtonText by remember { mutableStateOf("Программа перевода чисел из слов в числовой вид") }
    var replaceWordsButtonText by remember { mutableStateOf("Программа замены положения слов") }
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(color = Navy200),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onWordsToNumsClick, // Переход на WordsToNums
                    modifier = Modifier.padding(top = 20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Black200)
                ) {
                    Text(
                        wordsToNumsButtonText,
                        color = LightBlue,
                    )
                }

                Button(
                    onClick = onReplaceWordsClick, // Переход на ReplaceWords
                    modifier = Modifier.padding(top = 20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Black200)
                ) {
                    Text(
                        replaceWordsButtonText,
                        color = LightBlue,
                    )
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

sealed class NumberFormat(val value: Int) {
    class Units(value: Int) : NumberFormat(value)
    class Tenss(value: Int) : NumberFormat(value)
    class Tens(value: Int): NumberFormat(value)
    class Hundreds(value: Int) : NumberFormat(value)
    class Thousands(value: Int) : NumberFormat(value)
}

fun wordsToNumber(input: String): String {
    val numberWords = mapOf(
        "ноль" to NumberFormat.Units(0), "один" to NumberFormat.Units(1), "два" to NumberFormat.Units(2),
        "три" to NumberFormat.Units(3), "четыре" to NumberFormat.Units(4), "пять" to NumberFormat.Units(5),
        "шесть" to NumberFormat.Units(6), "семь" to NumberFormat.Units(7), "восемь" to NumberFormat.Units(8),
        "девять" to NumberFormat.Units(9), "десять" to NumberFormat.Tens(10),
        "одиннадцать" to NumberFormat.Tenss(11), "двенадцать" to NumberFormat.Tenss(12), "тринадцать" to NumberFormat.Tenss(13),
        "четырнадцать" to NumberFormat.Tenss(14), "пятнадцать" to NumberFormat.Tenss(15), "шестнадцать" to NumberFormat.Tenss(16),
        "семнадцать" to NumberFormat.Tenss(17), "восемнадцать" to NumberFormat.Tenss(18), "девятнадцать" to NumberFormat.Tenss(19),
        "двадцать" to NumberFormat.Tens(20), "тридцать" to NumberFormat.Tens(30), "сорок" to NumberFormat.Tens(40),
        "пятьдесят" to NumberFormat.Tens(50), "шестьдесят" to NumberFormat.Tens(60), "семьдесят" to NumberFormat.Tens(70),
        "восемьдесят" to NumberFormat.Tens(80), "девяносто" to NumberFormat.Tens(90), "сто" to NumberFormat.Hundreds(100),
        "двести" to NumberFormat.Hundreds(200), "триста" to NumberFormat.Hundreds(300), "четыреста" to NumberFormat.Hundreds(400),
        "пятьсот" to NumberFormat.Hundreds(500), "шестьсот" to NumberFormat.Hundreds(600), "семьсот" to NumberFormat.Hundreds(700),
        "восемьсот" to NumberFormat.Hundreds(800), "девятьсот" to NumberFormat.Hundreds(900), "тысяча" to NumberFormat.Thousands(1000)
    )

    val cleanInput = input.replace(Regex("\\s+"), " ").trim().lowercase()
    val words = cleanInput.split(" ")
    var result = 0
    var current = 0

    var lastType: KClass<out NumberFormat>? = null
    fun RealType(lastType: KClass<out NumberFormat>?): String{
        when (lastType?.simpleName) {
            "Units" -> return "единичного формата"
            "Tens" -> return "десяткового формата"
            "Tenss" -> return "формата 10-19"
            "Hundreds" -> return "сотенного формата"
            "Thousands" -> return "тысячного формата"
        }
        return ""
    }
    for ((index, word) in words.withIndex()) {
        val numberFormat = numberWords[word] ?: return "Ошибка: слово '$word' не распознано как часть числа."

        when (numberFormat) {
            is NumberFormat.Thousands -> {
                if (lastType != null && lastType != NumberFormat.Hundreds::class) {
                    return "Ошибка: числа тысячного формата не могут идти после чисел ${RealType(lastType)}!"
                    //return "Ошибка: в слове '$word' по индексу $index, числа тысячного формата не могут следовать после ${lastType.simpleName}."
                }
                current *= numberFormat.value
                result += current
                current = 0
            }
            is NumberFormat.Hundreds -> {
                if (lastType == NumberFormat.Tens::class || lastType == NumberFormat.Units::class || lastType == NumberFormat.Tenss::class) {
                    return "Ошибка: числа сотенного формата не могут идти после чисел ${RealType(lastType)}"
                    //return "Ошибка: в слове '$word' по индексу $index, числа сотенного формата не могут следовать после ${lastType.simpleName}."
                }
                current += numberFormat.value
            }
            is NumberFormat.Tens -> {
                if (lastType == NumberFormat.Units::class || lastType == NumberFormat.Tenss::class) {
                    return "Ошиьбка: числа десяткового формата не могут идти после чисел ${RealType(lastType)}"
                    //return "Ошибка: в слове '$word' по индексу $index, числа десяткового формата не могут следовать после единичного формата (слово '${words[index - 1]}', индекс ${index - 1})."
                }
                current += numberFormat.value
            }
            is NumberFormat.Tenss -> {
                if (lastType==NumberFormat.Units::class || lastType == NumberFormat.Tens::class || lastType==NumberFormat.Tenss::class){
                    return "Ошибка: числа формата 10-19 не могут идти после чисел ${RealType(lastType)}"
                    //return "Ошибка: в слове '$word' по индексу $index, числа сотенного формата не могут следовать после ${lastType.simpleName}."
                }
                current+=numberFormat.value
            }
            is NumberFormat.Units -> {
                if (lastType==NumberFormat.Units::class || lastType == NumberFormat.Tenss::class || lastType==NumberFormat.Tens::class) {
                    return "Ошибка: числа единичного формата не могут идти после чисел ${RealType(lastType)}"
                    //return "Ошибка: в слове '$word' по индексу $index, числа единичного формата не могут следовать после ${lastType.simpleName} (слово '${words[index - 1]}', индекс ${index - 1})."
                }
                /*else if (lastType==NumberFormat.Tenss::class){
                    return "Ошибка: в слове '$word' по индексу $index, числа единичного формата не могут следовать после чисел типа 10-19 (слово '${words[index - 1]}', индекс ${index - 1})."
                }*/
                current += numberFormat.value
            }
        }
        lastType = numberFormat::class
    }

    result += current
    return result.toString()
}



fun moveWordsToEnd(input: String, startIndex: Int, endIndex: Int): String {
    val words = input.split(" ").toMutableList()

    if (words.isEmpty()) {
        return "Ошибка: Строка пуста"
    }

    if (startIndex < 1) {
        return "Ошибка: Номер начального слова не может быть меньше 1"
    }

    if (endIndex > words.size) {
        return "Ошибка: Номер последнего слова больше количества слов в строке"
    }

    if (startIndex > endIndex) {
        return "Ошибка: Начальный индекс не может быть больше конечного"
    }

    val wordsToMove = words.subList(startIndex - 1, endIndex).toList()

    words.subList(startIndex - 1, endIndex).clear()

    words.addAll(wordsToMove)

    return words.joinToString(" ")
}