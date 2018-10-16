package com.startandroid.biimka.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String name;
    private int age;
    private long id;

    Student(String name, int age, long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getId() {
        return id;
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    private Student(Parcel parcel) {
        name = parcel.readString();
        age = parcel.readInt();
        id = parcel.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeLong(id);
    }
}
