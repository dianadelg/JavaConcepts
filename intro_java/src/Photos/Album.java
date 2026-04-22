package Photos;

import java.util.ArrayList;
import java.util.List;

public class Album {
	//name, list of photos, pick a photo we want based off of a photo name
	//search by tags
	private String name;
	private List<Photo> photos = new ArrayList<>();
	
	public Album (String name) {
		this.name = name;
		//wouldn't pass in photos --> we can have an album created without photos
	}
	
	public Album (String name, Photo photo) {
		this.name = name;
		this.photos.add(photo);
		//but we are saying sometimes you already have a photo and that works too
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public void addPhoto(Photo p) {
		if(this.photos.contains(p)) {
			System.out.println("Duplicate. Photo is already in album");
			return;
		}
		this.photos.add(p);
		System.out.println("Successfully added photo");
	}
	
	public Photo getPhotoByNameTag(String name, String tag) {
		//get the photo by name of photo and tag
		for(Photo p:this.getPhotos()) {
			if(p.getName().equals(name) && p.getTags().contains(tag)) {
				return p;
			}
		}
		return null; //if not found
	}
	
	@Override
	public String toString() {
		String albumContents="";
		System.out.println(this.name + " contents");
		for(Photo p : this.photos) {
			albumContents+= p.toString() + "  ";
		}
		return albumContents;
	}
	
	
	
}
