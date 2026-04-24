import java.util.Scanner
class NotesApp {
    var arhiveList: ArrayList<Archive> = ArrayList()
    fun start() {
        println("Добро пожаловать в приложение \"Заметки\"")
        showMainScreen(arhiveList)

    }

    fun  showMainScreen(archives: ArrayList<Archive>) {
        var command = "";
        var commandList= mutableMapOf("Создать архив" to "Создать архив","Выход" to "Выход")
        while (true) {
            println("Меню \"Архивы\"")
            println("Доступные команды:")

            if (!archives.isEmpty()) {
               for(i in 0 until archives.size) {
                   commandList.put((i+1).toString(),archives[i].name)
               }
            }
            for(key in commandList.keys) {
                println("\"$key\" -  ${commandList[key]}")
            }
            println("Введите команду:")
            command = Scanner(System.`in`).nextLine()
            if(!commandList.contains(command))
                println("Такой комманды нет")
        }
    }
}