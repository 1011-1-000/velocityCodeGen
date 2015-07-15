package com.generate.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.parser.util.EntityParser;

public class CodeGenerate {		 
	 public static void main(String[] args) {
		 VelocityEngine ve = new VelocityEngine();
		 ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "E:/Users/Leo/Documents/GitHub/velocityCodeGen/resources");
		 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		 EntityParser ep = new EntityParser();
		 
		 ve.init();
		 Template actionTpt = ve.getTemplate("action.vm");
		 Template daoTpt = ve.getTemplate("dao.vm");
		 VelocityContext ctx = new VelocityContext();
		 
		 //遍历文件夹下的所有实体类
		 String directoryPath = "E:/Users/Leo/Documents/GitHub/velocityCodeGen/src/com/vel/noparse";
		 File directory = new File(directoryPath);
		 File[] files = directory.listFiles();
		 for(File file:files){
			 
			 String filePath = directoryPath+File.separator+file.getName();
			 ep.parse(filePath);
			 
			 ctx.put("classNameLowCase", ep.getVarEntityClassName());
			 ctx.put("classNameUpCase", ep.getEntityClassName());
			 
			 Map<String,String> attrs = new HashMap<String,String>();
			 String[] names = ep.getProNames();
			 for(String name:names){
				 attrs.put(name, ep.getMethodReturnType(name));
			 }
			 ctx.put("attrs", attrs);
			 
			 String actionPath = "E:/Users/Leo/Documents/GitHub/velocityCodeGen/src/com/generate/action/"+ep.getEntityClassName()+"Action.java";
			 String daoPath = "E:/Users/Leo/Documents/GitHub/velocityCodeGen/src/com/generate/dao/"+ep.getEntityClassName()+"Dao.java";
			 merge(actionTpt,ctx,actionPath);
			 merge(daoTpt,ctx,daoPath);
			 
			 System.out.println(ep.getVarEntityClassName()+"已完成");
		 }
	 }

	 private static void merge(Template template, VelocityContext ctx, String path) {
		 PrintWriter writer = null;
		 try {
			 writer = new PrintWriter(path);
			 template.merge(ctx, writer);
			 writer.flush();
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 } finally 
		 {
			 writer.close();
		 }
	 }
}
