package game.view;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.imageio.ImageIO;

import game.utility.Protocol;


public class AllAnimations {

	
	private HashMap<String, CharacterAnimation> animationsP1; //animazioni player 1
	private HashMap<String, CharacterAnimation> animationsP2; //animazioni player 2
	private CharacterAnimation currentImgP1;
	private CharacterAnimation currentImgP2;
	
	public AllAnimations() {
		animationsP1 = new HashMap<String, CharacterAnimation>();
		animationsP1.put(Protocol.STOP, new CharacterAnimation(getResourcesP1("stop")));
		animationsP1.put(Protocol.FALL, new CharacterAnimation(getResourcesP1("fall")));
		animationsP1.put(Protocol.HIT, new CharacterAnimation(getResourcesP1("hit")));
		animationsP1.put(Protocol.WALK, new CharacterAnimation(getResourcesP1("walk")));
		animationsP1.put(Protocol.BACK, new CharacterAnimation(getResourcesP1("walk")));
		animationsP1.put(Protocol.SPECIAL, new CharacterAnimation(getResourcesP1("special")));
		animationsP1.put(Protocol.HURT, new CharacterAnimation(getResourcesP1("hurt")));
		
		currentImgP1 = animationsP1.get(Protocol.STOP);
		
		animationsP2 = new HashMap<String, CharacterAnimation>();
		animationsP2.put(Protocol.STOP, new CharacterAnimation(getResourcesP2("stop")));
		animationsP2.put(Protocol.FALL, new CharacterAnimation(getResourcesP2("fall")));
		animationsP2.put(Protocol.HIT, new CharacterAnimation(getResourcesP2("hit")));
		animationsP2.put(Protocol.WALK, new CharacterAnimation(getResourcesP2("walk")));	
		animationsP2.put(Protocol.BACK, new CharacterAnimation(getResourcesP2("walk")));
		animationsP2.put(Protocol.SPECIAL, new CharacterAnimation(getResourcesP2("special")));
		animationsP2.put(Protocol.HURT, new CharacterAnimation(getResourcesP2("hurt")));
		
		currentImgP2 = animationsP2.get(Protocol.STOP);
	}
	
	Image getCurrentImageP1() {
		return currentImgP1.getCurrentImage();
	}
	
	Image getCurrentImageP2() {
		return currentImgP2.getCurrentImage();
	}
	
	void changeAnimationP1(String type) {
		if(!animationsP1.containsKey(type) || currentImgP1 != animationsP1.get(Protocol.STOP)) 
			return;
		currentImgP1 = animationsP1.get(type);
	}
	
	void changeAnimationP2(String type) {
		if(!animationsP2.containsKey(type) || currentImgP2 != animationsP2.get(Protocol.STOP))
			return;
		currentImgP2 = animationsP2.get(type);
	}
	
	void updateP1() {
		if (currentImgP1 == null)
			return;
		if (!currentImgP1.update()) {
			currentImgP1 = animationsP1.get(Protocol.STOP);
		}
	}
	
	void updateP2() {
		if (currentImgP2 == null)
			return;
		if (!currentImgP2.update()) {
			currentImgP2 = animationsP2.get(Protocol.STOP);
		}
	}
	
	private ArrayList<Image> getResourcesP1(String name) {
		ArrayList<Image> images = new ArrayList<Image>();
		try {
			String path = "/game/resources/" + name + "P1" + "/";
			File f = new File(getClass().getResource(path).getPath());
			ArrayList<File> list = new ArrayList<File>();
			for (File r : f.listFiles()) {
				list.add(r);				
			}

			Collections.sort(list, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (File img : list) {				
				images.add(ImageIO.read(getClass().getResourceAsStream(path + img.getName())));
			}
		} catch (Exception e) {
			System.err.println("Error while loading resources");
		}
		return images;
	}
	
	private ArrayList<Image> getResourcesP2(String name) {
		ArrayList<Image> images = new ArrayList<Image>();
		try {
			String path = "/game/resources/" + name + "P2" + "/";
			File f = new File(getClass().getResource(path).getPath());
			ArrayList<File> list = new ArrayList<File>();
			for (File r : f.listFiles()) {
				list.add(r);				
			}

			Collections.sort(list, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (File img : list) {			
				images.add(ImageIO.read(getClass().getResourceAsStream(path + img.getName())));
			}
		} catch (Exception e) {
			System.err.println("Error while loading resources");
		}
		return images;
	}
}
