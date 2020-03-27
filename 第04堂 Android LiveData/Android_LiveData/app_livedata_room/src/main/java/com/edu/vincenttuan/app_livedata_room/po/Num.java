package com.edu.vincenttuan.app_livedata_room.po;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "num")
public class Num {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "n1")
    private Integer n1;

    public Num() {
    }

    public Num(Integer n1) {
        this.n1 = n1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getN1() {
        return n1;
    }

    public void setN1(Integer n1) {
        this.n1 = n1;
    }

    @Override
    public String toString() {
        return "Num{" +
                "id=" + id +
                ", n1=" + n1 +
                '}' + "\n";
    }
}
