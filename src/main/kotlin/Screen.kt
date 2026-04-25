import java.util.Scanner

class Screen(val archives: ArrayList<Archive>) {


    fun <T : Screenable> showMenu(
        screenName: String,
        objectList: ArrayList<T>,
        parentNum: Int?
    ): Boolean {
        var command = ""
        var cont = true
        val objectCreated = if (screenName == "Архивы") {
            "архив"
        } else {
            "заметку"
        }
        println("Меню $screenName")
        println("Доступные команды:")
        val commandList = mutableListOf(
            MenuItem("Создать $objectCreated") { showCreationMenu(screenName, parentNum) },
            MenuItem("Выход") {
                if (screenName == "Архивы")
                    cont = false
            })
        if (objectList.isNotEmpty()) {
            for (i in 0 until objectList.size) {
                if (objectList[i] is Archive)
                    commandList.add(MenuItem(objectList[i].getObjName()) {
                        showMenu(
                            "архива ${objectList[i].getObjName()}",
                            objectList[i].getChildList(),
                            i
                        )
                    })
                if (objectList[i] is Note) {
                    commandList.add(MenuItem(objectList[i].getObjName()) {
                        showNoteMenu(
                            objectList[i] as Note,
                            parentNum
                        )
                    })
                }
            }
        }
        for (i in commandList.indices)
            println("$i. - ${commandList[i].title}")
        println("Введите команду:")
        command = Scanner(System.`in`).nextLine()
        when {
            command.toIntOrNull() != null && command.toInt() in 0 until commandList.size -> {
                commandList[command.toInt()].action()
            }

            else -> {
                println("Неверная команда")
                showMenu(screenName, objectList, parentNum)
            }
        }
        return cont
    }

    fun showCreationMenu(screenName: String, parentNum: Int?) {
        var creationObjectType: String = ""
        var creationObjectText: String = ""
        var creationObjectName: String = ""
        creationObjectType = if (screenName == "Архивы")
            "архива"
        else "заметки"
        println("Меню создания $creationObjectType")
        println("Введите название $creationObjectType")
        creationObjectName = Scanner(System.`in`).nextLine()
        if (creationObjectName.isNotBlank())
            if (screenName == "Архивы") {
                archives.add(Archive(creationObjectName))
                showMenu("Архивы", archives, null)
            } else {
                println("Введите текст заметки: ")
                creationObjectText = Scanner(System.`in`).nextLine()
                if (creationObjectText.isNotEmpty())
                    if (parentNum != null) {
                        archives[parentNum].notesList.add(
                            Note(
                                creationObjectName,
                                creationObjectText
                            )
                        )
                        showMenu(
                            " архива ${archives[parentNum].name}",
                            archives[parentNum].getChildList(),
                            parentNum
                        )
                    } else {
                    }
                else {
                    println("Текст $creationObjectType не может быть пустым")
                    showCreationMenu(screenName, parentNum)
                }
            }
        else {
            println("Название $creationObjectType не может быть пустым")
            showCreationMenu(screenName, parentNum)
        }
    }

    fun showNoteMenu(note: Note, parentNum: Int?) {
        println("Заметка ${note.name}")
        println("Текст заметки: ${note.text}")
        println("Введите любой символ для возврата в предыдущее меню:")
        val command = Scanner(System.`in`).nextLine()
        if (parentNum != null)
            showMenu(
                " архива ${archives[parentNum].name}",
                archives[parentNum].notesList,
                parentNum
            )
    }


}