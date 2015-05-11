package items;

import java.awt.Image;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JFrame;

import start.MainWindow;

public class Item extends JFrame{
	ArrayList<String> keyFrameTranslation = new ArrayList<String>();
	ArrayList<String> keyFrameRotation = new ArrayList<String>();
	String m_posX = "", m_posY = "", m_width = "", m_height = "", m_rotation = "";
	int cachePosX, cachePosY, cacheWidth, cacheHeight, cacheRotation;
	int m_id;
	double m_ratio;
	String m_name;
	
	public int getPosXFromFormula () {
		String str = calculeVariable(m_posX);
		if (!str.equals("!")) {
			return (int) Double.parseDouble(str);
		} else 
			return 0;
	}
	
	public int getPosYFromFormula () {
		String str = calculeVariable(m_posY);
		if (!str.equals("!"))
			return (int) Double.parseDouble(str);
		else 
			return 0;
	}
	
	public int getWidthFromFormula () {
		String str = calculeVariable(m_width);
		if (!str.equals("!"))
			return (int) Double.parseDouble(str);
		else 
			return 0;
	}
	
	public int getHeightFromFormula () {
		String str = calculeVariable(m_height);
		if (!str.equals("!"))
			return (int) Double.parseDouble(str);
		else 
			return 0;
	}
	
	public int getWidth () {
		return cacheWidth;
	}
	
	public int getHeight () {
		return cacheHeight;
	}
	
	public int getPosX () {
		return cachePosX;
	}
	
	public int getPosY () {
		return cachePosY;
	}
	
	public int getRotation () {
		return cacheRotation;
	}
	
	public void setPosX (int i) {
		m_posX = i+"";
		cachePosX = i;
	}
	
	public void setPosY (int i) {
		m_posY = i+"";
		cachePosY = i;
	}
	
	public void setWidth (int i) {
		m_width = i+"";
		cacheWidth = i;
	}
	
	public void setHeight (int i) {
		m_height = i+"";
		cacheHeight = i;
	}
	
	public void setName (String name) {
		m_name = name;
	}
	
	public void cache () {
		try {
			cachePosX = (int) Double.parseDouble(calculeVariable(m_posX));
		} catch (NumberFormatException e) {
			cachePosX = 0;
		}
		try {
			cachePosY = (int) Double.parseDouble(calculeVariable(m_posY));
		} catch (NumberFormatException e) {
			cachePosY = 0;
		}
		try {
			cacheWidth = (int) Double.parseDouble(calculeVariable(m_width));
		} catch (NumberFormatException e) {
			cacheWidth = 0;
		}
		try {
			cacheHeight = (int) Double.parseDouble(calculeVariable(m_height));
		} catch (NumberFormatException e) {
			cacheHeight = 0;
		}
		try {
			cacheRotation = (int) Double.parseDouble(calculeVariable(m_rotation));
		} catch (NumberFormatException e) {
			cacheRotation = 0;
		}
	}
	
	public String getName () {
		return m_name;
	}
	
	public void setId (int i) {
		m_id = i;
	}
	
	public int getId () {
		return m_id;
	}
	
	public void setRotation (int i) {
		m_rotation = i+"";
		cacheRotation = i;
	}
	
	public int getRotationFormFormula () {
		String str = calculeVariable(m_rotation);
		if (!str.equals("!"))
			return (int) Double.parseDouble(str);
		else 
			return 0;
	}
	
	public double getRatio () {
		return m_ratio;
	}
	
	public String getPosXFormula () {
		return m_posX;
	}
	
	public String getPosYFormula () {
		return m_posY;
	}
	
	public String getWidthFormula () {
		return m_width;
	}
	
	public String getHeightFormula () {
		return m_height;
	}
	
	public String getRotationFormula () {
		return m_rotation;
	}
	
	public void setPosXFormula (String str) {
		m_posX = str;
		cache();
	}
	
	public void setPosYFormula (String str) {
		m_posY = str;
		cache();
	}
	
	public void setWidthFormula (String str) {
		m_width = str;
		cache();
	}
	
	public void setHeightFormula (String str) {
		m_height = str;
		cache();
	}
	
	public void setRotationFormula (String str) {
		m_rotation = str;
		cache();
	}
	
	public void addKeyFrameTranslate (int time,String x, String y, int type) {
		String str = "";
		if (type == 1) str = "t"+time+":"+x+","+y;
		else str = "T" + time + ":" + x + "," + y;
		System.out.println("keyframe added :" + str);
		
		int T = Integer.parseInt(str.substring(1, str.indexOf(':')));
		int finalIndex = 0;
		
		a:for (int index = 0; index < keyFrameTranslation.size();index++) {
			int testedTime = Integer.parseInt(keyFrameTranslation.get(index).substring(1, keyFrameTranslation.get(index).indexOf(':')));
			if (T < testedTime) {
				break a;
			} else {
				finalIndex += 1;
			}
		}
		keyFrameTranslation.add(finalIndex, str);
	}
	
	public String getKeyFrameTranslate (int i) {
		return keyFrameTranslation.get(i);
	}
	
	public String getLastKeyFrameTranslate (int i) {
		for (int index = 0; index < keyFrameTranslation.size(); index++) {
			String str = keyFrameTranslation.get(index);
			int testedTime = Integer.parseInt(str.substring(1,str.indexOf(':')));
			
			if (testedTime > i) {
				return keyFrameTranslation.get(index - 1);
			}
		}
		return keyFrameTranslation.get(keyFrameTranslation.size()-1);
	}
	
