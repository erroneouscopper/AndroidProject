package team3.phms;

public class NotesList {

    private int _id;
    private String _name;
    private String _note;

    public NotesList(String name) {
        this._name = name;
    }
    public void set_note(String note) {
        this._note = note;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_note() {
        return _note;
    }

}
