package com.project.tools;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Tools {

	public static String GetJson(List<String> list) {
		Gson gson = new Gson();
		JsonObject js = new JsonObject();
		//js.addProperty("info", gson.toJson(list, List.class));
		return gson.toJson(js);
	}
	
//	public static void main(String[] args) {
//		List<String> resultList = new ArrayList<>();
//		
//		resultList.add("1");
//		resultList.add("2");
//		resultList.add("3");
//		resultList.add("4");
//		resultList.add("5");
//		resultList.add("6");
//		resultList.add("7");
//		
//		System.out.println(GetJson(resultList));
//	}
	
	public static <OverallDistribution> List<Object> toObject(List<OverallDistribution> list){
		List<Object> objlist = new ArrayList<Object>();
		for(Object e : list){
			Object obj = (Object)e;
			objlist.add(obj);
		}
		return objlist;
	}

	
}
