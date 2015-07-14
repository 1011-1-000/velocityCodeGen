package com.vel.noparse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class StudentGenerate {		 
	 public static void main(String[] args) {
	 VelocityEngine ve = new VelocityEngine();
	 ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "E:/Users/Leo/Documents/GitHub/velocityCodeGen/resources");
	 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	 
	 ve.init();
	 Template actionTpt = ve.getTemplate("action.vm");
	 Template daoTpt = ve.getTemplate("dao.vm");
	 
	 VelocityContext ctx = new VelocityContext();
	 
	 ctx.put("classNameLowCase", "teacher");
	 ctx.put("classNameUpCase", "Teacher");
	 
	 String[][] attrs = {
	 {"Integer","id"},
	 {"String","name"},
	 {"String","serializeNo"},
	 {"String","titile"},
	 {"String","subject"}
	 };
	 ctx.put("attrs", attrs);
	 
	 String actionRootPath = System.getProperty("user.dir")+"/src/com/generate/action/TeacherAction.java";
	 String daoRootPath = System.getProperty("user.dir")+"/src/com/generate/dao/TeacherDao.java";
	 merge(actionTpt,ctx,actionRootPath);
	 merge(daoTpt,ctx,daoRootPath);
	 
	 System.out.println("success...");
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
