package com.vel.example;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import antlr.collections.List;

public class HelloVelocity {
	public static void main(String[] args){
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "E:/Users/Leo/Documents/GitHub/velocityCodeGen/resources");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		
		ve.init();
		
		Template t = ve.getTemplate("hellovelocity.vm");
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("name", "velocity");
		ctx.put("date", (new Date()).toString());
		
		ArrayList temp = new ArrayList();
		temp.add("1");
		temp.add("2");
		
		ctx.put("list", temp);
		
		StringWriter sw = new StringWriter();
		t.merge(ctx, sw);
		System.out.println(sw.toString());
	}
}
