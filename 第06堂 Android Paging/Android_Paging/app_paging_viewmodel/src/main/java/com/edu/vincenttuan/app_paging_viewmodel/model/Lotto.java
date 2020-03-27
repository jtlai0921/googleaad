package com.edu.vincenttuan.app_paging_viewmodel.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "lotto")
public class Lotto {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String nums;

    public Lotto() {
    }

    public Lotto(String nums) {
        this.nums = nums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "id=" + id +
                ", nums='" + nums + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(id, lotto.id) &&
                Objects.equals(nums, lotto.nums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nums);
    }
}
