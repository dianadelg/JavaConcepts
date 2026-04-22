package Photos;
import java.util.*;
public class Photo {
	//name, caption, date, List of tags
	//for beans, make fields private --> if class is not intended to be static, make the fields private
	private String name;
	private String caption;
	private Date date;
	private List<String> tags = new ArrayList<>();
	
	public Photo (String name, String caption, Date date) {
		this.setName(name);
		this.setCaption(caption);
		this.setDate(date);
		//do not add name validation in here. Think of it as you're the single photo and that's all you know
		//you don't know other photo names
		//the album does! so add validation in there
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getTags() {
		//this is to obtain the tags
		return tags;
	}

	public void setTags(List<String> tags) {
		//would use this when we have a photo object, and instead of doing .add .add etc,
		//create the tag array list already and just do photo.setTags(arrList)
		this.tags = tags;
	}

	public void addTag (String tag) {
		//add a single tag to the tag array list
		this.tags.add(tag);
	}
	
	@Override
	public String toString() {
		//to string should return strings
		String tagsString = "";
		for(String tag : this.tags) {
			tagsString += "#"+tag + " ";
		}
		return "Photo name: " + this.name + " caption: " + this.caption + " date: " + this.date + " tags: " +tagsString;
		
	}
	
	//would not look for tag and return photo in here. That doesn't make sense because it's photo object
	//would do it in album object
	
}
