package Photos;

import java.util.Date;

public class photoDriver {
	public static void main (String[] args) {
		Photo ph1 = new Photo("Photo 1", "Selfie of me", new Date());
		Photo ph2 = new Photo("Photo 2", "Kyle's car", new Date());
		ph2.addTag("q60");
		ph2.addTag("guyswholikecars");
		System.out.println(ph1);
		System.out.println(ph2);
		Album al1 = new Album ("Diana's Photos");
		al1.addPhoto(ph1);
		al1.addPhoto(ph2);
		System.out.println(al1);
		//try to add duplicate
		al1.addPhoto(ph1); 
	}
}
