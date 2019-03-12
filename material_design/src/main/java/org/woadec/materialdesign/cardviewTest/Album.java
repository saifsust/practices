package org.woadec.materialdesign.cardviewTest;

public class Album {

    private String name;
    private int numOfSongs;
    private int thumnail;

    public Album(String name, int numOfSongs, int thumnail) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumnail = thumnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumnail() {
        return thumnail;
    }

    public void setThumnail(int thumnail) {
        this.thumnail = thumnail;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", numOfSongs=" + numOfSongs +
                ", thumnail=" + thumnail +
                '}';
    }
}
