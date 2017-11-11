/*
 * This File  is used for extension search and it contains three threads
   Thread 1 which will search File by given Extension name in Source 1 and output file with log source1.txt
   Thread 2 which will search File by given Extension name in Source 2 and output file with log source2.txt
   Thread 3 which will search File by given Extension name in Source 3 and output file with log source3.txt
 */
package filetype;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dhwajverma
 */
public class extSearch 
{
    String extension="";
    String source1="";
    String source2="";
    String source3="";
    File fi1=new File("logSource1.txt");
    File fi2=new File("logSource2.txt");
    File fi3=new File("logSource3.txt");
    public extSearch(String ext,String src1,String src2,String src3)
    {
        this.extension=ext;
        this.source1=src1;
        this.source2=src2;
        this.source3=src3;
        if(fi1.exists())
            fi1.delete();
        if(fi2.exists())
            fi2.delete();
        if(fi3.exists())
            fi3.delete();
        ext1 ex1=new ext1();
        ex1.start();
        ext2 ex2 =new ext2();
        ex2.start();
        ext3 ex3 =new ext3();
        ex3.start();
    }
  class ext1 extends Thread
  {
      @Override
      public void run()
      {
          try {
              File folder = new File(source1);
              File[] listOfFiles = folder.listFiles();
              List<String> fileList=new ArrayList<String>();
              for(int i=0;i<listOfFiles.length;i++)
              {
                  File f=listOfFiles[i];
                  if(f.isFile())
                  {
                      
                      String s=f.getName();
                      String arr[]=s.split("\\.");
                      if(arr[1].equalsIgnoreCase(extension))
                      {
                          fileList.add(f.getName());
                          System.out.println("File found");
                          System.out.println("file name=="+f.getName());
                          System.out.println("file parent=="+f.getParent());
                          try {
                              Class.forName("org.sqlite.JDBC");
                              File temp = new File("fileinfo.db");
                              String conn = "jdbc:sqlite:"+ temp.getAbsolutePath().replace("\\","\\\\");
                              Connection c = DriverManager.getConnection(""+conn);
                              PreparedStatement stmt=c.prepareStatement("select * from fileinfo where ext = ?");
                              stmt.setString(1,arr[1]);
                              ResultSet rs = stmt.executeQuery();
                              File fi2=new File("logSource1.txt");
                              FileWriter writer = new FileWriter(fi2, true);
                              while (rs.next())
                              {
                                  String  ext = rs.getString(2);
                                  String dec  = rs.getString(3);
                                  String  category = rs.getString(4);
                                  String lang = rs.getString(5);
                                  String paradigm=rs.getString(6);
                                  String application=rs.getString(7);
                                  System.out.println("********* "+"%%%"+" **********");
                                  writer.write("********* "+"###"+" **********");
                                  writer.write("\r\n");
                                  writer.write("File name =="+f.getName());
                                  writer.write("\r\n");
                                  writer.write("File Path =="+f.getAbsolutePath());
                                  writer.write("\r\n");
                                  System.out.println( "Extension = " + ext );
                                  writer.write("Extension = " + ext);
                                  writer.write("\r\n");
                                  System.out.println( "Description = " + dec );
                                  writer.write("Description = " + dec);
                                  writer.write("\r\n");
                                  System.out.println( "Category = " + category );
                                  writer.write("Category = " + category);
                                  writer.write("\r\n");
                                  System.out.println( "Language = " + lang );
                                  writer.write("Language = " + lang);
                                  writer.write("\r\n");
                                  System.out.println( "Paradigm = " + paradigm );
                                  writer.write("Paradigm = " + paradigm);
                                  writer.write("\r\n");
                                  System.out.println("Application Associated = "+application);
                                  writer.write("Application Associated = "+application);
                                  writer.write("\r\n");
                                  writer.close();
                              }
                          } catch (ClassNotFoundException ex) {
                              ex.printStackTrace();
                          } catch (SQLException ex) {
                              ex.printStackTrace();
                          } catch (IOException ex) {
                              ex.printStackTrace();
                          }
                      }
                  }
              }
              if(fileList.isEmpty())
              {
                  
                  FileWriter writer=null;
                  try {
                      File f2=new File("logSource1.txt");
                      writer = new FileWriter(f2, true);
                      writer.write("No file exited with such name in Source 1");
                      writer.close();
                      Desktop.getDesktop().open(f2);
                  } catch (IOException ex) {
                      ex.printStackTrace();
                  } finally {
                      try {
                          writer.close();
                      } catch (IOException ex) {
                          ex.printStackTrace();
                      }
                  }
              }
              else
                  Desktop.getDesktop().open(fi1);
          } catch (IOException ex) {
             ex.printStackTrace();
          }
        } 
   }
  
