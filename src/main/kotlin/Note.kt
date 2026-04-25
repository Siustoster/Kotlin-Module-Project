data class Note(val name : String, var text : String) : Screenable {
    override fun getObjName():String {
        return name
    }

    override fun getChildList(): ArrayList<Note> {
        return arrayListOf(this)
    }
}