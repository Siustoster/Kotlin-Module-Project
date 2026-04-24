import java.util.Scanner
class NotesApp {
    var archiveList: ArrayList<Archive> = ArrayList()
    fun start() {
        println("Добро пожаловать в приложение \"Заметки\"")
        showMainScreen(archiveList)

    }

    fun  showMainScreen(archives: ArrayList<Archive>) {
        var cont = true
        val screen = Screen(archives)
        while (cont) {
            cont = screen.showMenu("Архивы", archiveList,null)

        }
    }
}