	public String getNextKeyFrameTranslate (int i) {
		for (int index = 0; index < keyFrameTranslation.size(); index++) {
			String str = keyFrameTranslation.get(index);
			int testedTime = Integer.parseInt(str.substring(1,str.indexOf(':')));
			
			if (testedTime > i) {
				return keyFrameTranslation.get(index);
			}
		}
		return keyFrameTranslation.get(keyFrameTranslation.size()-1);
	}
	
	public String[] getAllKeyFramesTranslation () {
		String[] str = new String[keyFrameTranslation.size()];
		for (int index = 0; index < keyFrameTranslation.size(); index++) {
			str[index] = keyFrameTranslation.get(index);
		}
		return str;
	}
	
	public void deleteKeyFrameTranslationAt (int time) {
		for (int index = 0; index < keyFrameTranslation.size();index++) {
			int testedTime = Integer.parseInt(keyFrameTranslation.get(index).substring(1, keyFrameTranslation.get(index).indexOf(':')));
			if (testedTime == time) 
				keyFrameTranslation.remove(index);
		}
	}
	
	public void addKeyFrameRotation (int time, String string) {
		String str = "r"+time+":"+string;
		System.out.println("keyframe added :" + str);
		
		int T = Integer.parseInt(str.substring(1, str.indexOf(':')));
		int finalIndex = 0;
		
		a:for (int index = 0; index < keyFrameRotation.size();index++) {
			int testedTime = Integer.parseInt(keyFrameRotation.get(index).substring(1, keyFrameRotation.get(index).indexOf(':')));
			if (T < testedTime) {
				break a;
			} else {
				finalIndex += 1;
			}
		}
		keyFrameRotation.add(finalIndex, str);
	}
	
	public String getLastKeyFrameRotation (int i) {
		for (int index = 0; index < keyFrameRotation.size(); index++) {
			String str = keyFrameRotation.get(index);
			int testedTime = Integer.parseInt(str.substring(1,str.indexOf(':')));
			
			if (testedTime > i) {
				return keyFrameRotation.get(index - 1);
			}
		}
		return keyFrameRotation.get(keyFrameRotation.size()-1);
	}
	
	public String getNextKeyFrameRotation (int i) {
		for (int index = 0; index < keyFrameRotation.size(); index++) {
			String str = keyFrameRotation.get(index);
			int testedTime = Integer.parseInt(str.substring(1,str.indexOf(':')));
			
			if (testedTime > i) {
				return keyFrameRotation.get(index);
			}
		}
		return keyFrameRotation.get(keyFrameRotation.size()-1);
	}
	
	public void deleteKeyFrameRotationAt (int time) {
		for (int index = 0; index < keyFrameRotation.size();index++) {
			int testedTime = Integer.parseInt(keyFrameRotation.get(index).substring(1, keyFrameRotation.get(index).indexOf(':')));
			if (testedTime == time) 
				keyFrameRotation.remove(index);
		}
	}
	
	public String[] getAllKeyFramesRotation () {
		String[] str = new String[keyFrameRotation.size()];
		for (int index = 0; index < keyFrameRotation.size(); index++) {
			str[index] = keyFrameRotation.get(index);
		}
		return str;
	}
	
	public String getKeyFrameRotation (int i) {
		return keyFrameRotation.get(i);
	}
	
	public String calculeVariable (String str) {
		System.out.println("calculevariable");
		if (str.isEmpty()) {
			return 0+"";
		} else {
			str = findAndChangeVariables(str);
			Object result;
			try {
				ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");
				result = engine.eval(str);
				return result.toString();
			} catch (ScriptException e) {
				return ("!");
			}
		}
	}
	
	public String calculeVariableNoChange (String str) {
		if (str.isEmpty()) {
			return " ";
		} else {
			str = findAndChangeVariables(str);
			return str;
		}
	}
	
	public String findAndChangeVariables (String str) {
		boolean b;
		System.out.println("findandchangevariable");
		do {
			b = false;
			if (str.indexOf("#time_frame") != -1) {
				str = str.replace("#time_frame", MainWindow.getTimeLine().getTime()+"");
				b = true;
			}
			if (str.indexOf("#camera_width") != -1) {
				str = str.replace("#camera_width", MainWindow.getCameraWidth()+"");
				b = true;
			}
			if (str.indexOf("#camera_height") != -1) {
				str = str.replace("#camera_height", MainWindow.getCameraHeight()+"");
				b = true;
			}
			if (str.indexOf("#me_width") != -1) {
				str = str.replace("#me_width", m_width+"");
				b = true;
			}
			if (str.indexOf("#me_height") != -1) {
				str = str.replace("#me_height", m_height+"");
				b = true;
			}
			if (str.indexOf("#item_width(") != -1) {
				try {
					String str1 = str.substring(str.indexOf("#item_width(") + 12, str.indexOf(')'));
					System.out.println("*-*-*-*-*"+str1);
					System.out.println("str:"+str+" : "+ str1 +" :: " + "#item_width(" + str1 + ")" + " ::: "+ str.indexOf("#item_width(" + str1 + ")"));
					str = str.replace("#item_width(" + str1 + ")", MainWindow.getItemByName(str1).getWidth()+"");
					System.out.println("str"+str+"::"+Thread.currentThread().getName());
					b = true;
				} catch (StringIndexOutOfBoundsException e) {
					break;
				}
			}
			if (str.endsWith(" ")) {
				str = str.substring(0, str.length() - 1);
				b = true;
			}
		} while (b);
		return str;
	}

}
