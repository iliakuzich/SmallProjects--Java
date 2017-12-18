/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.objects;

import java.util.Objects;
import ru.habrahabr.arlidor.utils.FileUtils;

/**
 *
 * @author Tony
 */
public class MP3 {

    private String name;
    private String path;

    public MP3(String name, String path) {
        this.name = name;
        this.path = path;
    }

    @Override
    // для корректного отображения объекта MP3 при добавлении в плейлист
    public String toString() {
        return FileUtils.getFileNameWithoutExtension(name);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.path);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MP3 other = (MP3) obj;
        return Objects.equals(this.path, other.path);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