  class ext2 extends Thread
  {
      @Override
      public void run()
      {
          try {
              File folder = new File(source2);
              File[] listOfFiles = folder.listFiles();
              List<String> fileList=new ArrayList<String>();
              for(int i=0;i<listOfFiles.length;i++)
              {
                  File f=listOfFiles[i];
                  if(f.isFile())
                  {
                      
                      String s=f.getName();
                      String arr[]=s.split("\\.");
                      if(arr[1].equalsIgnoreCase(extension))
                      {
                          fileList.add(f.getName());
                          System.out.println("File found");
                          System.out.println("file name=="+f.getName());
                          System.out.println("file parent=="+f.getParent());
                          try {
                              Class.forName("org.sqlite.JDBC");
                              File temp = new File("fileinfo.db");
                              String conn = "jdbc:sqlite:"+ temp.getAbsolutePath().replace("\\","\\\\");
                              Connection c = DriverManager.getConnection(""+conn);
                              PreparedStatement stmt=c.prepareStatement("select * from fileinfo where ext = ?");
                              stmt.setString(1,arr[1]);
                              ResultSet rs = stmt.executeQuery();
                              File fi2=new File("logSource2.txt");
                              FileWriter writer = new FileWriter(fi2, true);
                              while (rs.next())
                              {
                                  String  ext = rs.getString(2);
                                  String dec  = rs.getString(3);
                                  String  category = rs.getString(4);
                                  String lang = rs.getString(5);
                                  String paradigm=rs.getString(6);
                                  String application=rs.getString(7);
                                  System.out.println("********* "+"%%%"+" **********");
                                  writer.write("********* "+"###"+" **********");
                                  writer.write("\r\n");
                                  writer.write("File name =="+f.getName());
                                  writer.write("\r\n");
                                  writer.write("File Path =="+f.getAbsolutePath());
                                  writer.write("\r\n");
                                  System.out.println( "Extension = " + ext );
                                  writer.write("Extension = " + ext);
                                  writer.write("\r\n");
                                  System.out.println( "Description = " + dec );
                                  writer.write("Description = " + dec);
                                  writer.write("\r\n");
                                  System.out.println( "Category = " + category );
                                  writer.write("Category = " + category);
                                  writer.write("\r\n");
                                  System.out.println( "Language = " + lang );
                                  writer.write("Language = " + lang);
                                  writer.write("\r\n");
                                  System.out.println( "Paradigm = " + paradigm );
                                  writer.write("Paradigm = " + paradigm);
                                  writer.write("\r\n");
                                  System.out.println("Application Associated = "+application);
                                  writer.write("Application Associated = "+application);
                                  writer.write("\r\n");
                                  writer.close();
                              }
                          } catch (ClassNotFoundException ex) {
                              ex.printStackTrace();
                          } catch (SQLException ex) {
                              ex.printStackTrace();
                          } catch (IOException ex) {
                              ex.printStackTrace();
                          }
                      }
                  }
              }
              if(fileList.isEmpty())
              {
                  
                  FileWriter writer=null;
                  try {
                      File f2=new File("logSource2.txt");
                      writer = new FileWriter(f2, true);
                      writer.write("No file exited with such name in Source 2");
                      writer.close();
                      Desktop.getDesktop().open(f2);
                  } catch (IOException ex) {
                      ex.printStackTrace();
                  } finally {
                      try {
                          writer.close();
                      } catch (IOException ex) {
                          ex.printStackTrace();
                      }
                  }
              }
              else
                  Desktop.getDesktop().open(fi2);
          } catch (IOException ex) {
               ex.printStackTrace();
          }
        } 
   }
  
