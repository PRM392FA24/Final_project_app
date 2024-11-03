package com.example.prm_groupproject_shop.DTOs;

public class APIResponse<T> {
    private boolean _isIgnoreNullData;
    private T _data;
    private String message;
    private T data;

    // Getters and Setters
    public boolean is_isIgnoreNullData() {
        return _isIgnoreNullData;
    }

    public void set_isIgnoreNullData(boolean _isIgnoreNullData) {
        this._isIgnoreNullData = _isIgnoreNullData;
    }

    public T get_data() {
        return _data;
    }

    public void set_data(T _data) {
        this._data = _data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
