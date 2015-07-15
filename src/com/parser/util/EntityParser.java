package com.parser.util;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.AnnotationExpr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import xjavadoc.SourceClass;
import xjavadoc.XClass;
import xjavadoc.XJavaDoc;
import xjavadoc.XMethod;
import xjavadoc.XTag;
import xjavadoc.XTagFactory;
import xjavadoc.filesystem.XJavadocFile;

/**
 * pojo解析器。
 * 现在换用基于xJavaDoc了。
 * 
 * 需要根据POJO生成代码的话，现在设置的依据来源于这样几个方面：
 * 1、POJO的源代码本身
 * 2、相应的配置文件
 * 
 * 在代码生成的时候，有时需要多个实体参与，甚至可能还有非实体的类。
 * 
 *
 */
public class EntityParser {
	XClass xClass;
	Properties properties = new Properties();
	private	CompilationUnit cu;
	public EntityParser() {
	}
	public EntityParser(XClass xClass) {
		this.xClass = xClass;
	}
	public XClass parse(String srcPath){
		FileInputStream in;
		try {
			in = new FileInputStream(srcPath);
			cu = JavaParser.parse(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File srcFile = new File(srcPath);
		return parse(srcFile);
	}
	public XClass parse(File srcFile){
		XJavaDoc xJavaDoc = new XJavaDoc();
		XTagFactory tagFactory = new XTagFactory();
		XClass clazz = new SourceClass(xJavaDoc,new XJavadocFile(srcFile),false,tagFactory,"utf-8");
		this.xClass = clazz;
		return clazz;
	}
	/**
	 * 获取类的annotation。
	 * 
	 * @return
	 */
	public String getEntityAnnotation(){
		String annotation="";
		TypeDeclaration type = cu.getTypes().get(0);
        int length=type.getAnnotations().toString().length();
        annotation=type.getAnnotations().toString().substring(1, length-1);
        return annotation;
	}
	/**
	 * XClass，分析的基础。
	 * 
	 * @return
	 */
	public XClass getXClass() {
		return xClass;
	}
	public void setXClass(XClass class1) {
		xClass = class1;
	}
	/**
	 * 获取含有包的类名。
	 * 
	 * @return
	 */
	public String getEntityFullClassName(){
		return xClass.getQualifiedName();
	}
	/**
	 * 获取类名。
	 * 
	 * @return
	 */
	public String getEntityClassName(){
		return xClass.getName();
	}
	/**
	 * 获取类的名字，并把开头字母小写。
	 * 
	 * @return
	 */
	public String getVarEntityClassName(){
		return uncapitalize(getEntityClassName());
	}
	private String uncapitalize(String str){
		return str.substring(0,1).toLowerCase()+str.substring(1);
	}
	private String capitalize(String str){
		return str.substring(0,1).toUpperCase()+str.substring(1);
	}
	/**
	 * 属性列表。
	 * 
	 * @return
	 */
	public String[] getProNames(){
		  List methods = xClass.getMethods();
		  String[] proNames = new String[methods.size()];
		//或用clazz.getMethod(String)直接获取某个方法
		  int j = 0;
		  for(Iterator iter = methods.iterator();iter.hasNext();){
		   XMethod method = (XMethod) iter.next();
		   String methodName = method.getName();
		   String methodNamex = method.getNameWithSignature(true);
		   String proName = null;
		   if(methodName.startsWith("get")){
			   proName = methodName.substring(3);
		   }else if(methodName.startsWith("is")){
			   proName = methodName.substring(2);
		   }
		   if(proName == null)continue;
		   proNames[j++]=uncapitalize(proName);
		   
		   
		  }
		  String[] rtn = new String[j];
		System.arraycopy(proNames, 0, rtn, 0, j);
		
		return rtn;
	}
	/**
	 * 获取实体的名称。
	 * 
	 * @return
	 */
	public String getEntityName() {
		String comment = xClass.getDoc().getFirstSentence();
		if(comment.indexOf("。") > -1)comment = comment.substring(0,comment.indexOf("。"));
		return comment;
	}
	public XMethod getMethodByProName(String proName){
		XMethod method = xClass.getMethod("get"+capitalize(proName)+"()");
		if(method == null)method = xClass.getMethod("is"+capitalize(proName)+"()");
		return method;
	}
	public boolean hasTag(String proName, String tagName){
		return getTag( proName,  tagName) != null;
	}
	public XTag getTag(String proName, String tagName){
		try{
			return getMethodByProName(proName).getDoc().getTag(tagName);
		}catch(Exception e){
			//e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取某个属性对应的某个标签的某个属性。
	 * 
	 * @param proName
	 * @param tagName
	 * @param attrName
	 * @return
	 */
	public String getPropertyTagAttr(String proName, String tagName, String attrName){
		XTag tag = getTag( proName,  tagName);
		if(tag != null){
			String value = tag.getAttributeValue(attrName);
			return value;
		}
		return null;
	}
	/**
	 * 获取某个属性的注释。
	 * 
	 * @param proName
	 * @return
	 */
	public String getPropertyComment(String proName){
		XMethod method = getMethodByProName(proName);
		if(method == null)return null;
		String comment = method.getDoc().getFirstSentence();
		if(comment.indexOf("。") > -1)comment = comment.substring(0,comment.indexOf("。"));
		if(comment.indexOf("\n") > -1)comment = comment.substring(0,comment.indexOf("\n"));
		return comment;
	}
	public List<String> getAnnotationOfMethod(String methodName){
		TypeDeclaration type = cu.getTypes().get(0);
		List<String> ans = new ArrayList();
    	List<BodyDeclaration> members = type.getMembers();
        for (BodyDeclaration member : members) {
        	if (member instanceof MethodDeclaration){
               String mname =( (MethodDeclaration)member ).getName();
               if(methodName.equals(mname)){
            	   List<AnnotationExpr> aes = member.getAnnotations();
            	   for (AnnotationExpr ae : aes) {
            		   ans.add(ae.getName().getName());
				}
               }
        	}
        	
           }
	      return ans;
	}
	/**
	 * 配置文件。
	 * 提供源代码之外的，生成代码需要的信息。
	 * 
	 * @return
	 */
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	/**
	 * 简便方法，获取开头为本类的类型的属性的值。
	 * 
	 * @param key
	 * @return
	 */
	public String getClassProperty(String key){
		key = this.getEntityFullClassName()+"."+key;
		return properties.getProperty(key);
	}
	public void setClassProperty(String key,Object value){
		key = this.getEntityFullClassName()+"."+key;
		properties.put(key, value);
	}
	public String getMethodReturnType(String proName){
		XMethod method = xClass.getMethod("get"+capitalize(proName)+"()");
		if(method == null)method = xClass.getMethod("is"+capitalize(proName)+"()");
		
		return method.getReturnType().getType().getName();
	}
	public static void main(String[] args) {
		String srcPath = ("D:/Aisainfo/JAVA/erp/src/com/yuqiaotech/crm/model/Customer.java");
		EntityParser p = new EntityParser();
		p.parse(srcPath);
		String ea = p.getEntityAnnotation();
		System.out.println("EntityParser.main()"+ea);
		List<String> ma = p.getAnnotationOfMethod("getId");
		for (String str : ma) {
			System.out.println("EntityParser.main()str="+str);
		}
		System.out.println("=============================");
		System.out.println(p.getEntityFullClassName());
		System.out.println(p.getEntityClassName());
		System.out.println(p.getEntityName());
		String[] names = p.getProNames();
		int i =0;
		for(String name : names){
			i++;
			System.out.print(name+" ");
			System.out.print(p.getPropertyComment(name));
			System.out.println(p.getMethodReturnType(name));
		}
		System.out.println(i);
	}
}