  class ext3 extends Thread
  {
      @Override
      public void run()
      {
          try {
              File folder = new File(source3);
              File[] listOfFiles = folder.listFiles();
              List<String> fileList=new ArrayList<String>();
              for(int i=0;i<listOfFiles.length;i++)
              {
                  File f=listOfFiles[i];
                  if(f.isFile())
                  {
                      
                      String s=f.getName();
                      String arr[]=s.split("\\.");
                      if(arr[1].equalsIgnoreCase(extension))
                      {
                          fileList.add(f.getName());
                          System.out.println("File found");
                          System.out.println("file name=="+f.getName());
                          System.out.println("file parent=="+f.getParent());
                          try {
                              Class.forName("org.sqlite.JDBC");
                              File temp = new File("fileinfo.db");
                              String conn = "jdbc:sqlite:"+ temp.getAbsolutePath().replace("\\","\\\\");
                              Connection c = DriverManager.getConnection(""+conn);
                              PreparedStatement stmt=c.prepareStatement("select * from fileinfo where ext = ?");
                              stmt.setString(1,arr[1]);
                              ResultSet rs = stmt.executeQuery();
                              File fi2=new File("logSource3.txt");
                              FileWriter writer = new FileWriter(fi2, true);
                              while (rs.next())
                              {
                                  String  ext = rs.getString(2);
                                  String dec  = rs.getString(3);
                                  String  category = rs.getString(4);
                                  String lang = rs.getString(5);
                                  String paradigm=rs.getString(6);
                                  String application=rs.getString(7);
                                  System.out.println("********* "+"%%%"+" **********");
                                  writer.write("********* "+"###"+" **********");
                                  writer.write("\r\n");
                                  writer.write("File name =="+f.getName());
                                  writer.write("\r\n");
                                  writer.write("File Path =="+f.getAbsolutePath());
                                  writer.write("\r\n");
                                  System.out.println( "Extension = " + ext );
                                  writer.write("Extension = " + ext);
                                  writer.write("\r\n");
                                  System.out.println( "Description = " + dec );
                                  writer.write("Description = " + dec);
                                  writer.write("\r\n");
                                  System.out.println( "Category = " + category );
                                  writer.write("Category = " + category);
                                  writer.write("\r\n");
                                  System.out.println( "Language = " + lang );
                                  writer.write("Language = " + lang);
                                  writer.write("\r\n");
                                  System.out.println( "Paradigm = " + paradigm );
                                  writer.write("Paradigm = " + paradigm);
                                  writer.write("\r\n");
                                  System.out.println("Application Associated = "+application);
                                  writer.write("Application Associated = "+application);
                                  writer.write("\r\n");
                                  writer.close();
                              }
                          } catch (ClassNotFoundException ex) {
                              ex.printStackTrace();
                          } catch (SQLException ex) {
                              ex.printStackTrace();
                          } catch (IOException ex) {
                              ex.printStackTrace();
                          }
                      }
                  }
              }
              if(fileList.isEmpty())
              {
                  
                  FileWriter writer=null;
                  try {
                      File f2=new File("logSource2.txt");
                      writer = new FileWriter(f2, true);
                      writer.write("No file exited with such name in Source 2");
                      writer.close();
                      Desktop.getDesktop().open(f2);
                  } catch (IOException ex) {
                      ex.printStackTrace();
                  } finally {
                      try {
                          writer.close();
                      } catch (IOException ex) {
                          ex.printStackTrace();
                      }
                  }
              }
              else
                  Desktop.getDesktop().open(fi3);
          } catch (IOException ex) {
              ex.printStackTrace();
          }
        } 
   }
 }
 




