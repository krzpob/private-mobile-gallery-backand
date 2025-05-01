package pl.com.javasoft.mobile_gallery.infrastructure.storage;

public interface PhotoStorageService {

    public String storage(String nameOfPhoto, byte[] image);
}
