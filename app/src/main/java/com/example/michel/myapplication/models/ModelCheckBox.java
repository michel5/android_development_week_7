package com.example.michel.myapplication.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "listCheckBox")
public class ModelCheckBox {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "actiefCheckBox")
    private boolean actiefCheckbox;

    public ModelCheckBox(String text, boolean actiefCheckbox) {
        this.text = text;
        this.actiefCheckbox = actiefCheckbox;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isActiefCheckbox() {
        return actiefCheckbox;
    }

    public void setActiefCheckbox(boolean actiefCheckbox) {
        this.actiefCheckbox = actiefCheckbox;
    }
}
