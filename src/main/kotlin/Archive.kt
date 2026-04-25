class Archive (val name:String) : Screenable {
    var notesList: ArrayList<Note> = ArrayList()
    override fun getObjName(): String {
        return name
    }

    override fun getChildList(): ArrayList<Note> {
        return notesList
    }

}