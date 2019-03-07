package org.woadec.authentication.smsreader;

public class SMS {
    private String _id;
    private String _address;
    private String _msg;
    private String _readState; //"0" for have not read sms and "1" for have read sms
    private String _time;
    private String _folderName;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        this._address = address;
    }

    public String getMsg() {
        return _msg;
    }

    public void setMsg(String msg) {
        this._msg = msg;
    }

    public String getReadState() {
        return _readState;
    }

    public void setReadState(String readState) {
        this._readState = readState;
    }

    public String getTime() {
        return _time;
    }

    public void setTime(String time) {
        this._time = time;
    }

    public String getFolderName() {
        return _folderName;
    }

    public void setFolderName(String folderName) {
        this._folderName = folderName;
    }
}
